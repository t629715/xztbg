package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: GoldRedeemConfService 
* @Description: 黄金赎回配置
* @author htt
* @date 2017-10-19 下午4:30:26 
*
 */
public interface GoldRedeemConfService {
	
	/**
	 * 
	* @Title: selectByGoldRedeemConf 
	* @Description: 黄金赎回配置查询
	* @param isEnable  使用启用 1：启用；0：禁用
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByGoldRedeemConf(Integer isEnable);

}
