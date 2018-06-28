package com.fx.xzt.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.entity.UserLoginInfo;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserLoginInfoService;
import com.fx.xzt.sys.service.UserLoginService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.github.pagehelper.PageInfo;

/**
 * 
* @Title: UserLoginInfoController.java 
* @Package com.fx.xzt.sys.controller
* @Description: TODO
* @author SYan  
* @date 2017年8月10日 下午3:59:42 
* @version V1.0
 */
@Controller
@RequestMapping("/userLoginInfo")
public class UserLoginInfoController {
	
	@Resource
	UserLoginInfoService userLoginInfoService;
	
	@Resource
	UserLoginService userLoginService;
	@Resource
	LogRecordService logRecordService;
	
	/**
	 * 获取登陆信息
	 * @param userName
	 * @param loginFrom
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/selectByAll")
	@ResponseBody
	public PageInfo<UserLoginInfo> selectByAll(HttpServletRequest request,String userName,String loginFrom,String startTime,String endTime,Integer pageNum,Integer pageSize) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("修改账号状态");
		log.setContent("查询成功");
		log.setModuleName(ConstantUtil.logRecordModule.KHFX.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		AuditLog.info(log.toString());
		return userLoginInfoService.getfindAll(userName,loginFrom,startTime,endTime,pageNum,pageSize);
	}
	
	/**
	 * 修改账号状态  启用/禁用
	 */
	@RequestMapping("/updateLoginStatus")
	@ResponseBody
	public Map<String,Object> updateLoginStatus(HttpServletRequest request,UserLogin userLogin) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("修改账号状态");
		log.setContent("修改失败");
		log.setModuleName(ConstantUtil.logRecordModule.KHFX.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userLoginService.updateByIdSelective(userLogin);
		if (msg >0){
			log.setUserId(userLogin.getUserid());
			log.setContent("修改成功");
		}
		map.put("msg", msg);
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return map;
	}
}
