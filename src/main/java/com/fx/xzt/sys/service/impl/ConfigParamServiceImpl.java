package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.ConfigParam;
import com.fx.xzt.sys.mapper.ConfigParamMapper;
import com.fx.xzt.sys.service.ConfigParamService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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

	/**
	 * 查询
	 */
	public PageInfo<Map<String, Object>> selectByAll(String paramName, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("paramName", paramName);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = configParamMapper.selectByAll(map);
        if (list != null && list.size() > 0) {
        	for (Map<String, Object> map1 : list) {
				Object cacheRegionObj = map1.get("cacheRegion");
				if (cacheRegionObj != null && cacheRegionObj != "") {
					map1.put("cacheRegion", ConstantUtil.cacheRegion.toMap().get(cacheRegionObj.toString()));
				}
        	}
        }
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 新增
	 */
	@Transactional
	public int insert(String paramName, String paramValue, String valueType,
			String description, String cacheRegion) {
		int flag = 0;
		if (StringUtil.isNotEmpty(paramName)) {
			ConfigParam configParam = new ConfigParam();
			configParam.setParamName(paramName);
			configParam.setParamValue(paramValue);
			configParam.setValueType(valueType);
			configParam.setDescription(description);
			if (StringUtil.isNotEmpty(cacheRegion)) {
				configParam.setCacheRegion(Short.valueOf(cacheRegion));
			}
			flag = configParamMapper.add(configParam);
		}
		return flag;
	}

	/**
	 * 修改
	 */
	@Transactional
	public int update(String paramName, String paramValue, String valueType,
			String description, String cacheRegion) {
		int flag = 0;
		if (StringUtil.isNotEmpty(paramName)) {
			ConfigParam configParam = new ConfigParam();
			configParam.setParamName(paramName);
			configParam.setParamValue(paramValue);
			configParam.setValueType(valueType);
			configParam.setDescription(description);
			if (StringUtil.isNotEmpty(cacheRegion)) {
				configParam.setCacheRegion(Short.valueOf(cacheRegion));
			}
			flag = configParamMapper.update(configParam);
		}
		return flag;
	}

	/**
	 * 删除
	 */
	@Transactional
	public int deleteByName(String paramName) {
		int flag = 0;
		if (StringUtil.isNotEmpty(paramName)) {
			flag = configParamMapper.deleteByName(paramName);
		}
		return flag;
	}

	/**
	 * 获取回购配置 liaijiao
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectBuyBackConf() {
		List<Map<String, Object>> list=configParamMapper.selectBuyBack();
		return list;
	}


}
