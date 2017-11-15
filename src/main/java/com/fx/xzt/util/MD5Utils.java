package com.fx.xzt.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 
* @ClassName: MD5Utils 
* @Description: 用户密码加密工具类
* @author jcwang
* @date 2017年8月1日 下午3:34:42 
*
 */
public class MD5Utils {  
	private static String salt = "727xzh";
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param sourceStr
	 * @return
	 * @author jcwang
	 * @date 2017年8月1日 下午3:44:37
	 */
	 public static String encrypt(String sourceStr) {
	        try {
	            // 获得MD5摘要算法的 MessageDigest对象  
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
	            String sourcesalt = sourceStr+salt;
	            // 使用指定的字节更新摘要  
	            mdInst.update(sourcesalt.getBytes());  
	            // 获得密文  
	            byte[] md = mdInst.digest();
	            // 把密文转换成十六进制的字符串形式  
	            StringBuffer buf = new StringBuffer();  
	            for (int i = 0; i < md.length; i++) {  
	                int tmp = md[i];  
	                if (tmp < 0)  
	                    tmp += 256;  
	                if (tmp < 16)  
	                    buf.append("0");  
	                buf.append(Integer.toHexString(tmp));  
	            }  
	            //return buf.toString().substring(8, 24);// 16位加密  
	             return buf.toString();// 32位加密  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return null;  
	        }
	    }

	/**
	 * MD5解密
	 * @param password
	 * @return
	 *//*
	public static String convertMD5(String password){
		 char[] a = password.toCharArray();
		 for (int i = 0; i < a.length; i++){
			 a[i] = (char) (a[i] ^ 't');
		 }
		 String s = new String(a);
		 return s;
	 }
//	    public static void main(String[] args) {  
//	        String str = "123456"+salt;
//	        String encryptStr = encrypt(str);
//	        System.out.println("加密前：" + str);
//	        System.out.println("加密后：" + encryptStr);  
//	    }
private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
	// 生成一个可信任的随机数源
	SecureRandom sr = new SecureRandom();

	// 从原始密钥数据创建DESKeySpec对象
	DESKeySpec dks = new DESKeySpec(key);

	// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	SecretKey securekey = keyFactory.generateSecret(dks);

	// Cipher对象实际完成解密操作
	Cipher cipher = Cipher.getInstance("DES");

	// 用密钥初始化Cipher对象
	cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

	return cipher.doFinal(data);
}*/


}  
