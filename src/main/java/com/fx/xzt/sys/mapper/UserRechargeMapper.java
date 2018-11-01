package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserRecharge;
import com.fx.xzt.sys.model.UserRechargeModel;

/**
 * 
* @Title: UserRechargeMapper.java 
* @Package com.fx.xzt.sys.mapper
* @Description: TODO
* @author SYan  
* @date 2017年8月22日 上午9:27:49 
* @version V1.0
 */
@Repository
public interface UserRechargeMapper extends BaseMapper<UserRecharge>{
	List<UserRechargeModel> getByAll(Map<String,Object> map);
	
	/**
	 * 
	* @Title: selectByRecharge 
	* @Description: 查询现金充值记录
	* @param @param map
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByRecharge (Map<String,Object> map);

	/**
	 * 
	* @Title: selectByRechargeCount 
	* @Description: 查询现金充值-总计
	* @param @param map
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
    Map<String, Object> selectByRechargeCount(Map<String,Object> map);

	/**
	 * liaijiao
	 * 获取充值平台
	 * @return
	 */
	List<Map<String,Object>> selectPlatformName();
	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/4/23 13:24
	 * @Description：人工充值的记录
	 * @param userRecharge
	 * @return
	 */
	Integer insertOneRecord(UserRecharge userRecharge);
}
