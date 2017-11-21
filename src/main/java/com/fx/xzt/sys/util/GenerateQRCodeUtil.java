package com.fx.xzt.sys.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.jcraft.jsch.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
import java.util.Properties;

public class GenerateQRCodeUtil {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private GenerateQRCodeUtil() {}


    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }


    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }


    public static void writeToStream(HttpServletResponse response,BitMatrix matrix, String format)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "jpeg", sos);
        sos.close();
    }

    /**
     * 根据内容，生成指定宽高、指定格式的二维码图片
     *
     * @param text   内容
     * @param width  宽
     * @param height 高
     * @param format 图片格式
     * @return 生成的二维码图片路径
     * @throws Exception
     */
    public static String generateQRCode(String text, int width, int height, String format) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        width = 100;
        height = 100;
        format = "png";
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = toBufferedImage(bitMatrix);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image,"png",os);
        os.close();
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        String childDirectory = "qrCode/";
        String fileName = text;
        ChannelSftp sftp = connect();
        boolean success = false;
        try {
            sftp.cd(Constant.IMGUPLOAD_PATH + "/" + childDirectory);
            //home/ftpuser/www/images/qrCode/text.png
            sftp.put(is, fileName);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                String newpath = Constant.IMGUPLOAD_PATH;
                String[] dirs = childDirectory.split("/");
                if (dirs != null && dirs.length > 0) {
                    for (int i = 0; i < dirs.length; i++) {
                        try {
                            newpath = newpath + "/" + dirs[i];
                            sftp.mkdir(newpath);
                        } catch (SftpException e1) {
                            // TODO Auto-generated catch block
                             e1.printStackTrace();
                        }
                    }
                }
                sftp.cd(newpath);
                sftp.put(is, fileName);
                success = true;
            } catch (SftpException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        String pathName = "F:/new.png";
        File outputFile = new File(pathName);
        GenerateQRCodeUtil.writeToFile(bitMatrix, format, outputFile);
        return pathName;
    }


    public static ChannelSftp connect() {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(Constant.IMGUPLOAD_USERNAME, Constant.IMGUPLOAD_URL,
                    Constant.IMGUPLOAD_PORT);
            Session sshSession =
                    jsch.getSession(Constant.IMGUPLOAD_USERNAME, Constant.IMGUPLOAD_URL,
                            Constant.IMGUPLOAD_PORT);
            sshSession.setPassword(Constant.IMGUPLOAD_PASSWORD);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sftp;
    }
}
