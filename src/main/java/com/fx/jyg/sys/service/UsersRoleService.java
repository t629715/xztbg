package com.fx.jyg.sys.service;

import java.util.List;

import com.fx.jyg.sys.entity.UsersRole;

public interface UsersRoleService extends IService<UsersRole>{
	/**
	 * 添加
	 */
	int insertRole(UsersRole usersRole,List<Integer> pids);
	/**
	 * 修改
	 */
	int updateByIdSelective(UsersRole usersRole,List<Integer> pids);
	/**
	 * 删除
	 */
	int deleteById(Integer id);
	/**
	 * 角色列表
	 */
	List<UsersRole> getByAll(UsersRole usersRole);
}
