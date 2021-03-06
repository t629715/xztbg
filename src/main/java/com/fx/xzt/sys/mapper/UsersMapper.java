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
	int updateByPIdSelective(Users users);

	int deleteById(Long id);

	/**
	 * 根据代理商id删除经纪人
	 * @param pid
	 * @return
	 */
	int deleteByPid(Long pid);
	
	List<UsersModel> selectByUsersModel(Map<String,Object> map);
	
	Users selectByPhone(String phone);
	
	Users selectById(Long id);
	Map<String,Object> selectById1(Long id);

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
	 * 
	* @Title: selectByChannelMessage 
	* @Description: 获取渠道商数据
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String,Object>> selectByChannelMessage();

	/**
	 * 根据代理商名字，经纪人名字、创建时间、类型查询 tianliya
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getByAgentNameAndType(Map<String, Object> map);
	/**
	 * 商户管理-运营商视角 tianliya
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSightOfOperator(Map<String, Object> map);

}
