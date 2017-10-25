package com.fx.xzt.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
* @ClassName: DateUtils 
* @Description: 日期工具类
* @author jcwang
* @date 2017年8月23日 下午6:10:29 
*
 */
public class DateUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	private static SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
	
	private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 
	 * @Description: 格式化时间为串行，格式:yyyyMMdd
	 * @param date  处理时间
	 * @author jcwang
	 * @date 2017年8月23日 下午6:16:57
	 */
	public static String formatDate(Date date){
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @Description: 格式化时间为串行，格式:yyyy-MM-dd HH:mm:ss
	 * @param date  处理时间
	 * @author jcwang
	 * @date 2017年8月23日 下午6:16:57
	 */
	public static String formatDateByMidLine(Date date){
		return sdf1.format(date);
	}
	/**
	 *
	 * @Description: 格式化时间为串行，格式:yyyy-MM-dd
	 * @param date  处理时间
	 * @author jcwang
	 * @date 2017年8月23日 下午6:16:57
	 */
	public static String formatDateByMidLine1(Date date){
		return sdf1.format(date);
	}
	
	/**
	 * 
	 * @Description: 格式化时间为串行，格式:HHmmss
	 * @param date  处理时间
	 * @author jcwang
	 * @date 2017年8月23日 下午6:16:57
	 */
	public static String formatTime(Date date){
		return sdf2.format(date);
	}
	
	/**
	 * 
	 * @Description: 格式化时间为串行，格式:HHmmss
	 * @param date  处理时间
	 * @author jcwang
	 * @date 2017年8月23日 下午6:16:57
	 */
	public static String formatDateTime(Date date){
		return sdf3.format(date);
	}
}
