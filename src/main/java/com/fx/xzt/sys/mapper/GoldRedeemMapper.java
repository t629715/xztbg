package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.GoldRedeem;

/**
 * 
* @ClassName: GoldRedeemMapper 
* @Description: 黄金赎回
* @author htt
* @date 2017-10-19 下午2:37:24 
*
 */
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
	
}
