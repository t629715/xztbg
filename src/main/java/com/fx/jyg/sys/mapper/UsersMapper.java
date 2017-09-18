package com.fx.jyg.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.Users;
import com.fx.jyg.sys.model.UsersModel;

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

}