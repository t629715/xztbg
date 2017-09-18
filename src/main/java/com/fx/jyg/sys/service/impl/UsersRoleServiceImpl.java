package com.fx.jyg.sys.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.jyg.sys.entity.UsersPermission;
import com.fx.jyg.sys.entity.UsersRole;
import com.fx.jyg.sys.entity.UsersRolePermission;
import com.fx.jyg.sys.mapper.UsersRoleMapper;
import com.fx.jyg.sys.service.UsersRolePermissionService;
import com.fx.jyg.sys.service.UsersRoleService;

@Service
public class UsersRoleServiceImpl extends BaseService<UsersRole> implements UsersRoleService{

	@Resource
	UsersRoleMapper usersRoleMapper;
	@Resource
	UsersRolePermissionService usersRolePermissionService;
	
	@Transactional
	public int insertRole(UsersRole usersRole,List<Integer> pids) {
		usersRole.setCreateTime(new Date());
		int msg=usersRoleMapper.insertRole(usersRole);
		if(msg>0){
			Integer rid = usersRole.getId();
			if(pids!=null&&!pids.isEmpty()){
				for (Integer pid : pids) {
					UsersRolePermission urp = new UsersRolePermission();
					urp.setRid(rid);
					urp.setPid(pid);
					msg = usersRolePermissionService.insertUsersRolePermission(urp);
				}
			}
		}
		return msg;
	}
	
	@Transactional
	public int updateByIdSelective(UsersRole usersRole,List<Integer> pids) {
		int msg = usersRoleMapper.updateByIdSelective(usersRole);
		if(msg>0){
			Integer rid = usersRole.getId();
			if(pids!=null&&!pids.isEmpty()){
				msg = usersRolePermissionService.deleteByRid(rid);
				if(msg>0){
					for (Integer pid : pids) {
						UsersRolePermission urp = new UsersRolePermission();
						urp.setRid(rid);
						urp.setPid(pid);
						msg = usersRolePermissionService.insertUsersRolePermission(urp);
					}
				}
			}
		}
		return msg;
	}
	
	@Transactional
	public int deleteById(Integer id) {
		return usersRoleMapper.deleteById(id);
	}

	public List<UsersRole> getByAll(UsersRole usersRole) {
		return usersRoleMapper.getByAll(usersRole);
	}

}
