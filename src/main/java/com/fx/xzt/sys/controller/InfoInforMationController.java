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

import com.fx.xzt.sys.entity.InfoInformation;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InfoInforMationService;
import com.github.pagehelper.PageInfo;

/**\
 * 
* @Title: InfoInforMationController.java 
* @Package com.fx.xzt.sys.controller
* @Description: TODO
* @author tianliya
* @date 2017年10月17日
* @version V1.0
 */
@Controller
@RequestMapping(value="/infoInforMation")
public class InfoInforMationController {
	@Resource
	InfoInforMationService infoInforMationService;
	@Resource
	LogRecordService logRecordService;
	/**
	 * 获取信息发布 集合
	 * @param title 标题
	 * @param startTime 时间
	 * @param endTime 
	 * @param state 状态
	 * @param operator 操作人
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/selectByInfoInforMation",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse selectByInfoInforMation(HttpServletRequest request, String title, String startTime, String endTime, Integer state, String operator, Integer pageNum, Integer pageSize) throws ParseException {
		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("查询资讯信息");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.ZXGL.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				PageInfo<Map<String, Object>> pageInfo = infoInforMationService.getByAll(title, startTime, endTime,
						state,operator, pageNum, pageSize);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(pageInfo);
				response.setMsg("操作成功！");
				log.setUserId(users.getId());
				log.setContent("查询成功");
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
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}
	/**
	 * 接口局部完成
	 * @param infoInforMation
	 * @return
	 */
	@RequestMapping(value="/posted",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse posted(HttpServletRequest request, InfoInformation infoInforMation) throws ParseException {
		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("发布资讯");
		log.setContent("发布失败");
		log.setModuleName(ConstantUtil.logRecordModule.ZXGL.getName());
		log.setType(ConstantUtil.logRecordType.XZ.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				infoInforMation.setOperator(users.getPhone()+"");
				int i = infoInforMationService.posted(infoInforMation);
				if (i != 0){
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(i);
					response.setMsg("操作成功！");
					log.setUserId(users.getId());
					log.setContent("发布成功");
				}else {
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(i);
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
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}
	/**
	 * 查看
	 */
	@RequestMapping(value="/selectBySerialNo",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse selectBySerialNo(HttpServletRequest request, Long infoId) throws ParseException {
		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("查看一条资讯");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.ZXGL.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				Map<String, Object> pageInfo = infoInforMationService.getById(infoId);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(pageInfo);
				response.setMsg("操作成功！");
				log.setUserId(users.getId());
				log.setContent("查询成功");
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
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit",method= RequestMethod.POST)
	@ResponseBody
	public CommonResponse edit(HttpServletRequest request, InfoInformation infoInforMation) throws ParseException {
		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("修改资讯信息");
		log.setContent("修改失败");
		log.setModuleName(ConstantUtil.logRecordModule.ZXGL.getName());
		log.setType(ConstantUtil.logRecordType.XG.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				infoInforMation.setOperator(users.getUserName());
				int i = infoInforMationService.edit(infoInforMation);
				if (i != 0){
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(i);
					response.setMsg("操作成功！");
					log.setUserId(users.getId());
					log.setContent("修改成功");
				}else {
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(i);
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
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse deleteById(HttpServletRequest request, Long infoId) throws ParseException {

		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("删除资讯信息");
		log.setContent("删除失败");
		log.setModuleName(ConstantUtil.logRecordModule.ZXGL.getName());
		log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				int i = infoInforMationService.deleteById(infoId);
				if (i != 0){
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(i);
					response.setMsg("操作成功！");
					log.setUserId(users.getId());
					log.setContent("删除成功");
				}else {
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(i);
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
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}
	/**
	 * 置顶/取消
	 * 传递 id 和 置顶状态
	 */
	/*@RequestMapping(value="/editTopState")
	@ResponseBody
	public Map<String,Object> editTopState(InfoInforMation infoInforMation){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoInforMationService.editTopState(infoInforMation);
		map.put("msg", msg);
		return map;
	}*/


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
		log.setTitle("查询资讯发布人");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				List list = infoInforMationService.getOperators();
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
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}
}
