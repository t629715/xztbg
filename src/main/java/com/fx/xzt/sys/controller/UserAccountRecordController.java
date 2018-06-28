package com.fx.xzt.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserAccountRecordService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/userAccountRecord")
public class UserAccountRecordController {
	
	@Resource
	UserAccountRecordService userAccountRecordService;
	@Resource
	LogRecordService logRecordService;
	
	/**
	 * 获取消费集合
	 * @return
	 */
	@RequestMapping(value="/selectByAll")
	@ResponseBody
	public PageInfo<UserAccountRecord> selectByAll(HttpServletRequest request, String userName, String startTime, String endTime, Integer pageNum,
			Integer pageSize) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("获取消费集合");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.BZHTJ.getName());
		log.setType(ConstantUtil.logRecordType.DC.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		log.setUserId(users.getId());
		log.setContent("查询成功");
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return userAccountRecordService.getAll(userName, startTime, endTime, pageNum, pageSize);
	}
	/**
	 * 导出excel
	 */
	@RequestMapping(value="/excelAccountRecord")
	@ResponseBody
	public void excelAccountRecord(HttpServletRequest request, HttpServletResponse response,String userName, String startTime, String endTime){
		List<UserAccountRecord> list = userAccountRecordService.getExcelAll(userName, startTime, endTime);
		POIUtils poi = new POIUtils();
		String[] heads = {"账号","人民币","优币","操作","消费时间"};
		String[] columns = {"username","rmbatm","uamt","action","createtime"};
		poi.doExport(request, response, list, "消费记录", "消费记录", heads, columns);
	}
	/**
	 * 奖励发放记录集合
	 * @return
	 */
	@RequestMapping(value="/selectGameAward")
	@ResponseBody
	public PageInfo<UserAccountRecord> selectGameAward(HttpServletRequest request,String userName, String startTime, String endTime, Integer pageNum,
			Integer pageSize) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("奖励发放记录集合");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.BZHTJ.getName());
		log.setType(ConstantUtil.logRecordType.DC.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		log.setUserId(users.getId());
		log.setContent("查询成功");
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return userAccountRecordService.getGameAward(userName, startTime, endTime, pageNum, pageSize);
	}
	/**
	 * 奖励发放记录导出excel
	 */
	@RequestMapping(value="/excelGameAward")
	@ResponseBody
	public void excelGameAward(HttpServletRequest request, HttpServletResponse response,String userName, String startTime, String endTime){
		List<UserAccountRecord> list = userAccountRecordService.getGameAwardExcel(userName, startTime, endTime);
		POIUtils poi = new POIUtils();
		String[] heads = {"账号","人民币","优币","原因","发放时间"};
		String[] columns = {"username","rmbatm","uamt","action","createtime"};
		poi.doExport(request, response, list, "奖励发放记录", "奖励发放记录", heads, columns);
	}
}
