package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.UsersPermission;
import com.fx.xzt.sys.model.TreeModel;
import com.fx.xzt.sys.model.UsersModel;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UsersPermissionService;
import com.fx.xzt.sys.service.UsersService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: UserController 
* @Description: 用户操作类 
* @author jcwang
* @date 2017年7月28日 下午3:56:08 
*
 */
@Controller
@RequestMapping("/permission")
public class UserPermissionController {
	
	@Resource
	private UsersService userService;
	@Resource
	UsersPermissionService usersPermissionService;

	@Resource
	LogRecordService logRecordService;
	
	/**
	 * 插入新用户
	 * @param userInfo 用户信息类
	 * @return  成功:保存入库
	 *          失败:提示用户保存失败
	 */
	@ResponseBody
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestBody Users userInfo){
		if(userInfo.getStatus()==null){
			userInfo.setStatus("0");
		}
		Integer count = userService.save(userInfo);
		if(count>0){
			return "保存用户信息成功";
		}
		return "保存用户信息失败";
	}
	
	/**
	 * 查询用户信息根据用户id
	 * @param uid  用户ID
	 * @return   成功:提示找到的用户名
	 *           失败:查找失败
	 */
	@ResponseBody
	@RequestMapping(value="/select", method=RequestMethod.GET)
	public String select(@RequestParam Long uid){
		Users userInfo = userService.selectByKey(uid);
		if(userInfo!=null){
			return "您要查找的用户名是:"+userInfo.getUserName();
		}
		return "查找用户失败";
	}

	@ResponseBody
	@RequestMapping(value="/selectPermissions", method=RequestMethod.GET)
	public CommonResponse selectPermissions(){
		CommonResponse response = new CommonResponse();
		TreeModel list = usersPermissionService.getByRidsAll();
		if (list != null){
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			response.setData(list);
			response.setMsg("1");
		}else {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			response.setData(list);
			response.setMsg("0");
		}

		return response;
	}
	
	/**
	 * 根据用户ID删除用户
	 * @param uid  用户ID
	 * @return   成功:成功删除用户
	 *           失败:删除失败
	 */
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public String delete(HttpServletRequest request,@RequestParam Long uid) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("删除用户");
		log.setContent("删除失败");
		log.setModuleName("账号管理");
		log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("currentUser");
		Integer count = userService.delete(uid);
		if(count>0){
			log.setContent("删除成功");
			log.setUserId(users.getId());
			logRecordService.add(log);
			AuditLog.info(log.toString());
			return "删除用户信息成功";
		}
		log.setContent("删除失败");
		log.setUserId(users.getId());
		logRecordService.add(log);
		return "删除用户信息失败";
	}
	/**
	 * 添加用户 手机号 和 密码必须传递
	 */
	@RequestMapping(value="/insertUser")
	@ResponseBody
	public Map<String,Object> insertUser(HttpServletRequest request,@RequestParam(value="rids", required=false)List<Integer> rids) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("添加后台用户");
		log.setContent("添加失败");
		log.setModuleName("账号管理");
		log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("currentUser");
		int msg = userService.insertUsers(users, rids);
		if (msg > 0){
			log.setUserId(users.getId());
			log.setContent("添加后台用户成功");
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public Map<String,Object> deleteUser(HttpServletRequest request,Long id) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("删除后台用户");
		log.setContent("删除失败");
		log.setModuleName("账号管理");
		log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("currentUser");
		int msg = userService.deleteById(id);
		if (msg > 0){
			log.setUserId(users.getId());
			log.setContent("删除后台用户成功");
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		map.put("msg", msg);
		return map;
	}
	/**
	 * 更新用户 id 手机号 和 密码必须传递
	 */
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public Map<String,Object> updateUser(HttpServletRequest request,Users user,@RequestParam(value="rids", required=false)List<Integer> rids) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("修改后台用户");
		log.setContent("修改失败");
		log.setModuleName("账号管理");
		log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("currentUser");
		int msg = userService.updateByIdSelective(user, rids);
		if (msg>0){
			log.setUserId(users.getId());
			log.setContent("修改后台用户成功");
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		map.put("msg", msg);
		return map;
	}
	/**
	 * 用户信息列表
	 */
	@RequestMapping(value="/selectByUsers")
	@ResponseBody
	public PageInfo<UsersModel> selectByUsers(String phone, String startTime, String endTime, Integer pageNum,
			Integer pageSize){
		return userService.selectByUsersModel(phone, startTime, endTime, pageNum, pageSize);
	}

	/**
	 * 获取代理商列表
	 * @return
	 */
	@RequestMapping(value="/selectByAgentMessage")
	@ResponseBody
	public Object selectByAgentMessage(HttpServletRequest request){
		CommonResponse cr = new CommonResponse();
        try {
			HttpSession session = request.getSession();
			Users users = (Users) session.getAttribute("currentUser");
        	List<Map<String, Object>> list = userService.selectByAgentMessage(users.getPid());
        	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
            cr.setData(list);
            cr.setMsg("操作成功！");
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
            // e.printStackTrace();
        }
        return cr;
	}

	/**
	 * 根据代理商获取经纪人列表
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/selectByBrokerMessage")
	@ResponseBody
	public Object selectByBrokerMessage(@RequestParam Long pid){
		CommonResponse cr = new CommonResponse();
        try {
        	List<Map<String, Object>> list = userService.selectByBrokerMessage(pid);
        	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
            cr.setData(list);
            cr.setMsg("操作成功！");
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
            // e.printStackTrace();
        }
        return cr;
	}
}
