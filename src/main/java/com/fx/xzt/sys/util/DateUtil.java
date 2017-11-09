package com.fx.xzt.sys.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	private static final long ONE_MINUTE = 60000L;
	private static final long ONE_HOUR = 3600000L;
	private static final long ONE_DAY = 86400000L;
	private static final long ONE_WEEK = 604800000L;

	private static final String ONE_SECOND_AGO = "秒前";
	private static final String ONE_MINUTE_AGO = "分钟前";
	private static final String ONE_HOUR_AGO = "小时前";
	private static final String ONE_DAY_AGO = "天前";
	private static final String ONE_MONTH_AGO = "月前";
	private static final String ONE_YEAR_AGO = "年前";

	/**
	 * 
	 * @Method: getTodayZeroDate 
	 * @Description: 获取当天0点的date
	 * @return
	 * @throws
	 */
	public static Date getTodayZeroDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static String format(Date date) {
		long delta = new Date().getTime() - date.getTime();
		if (delta < 1L * ONE_MINUTE) {
			long seconds = toSeconds(delta);
			return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
		}
		if (delta < 45L * ONE_MINUTE) {
			long minutes = toMinutes(delta);
			return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
		}
		if (delta < 24L * ONE_HOUR) {
			long hours = toHours(delta);
			return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
		}
		if (delta < 48L * ONE_HOUR) {
			return "昨天";
		}
		if (delta < 30L * ONE_DAY) {
			long days = toDays(delta);
			return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
		}
		if (delta < 12L * 4L * ONE_WEEK) {
			long months = toMonths(delta);
			return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
		} else {
			long years = toYears(delta);
			return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
		}
	}

	public static String cgformat(Date date) throws ParseException {
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		String res = "";
		// 相差天数
		int day = DateUtil.plusDay(now, calendar.getTime());
		switch (day) {
		case 0:
			int minute = plus(now, date);
			if (minute > 180) {
				res = convertDateToString(date, "HH:mm");
			} else if (minute > 120) {
				res = "2小时前";
			} else if (minute > 90) {
				res = "1.5小时前";
			} else if (minute > 60) {
				res = "1小时前";
			} else if (minute > 30) {
				res = "半小时前";
			} else if (minute > 25) {
				res = "25分钟前";
			} else if (minute > 15) {
				res = "15分钟前";
			} else if (minute > 5) {
				res = "5分钟前";
			} else if (minute > 3) {
				res = "3分钟前";
			} else {
				res = "刚刚";
			}
			break;
		case 1:
			res = convertDateToString(date, "HH:mm");
			break;
		case 2:
			res = convertDateToString(date, "HH:mm");
			break;
		default:
			res = convertDateToString(date, "MM/dd HH:mm");
			break;
		}
		return res;
		//
		// int date = calendar.get(Calendar.DATE);
		// if(calendar.get(Calendar.DATE))
		// Date nowD = convertStringToDate(convertDateToString("yyyy-MM-dd"),
		// "yyyy-MM-dd");
		// long cha = date.getTime() - nowD.getTime();
		// if (cha > 0) {
		// return "今天" + convertDateToString(date, "H") + "点";
		// } else {
		// if (Math.abs(cha) < 24L * ONE_HOUR) {
		// return "昨天" + convertDateToString(date, "H") + "点";
		// } else {
		// return convertDateToString(date, "yyyy-MM-dd HH:mm:ss");
		// }
		// }
	}

	private static long toSeconds(long date) {
		return date / 1000L;
	}

	private static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	private static long toHours(long date) {
		return toMinutes(date) / 60L;
	}

	private static long toDays(long date) {
		return toHours(date) / 24L;
	}

	private static long toMonths(long date) {
		return toDays(date) / 30L;
	}

	private static long toYears(long date) {
		return toMonths(date) / 365L;
	}

	/**
	 * 
	 * @Method: getGMTDate 
	 * @Description: 把本地时间转换成GMT时间
	 * @param d Date
	 * @return
	 * @throws
	 */
	public static String getGMTDate(Date d) {
		DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", new Locale("English"));
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(format.format(d));
		return format.format(d);
	}

	/**
	 * 
	 * @Method: changeDate
	 * @Description: 更新时间
	 * @param date
	 *            原时间
	 * @param field
	 *            更新字段 Calendar.DATE
	 * @param amount
	 *            更新范围
	 * @return
	 * @return Date 新日期
	 * @throws
	 */
	public static Date changeDate(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Method: plus
	 * @Description: 求两个时间类型的分钟差
	 * @return Integer
	 * @throws
	 */
	public static int plus(Date date1, Date date2) {
		Long diff = date1.getTime() - date2.getTime();
		Long days = diff / (1000 * 60);
		return days.intValue();
	}

	/**
	 * 求两个时间类型的天差
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static int plusDay(Date date1, Date date2) throws ParseException {
		Long diff = date1.getTime() - date2.getTime();
		Long days = diff / (1000 * 3600 * 24);
		return days.intValue();
	}

	/**
	 * 
	 * @Method: convertDateToString
	 * @Description: 获取特定格式的日期字符串
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return
	 * @throws
	 */
	public static String convertDateToString(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String dateAsString = dateFormat.format(date);
		return dateAsString;
	}

	public static String convertDateToString(String format) {
		return convertDateToString(new Date(), format);
	}

	/**
	 * 
	 * @Method: convertStringToDate
	 * @Description: 把字符串按照给定格式进行格式化
	 * @param dateStr
	 *            要格式化的日期字符串
	 * @param format
	 *            可以使用内置预定义的格式
	 * @return
	 * @throws ParseException
	 * @return Date 格式化的日期对象
	 * @throws
	 */
	public static Date convertStringToDate(String dateStr, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(dateStr);

	}

	/**
	 * 
	 * @Method: getFirstDateOfMonth 
	 * @Description: 日期的月份第一天
	 * @param dateStr 日期
	 * @param format 格式，输出同输入
	 * @throws ParseException
	 * @throws
	 */
	public static String getFirstDateOfMonth(String dateStr, String format) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		if (dateStr == null) {
			calendar.setTime(new Date());
		} else {
			calendar.setTime(convertStringToDate(dateStr, format));
		}
		calendar.set(Calendar.DATE, 1);
		return new SimpleDateFormat(format).format(calendar.getTime());
	}

	/**
	 * @throws ParseException
	 * 
	 * @Method: getLastDateOfMonth
	 * @Description: 获取本月最大日期
	 * @param dateStr 日期
	 * @param format 格式，输出同输入
	 * @return String
	 * @throws
	 */
	public static String getLastDateOfMonth(String dateStr, String format) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(convertStringToDate(dateStr, format));
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat(format).format(calendar.getTime());
	}

	/**
	 * 
	 * @Method: getMontoSuninMonth
	 * @Description: 获取每月周一至周五的日期（YYYY-MM-DD） @ @param monthDate(YYYY-MM) @ @return
	 * @return List<String>周一到周五的日期
	 * @throws
	 */
	public static List<String> getMontoSuninMonth(String monthDate) {

		List<String> str = new ArrayList<String>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf1.setLenient(false);
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEE");
		for (int i = 1; i < 32; i++) {
			try {
				Date date = sdf1.parse(monthDate + "-" + i);
				if (!sdf2.format(date).equals("星期六") && !sdf2.format(date).equals("星期日")) {
					str.add(sdf1.format(date));
				}
			} catch (ParseException e) {
			}
		}
		return str;
	}

	/**
	 * 
	 * @Method: maxDaysInMonth
	 * @Description: 得到每月的最大天数 @ @param monthDate (YYYY-MM) @ @return @ @throws
	 *               ParseException
	 * @return Integer最i大天数
	 * @throws
	 */
	public static Integer maxDaysInMonth(String monthDate) throws ParseException {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM");
		Date start = sim.parse(monthDate);
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(start);// 设置当前日期
		// calendar.add(Calendar.MONTH, -1);//月份减一
		int maxDate = calendar.getActualMaximum(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 方法名 ：modify<BR>
	 * 方法说明 ：对日期对象进行修改<BR>
	 * 备注 ：<BR>
	 * 1、date为空（null）抛出异常 <BR>
	 * 2、year,month,day,hour,minute,second都可以使用正负数来增加或者减少对应单位<BR>
	 * 
	 * @param year
	 *            要增加或者减少的年(零表示不修改,负数表示减少)<BR>
	 * @param month
	 *            要增加或者减少的月(零表示不修改,负数表示减少)<BR>
	 * @param day
	 *            要增加或者减少的日(零表示不修改,负数表示减少)<BR>
	 * @param hour
	 *            要增加或者减少的时(零表示不修改,负数表示减少)<BR>
	 * @param minute
	 *            要增加或者减少的分(零表示不修改,负数表示减少)<BR>
	 * @param second
	 *            要增加或者减少的秒(零表示不修改,负数表示减少)<BR>
	 * @return 修改好的日期对象<BR>
	 */
	public static Date modify(Date date, int year, int month, int day, int hour, int minute, int second) {

		// 校验参数
		if (date == null) {
			throw new IllegalArgumentException("Args can not be null!");
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DAY_OF_MONTH, day);
		c.add(Calendar.HOUR_OF_DAY, hour);
		c.add(Calendar.MINUTE, minute);
		c.add(Calendar.SECOND, second);

		return c.getTime();
	}

	/**
	 * 
	 * @Method: beforeDay
	 * @Description: 得到当前日期的前一天 @ @param checkTime @ @return @ @throws
	 *               ParseException
	 * @return String
	 * @throws
	 */
	public static String beforeDay(String checkTime) throws ParseException {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sim.parse(checkTime);
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(start);// 设置当前日期
		calendar.add(5, -1);// 日期-1
		String end = sim.format(calendar.getTime());
		return end;
	}

	public static Date getValidDate(Integer days) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar thisDay = Calendar.getInstance();
		thisDay.add(Calendar.DAY_OF_MONTH, days);
		return convertStringToDate(f.format(thisDay.getTime()), "yyyy-MM-dd");
	}

	public static int getAge(String brithday) {
		Date birthDay = null;
		try {
			birthDay = convertStringToDate(brithday, "yyyy");
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();// 获取现在时间
		int thisYear = calendar.get(Calendar.YEAR);
		calendar.setTime(birthDay);
		int birthYear = calendar.get(Calendar.YEAR);
		return thisYear - birthYear;
	}

	/**
	 * 
	 * @Method: compareDay 
	 * @Description: 对比日期天数
	 * @param date1起
	 * @param date2至
	 * @return
	 * @throws
	 */
	public static int compareDay(Date d1, Date d2) {
		Calendar c = Calendar.getInstance();
		c.setTime(d1);
		long time1 = c.getTimeInMillis();
		c.setTime(d2);
		long time2 = c.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return (int) between_days;
	}

	/**
	 * @throws ParseException 
	 * 获取两个时间的时间查 如1天2小时30分钟
	 * @Method: getDatePoor 
	 * @ @param endDate
	 * @ @param nowDate
	 * @ @return
	 * @return String
	 * @throws
	 */
	public static String getDatePoor(Date endDate, Date nowDate) throws ParseException {
		String nend = convertDateToString(endDate, "yyyy-MM-dd");
		endDate = convertStringToDate(nend + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟";
	}

	public static long compareHour(Date nowD, Date d1) throws ParseException {
		long nh = 1000 * 60 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = nowD.getTime() - d1.getTime();
		// 计算差多少小时
		long hour = diff / nh;
		return hour;
	}

	public static String getTimeMill() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * 
	 * @Method: getDate 
	 * @Description: 获取日期
	 * @param day 要加减的天数
	 * @param hour 设定小时
	 * @param minute 设定分钟
	 * @param second 设定秒
	 * @return
	 * @throws
	 */
	public static Date getDate(int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		if (day != 0) {
			calendar.add(Calendar.DATE, day);
		}
		return calendar.getTime();
	}

	/**
	 * 
	 * @Method: convertSecondToTime 
	 * @Description: 把秒数转换成中文 xx时xx分xx秒
	 * @param second
	 * @return
	 * @throws
	 */
/*	public static String convertSecondToTime(int second) {
		if (second == 0)
			return "0秒";
		String time = "";
		int hour = Math.floorDiv(second, 3600);
		if (hour > 0) {
			time += hour + "小时";
			second = second - hour * 3600;
		}
		int miniute = Math.floorDiv(second, 60);
		if (miniute > 0) {
			time += miniute + "分";
			second = second - miniute * 60;
		}
		time += second + "秒";
		return time;
	}*/

	/**
	 * 
	 * @Method: convertTimeMillisToDate 
	 * @Description: 时间毫秒数转成Date
	 * @param timeMillis
	 * @return
	 * @throws
	 */
	public static Date convertTimeMillisToDate(long timeMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeMillis);
		return calendar.getTime();
	}

	public static void main(String[] args) throws Exception {
		String s = "2017-08-09 16:11:11";
		String m = "2017-08-10 16:11:10";
		Date today = DateUtil.getTodayZeroDate();
		String endTypeTime = DateUtil.convertDateToString(today, "yyyy-MM-dd HH:mm:ss");
		System.out.println(endTypeTime);
	}

}
