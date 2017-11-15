package com.fx.xzt.sys.util;

public class Constant {

	/**
	 * 数据请求返回码
	 */
	public static final int RESCODE_SUCCESS = 1000;				//成功
	public static final int RESCODE_SUCCESS_MSG = 1001;			//成功(有返回信息)
	public static final int RESCODE_EXCEPTION = 1002;			//请求抛出异常,失败
	public static final int RESCODE_NOEXIST = 1003;				//查询结果为空
	public static final int RESCODE_NOAUTH = 1004;			    //无操作权限
	
	/**
	 * jwt
	 */
	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "fuxin2017xiangzhihui080915312700";
	//public static final int JWT_TTL = 60*60*1000;  //millisecond
	public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
	public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
	
	/**
	 * token
	 */
	public static final String ID_COLUMN = "id";
	public static final String NAME_COLUMN = "userName";
	public static final String DEVICE = "device";
	public static final String NEW_TIMES = "times";
	/**
	 * sign 签名
	 */
	public static final String SIGN = "sign";
	/**
	 * 用户
	 */
	public static final String CURRENT_USER = "currentUser";
	
	/**
	 * 图片上传FTP服务器地址,账号密码
	 */
	public static final String IMGUPLOAD_URL = "120.24.156.128";
	public static final int IMGUPLOAD_PORT = 52113;
	public static final String IMGUPLOAD_USERNAME = "query";
	public static final String IMGUPLOAD_PASSWORD = "query@1qaz!QAZ";
	public static final String IMGUPLOAD_PATH = "/home/ftpuser/www/images";
	
	public static final String NEW_FLAG= "N";
	public static final String SPE_FLAG= "P";
	
	public static final String NEW_MONEY= "M";
	public static final String SPE_MONEY= "U";
	
	public static final String D_PERIOD= "D";
	public static final String M_PERIOD= "M";
	public static final String W_PERIOD= "W";
	
	/**
	 * 委托类型
	 */
	public static final String ORDER_TYPE_N= "N";
	
	/**
	 * 开仓
	 */
	public static final String ORDER_TYPE_O= "O";
	
	/**
	 * 撤回 
	 */
	public static final String ORDER_TYPE_C= "C";
}
