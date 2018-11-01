package com.fx.xzt.sys.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public TreeModel getPermissions() {

		List<UsersPermission> data = usersPermissionMapper.getPermissions();
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
            	perMap.put("index", (p.getSref() != null && p.getSref() != "") ? p.getSref() : p.getId() + "");
            	perMap.put("sref", p.getSref() != null ? p.getSref() : p.getId() + "");
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
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/12 12:25
	 * @Description：处理二级菜单
	 * @param map
	 * @param data
	 */
	public void handleMenu1(Map<String,Object> map, List<UsersPermission> data) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for (UsersPermission p : data) {
			if (p.getPid().equals(map.get("id"))) {
				Map<String,Object> perMap = new HashMap<String,Object>();
				perMap.put("icon", StringUtil.convertNullToEmpty(p.getIcon()));
				perMap.put("pid", p.getPid());
				perMap.put("id", p.getId());
				perMap.put("index", StringUtil.isNotEmpty(p.getSref()) ? p.getSref() : p.getId() + "");
				perMap.put("sref", p.getSref() != null ? p.getSref() :  "");
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
	@Override
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
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/12 12:27
	 * @Description：获取全部菜单
	 * @return
	 */
	@Override
	public Map<String, Object> getByUsersPermissionAllNew1() {
		List<UsersPermission> data = usersPermissionMapper.getByRidsAll();
		Map<String,Object> permission = new HashMap<String,Object>();
		permission.put("icon", "");
		permission.put("pid", -1);
		permission.put("id", 0);
		permission.put("title", "象智投后台");
		handleMenu1(permission, data);
		return permission;
	}

	/**
	 * 根据用户角色获取菜单
	 */
	@Override
	public Map<String, Object> getByUsersPermission(List<Integer> rids, String userName) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(rids != null && rids.size() > 0){
			map.put("rids", rids);
		}
		List<UsersPermission> data = usersPermissionMapper.getByRids(map);
		/*if (!"admin".equals(userName)){
			Iterator<UsersPermission> it = data.iterator();
			while(it.hasNext()){
				UsersPermission x = it.next();
				if(x.getText().equals("金权交割")){
					it.remove();
				}
			}
		}*/
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
	 * 新增
	 */
	@Override
	@Transactional
	public int insert(String text, String icon, String label, String translate,
			String pid, String type, String sref) {
		int flag = 0;
		if (StringUtil.isNotEmpty(text)) {
			UsersPermission permission = new UsersPermission();
			permission.setText(text);
			permission.setIcon(icon);
			permission.setLabel(label);
			permission.setTranslate(translate);
			if (StringUtil.isNotEmpty(pid)) {
				permission.setPid(Integer.valueOf(pid));
			}
			if (StringUtil.isNotEmpty(type)) {
				permission.setType(Integer.valueOf(type));
			}
			if ("1".equals(type)){

			}else {
				permission.setSref(sref);
			}
			flag = usersPermissionMapper.insertSelective(permission);
		}
		return flag;
	}

	/**
	 * 修改
	 */
	@Override
	@Transactional
	public int update(String text, String icon, String label, String translate,
			String pid, String type, String sref, String id) {
		int flag = 0;
		if (StringUtil.isNotEmpty(id)) {
			UsersPermission permission = new UsersPermission();
			permission.setId(Integer.valueOf(id));
			permission.setText(text);
			permission.setIcon(icon);
			permission.setLabel(label);
			permission.setTranslate(translate);
			if (StringUtil.isNotEmpty(pid)) {
				permission.setPid(Integer.valueOf(pid));
			}
			if (StringUtil.isNotEmpty(type)) {
				permission.setType(Integer.valueOf(type));
			}
			if (pid != "0"){

			}else {
				permission.setSref(sref);
			}
			flag = usersPermissionMapper.updateByIdSelective(permission);
		}
		return flag;
	}
	
	/**
	 * 
	* @Title: deleteById 
	* @Description: 删除
	* @param id
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	@Override
	@Transactional
	public int deleteById(String id) {
		int flag = 0;
		if (StringUtil.isNotEmpty(id)) {
			flag = usersPermissionMapper.deleteById(Integer.valueOf(id));
			if (flag > 0) {
				usersPermissionMapper.deleteByPid(Integer.valueOf(id));
			}
		}
		return flag;
	}
}
