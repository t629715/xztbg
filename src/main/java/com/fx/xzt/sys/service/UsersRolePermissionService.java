package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.UsersRolePermission;

public interface UsersRolePermissionService extends IService<UsersRolePermission>{
	/**
	 * 添加
	 */
	int insertUsersRolePermission(UsersRolePermission urp);
	/**
	 * 修改
	 */
	int updateByIdSelective(UsersRolePermission urp);
	/**
	 * 删除
	 */
	int deleteById(Integer id);
	/**
	 * 根据 rid 删除
	 */
	int deleteByRid(Integer rid);
}
