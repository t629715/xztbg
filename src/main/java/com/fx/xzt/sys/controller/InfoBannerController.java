package com.fx.xzt.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.InfoBanner;
import com.fx.xzt.sys.service.InfoBannerService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/infoBanner")
public class InfoBannerController {
	
	@Resource
	InfoBannerService infoBannerService;
	@Resource
	LogRecordService logRecordService;

	private static final Logger logger = LoggerFactory.getLogger(InfoBannerController.class);

	/**
	 * 添加
	 * @param infoBanner
	 * @return
	 */
	@RequestMapping(value="/insertBanner",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse insertBanner(HttpServletRequest request, InfoBanner infoBanner) throws ParseException {

		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("添加Banner");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGWGL.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				infoBanner.setOperator(users.getUserName());
				int msg = infoBannerService.add(infoBanner);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(msg);
				response.setMsg("操作成功！");
				log.setUserId(users.getId());
				log.setContent("添加成功");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}

	/**
	 * 编辑
	 * @param infoBanner
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse edit(HttpServletRequest request, InfoBanner infoBanner) throws ParseException {

		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("编辑图片");
		log.setContent("编辑失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGWGL.getName());
		log.setType(ConstantUtil.logRecordType.XG.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				infoBanner.setOperator(users.getUserName());
				int msg = infoBannerService.edit(infoBanner);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(msg);
				response.setMsg("操作成功！");
				log.setUserId(users.getId());
				log.setContent("编辑成功");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}

	/**
	 * 删除
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse delete(HttpServletRequest request, Long serialNo) throws ParseException {
		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("删除图片");
		log.setContent("删除失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGWGL.getName());
		log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				int msg = infoBannerService.deleteById(serialNo);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(msg);
				response.setMsg("操作成功！");
				log.setUserId(users.getId());
				log.setContent("删除成功");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}

	/**
	 * 修改图片显示的顺序
	 * @param downSortNo 交换的上一条
	 * @param downSortNo 交换的另一条
	 * @return
	 */
	@RequestMapping(value="/upDown",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse upDown(HttpServletRequest request, Integer upSortNo,Integer downSortNo) throws ParseException {

		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("修改图片顺序");
		log.setContent("修改失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGWGL.getName());
		log.setType(ConstantUtil.logRecordType.XG.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				int msg = infoBannerService.up(upSortNo, downSortNo);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(msg);
				response.setMsg("操作成功！");
				log.setUserId(users.getId());
				log.setContent("修改成功");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}
	/**
	 * 根据page查询列表
	 */
	@RequestMapping(value="/selectByPageAll",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse selectByPageAll(HttpServletRequest request, Integer page,Integer pageNum,Integer pageSize) throws ParseException {
		CommonResponse response = new CommonResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//操作日志
		LogRecord log = new LogRecord();
		log.setTitle("查询Banner");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.GGWGL.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				PageInfo<InfoBanner> pageInfo = infoBannerService.getByPageAll(page, pageNum, pageSize);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(pageInfo);
				response.setMsg("操作成功！");
				log.setUserId(users.getId());
				log.setContent("查询成功");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return response;
	}

	/**
	 * 获取广告图片  tianliya
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/getAdPic1",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse getAdPic1(HttpServletRequest request,Short page){
		CommonResponse commonResponse = new CommonResponse();
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		if (users != null){
			commonResponse.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			commonResponse.setData(infoBannerService.getAdPic(page,8));
		}else {
			commonResponse.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
			commonResponse.setData("");
		}
		return commonResponse;
	}
	@RequestMapping(value="/getAdPic2",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse getAdPic2(HttpServletRequest request, Short page){
		CommonResponse commonResponse = new CommonResponse();
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		if (users != null){
			commonResponse.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			commonResponse.setData(infoBannerService.getAdPic(page,1));
		}else {
			commonResponse.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
			commonResponse.setData("");
		}
		return commonResponse;
	}

	@RequestMapping(value="/getOneBySerialNo",method=RequestMethod.POST)
	@ResponseBody
	public InfoBanner getOneBySerialNo(Long serialNo){
		return infoBannerService.selectById(serialNo);
	}
}
