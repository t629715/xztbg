package com.fx.xzt.sys.mapper;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserLogin;

/**
 * 
* @Title: UserLoginMapper.java 
* @Package com.fx.xzt.sys.mapper
* @Description: TODO
* @author SYan  
* @date 2017年8月21日 下午4:16:44 
* @version V1.0
 */
@Repository
public interface UserLoginMapper extends BaseMapper<UserLogin>{
	int updateByIdSelective(UserLogin userLogin);

	List<Map<String, Object>> getByRegisterMessage(Map<String,Object> map);
	
	/**
	 * 
	* @Title: getByAccount 
	* @Description: 获取用户账户信息
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> getByAccount(Map<String,Object> map);
	
	/**
	 * 
	* @Title: getByAttributionPro 
	* @Description: 注册信息查询-归属地获取
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> getByAttributionPro();

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/4/23 10:17
	 * @Description：通过userName获取数据
	 * @param userName
	 * @return
	 */
	UserLogin selectByUserName(String userName);

}
