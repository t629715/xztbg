package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * 
	* @Title: deleteByPId 
	* @Description: 根据pid删除子菜单
	* @param pid
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int deleteByPid(Integer pid);
	
	/**
	 * 根据 rids 获取 功能列表
	 */
	
	List<UsersPermission> getByRids(Map<String,Object> map);
	/**
	 * 获取全部的 功能
	 * 
	 */
	List<UsersPermission> getByRidsAll();

	/**
	 * 获取所有的菜单
	 * @return
	 */
	List<UsersPermission> getPermissions();

	/**
	 * 根据Rid获取
	 * @param pid
	 * @return
	 */
	Integer getByPid(Integer pid );
}
