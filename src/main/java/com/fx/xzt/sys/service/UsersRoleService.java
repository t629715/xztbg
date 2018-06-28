package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.UsersRole;

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
	
	/**
	 * 
	* @Title: getById 
	* @Description: 根据id获取角色信息
	* @param id
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> getById(String id);
}
