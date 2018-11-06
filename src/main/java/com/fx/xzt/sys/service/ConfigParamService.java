package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.ConfigParam;
import com.github.pagehelper.PageInfo;

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
    
    /**
     * 
    * @Title: selectByAll 
    * @Description: 查询
    * @param paramName
    * @param pageNum
    * @param pageSize
    * @return    设定文件 
    * @return PageInfo<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    PageInfo<Map<String, Object>> selectByAll(String paramName, Integer pageNum, Integer pageSize);
    
    /**
     * 
    * @Title: insert 
    * @Description: 新增
    * @param paramName 参数名称
    * @param paramValue 参数值
    * @param valueType 参数类型
    * @param description 描述
    * @param cacheRegion 缓存数据区域 0:Local(map), 1:Remote(redis)
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author htt
     */
    int insert(String paramName, String paramValue, String valueType, String description, String cacheRegion);
    
    /**
     * 
    * @Title: update 
    * @Description: 修改
    * @param paramName 参数名称
    * @param paramValue 参数值
    * @param valueType 参数类型
    * @param description 描述
    * @param cacheRegion 缓存数据区域 0:Local(map), 1:Remote(redis)
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author htt
     */
    int update(String paramName, String paramValue, String valueType, String description, String cacheRegion);
    
    /**
     * 
    * @Title: deleteByName 
    * @Description: 删除
    * @param paramName
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author htt
     */
    int deleteByName(String paramName);

    /**
     * 获取回购配置  liaijiao
     * @return
     */
    List<Map<String, Object>> selectBuyBackConf();


}


