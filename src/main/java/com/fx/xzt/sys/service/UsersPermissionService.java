package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.UsersPermission;
import com.fx.xzt.sys.model.TreeModel;
import com.fx.xzt.sys.model.UsersMenuModel;

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
	
	/**
	 * 
	* @Title: getByUsersPermissionRids 
	* @Description: 根据用户角色获取菜单
	* @param rids
	* @return    设定文件 
	* @return UsersMenuModel    返回类型 
	* @throws 
	* @author htt
	 */
	UsersMenuModel getByUsersPermissionRids(List<Integer> rids);
	
	/**
	 * 
	* @Title: getByUsersPermissionAll 
	* @Description: 获取用户菜单--全部
	* @return    设定文件 
	* @return UsersMenuModel    返回类型 
	* @throws 
	* @author htt
	 */
	UsersMenuModel getByUsersPermissionAll();

	TreeModel getPermissions();
	
	/**
	 * 
	* @Title: getByUsersPermission 
	* @Description: 根据用户角色获取菜单
	* @param rids
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> getByUsersPermission(List<Integer> rids);
	
	/**
	 * 
	* @Title: getByUsersPermissionAllNew 
	* @Description: 获取全部菜单
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> getByUsersPermissionAllNew();
}
