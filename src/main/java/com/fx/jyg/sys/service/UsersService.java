package com.fx.jyg.sys.service;

import java.util.List;

import com.fx.jyg.sys.entity.Users;
import com.fx.jyg.sys.model.UsersModel;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: UserService 
* @Description: 用户操作接口声明
* @author jcwang
* @date 2017年7月31日 下午4:01:57 
*
 */
public interface UsersService extends IService<Users>{

	/** 从缓存中获取 */
	Users getUserInfo(Long uid);

	/** 获取用户信息集合 */
	List<Users> listUserInfo();

	/** 根据用户名获取用户信息 */
	Users getUserInfoNyName(String currentUserName);

	/** 根据用户名密码校验用户是否存在 */
	Users getUserInfo(String username, String password);

	/** 根据手机号密码校验用户是否存在 */
	Users getUserInfoNyPhone(String phone, String password);
	/**
	 * 添加
	 * @param users
	 * @return
	 */
	int insertUsers(Users users,List<Integer> rids);
	/**
	 * 更新
	 * @param users
	 * @return
	 */
	int updateByIdSelective(Users users,List<Integer> rids);
	/**
	 * 删除
	 * @param users
	 * @return
	 */
	int deleteById(Long id);
	
	PageInfo<UsersModel> selectByUsersModel(String phone,String startTime,String endTime,Integer pageNum,Integer pageSize);

}
