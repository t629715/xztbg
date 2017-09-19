package com.fx.xzt.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.UsersPermission;
import com.fx.xzt.sys.entity.UsersRole;
import com.fx.xzt.sys.entity.UsersUserRole;
import com.fx.xzt.sys.model.TreeModel;
import com.fx.xzt.sys.model.UsersUserRoleModel;
import com.fx.xzt.sys.service.UsersPermissionService;
import com.fx.xzt.sys.service.UsersRoleService;
import com.fx.xzt.sys.service.UsersUserRoleService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	UsersPermissionService usersPermissionService;
	
	@Resource
	UsersUserRoleService usersUserRoleService;
	
	@Resource
	UsersRoleService usersRoleService;
	
	/**
	 * 获取等路人权限菜单集合 1 成功 -2菜单获取失败 0登陆出错
	 * @param rids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectByPermission")
	public Map<String,Object> selectByPermission(HttpSession httpSession){
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			Users u=(Users) httpSession.getAttribute("currentUser");
			List<Integer> rids = usersUserRoleService.selectByUserId(u.getId().intValue());
			map.put("users", u);
			map.put("msg", -2);
			if(rids!=null&&!rids.isEmpty()){
				TreeModel tree = usersPermissionService.getByRids(rids);
				List<TreeModel> list = new ArrayList<TreeModel>();
				list.add(tree);
				map.put("data", list);
				map.put("msg", 1);
			}
		} catch (Exception e) {
			map.put("msg", 0);
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 根据 角色获取菜单功能
	 * 不传递则 获取全部 功能
	 */
	@ResponseBody
	@RequestMapping(value="/selectByRidPermission")
	public TreeModel selectByRidPermission(Integer rid){
		List<Integer> rids = new ArrayList<Integer>();
		if(rid!=null){
			rids.add(rid);
		}
		return usersPermissionService.getByRids(rids);
	}
	/**
	 * 根据 角色获取菜单功能
	 * 不传递则 获取全部 功能
	 */
	@ResponseBody
	@RequestMapping(value="/selectByRidPermissionAll")
	public TreeModel selectByRidPermissionAll(){
		return usersPermissionService.getByRidsAll();
	}
	/**
	 * 角色列表
	 */
	@ResponseBody
	@RequestMapping(value="/selectByRoleAll")
	public List<UsersRole> selectByRoleAll(){
		return usersRoleService.getByAll(null);
	}
	/**
	 * 查询每个用户的角色集合 列表
	 */
	@ResponseBody
	@RequestMapping(value="/selectByUserRoleAll")
	public PageInfo<UsersUserRoleModel> selectByUserRoleAll(Integer pageNum,Integer pageSize){
		return usersUserRoleService.selectByUsersRole(pageNum, pageSize);
	}
	/**
	 * 查询每个角色的用户集合 列表
	 */
	@ResponseBody
	@RequestMapping(value="/selectByRoleUsers")
	public PageInfo<UsersUserRoleModel> selectByRoleUsers(String phone,String startTime,String endTime,Integer pageNum,Integer pageSize){
		return usersUserRoleService.selectByRoleUsers(phone, startTime, endTime, pageNum, pageSize);
	}
	/**
	 * 添加功能
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/insertPermission")
	@ResponseBody
	public Map<String,Object> insertPermission(UsersPermission usersPermission){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = usersPermissionService.insertSelective(usersPermission);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 修改功能
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/updatePermission")
	@ResponseBody
	public Map<String,Object> updatePermission(UsersPermission usersPermission){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = usersPermissionService.updateByIdSelective(usersPermission);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除功能
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/deletePermission")
	@ResponseBody
	public Map<String,Object> deletePermission(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = usersPermissionService.deleteById(id);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 添加角色
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/insertRole")
	@ResponseBody
	public Map<String,Object> insertRole(UsersRole usersRole,@RequestParam(value="pids", required=false)List<Integer> pids){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = usersRoleService.insertRole(usersRole,pids);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 修改角色
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/updateRole")
	@ResponseBody
	public Map<String,Object> updateRole(UsersRole usersRole,@RequestParam(value="pids", required=false)List<Integer> pids){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = usersRoleService.updateByIdSelective(usersRole,pids);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除角色
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/deleteRole")
	@ResponseBody
	public Map<String,Object> deleteRole(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		List<UsersUserRole> list = usersUserRoleService.selectByRoleId(id);
		if(list==null||list.isEmpty()){
			map.put("msg", -1);
			map.put("explain", "该权限下面有用户无法删除");
			return map;
		}
		int msg = usersRoleService.deleteById(id);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 设定角色
	 */
	@RequestMapping(value="/insertUserRole")
	@ResponseBody
	public Map<String,Object> insertUserRole(@RequestParam(value="rids", required=false)List<Integer> rids,@RequestParam(value="uids", required=false)List<Integer> uids){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = usersUserRoleService.insertSelective(rids, uids);
		map.put("msg", msg);
		return map;
	}
	
}
