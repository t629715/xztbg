package com.fx.jyg.util;

import java.security.MessageDigest;

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
	 
//	    public static void main(String[] args) {  
//	        String str = "123456"+salt;
//	        String encryptStr = encrypt(str);
//	        System.out.println("加密前：" + str);
//	        System.out.println("加密后：" + encryptStr);  
//	    }

    
}  
