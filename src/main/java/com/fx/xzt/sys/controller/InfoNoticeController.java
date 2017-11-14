package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
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
	
	/**
	 * 添加
	 * @param infoNotice
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(InfoNotice infoNotice,HttpSession httpSession){
		Map<String,Object> map = new HashMap<String,Object>();
		Users u=(Users) httpSession.getAttribute("currentUser");
		if (u != null){
			infoNotice.setOperator(u.getUserName()+"");
			int msg = infoNoticeService.add(infoNotice);
			map.put("msg", msg);
		}else{
			map.put("msg",-1);
		}

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
	public Map<String,Object> edit(InfoNotice infoNotice,HttpSession httpSession){
		Map<String,Object> map = new HashMap<String,Object>();
		Users u=(Users) httpSession.getAttribute("currentUser");
		if (u != null){
			infoNotice.setOperator(u.getUserName()+"");
			int msg = infoNoticeService.edit(infoNotice);
			map.put("msg", msg);
		}else{
			map.put("msg",-1);
		}

		return map;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,Object> delete(HttpSession session,Long serialNo){
		Map<String,Object> map = new HashMap<String,Object>();
		Users users = (Users) session.getAttribute("currentUser");
		if (users != null){
			int msg = infoNoticeService.deleteById(serialNo);
			map.put("msg", msg);
		}else{
			map.put("msg",-1);
		}
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
	public PageInfo<InfoNotice> seelctAll(HttpSession session,String title, String startTime, String endTime, String operator,
			Integer pageNum, Integer pageSize){
		Users users = (Users) session.getAttribute("currentUser");
		PageInfo pageInfo = new PageInfo();
		if (users != null){
			pageInfo = infoNoticeService.getInfoNoticeAll(title, startTime, endTime, operator, pageNum, pageSize);
		}
		return pageInfo;
	}
	/**
	 * 
	 */
	@RequestMapping(value="/upDown")
	@ResponseBody
	public Map<String,Object> upDown(Long upSerialNo, Long downSerialNo){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoNoticeService.upDown(upSerialNo, downSerialNo);
		map.put("msg", msg);
		return map;
	}
	
	@RequestMapping(value="/selectAll")
	@ResponseBody
	public InfoNotice selectBySerialNo(Long serialNo){
		return infoNoticeService.getBySerialNo(serialNo);
	}

	/**
	 * 获取发布人
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOperators")
	@ResponseBody
	public CommonResponse getOperators(HttpServletRequest request){
		CommonResponse response = new CommonResponse();
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				List list = infoNoticeService.getOperators();
				if (list != null && list.size() != 0){
					response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
					response.setData(list);
					response.setMsg("操作成功！");
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
		return response;
	}
}
