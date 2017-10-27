package com.fx.xzt.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UsersRolePermission;
import com.fx.xzt.sys.mapper.UsersRolePermissionMapper;
import com.fx.xzt.sys.service.UsersRolePermissionService;

import java.util.List;

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
	/**
	 * 获取某个角色的菜单集合 田力亚
	 * @param roleId
	 * @return
	 */
	@Override
	public List<Integer> selectPidByRoleId(Integer roleId) {
		return usersRolePermissionMapper.selectPidByRoleId(roleId) ;
	}

}
