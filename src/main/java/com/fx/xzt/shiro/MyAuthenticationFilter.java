package com.fx.xzt.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.fx.xzt.util.MD5Utils;

/**
 * 
* @ClassName: MyAuthenticationFilter 
* @Description: 重写token认证
* @author jcwang
* @date 2017年7月31日 下午3:55:38 
*
 */
public class MyAuthenticationFilter extends FormAuthenticationFilter{

	public static final String DEFAULT_PHONE_PARAM = "phone";
	
	private String phoneParam = DEFAULT_PHONE_PARAM;
	
	public MyAuthenticationFilter(){
		setFailureKeyAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	}
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String phone = getPhone(request);
		String password = getPassword(request);
		password = MD5Utils.encrypt(password);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request); 
		
		return new MyAuthenticationToken(phone,password,rememberMe,host);
	}

	public String getPhoneParam() {
		return phoneParam;
	}

	public void setPhoneParam(String phoneParam) {
		this.phoneParam = phoneParam;
	}

	protected String getPhone(ServletRequest request) {
		return WebUtils.getCleanParam(request, getPhoneParam());
	} 
	
}
