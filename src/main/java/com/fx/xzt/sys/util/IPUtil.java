package com.fx.xzt.sys.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: IPUtil
 * @Description: 获取IP
 * @author htt
 * @date 2017-11-28 下午3:36:39
 * 
 */
public class IPUtil {

	/** 获取客户端IP */
	public static final String getHost(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null && ip.indexOf(",") > 0) {
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			ip = ip.substring(0, ip.indexOf(","));
		}
		if (StringUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			if (StringUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StringUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StringUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Real-IP");
			}
			if (StringUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
				InetAddress inet = null;
				try { // 根据网卡取本机配置的IP
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
				}
				ip = inet.getHostAddress();
			}
		}
		return ip;
	}

}
