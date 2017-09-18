package com.fx.jyg.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.jyg.sys.entity.UsersUserRole;
import com.fx.jyg.sys.mapper.UsersUserRoleMapper;
import com.fx.jyg.sys.model.UsersUserRoleModel;
import com.fx.jyg.sys.service.UsersUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UsersUserRoleServiceImpl extends BaseService<UsersUserRole> implements UsersUserRoleService{

	@Resource
	UsersUserRoleMapper usersUserRoleMapper;
	
	public int insertSelective(List<Integer> rids,List<Integer> uids) {
		int msg = -1;
		UsersUserRole usersUserRole = new UsersUserRole();
		if(uids!=null&&!uids.isEmpty()){
			for (Integer uid: uids) {
				for(Integer rid :rids){
					usersUserRole.setId(null);
					usersUserRole.setRid(rid);
					usersUserRole.setUid(uid);
					deleteByUserRole(usersUserRole);
					msg = usersUserRoleMapper.insertUsersUserRole(usersUserRole);
				}
			}
		}
		return msg;
	}
	public int insertUpdate(List<Integer> rids,Integer uid){
		int msg = -1;
		UsersUserRole usersUserRole = new UsersUserRole();
		deleteByUser(uid);
		for(Integer rid :rids){
			usersUserRole.setId(null);
			usersUserRole.setRid(rid);
			usersUserRole.setUid(uid);
			msg = usersUserRoleMapper.insertUsersUserRole(usersUserRole);
		}
		return msg;
	}
	public int updateByIdSelective(UsersUserRole usersUserRole) {
		return usersUserRoleMapper.updateByIdSelective(usersUserRole);
	}

	public int deleteById(Integer id) {
		return usersUserRoleMapper.deleteById(id);
	}

	public List<Integer> selectByUserId(Integer uid) {
		return usersUserRoleMapper.selectByUserId(uid);
	}

	public PageInfo<UsersUserRoleModel> selectByUsersRole(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<UsersUserRoleModel> list = usersUserRoleMapper.selectByUsersRole();
		return new PageInfo<UsersUserRoleModel>(list);
	}

	public PageInfo<UsersUserRoleModel> selectByRoleUsers(String phone,String startTime,String endTime,Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum,pageSize);
		List<UsersUserRoleModel> list = usersUserRoleMapper.selectByRoleUsers(map);
		return new PageInfo<UsersUserRoleModel>(list);
	}

	public int deleteByUserRole(UsersUserRole usersUserRole) {
		return usersUserRoleMapper.deleteByUserRole(usersUserRole);
	}

	public int deleteByUser(Integer uid) {
		return usersUserRoleMapper.deleteByUser(uid);
	}
	public List<UsersUserRole> selectByRoleId(Integer rid) {
		return usersUserRoleMapper.selectByRoleId(rid);
	}

}
