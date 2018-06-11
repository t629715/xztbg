package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.SaveGoldRecord;

/**
 * 
* @ClassName: SaveGoldRecordMapper 
* @Description: 存金记录
* @author htt
* @date 2018-5-29 上午11:28:05 
*
 */
@Repository
public interface SaveGoldRecordMapper extends BaseMapper<SaveGoldRecord> {
	
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
	List<Map<String, Object>> selectByAll (Map<String,Object> map);
	
	/**
	 * 
	* @Title: countByAllS 
	* @Description: 查询统计
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> countByAll (Map<String,Object> map);

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/11 11:11
	 * @Description：存金宝买入总金额
	 * @return
	 */
	Float countBuyGold();

}
