package com.fx.xzt.sys.service;

import java.util.List;

import com.fx.xzt.sys.entity.ConfigParam;

/**
 * 
* @ClassName: ConfigParamService 
* @Description: 配置参数接口声明
* @author jcwang
* @date 2017年8月10日 下午2:36:11 
*
 */
public interface ConfigParamService extends IService<ConfigParam>{

	/**
	 * 
	 * @Description: 获得全部配置参数
	 * @author jcwang
	 * @date 2017年8月10日 下午2:36:44
	 */
	public List<ConfigParam> selectAll();
	
	/**
     * 根据主键查询数据
     */
    ConfigParam selectConfigParamByKey(String key);
}
