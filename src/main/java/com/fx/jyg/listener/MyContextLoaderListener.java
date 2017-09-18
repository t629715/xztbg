package com.fx.jyg.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fx.jyg.sys.entity.ConfigParam;
import com.fx.jyg.sys.service.ConfigParamService;
import com.fx.jyg.util.LoggerUtils;
import com.fx.jyg.util.OSCache;

/**
 * 
* @ClassName: MyContextLoaderListener 
* @Description: 系统启动时加载配置表中数据
* @author jcwang
* @date 2017年8月11日 下午12:47:07 
*
 */
public class MyContextLoaderListener extends ContextLoaderListener {  
	private final Logger logger = LoggerUtils.getLogger("MyContextLoaderListener");
    private ConfigParamService configParamService;
    private RedisTemplate redisTemplate;
    /** 
     * @description 重写ContextLoaderListener的contextInitialized方法 
     */  
    public void contextInitialized(ServletContextEvent event) {  
        super.contextInitialized(event);  
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());  
        //获取bean  
        configParamService = (ConfigParamService) applicationContext.getBean("configParamService");   
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate"); 
        System.out.println(configParamService.selectAll().size());
        List<ConfigParam> li = configParamService.selectAll();
        Map<String, Object> local = new HashMap<String, Object>();
        for (ConfigParam configParam : li) {
        	if(configParam.getCacheRegion()==0){
        		local.put(configParam.getParamName(), convert(configParam.getParamValue(), configParam.getValueType()));
        	}else{
        		redisTemplate.opsForValue().set(configParam.getParamName(), convert(configParam.getParamValue(), configParam.getValueType()));
        	}
		}
        new OSCache(local);
        local.clear();
    }
    
    /**
     * 
     * @Description: 转换类型
     * @param value 值
     * @param type  设置的类型
     * @author jcwang
     * @date 2017年8月11日 下午12:46:29
     */
    private Object convert(String value, String type){
    	if(type.equals("int")){
    		return Integer.valueOf(value);
    	}else if(type.equals("double")){
    		return Double.valueOf(value);
    	}else if(type.equals("boolean")){
    		return Boolean.valueOf(value);
    	}else{
    		return value;
    	}
    }
    
}  
