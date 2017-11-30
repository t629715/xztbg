package com.fx.xzt.sys.service.impl;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.fx.xzt.sys.entity.*;
import com.fx.xzt.sys.mapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.redis.RedisService;
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
	private UserInfoMapper userInfoMapper;
	@Resource
	private UsersMapper usersMapper;
	
	@Resource
	private RedisService redisService;
	
	@Resource
	private UsersUserRoleService usersUserRoleService;
	@Resource
	private ConfigParamMapper configParamMapper;
	@Resource
	UsersRolePermissionMapper usersRolePermissionMapper;

	@Resource

	IncomeSharingConfMapper incomeSharingConfMapper;
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
		String phone = users.getUserName();
		users.setPhone(users.getUserName());
		Users selectUser = usersMapper.selectByPhone(phone);
		int msg = 0;
		if(selectUser==null){
			List<Integer> uids = new ArrayList<Integer>();
			if (users.getPassword() == ""){
				users.setPassword("123456");
			}
			users.setPassword(MD5Utils.encrypt(users.getPassword()));
			users.setCreateTime(new Date());
			users.setUpdateTime(new Date());
			users.setStatus("1");
			msg=usersMapper.insertUsers(users);
			if(msg>0&&rids!=null&&!rids.isEmpty()){
				Users selectUser1 = usersMapper.selectByPhone(phone);
				IncomeSharingConf incomeSharingConf = new IncomeSharingConf();
				incomeSharingConf.setGoldPercent(0d);
				incomeSharingConf.setGoldRightPercent(0d);
				incomeSharingConf.setAgentId(selectUser1.getId());
				if (selectUser1.getPid() == 1){
					incomeSharingConfMapper.insertIncomeSharingConf(incomeSharingConf);
				}

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
			if (users.getPassword() != null && users.getPassword() != ""){
				users.setPassword(MD5Utils.encrypt(users.getPassword()));
			}
			else {
				users.setPassword("");
			}
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
		int i = 0;

		try{
			List<UserInfo> userInfos = userInfoMapper.selectUserInfoByAgentId(id);
			Users users = usersMapper.selectById(id);
			if (users.getPid() == 1){//如果删除的用户是代理商
				//删除代理商的分成信息
				incomeSharingConfMapper.deleteByAgentId(id);
				List<ConfigParam> configParams = configParamMapper.selectConfigParamByKey("BROKER_ID");
				if (userInfos != null && userInfos.size() != 0){
					for (UserInfo userInfo1:userInfos){
						//设置客户代理商id为2
						userInfo1.setAgentId(configParams.get(0).getParamValue());
						//设置客户经纪人为null
						userInfo1.setBrokerId(null);
						//修改客户信息
						userInfoMapper.editUserInfo(userInfo1);
					}
				}
			}
			else {//如果删除的用户是经纪人
				if (userInfos != null && userInfos.size() != 0){
					for (UserInfo userInfo1:userInfos){
						//客户的经纪人设为null
						userInfo1.setBrokerId(null);
						//修改客户信息
						userInfoMapper.editUserInfo(userInfo1);
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		usersMapper.deleteByPid(id);//删除经纪人
		i = usersMapper.deleteById(id);
		if (i>0){
			UsersUserRole usersUserRole = new UsersUserRole();
			//获取设置用户角色实体类对象
			usersUserRole.setUid(id.intValue());
			//删除用户-角色关联表对应的数据
			usersUserRoleService.deleteByUserRole(usersUserRole);
		}
		return i;
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
	
	 /** 小象视角-查询
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

	/**
	 * 商户管理-运营商视角
	 * @param pid
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<Map<String, Object>> sightOfOperator(Long pid, String startTime, String endTime, Integer pageNum, Integer pageSize) {
		Map map = new HashMap();
		map.put("pid",pid);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		PageHelper.startPage(pageNum,pageSize);
		List list  = usersMapper.getSightOfOperator(map);
		PageInfo pageInfo = new PageInfo(list);
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> execelSightOfElephant(Long id , Long  pid,
														 String startTime, String endTime
														 ) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("pid",pid);
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		List<Map<String,Object>> list  = usersMapper.getByAgentNameAndType(map);
		for (Map m:list){
			if ("1".equals(m.get("type"))){
				m.put("type","代理商");
			}else{
				m.put("type","经纪人");
			}
		}
		return list;
	}

	@Override
	public Users getUser(Long id) {
		return usersMapper.selectById(id);
	}


}
