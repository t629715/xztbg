package com.fx.xzt.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.redis.RedisService;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.mapper.UsersMapper;
import com.fx.xzt.sys.model.UsersModel;
import com.fx.xzt.sys.service.UsersService;
import com.fx.xzt.sys.service.UsersUserRoleService;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.DateUtils;
import com.fx.xzt.util.JsonUtils;
import com.fx.xzt.util.MD5Utils;
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
			users.setUserName(phone);
			users.setCreateTime(new Date());
			users.setUpdateTime(new Date());
			users.setStatus("1");
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
		Map map = new HashMap();
		if (startTime != null && startTime !=""){
			Date start = null;
			start = DateUtil.convertTimeMillisToDate(Long.valueOf(startTime));
			String st = DateUtils.formatDateByMidLine(start);
			map.put("startTime",st);
		}
		if (endTime != null && endTime !=""){
			Date end = null;
			end = DateUtil.convertTimeMillisToDate(Long.valueOf(endTime));
			String st = DateUtils.formatDateByMidLine(end);
			map.put("endTime",st);
		}

		map.put("phone", phone);
		PageHelper.startPage(pageNum,pageSize);
		List<UsersModel> list = usersMapper.selectByUsersModel(map);
		PageInfo<UsersModel> pageInfo = new PageInfo<UsersModel>(list);

		if (list.size() == 0){
			pageInfo.setPageNum(1);
			return pageInfo;
		}
		else {
			return  pageInfo;
		}
	}

	/**
	 *  获取代理商列表
	 * @return
	 */
	public List<Map<String,Object>> selectByAgentMessage(){
		return usersMapper.selectByAgentMessage();
	}

	/**
	 * 获取经纪人列表
	 * @param pid
	 * @return
	 */
	public List<Map<String,Object>> selectByBrokerMessage(Long pid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pid",pid);
		return usersMapper.selectByBrokerMessage(map);
	}

	/**
	 * 运营商视角 - 查询
	 * @param pid
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
 	* @Author:  tianliya
 	* @Description:
 	* @Date:16:16 2017/10/20
	*/
	@Override
	public PageInfo<Map<String, Object>> sightOfCarrieroperator(Long pid, String startTime, String endTime,
																Integer pageNum,
																Integer pageSize) {

		Map map = new HashMap();
		if (startTime != null && startTime !=""){
			Date start = null;
			start = DateUtil.convertTimeMillisToDate(Long.valueOf(startTime));
			String st = DateUtils.formatDateByMidLine(start);
			map.put("startTime",st);
		}
		if (endTime != null && endTime !=""){
			Date end = null;
			end = DateUtil.convertTimeMillisToDate(Long.valueOf(startTime));
			String st = DateUtils.formatDateByMidLine(end);
			map.put("endTime",st);
		}
		Users users = usersMapper.selectById(pid);
		Map mo = usersMapper.getOneByUserId(pid);
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(mo);
		list.addAll(1,usersMapper.selectByBrokerMessage(map));
		PageHelper.startPage(pageNum,pageSize);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		if (list.size()==0){
			pageInfo.setPageNum(1);
		}
		return pageInfo;
	}

	/**
	 * @param usersInfo
	 * @return
	 * @Author:  tianliya
	 * @Description: 运营商视角-新建经纪人
	 * @Date:11:18 2017/10/21
	*/
	@Transactional
	public int insertAgent(Users usersInfo) {
		int msg = usersMapper.insertUsers(usersInfo);
		return msg;
	}

	/**
	 * 获取渠道商数据
	 */
	public List<Map<String, Object>> selectByChannelMessage() {
		return usersMapper.selectByChannelMessage();
	}
	
	 /* 小象视角-查询
	 * @param
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Override
	public PageInfo<Map<String, Object>> sightOfElephant(Long id , Long  pid,
														 String startTime, String endTime,
														 Integer pageNum, Integer pageSize) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("pid",pid);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		PageHelper.startPage(pageNum,pageSize);
		List list  = usersMapper.getByAgentNameAndType(map);
		PageInfo pageInfo = new PageInfo(list);
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> excelSightOfElephant(Long id, Long pid, String startTime, String endTime) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("pid",pid);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		List list  = usersMapper.getByAgentNameAndType(map);
		return list;
	}


}
