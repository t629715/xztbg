package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.ConfigParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ConfigParamMapper extends BaseMapper<ConfigParam>{
    /**
     * 根据主键查询数据
     */
    List<ConfigParam> selectConfigParamByKey(String key);
    
    /**
     * 
    * @Title: selectByAll 
    * @Description: 查询
    * @param map
    * @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> selectByAll(Map<String, Object> map);
    
    /**
     * 新增
     */
    int add(ConfigParam configParam);
    
    /**
     * 
    * @Title: update 
    * @Description: 修改
    * @param configParam
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author htt
     */
    int update(ConfigParam configParam);
    
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
     * 获取回购配置
     * @return
     */
    List<Map<String, Object>> selectBuyBack();


}