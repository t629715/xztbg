package com.fx.xzt.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.service.UserAccountRecordService;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/userAccountRecord")
public class UserAccountRecordController {
	
	@Resource
	UserAccountRecordService userAccountRecordService;
	
	/**
	 * 获取消费集合
	 * @return
	 */
	@RequestMapping(value="/selectByAll")
	@ResponseBody
	public PageInfo<UserAccountRecord> selectByAll(String userName, String startTime, String endTime, Integer pageNum,
			Integer pageSize){
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
	public PageInfo<UserAccountRecord> selectGameAward(String userName, String startTime, String endTime, Integer pageNum,
			Integer pageSize){
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
