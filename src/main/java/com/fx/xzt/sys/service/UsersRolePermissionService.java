package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.UsersRolePermission;

import java.util.List;

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
	/**
	 * 角色管理-获取某个角色的菜单集合
	 * @param roleId
	 * @return
	 */
	List<Integer> selectPidByRoleId(Integer roleId);
}
