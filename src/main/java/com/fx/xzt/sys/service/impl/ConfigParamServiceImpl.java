package com.fx.xzt.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.ConfigParam;
import com.fx.xzt.sys.mapper.ConfigParamMapper;
import com.fx.xzt.sys.service.ConfigParamService;

/**
 * 
* @ClassName: ConfigParamServiceImpl 
* @Description: 配置参数操作类
* @author jcwang
* @date 2017年8月10日 下午2:33:15 
*
 */
@Service("configParamService")
public class ConfigParamServiceImpl extends BaseService<ConfigParam> implements ConfigParamService{

	@Resource
	private ConfigParamMapper configParamMapper;

	/**
	 * @Description: 获得全部配置参数
	 * @author jcwang
	 * @date 2017年8月10日 下午2:36:44
	 */
	public List<ConfigParam> selectAll() {
		return configParamMapper.selectAll();
	}
	
	/**
     * 根据主键查询数据
     */
    public ConfigParam selectConfigParamByKey(String key) {
    	ConfigParam configParam = null;
    	List<ConfigParam> list = configParamMapper.selectConfigParamByKey(key);
    	if (list != null && list.size() == 1) {
    		configParam = list.get(0);
    	}
    	return configParam;
    }
}
