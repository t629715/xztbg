package com.fx.xzt.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UsersPermission;
import com.fx.xzt.sys.mapper.UsersPermissionMapper;
import com.fx.xzt.sys.model.TreeModel;
import com.fx.xzt.sys.model.UsersMenuModel;
import com.fx.xzt.sys.service.UsersPermissionService;
import com.fx.xzt.sys.util.StringUtil;

@Service
public class UsersPermissionServiceImpl extends BaseService<UsersPermission> implements UsersPermissionService{
	
	@Resource
	UsersPermissionMapper usersPermissionMapper;
	
	public int insertSelective(UsersPermission usersPermission) {
		return usersPermissionMapper.insertSelective(usersPermission);
	}

	public int updateByIdSelective(UsersPermission usersPermission) {
		return usersPermissionMapper.updateByIdSelective(usersPermission);
	}

	public int deleteById(Integer id) {
		return usersPermissionMapper.deleteById(id);
	}

	public TreeModel getByRids(List<Integer> rids) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(rids!=null&&rids.size()>0){
			map.put("rids", rids);
		}
		List<UsersPermission> data = usersPermissionMapper.getByRids(map);
		TreeModel tree = new TreeModel();
		tree.setIcon("");
        tree.setLabel("");
        tree.setId(0);
        tree.setPid(-1);
        tree.setText("聚优顾后台");
        tree.setTranslate("聚优顾后台");
        getTree(tree,data);
		return tree;
	}
	/**
	 * 递归 便利树
	 * @param tm
	 * @param data
	 */
	 public void getTree(TreeModel tm, List<UsersPermission> data) {
	        List<TreeModel> list = new ArrayList<TreeModel>();
	        for (UsersPermission p : data) {
	            if (p.getPid().equals(tm.getId())) {
	                TreeModel t = new TreeModel();
	                t.setIcon(p.getIcon());
	                t.setLabel(p.getLabel());
	                t.setSref(p.getSref());
	                t.setId(p.getId());
	                t.setPid(p.getPid());
	                t.setText(p.getText());
	                t.setTranslate(p.getTranslate());
	                t.setType(p.getType());
	                getTree(t, data);
	                list.add(t);
	            }
	        }
	        tm.setSubmenu(list);
	    }

	public TreeModel getByRidsAll() {
		List<UsersPermission> data = usersPermissionMapper.getByRidsAll();
		TreeModel tree = new TreeModel();
		tree.setIcon("");
        tree.setLabel("");
        tree.setId(0);
        tree.setPid(-1);
        tree.setText("聚优顾后台");
        tree.setTranslate("聚优顾后台");
        getTree(tree,data);
		return tree;
	}
	
	/**
	 * 
	* @Title: getMenu 
	* @Description: 子菜单处理
	* @param model
	* @param data    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	public void getMenu(UsersMenuModel model, List<UsersPermission> data) {
        List<UsersMenuModel> list = new ArrayList<UsersMenuModel>();
        for (UsersPermission p : data) {
            if (p.getPid().equals(model.getId())) {
            	UsersMenuModel m = new UsersMenuModel();
            	m.setIcon(p.getIcon());
            	m.setPid(p.getPid());
            	m.setId(p.getId());
            	m.setIndex(p.getSref());
            	m.setTitle(p.getText());
            	getMenu(m, data);
                list.add(m);
            }
        }
        model.setSubs(list);
    }

	/**
	 * 获取用户菜单--全部
	 */
	public UsersMenuModel getByUsersPermissionAll() {
		List<UsersPermission> data = usersPermissionMapper.getByRidsAll();
		UsersMenuModel m = new UsersMenuModel();
    	m.setIcon("");
    	m.setPid(-1);
    	m.setId(0);
    	m.setIndex("0");
    	m.setTitle("象智投后台");
    	getMenu(m, data);
		return m;
	}

	@Override
	public List<Map> getPermissions() {
		Map map = new HashMap();
		map.put("type",1);
		List<Map> first = usersPermissionMapper.getPermissions(map);
		for (Map map1:first){
			Map mapf = new HashMap();
			mapf.put("type",2);
			mapf.put("id",map1.get("id"));
			List<Map> second = usersPermissionMapper.getPermissions(mapf);
			for (Map map2:second){
				map.put("type",3);
				map.put("id",map2.get("id"));
				List<Map> thread = usersPermissionMapper.getPermissions(map2);
				map2.put("thread",thread);
			}
			map1.put("second",second);
		}
		return first;
	}

	/**
	 * 根据用户角色获取菜单
	 */
	public UsersMenuModel getByUsersPermissionRids(List<Integer> rids) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(rids != null && rids.size() > 0){
			map.put("rids", rids);
		}
		List<UsersPermission> data = usersPermissionMapper.getByRids(map);
		UsersMenuModel m = new UsersMenuModel();
    	m.setIcon("");
    	m.setPid(-1);
    	m.setId(0);
    	m.setIndex("0");
    	m.setTitle("象智投后台");
    	getMenu(m, data);
    	return m;
	}
	
	/**
	 * 
	* @Title: handleMenu 
	* @Description: 循环处理二级菜单
	* @param map
	* @param data    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	public void handleMenu(Map<String,Object> map, List<UsersPermission> data) {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (UsersPermission p : data) {
            if (p.getPid().equals(map.get("id"))) {
            	Map<String,Object> perMap = new HashMap<String,Object>();
            	perMap.put("icon", StringUtil.convertNullToEmpty(p.getIcon()));
            	perMap.put("pid", p.getPid());
            	perMap.put("id", p.getId());
            	//perMap.put("index", StringUtil.isNotEmpty(p.getSref()) ? p.getSref() : p.getId() + "");
            	perMap.put("index", p.getSref() != null ? p.getSref() : p.getId() + "");
            	perMap.put("title", StringUtil.convertNullToEmpty(p.getText()));
            	handleMenu(perMap, data);
                list.add(perMap);
            }
        }
        if (list != null && list.size() > 0) {
        	map.put("subs", list);
        }
    }
	
	/**
	 * 
	* @Title: getByUsersPermissionAllNew 
	* @Description: 获取全部菜单
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author htt
	 */
	public Map<String, Object> getByUsersPermissionAllNew() {
		List<UsersPermission> data = usersPermissionMapper.getByRidsAll();
		Map<String,Object> permission = new HashMap<String,Object>();
		permission.put("icon", "");
		permission.put("pid", -1);
		permission.put("id", 0);
		permission.put("index", "0");
		permission.put("title", "象智投后台");
		handleMenu(permission, data);
		return permission;
	}

	/**
	 * 根据用户角色获取菜单
	 */
	public Map<String, Object> getByUsersPermission(List<Integer> rids) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(rids != null && rids.size() > 0){
			map.put("rids", rids);
		}
		List<UsersPermission> data = usersPermissionMapper.getByRids(map);
		Map<String,Object> permission = new HashMap<String,Object>();
		permission.put("icon", "");
		permission.put("pid", -1);
		permission.put("id", 0);
		permission.put("index", "0");
		permission.put("title", "象智投后台");
		handleMenu(permission, data);
		return permission;
	}

}
