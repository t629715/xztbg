package com.fx.jyg.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.UsersUserRole;
import com.fx.jyg.sys.model.UsersUserRoleModel;

@Repository
public interface UsersUserRoleMapper extends BaseMapper<UsersUserRole>{
	/**
	 * 添加
	 */
	int insertUsersUserRole(UsersUserRole usersUserRole);
	/**
	 * 修改
	 */
	int updateByIdSelective(UsersUserRole usersUserRole);
	/**
	 * 删除
	 */
	int deleteById(Integer id);
	/**
	 * 根据 用户和角色删除
	 */
	int deleteByUserRole(UsersUserRole usersUserRole);
	/**
	 * 根据 用户删除角色
	 */
	int deleteByUser(Integer uid);
	/**
	 * 查询 用户角色信息
	 */
	List<Integer> selectByUserId(Integer uid);
	/**
	 * 查询每个用户的角色集合 列表
	 */
	List<UsersUserRoleModel> selectByUsersRole();
	/**
	 * 查询每个角色的用户集合 列表
	 */
	List<UsersUserRoleModel> selectByRoleUsers(Map<String,Object> map);
	/**
	 * 查询角色用户信息
	 * 
	 */
	List<UsersUserRole> selectByRoleId(Integer rid);
}
