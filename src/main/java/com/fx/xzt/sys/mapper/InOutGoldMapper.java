package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.InOutGold;

/**
 * 
* @ClassName: InOutGoldMapper 
* @Description: 出入金管理
* @author htt
* @date 2017-10-20 下午2:33:32 
*
 */
@Repository
public interface InOutGoldMapper extends BaseMapper<InOutGold> {

	/**
	 * 
	* @Title: selectByInOutGold 
	* @Description: 出入金查询
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByInOutGold (Map<String,Object> map);
	
	/**
	 * 
	* @Title: selectByRechargeChannel 
	* @Description: 出入金分析--支付渠道分析
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByRechargeChannel (Map<String,Object> map);
	
	/**
	 * 
	* @Title: selectByAgent 
	* @Description: 出入金分析--运营商出入金分析
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByAgent (Map<String,Object> map);
	
	/**
	 * 
	* @Title: selectByAgentNet 
	* @Description: 出入金分析-运营商净入金分析
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByAgentNet (Map<String,Object> map);
}
