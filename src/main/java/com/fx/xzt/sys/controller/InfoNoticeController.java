package com.fx.xzt.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.InfoNotice;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InfoNoticeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/infoNotice")
public class InfoNoticeController {
	@Resource
	InfoNoticeService infoNoticeService;
	@Resource
	LogRecordService logRecordService;
	
	/**
	 * 添加
	 * @param infoNotice
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(InfoNotice infoNotice,HttpSession httpSession,HttpServletRequest request) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("发布公告");
		log.setContent("发布失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGGL.getName());
		log.setType(ConstantUtil.logRecordType.XZ.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		Users u=(Users) httpSession.getAttribute("currentUser");
		if (u != null){
			infoNotice.setOperator(u.getUserName()+"");
			int msg = infoNoticeService.add(infoNotice);
			log.setUserId(u.getId());
			log.setContent("查询成功");
			map.put("msg", msg);
		}else{
			map.put("msg",-1);
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return map;
	}
	
	/**
	 * 编辑
	 * @param infoNotice
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(InfoNotice infoNotice,HttpSession httpSession,HttpServletRequest request) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		Users u=(Users) httpSession.getAttribute("currentUser");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("编辑公告");
		log.setContent("编辑失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGGL.getName());
		log.setType(ConstantUtil.logRecordType.XG.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		if (u != null){
			infoNotice.setOperator(u.getUserName()+"");
			int msg = infoNoticeService.edit(infoNotice);
			log.setUserId(u.getId());
			log.setContent("编辑成功");
			map.put("msg", msg);
		}else{
			map.put("msg",-1);
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return map;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,Object> delete(HttpSession session,Long serialNo,HttpServletRequest request) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("删除公告");
		log.setContent("删除失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGGL.getName());
		log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		Users users = (Users) session.getAttribute("currentUser");
		if (users != null){
			int msg = infoNoticeService.deleteById(serialNo);
			log.setUserId(users.getId());
			log.setContent("删除成功");
			map.put("msg", msg);
		}else{
			map.put("msg",-1);
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return map;
	}
	
	/**
	 * 列表
	 * @param title
	 * @param startTime
	 * @param endTime
	 * @param operator
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/seelctAll")
	@ResponseBody
	public PageInfo<InfoNotice> seelctAll(HttpServletRequest request,HttpSession session,String title, String startTime, String endTime, String operator,
			Integer pageNum, Integer pageSize) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("查询公告");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGGL.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		Users users = (Users) session.getAttribute("currentUser");
		PageInfo pageInfo = new PageInfo();
		if (users != null){
			log.setUserId(users.getId());
			log.setContent("查询成功");
			pageInfo = infoNoticeService.getInfoNoticeAll(title, startTime, endTime, operator, pageNum, pageSize);
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return pageInfo;
	}
	/**
	 * 
	 */
	@RequestMapping(value="/upDown")
	@ResponseBody
	public Map<String,Object> upDown(HttpServletRequest request,Long upSerialNo, Long downSerialNo) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("查询公告");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGGL.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("currentUser");
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			int msg = infoNoticeService.upDown(upSerialNo, downSerialNo);
			map.put("msg", msg);
			log.setContent("查询成功");
			log.setUserId(users.getId());
		}catch (Exception e){
			e.printStackTrace();
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return map;
	}
	
	@RequestMapping(value="/selectAll")
	@ResponseBody
	public InfoNotice selectBySerialNo(HttpServletRequest request,Long serialNo) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("查询公告");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGGL.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("currentUser");
		log.setUserId(users.getId());
		log.setContent("查询成功");
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return infoNoticeService.getBySerialNo(serialNo);
	}

	/**
	 * 获取发布人
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOperators")
	@ResponseBody
	public CommonResponse getOperators(HttpServletRequest request) throws ParseException {
		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("查询发布人信息");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				List list = infoNoticeService.getOperators();
				if (list != null && list.size() != 0){
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(list);
					response.setMsg("操作成功！");
					log.setUserId(users.getId());
					log.setContent("查询成功");
				}else {
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(list);
					response.setMsg("操作失败！");
				}

			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败！");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败！");
			throw e;
		}
		AuditLog.info(log.toString());
		return response;
	}
}
