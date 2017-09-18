package com.fx.jyg.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.jyg.redis.RedisService;
import com.fx.jyg.sys.entity.Users;
import com.fx.jyg.sys.mapper.UsersMapper;
import com.fx.jyg.sys.model.UserInfoModel;
import com.fx.jyg.sys.model.UsersModel;
import com.fx.jyg.sys.service.UsersService;
import com.fx.jyg.sys.service.UsersUserRoleService;
import com.fx.jyg.util.JsonUtils;
import com.fx.jyg.util.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: UserServiceImpl 
* @Description: 用户操作实现类 
* @author jcwang
* @date 2017年7月31日 下午4:01:32 
*
 */
@Service
public class UsersImpl extends BaseService<Users> implements UsersService {
	
	@Resource
	private UsersMapper usersMapper;
	
	@Resource
	private RedisService redisService;
	
	@Resource
	private UsersUserRoleService usersUserRoleService;
	
	public static final String USER_INFO = "uid";

	public Users getUserInfo(Long uid) {
		String json = redisService.get(USER_INFO+uid);
		if(json==null){
			Users userInfo = selectByKey(uid);
			String userinfostr = JsonUtils.toJSONString(userInfo);
			if(userInfo!=null){
				redisService.put(USER_INFO+uid, userinfostr, 1, TimeUnit.HOURS);
			}
			return userInfo;
		}

		return JsonUtils.toBean(json, Users.class);
	}

	public List<Users> listUserInfo() {
		return usersMapper.selectAll();
	}

	public Users getUserInfoNyName(String currentUserName) {
		return usersMapper.getUserInfoNyName(currentUserName);
	}

	public Users getUserInfo(String username, String password) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", username);
		map.put("password", password);
		return usersMapper.getUserInfo(map);
	}

	public Users getUserInfoNyPhone(String phone, String password) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("phone", phone);
		map.put("password", password);
		return usersMapper.getUserInfoNyPhone(map);
	}
	
	@Transactional
	public int insertUsers(Users users,List<Integer> rids) {
		String phone = users.getPhone();
		Users selectUser = usersMapper.selectByPhone(phone);
		int msg = 0;
		if(selectUser==null){
			List<Integer> uids = new ArrayList<Integer>();
			users.setPassword(MD5Utils.encrypt(users.getPassword()));
			users.setCreateTime(new Date());
			users.setUpdateTime(new Date());
			users.setStatus("0");
			msg=usersMapper.insertUsers(users);
			if(msg>0&&rids!=null&&!rids.isEmpty()){
				Integer uid = users.getId().intValue();
				uids.add(uid);
				msg = usersUserRoleService.insertSelective(rids, uids);
			}
		}else{
			msg = -1;
		}
		return msg;
	}
	
	@Transactional
	public int updateByIdSelective(Users users,List<Integer> rids) {
		String phone = users.getPhone();
		Users selectUserId = usersMapper.selectById(users.getId());
		Users selectUser = usersMapper.selectByPhone(phone);
		int msg = 0;
		if(selectUser==null||users.getPhone().equals(selectUserId.getPhone())){
			users.setPassword(MD5Utils.encrypt(users.getPassword()));
			users.setUpdateTime(new Date());
			msg=usersMapper.updateByIdSelective(users);
			if(msg>0&&rids!=null&&!rids.isEmpty()){
				Integer uid = users.getId().intValue();
				msg = usersUserRoleService.insertUpdate(rids, uid);
			}
		}else{
			msg = -1;
		}
		return msg;
	}
	
	@Transactional
	public int deleteById(Long id) {
		return usersMapper.deleteById(id);
	}

	public PageInfo<UsersModel> selectByUsersModel(String phone, String startTime, String endTime, Integer pageNum,
			Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum,pageSize);
		List<UsersModel> list = usersMapper.selectByUsersModel(map);
		return new PageInfo<UsersModel>(list);
	}

}
