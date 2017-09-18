package com.fx.jyg.sys.mapper;

import org.springframework.stereotype.Repository;

import com.fx.jyg.sys.entity.UsersRolePermission;

@Repository
public interface UsersRolePermissionMapper extends BaseMapper<UsersRolePermission>{
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
