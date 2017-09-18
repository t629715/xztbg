package com.fx.jyg.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.jyg.sys.model.UserRechargeModel;
import com.fx.jyg.sys.service.UserRechargeService;
import com.fx.jyg.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @Title: UserRechargeController.java 
* @Package com.fx.jyg.sys.controller 
* @Description: TODO
* @author SYan  
* @date 2017年8月22日 上午11:19:17 
* @version V1.0
 */
@Controller
@RequestMapping(value="/userRecharge")
public class UserRechargeController {
	@Resource
	UserRechargeService userRechargeService;
	
	/**
	 * 获取充值记录 集合 
	 * @return
	 */
	@RequestMapping(value="/selectAll")
	@ResponseBody
	public PageInfo<UserRechargeModel> selectAll(String username,String rechargeid,String merchantordernum,String startTime,String endTime,String rechargechannel,Short status,Integer pageNum,Integer pageSize){
		return userRechargeService.getAll(username, rechargeid, merchantordernum, startTime, endTime, rechargechannel, status, pageNum, pageSize);
	}
	/**
	 * 导出excel
	 */
	@RequestMapping(value="/excelRecharge")
	public void excelRecharge(HttpServletRequest request, HttpServletResponse response,String username,String rechargeid,String merchantordernum,String startTime,String endTime,String rechargechannel,Short status){
		List<UserRechargeModel> list = userRechargeService.getExcelAll(username, rechargeid, merchantordernum, startTime, endTime, rechargechannel, status);
		POIUtils poi = new POIUtils();
		String[] heads = {"账号","充值流水号","商户订单号","人民币","优币","渠道","充值时间","充值状态"};
		String[] columns = {"username","rechargeid","merchantordernum","rmbamt","uamt","rechargeChannelName","formatRechargetime","statusName"};
		poi.doExport(request, response, list, "充值记录", "充值记录", heads, columns);
	}
}
