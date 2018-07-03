package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserAccountRecordService;
import com.fx.xzt.sys.service.UserGoldAccountService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userGoldAccountRecord")
public class UserGoldAccountController {
	
	@Resource
	UserGoldAccountService userGoldAccountService;
	@Resource
	LogRecordService logRecordService;
	
	/**
	 * 获取消费集合
	 * @return
	 */
	@RequestMapping(value="/updateUserGoldAccount")
	@ResponseBody
	public CommonResponse updateUserGoldAccount(HttpServletRequest request, Double gram, Long userId,Short type,String description,String operatorName) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("黄金充值");
		log.setContent("充值失败");
		log.setModuleName(ConstantUtil.logRecordModule.RGHJCZ.getName());
		log.setType(ConstantUtil.logRecordType.CZ.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		CommonResponse commonResponse = userGoldAccountService.updateUserGoldAccount(users,gram,userId,type,description,operatorName);
		if(commonResponse.getCode() == 1001){
			log.setContent("查询成功");
		}
		log.setUserId(users.getId());
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return commonResponse;
	}
}
