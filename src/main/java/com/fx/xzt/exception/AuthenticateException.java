package com.fx.xzt.exception;

/**
 * 
* @ClassName: AuthenticateException 
* @Description: 认证异常 
* @author jcwang
* @date 2017年7月31日 下午3:52:00 
*
 */
public class AuthenticateException extends RuntimeException {

	private static final long serialVersionUID = -2808697971601340223L;
	
	public AuthenticateException() {
		super();
	}

	public AuthenticateException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticateException(String message) {
		super(message);
	}

	public AuthenticateException(Throwable cause) {
		super(cause);
	}

}
