package com.fx.xzt.sys.service;

import java.util.List;

import com.fx.xzt.sys.entity.UsersPermission;
import com.fx.xzt.sys.entity.UsersRole;
import com.fx.xzt.sys.entity.UsersUserRole;
import com.fx.xzt.sys.model.UserInfoModel;
import com.fx.xzt.sys.model.UsersUserRoleModel;
import com.github.pagehelper.PageInfo;

public interface UsersUserRoleService extends IService<UsersUserRole>{
	/**
	 * 添加
	 */
	int insertSelective(List<Integer> rids,List<Integer> uids);
	/**
	 * 先删除再添加
	 * @param rids
	 * @param uid
	 * @return
	 */
	public int insertUpdate(List<Integer> rids,Integer uid);
	/**
	 * 修改
	 */
	int updateByIdSelective(UsersUserRole usersUserRole);
	/**
	 * 删除
	 */
	int deleteById(Integer id);
	/**
	 * 根据 用户和角色删除
	 */
	int deleteByUserRole(UsersUserRole usersUserRole);
	/**
	 * 根据 用户删除角色
	 */
	int deleteByUser(Integer uid);
	/**
	 * 查询 用户角色信息
	 */
	List<Integer> selectByUserId(Integer uid);
	/**
	 * 查询每个用户的角色集合 列表
	 */
	PageInfo<UsersUserRoleModel> selectByUsersRole(Integer pageNum,Integer pageSize);
	/**
	 * 查询每个角色的用户集合 列表
	 */
	PageInfo<UsersUserRoleModel> selectByRoleUsers(String phone,String startTime,String endTime,Integer pageNum,Integer pageSize);

	/**
	 * 角色管理-查询  tianliya 10月23日
	 * @param roleName
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo selectRoleUsers(String roleName,String startTime,String endTime,Integer pageNum,Integer pageSize);
	
	List<UsersUserRole> selectByRoleId(Integer roleId);

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/4/24 13:49
	 * @Description：获取用户的角色
	 * @param roleId
	 * @return
	 */
	List<UsersPermission> getByUserId(Long roleId);


	
}
