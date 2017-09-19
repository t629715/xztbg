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
import com.fx.xzt.sys.service.UsersPermissionService;

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

}
