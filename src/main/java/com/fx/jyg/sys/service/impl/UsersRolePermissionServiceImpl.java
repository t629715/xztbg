package com.fx.jyg.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.jyg.sys.entity.UsersRolePermission;
import com.fx.jyg.sys.mapper.UsersRolePermissionMapper;
import com.fx.jyg.sys.service.UsersRolePermissionService;

@Service
public class UsersRolePermissionServiceImpl extends BaseService<UsersRolePermission> implements UsersRolePermissionService{
	
	@Resource
	UsersRolePermissionMapper usersRolePermissionMapper;
	
	public int insertUsersRolePermission(UsersRolePermission urp) {
		return usersRolePermissionMapper.insertUsersRolePermission(urp);
	}

	public int updateByIdSelective(UsersRolePermission urp) {
		return usersRolePermissionMapper.updateByIdSelective(urp);
	}

	public int deleteById(Integer id) {
		return usersRolePermissionMapper.deleteById(id);
	}

	public int deleteByRid(Integer rid) {
		// TODO Auto-generated method stub
		return usersRolePermissionMapper.deleteByRid(rid);
	}

}
