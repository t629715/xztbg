package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.GoldRedeem;

/**
 * 
* @ClassName: GoldRedeemMapper 
* @Description: 黄金赎回
* @author htt
* @date 2017-10-19 下午2:37:24 
*
 */
@Repository
public interface GoldRedeemMapper extends BaseMapper<GoldRedeem> {
	
	/**
	 * 
	* @Title: selectByGoldRedeem 
	* @Description: 黄金赎回查询
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByGoldRedeem (Map<String,Object> map);
	
	/**
	 * 
	* @Title: selectByGoldRedeemCount 
	* @Description: 黄金赎回--黄金克重统计
	* @param map
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> selectByGoldRedeemCount(Map<String,Object> map);
	
	/**
	 * 
	* @Title: insertGoldRedeem 
	* @Description: 黄金赎回--新增
	* @param goldRedeem
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int insertGoldRedeem(GoldRedeem goldRedeem);
	
}
