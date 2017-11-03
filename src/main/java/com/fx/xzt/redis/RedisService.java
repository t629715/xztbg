package com.fx.xzt.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
* 
* @ClassName: RedisService 
* @Description: redis缓存接口。 
*  缓存时间单位默认分钟，
*  默认timeout值为5，
*  没有timeout和timeunit的方法默认不设置有效时间，永久有效。
* @author jcwang
* @date 2017年7月31日 下午3:54:17 
*
*/
public interface RedisService {

	int EXPIRE_TIME_1 = 1;

	int EXPIRE_TIME_2 = 2;

	int EXPIRE_TIME_5 = 5;

	int EXPIRE_TIME_7 = 7;

	int EXPIRE_TIME_15 = 15;
	
	int EXPIRE_TIME_30 = 30;

	<T> T get(String key, Class<T> cls);

	/** 
     * 验证是否存在 
     * @param key 键名
     * @return 
     */  
	boolean exists(String key);

	Set<String> allKeys(String pattern);
	
	/** 
     * 删除
     * @param key 键名
     * @return 
     */  
	void delete(String key);
	
	/** 
     * 过期设置
     * @param key 键名
     * @param timeout 有效时间
     * @return 
     */  
	boolean expire(String key, long timeout, TimeUnit timeUnit);
	
	/** 
     * 过期设置
     * @param key 键名
     * @param timeout 有效时间
     * @return 
     */  
	boolean expire(String key, long timeout);
	
	/** 
     * 存入
     * @param key 键名
     * @param value 值
     * @return 
     */  
	void put(String key, String value);
	
	/** 
     * 存入并指定有效期 
     * @param key 键名
     * @param value 值
     * @param timeout 有效时间
     * @return 
     */  
	void put(String key, String value, int timeout);
	
	/** 
     * 存入并指定有效期 
     * @param key 键名
     * @param value 值
     * @param timeout 有效时间
     * @return 
     */  
	void put(String key, String value, int timeout, TimeUnit unit);
	
	/** 
     * 根据键获得值 
     * @param key 键名
     * @return 
     */  
	String get(String key);
	
	
	/** ============== list ==================== */
	
	 /** 
     * 压栈 
     * @param key 键名
     * @param value 值
     * @return 
     */  
    public Long push(String key, String value);
    
    /** 
     * 出栈 
     * @param key 键名
     * @return 
     */  
    public String pop(String key);
  
    /** 
     * 入队 
     * @param key 键名
     * @param value 值
     * @return 
     */  
    public Long in(String key, String value);
  
    /** 
     * 出队 
     * @param key 键名
     * @return 
     */  
    public String out(String key);
  
    /** 
     * 栈/队列长 
     * @param key 键名
     * @return 
     */  
    public Long length(String key);
  
    /** 
     * 范围检索 
     * @param key 键名
     * @param start 起始点
     * @param end   结束点
     * @return 
     */  
    public List<String> range(String key, int start, int end);
    
    /** 
     * 移除 
     * @param key 键名
     * @param i   
     * @param value 值
     */  
    public void remove(String key, long i, String value);
  
    /** 
     * 检索 
     *@param key 键名
     * @param index 
     * @return 
     */  
    public String index(String key, long index);
  
    /** 
     * 置值 
     * @param key 键名
     * @param value 值
     * @param index 
     */  
    public void set(String key, long index, String value);
  
    /** 
     * 裁剪 
     * @param key 键名
     * @param start 起始点
     * @param end   结束点
     */  
    public void trim(String key, long start, int end);
    
    /** ============== set ==================== */
    /** 
     * 添加单个元素
     * @param key 键名
     * @param value 值
     * @return 
     */  
    public void add(String key, String value);
    
	 /** 
     * 添加数组元素
     * @param key 键名
     * @param value 数组值
     * @return 
     */  
    public void add(String key, String... value);
  
    /** 
     * 移除单个元素
     * @param key 键名
     * @param value 值
     * @return 
     */  
    public void remove(String key, String value);
    
	 /** 
     * 移除数组元素
     * @param key 键名
     * @param value 数组值
     * @return 
     */  
    public void remove(String key, Object... value);
    
}
