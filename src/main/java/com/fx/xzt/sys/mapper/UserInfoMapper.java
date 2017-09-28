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
	 * 获取账户信息列表金额黄金统计
	 * @return
	 */
	Map<String,Object> getByAccountCount(Map<String,Object> map);
	
}