package com.fx.xzt.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sql.ConnectionPoolDataSource;

import com.fx.xzt.sys.service.UsersRolePermissionService;
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
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
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

	@Resource
	UsersRolePermissionService usersRolePermissionService;
	
	/**
	 * 
	* @Title: selectByPermissionAll 
	* @Description: 获取后台全部菜单
	* @return    设定文件 
	* @return UsersMenuModel    返回类型 
	* @throws 
	* @author htt
	 */
	@ResponseBody
	@RequestMapping(value="/selectByPermissionAll")
	public Object selectByPermissionAll(){
		//Map<String, Object> map = new HashMap<>();
		//List<UsersMenuModel> list = new ArrayList<>();
		//UsersMenuModel model = usersPermissionService.getByUsersPermissionAll();
		Map<String, Object> permission = usersPermissionService.getByUsersPermissionAllNew();
		CommonResponse cr = new CommonResponse();
		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
        cr.setData(permission);
        cr.setMsg("操作成功！");
		return cr;
	}
	
	/**
	 * 
	* @Title: selectByPermission 
	* @Description: 根据登录用户获取菜单信息
	* @param httpSession
	* @return    设定文件 
	* @return Map<String,Object>    msg：1 成功 -2菜单获取失败 0登陆出错
	* @throws 
	* @author htt
	 */
	@ResponseBody
	@RequestMapping(value="/selectByPermission")
	public Object selectByPermission(HttpSession httpSession){
		CommonResponse cr = new CommonResponse();
		try {
			Users u = (Users) httpSession.getAttribute("currentUser");
			if (u != null) {
				List<Integer> rids = usersUserRoleService.selectByUserId(u.getId().intValue());
				if(rids != null && !rids.isEmpty()){
					//Map<String, Object> map = new HashMap<>();
					/*UsersMenuModel model = usersPermissionService.getByUsersPermissionRids(rids);
					List<UsersMenuModel> list = new ArrayList<>();
					list.add(model);*/
					//map.put("items", permission);
					Map<String, Object> permission = usersPermissionService.getByUsersPermission(rids,u.getUserName());
					cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
		            cr.setData(permission);
		            cr.setMsg("操作成功！");
				} else {
					cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
	                cr.setData("{}");
	                cr.setMsg("操作失败！");
				}
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }
		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("登录失败！");
			e.printStackTrace();
		}
		return cr;
	}
	
	/**
	 * 获取等路人权限菜单集合 1 成功 -2菜单获取失败 0登陆出错------废弃不用--htt
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectByPermissionOld")
	public Map<String,Object> selectByPermissionOld(HttpSession httpSession){
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
	 * 查询每个角色的用户集合 列表  tianliya
	 */
	@ResponseBody
	@RequestMapping(value="/selectRoleUsers")
	public PageInfo selectRoleUsers(String roleName,String startTime,String endTime,Integer pageNum,Integer pageSize){
		return usersUserRoleService.selectRoleUsers(roleName, startTime, endTime, pageNum, pageSize);
	}



	/**
	 * 添加功能
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
	 * @param
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
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/deleteRole")
	@ResponseBody
	public Map<String,Object> deleteRole(Integer roleId){
		Map<String,Object> map = new HashMap<String,Object>();
		List<UsersUserRole> list = usersUserRoleService.selectByRoleId(roleId);
		if (!list.isEmpty()){
			map.put("msg", -1);
			map.put("explain", "该权限下面有用户无法删除");
			return map;
		}else if (list.size() != 0){
			map.put("msg", -1);
			map.put("explain", "该权限下面有用户无法删除");
			return map;
		}

		int msg = usersRoleService.deleteById(roleId);
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


	/**
	 * 获取某个角色的菜单集合
	 * @param rid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectPidByRid")
	public Map selectPidByRid(Integer rid){
		Map map = new HashMap();
		List pids = usersRolePermissionService.selectPidByRoleId(rid);
		map.put("pids",pids);
		return map;
	}

}
