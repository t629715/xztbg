package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.UsersPermission;
import com.fx.xzt.sys.model.TreeModel;

public interface UsersPermissionService extends IService<UsersPermission>{
	
	/**
	 * 添加
	 */
	int insertSelective(UsersPermission usersPermission);

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
	
	TreeModel getByRids(List<Integer> rids);
	
	TreeModel getByRidsAll();
	
	
}
