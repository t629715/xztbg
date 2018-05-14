package com.fx.xzt.exception;

/**
 * 
* @ClassName: GlobalException 
* @Description: 全局异常类
* @author jcwang
* @date 2017年7月31日 下午4:30:12 
*
 */
public class GlobalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String module;

	/**
	 * 初始化异常类
	 * @param module  模块名称
	 * @param message 异常信息
	 */
	public GlobalException(String module, String message) {
		this.message = message;
		this.module = module;
	}

	@Override
	public String getMessage() {
		return "[" + module +"] :"+message;
	}

	/**
	 * 设置异常信息
	 * @param module  模块名称
	 * @param message 异常信息
	 */
	public void setMessage(String module, String message) {
		this.message = message;
	}
}
