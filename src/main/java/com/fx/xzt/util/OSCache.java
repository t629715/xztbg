package com.fx.xzt.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: OSCache 
* @Description: 系统缓存，存放系统设定的参数
* @author jcwang
* @date 2017年8月11日 下午12:53:18 
*
 */
public class OSCache {

	private static Map<String,Object> cache = new HashMap<String,Object>();
	
	public OSCache(Map<String,Object> params){
		cache.putAll(params);
	}
	
	/**
	 * 
	 * @Description:根据名称从缓存中获取值
	 * @param name 名称
	 * @author jcwang
	 * @date 2017年8月11日 下午12:54:52
	 */
	public static Object getValueByName(String name){
		return cache.get(name);
	}
	
	/**
	 * 
	 * @Description:根据名称从缓存中获取值,如果没有，返回默认值
	 * @param name 名称
	 * @param defautValue 默认值
	 * @author jcwang
	 * @date 2017年8月11日 下午12:54:52
	 */
	public static Object getValueByName(String name,String defautValue){
		Object obj = cache.get(name);
		if(null==obj) return defautValue;
		return obj;
	}
}
