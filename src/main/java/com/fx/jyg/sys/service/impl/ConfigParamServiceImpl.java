package com.fx.jyg.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.jyg.sys.entity.ConfigParam;
import com.fx.jyg.sys.mapper.ConfigParamMapper;
import com.fx.jyg.sys.service.ConfigParamService;

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
	
}
