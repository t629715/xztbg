package com.fx.xzt.sys.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Hashtable;
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
        ImageIO.write(image, "png", sos);
        sos.close();
    }

    /**
     * 根据内容，生成指定宽高、指定格式的二维码图片
     *
     * @param text   内容
     * @return 生成的二维码图片路径
     * @throws Exception
     */
    public static void generateQRCode(HttpServletResponse response,String text) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        int width = 500;
        int height = 500;
        String format = "png";
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode("http://www.baidu.com", BarcodeFormat.QR_CODE, width, height, hints);
        //生成二维码图片
        BufferedImage image = toBufferedImage(bitMatrix);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //创建图片输出流
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);
        //获取图片输出流
        ImageIO.write(image, "png", imageOutputStream);
        //创建图片输入流
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        long length = imageOutputStream.length();
        //设置response
        response.setContentType("application/octet-stream");
        response.setContentLength((int)length);
        response.setHeader("Content-Disposition","attachment;filename="+new String((text+".png").getBytes("gbk"),"iso-8859-1"));
        byte[] bytes = new byte[1024];
        OutputStream outputStream = response.getOutputStream();
        long count = 0;
        while(count < length){
            int len = inputStream.read(bytes, 0, 1024);
            count +=len;
            outputStream.write(bytes, 0, len);
        }
        inputStream.close();
        outputStream.close();
        /*// 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();//创建输出流对象
        ImageIO.write(image, "png", sos);//将image写入到输出流中


        String pathName = "c:/"+text+".png";//文件保存路径
        File outputFile = new File(pathName);
        GenerateQRCodeUtil.writeToFile(bitMatrix, format, outputFile);//将数据写到本地*/
    }
}
