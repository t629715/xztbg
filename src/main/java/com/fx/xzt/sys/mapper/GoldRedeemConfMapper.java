package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.GoldRedeemConf;

/**
 * 
* @ClassName: GoldRedeemMapper 
* @Description: 黄金赎回配置
* @author htt
* @date 2017-10-19 下午2:37:24 
*
 */
public interface GoldRedeemConfMapper extends BaseMapper<GoldRedeemConf> {
	
	/**
	 * 
	* @Title: selectByGoldRedeem 
	* @Description: 黄金赎回配置查询
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByGoldRedeemConf (Map<String,Object> map);
	
}
