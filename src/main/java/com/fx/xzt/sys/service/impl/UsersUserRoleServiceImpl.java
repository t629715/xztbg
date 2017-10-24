package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fx.xzt.sys.entity.UsersRole;
import com.fx.xzt.sys.mapper.UsersMapper;
import com.fx.xzt.sys.mapper.UsersRoleMapper;
import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UsersUserRole;
import com.fx.xzt.sys.mapper.UsersUserRoleMapper;
import com.fx.xzt.sys.model.UsersUserRoleModel;
import com.fx.xzt.sys.service.UsersUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UsersUserRoleServiceImpl extends BaseService<UsersUserRole> implements UsersUserRoleService{

	@Resource
	UsersUserRoleMapper usersUserRoleMapper;
	@Resource
	UsersRoleMapper usersRoleMapper;
	@Resource
	UsersMapper usersMapper;
	
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

	/**
	 * 角色管理-查询  tianliya 10月23日
	 * @param userName
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo selectRoleUsers(String userName, String startTime, String endTime, Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap();
		map.put("userName",userName);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		PageHelper.startPage(pageNum,pageSize);
		//获取所有的角色
		List<Map<String, Object>> usersRoles = usersRoleMapper.getRoles(map);
		for (Map m:usersRoles){
			List<UsersUserRole> list = usersUserRoleMapper.selectByRoleId(new Integer(m.get("id").toString()));
			String sb = "";
			String  uName = null;
			for (UsersUserRole uur:list){
				if (uur.getUid()!= null){
					if (usersMapper.selectById(new Long(uur.getUid())) != null)
					uName = usersMapper.selectById(new Long(uur.getUid())).getUserName();
				}
				if (!sb.equals(""))
					sb += "、";
				sb += uName;
			}
			m.put("userNames",sb);
		}
		//获取角色对应的用户集合
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(usersRoles);
		return pageInfo;
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
