package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.model.UserWithdrawCashModel;
import com.fx.xzt.sys.service.UserWithdrawCashService;
import com.fx.xzt.sys.util.UserWithdrawCashStatusEnum;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/userWithdrawCash")
public class UserWithdrawCashController {
	@Resource
	UserWithdrawCashService userWithdrawCashService;
	
	/**
	 * 出金管理 集合 
	 * @return
	 */
	@RequestMapping(value="/selectAll")
	@ResponseBody
	public PageInfo<UserWithdrawCashModel> selectAll(String userName, String startTime, String endTime, String status,
			Integer pageNum, Integer pageSize){
		return userWithdrawCashService.getByAll(userName, startTime, endTime, status, pageNum, pageSize);
	}
	
	/**
	 * 出金管理导出excel
	 */
	@RequestMapping(value="/excelRecharge")
	public void excelRecharge(HttpServletRequest request, HttpServletResponse response,
			String userName, String startTime, String endTime, String status){
		List<UserWithdrawCashModel> list = userWithdrawCashService.getByAllExcel(userName, startTime, endTime, status);
		POIUtils poi = new POIUtils();
		String[] heads = {"账号","提现金额","绑定银行","绑定银行卡号","冻结时间","完成时间","状态"};
		String[] columns = {"username","withdrawamt","bankName","accountnum","withdrawtimeString","finishtimeString","statusName"};
		poi.doExport(request, response, list, "出金记录", "出金记录", heads, columns);
	}
	/**
	 * 修改状态 体现完成
	 */
	@RequestMapping(value="/editStatus")
	@ResponseBody
	public Map<String,Object> editStatus(String withdrawid,Integer type){
		int msg = 0;
		if(type==1){
			//提现
			msg = userWithdrawCashService.updateByIdStatus(withdrawid);
		}else if(type==2){
			//拒绝
			msg = userWithdrawCashService.updateWithdrawCashAndAccount(withdrawid);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg", msg);
		return map;
	}
}
