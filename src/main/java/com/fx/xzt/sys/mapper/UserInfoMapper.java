package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserInfo;
import com.fx.xzt.sys.model.UserInfoModel;

/**
 * 
* @Title: UserInfoMapper.java 
* @Package com.fx.xzt.sys.mapper
* @Description: TODO
* @author SYan  
* @date 2017年8月11日 下午5:25:46 
* @version V1.0
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo>{
	/**
	 * 查询认证列表--弃用
	 * @param map
	 * @return
	 */
	List<UserInfoModel> getByAll(Map<String,Object> map);
	/**
	 * 认证  通过了 修改RealNameAuthApproveState 1 和 RealNameAuthStatus 1
	 */
	int editUserInfo(UserInfo u);
	/**
	 * 账户信息列表
	 */
	List<Map<String, Object>> getByAccountMessage(Map<String,Object> map);

	/**
	 * 实名认证列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getByRealNameAuth(Map<String,Object> map);
	
	/**
	 * 
	* @Title: getByRealNameAuthApprove 
	* @Description: 实名认证已审核列表
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> getByRealNameAuthApprove(Map<String,Object> map);

	/**
	 * 获取账户信息列表金额黄金统计
	 * @return
	 */
	Map<String,Object> getByAccountCount(Map<String,Object> map);

	/**
	 * 合作伙伴-获取下级客户信息
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSubClients(Map<String,Object> map);

	/**
	 * 合作伙伴-获取下级客户信息金钱统计
	 * @param map
	 * @return
	 */
	Map<String, Object> getSubClientsAccountCount(Map<String,Object> map);

	/**
	 * 修改客户的经纪人id
	 * @param map
	 * @return
	 */
	int updateUserInfoBrokerId(Map<String,Object> map);
	
	/**
	 * 
	* @Title: getByUserAnalysis 
	* @Description: 用户分析
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> getByUserAnalysis(Map<String,Object> map);
	
	/**
	 * 
	* @Title: getByUserAnalysisCount 
	* @Description: 用户分析-统计
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> getByUserAnalysisCount(Map<String,Object> map);
	
	/**
	 * 
	* @Title: getByUserAttribute 
	* @Description: 用户属性
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> getByUserAttribute(Map<String,Object> map);
	
	/**
	 * 
	* @Title: getByUserAttributeCount 
	* @Description: 用户属性--统计
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> getByUserAttributeCount(Map<String,Object> map);

	/**
	 * 根据代理商id获取客户信息
	 */
	List<UserInfo> selectUserInfoByAgentId(Long agentId);

}