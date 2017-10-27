package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UsersPermission;

@Repository
public interface UsersPermissionMapper extends BaseMapper<UsersPermission> {
	/**
	 * 添加
	 */
	int insertUsersPermission(UsersPermission usersPermission);

	/**
	 * 修改
	 */
	
	int updateByIdSelective(UsersPermission usersPermission);

	/**
	 * 删除
	 */
	
	int deleteById(Integer id);
	
	/**
	 * 根据 rids 获取 功能列表
	 */
	
	List<UsersPermission> getByRids(Map<String,Object> map);
	/**
	 * 获取全部的 功能
	 * 
	 */
	List<UsersPermission> getByRidsAll();

	List<UsersPermission> getPermissions();
}
