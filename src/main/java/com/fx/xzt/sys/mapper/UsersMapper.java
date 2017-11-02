package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.model.UsersModel;

/**
 * 
* @ClassName: UserInfoMapper 
* @Description: 用户信息映射类
* @author jcwang
* @date 2017年7月31日 下午4:04:24 
*
 */
@Repository
public interface UsersMapper extends BaseMapper<Users>{
	List<Users> selectAll();

	Users getUserInfoNyName(String user_name);

	Users getUserInfo(Map<String, String> map);

	Users getUserInfoNyPhone(Map<String, String> map);
	
	int insertUsers(Users users);
	
	int updateByIdSelective(Users users);
	
	int deleteById(Long id);
	
	List<UsersModel> selectByUsersModel(Map<String,Object> map);
	
	Users selectByPhone(String phone);
	
	Users selectById(Long id);

	/**
	 *  获取代理商列表
	 * @return
	 */
	List<Map<String,Object>> selectByAgentMessage();

	/**
	 * 获取经纪人列表
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectByBrokerMessage(Map<String,Object> map);

	/**
	 * 根据id获取数据
	 * @param userId
	 * @return
	 */
	Map<String, Object> getOneByUserId(Long userId);

	/**
	 * 根据代理商名字，经纪人名字、创建时间、类型查询 tianliya
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getByAgentNameAndType(Map<String, Object> map);

}