package com.fx.xzt.sys.util;

/**
 * 
* @ClassName: StringUtil 
* @Description: 字符串工具类
* @author htt
* @date 2017-10-17 下午7:05:06 
*
 */
public class StringUtil {

	/**
	 * 
	* @Title: convertNullToEmpty 
	* @Description: null转换为空字符串
	* @param s    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	public static String convertNullToEmpty (String s) {
		if (!isNotEmpty(s)) {
			s = "";
		}
		return s ;
	}
	
	/**
	 * 
	* @Title: isNotEmpty 
	* @Description: 判断字符串不为空
	* @param s
	* @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	* @author htt
	 */
	public static boolean isNotEmpty (String s) {
		boolean b = false;
		if (s != null && !s.equals("") && s.length() > 0) {
			b = true;
		}
		return b;
	}
	
	/**
	 * 
	* @Title: isBlank 
	* @Description: 判断字符串为空
	* @param s
	* @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	* @author htt
	 */
	public static boolean isBlank (String s) {
		boolean b = false;
		if (s == null || s.equals("") || s.length() == 0) {
			b = true;
		}
		return b;
	}
	
	/**
	 * 
	* @Title: fundsHandle 
	* @Description: 金额处理（分转为圆，为空处理为0）
	* @param object
	* @return    设定文件 
	* @return Double    返回类型 
	* @throws 
	* @author htt
	 */
	public static Double fundsHandle(Object object) {
		Double d = 0.0;
		if (object != null && object != "") {
			d = Double.valueOf(object.toString())/100;
		}
		return d;
	}
}
