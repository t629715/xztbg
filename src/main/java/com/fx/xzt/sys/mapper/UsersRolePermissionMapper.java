package com.fx.xzt.sys.mapper;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UsersRolePermission;

import java.util.List;
import java.util.Map;

@Repository
public interface UsersRolePermissionMapper extends BaseMapper<UsersRolePermission>{
	/**
	 * 添加
	 */
	int insertUsersRolePermission(UsersRolePermission urp);
	/**
	 * 修改
	 */
	int updateByIdSelective(UsersRolePermission urp);
	/**
	 * 删除
	 */
	int deleteById(Integer id);
	/**
	 * 根据 rid 删除
	 */
	int deleteByRid(Integer rid);

	/**
	 * 查询某个角色的菜单集合
	 *
	 */
	List<Integer> selectPidByRoleId(Integer rid);
}
