package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.GoldWithdraw;

/**
 * 
* @ClassName: GoldWithdrawMapper 
* @Description: 黄金提取
* @author htt
* @date 2017-10-17 下午1:46:39 
*
 */
public interface GoldWithdrawMapper extends BaseMapper<GoldWithdraw> {
	
	/**
	 * 
	* @Title: selectByGoldWithdraw 
	* @Description: 黄金提取查询
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByGoldWithdraw (Map<String,Object> map);
	
	/**
	 * 
	* @Title: selectByGoldWithdrawCount 
	* @Description: 黄金提取查询-物流费、黄金、保险费用统计
	* @param map
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> selectByGoldWithdrawCount(Map<String,Object> map);
	
	/**
	 * 
	* @Title: updateById 
	* @Description: 黄金提取修改
	* @param goldWithdraw
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int updateById(GoldWithdraw goldWithdraw);

}
