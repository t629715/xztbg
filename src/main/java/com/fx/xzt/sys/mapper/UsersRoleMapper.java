package com.fx.xzt.sys.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UsersRole;


/**
 * 
* @Title: UsersRoleMapper.java 
* @Package com.fx.xzt.sys.mapper
* @Description: TODO
* @author SYan  
* @date 2017年8月15日 下午6:05:40 
* @version V1.0
 */
@Repository
public interface UsersRoleMapper extends BaseMapper<UsersRole>{
	/**
	 * 添加
	 */
	int insertRole(UsersRole usersRole);
	/**
	 * 修改
	 */
	int updateByIdSelective(UsersRole usersRole);
	/**
	 * 删除
	 */
	int deleteById(Integer id);
	/**
	 * 角色列表
	 */
	List<UsersRole> getByAll(UsersRole usersRole);
	
}
