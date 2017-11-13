package com.fx.xzt.sys.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.GoldWithdrawService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: GoldWithdrawController 
* @Description: 黄金提取
* @author htt
* @date 2017-10-17 下午2:24:25 
*
 */
@Controller
@RequestMapping("/goldWithdraw")
public class GoldWithdrawController {
	
	@Resource
	GoldWithdrawService goldWithdrawService;
	
	/**
	 * 
	* @Title: selectByGoldWithdraw 
	* @Description: 黄金提取查询
	* @param request
	* @param userName   用户账号
	* @param startTime  申请开始时间
	* @param endTime    申请结束时间
	* @param agentName  代理商账号
	* @param brokerName 经纪人账号
	* @param status     状态1:未发货2:已发货
	* @param pageNum    页数
	* @param pageSize   条数
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByGoldWithdraw")
    @ResponseBody
	public Object selectByGoldWithdraw(HttpServletRequest request, String userName, String startTime, String endTime, String agentName,
			String brokerName, Integer status, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws Exception {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = goldWithdrawService.selectByGoldWithdraw(userName, startTime, endTime, agentName, brokerName, status, pageNum, pageSize);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(pageInfo);
                cr.setMsg("操作成功！");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }
        } catch (Exception e) {
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
            // e.printStackTrace();
        }
        return cr;
    }
	
	/**
	 * 
	* @Title: excelGoldWithdraw 
	* @Description: 黄金提取查询-导出
	* @param request
	* @param response
	* @param userName   用户账号
	* @param startTime  申请开始时间
	* @param endTime  申请结束时间
	* @param agentName  代理商账号
	* @param brokerName 经纪人账号
	* @param status  状态1:未发货2:已发货
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelGoldWithdraw")
    @ResponseBody
    public void excelGoldWithdraw(HttpServletRequest request, HttpServletResponse response, String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer status) throws Exception{
		try {
            String tieleName = "黄金提取记录";
            String excelName = "黄金提取记录";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                List<Map<String, Object>> list = goldWithdrawService.excelGoldWithdraw(userName, startTime, endTime, 
                		agentName, brokerName, status);
                if (list != null && list.size() > 0) {
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    for (Map<String, Object> map : list) {
                    	Object insuranceObj =  map.get("insurance");
                    	Object logisticsFeeObj =  map.get("logisticsFee");
                    	Object applyTimeObj = map.get("applyTime");
                    	Object sendTimeObj = map.get("sendTime");
                    	
               		 	if (applyTimeObj != null && applyTimeObj != "") {
               		 		map.put("applyTime", sdf.format(sdf.parse(applyTimeObj.toString())));
                        }
               		 	
	               		if (sendTimeObj != null && sendTimeObj != "") {
	               			map.put("sendTime", sdf.format(sdf.parse(sendTimeObj.toString())));
	                    }
	               		
                        if (insuranceObj != null && insuranceObj != "") {
                        	Double insurance = Double.valueOf(insuranceObj.toString());
                        	map.put("insurance", insurance/100);
                        }
                        if (logisticsFeeObj != null && logisticsFeeObj != "") {
                        	Double logisticsFee = Double.valueOf(logisticsFeeObj.toString());
                        	map.put("logisticsFee", logisticsFee/100);
                        }
                        map.put("type", ConstantUtil.goldWithdrawType.toMap().get(map.get("type").toString()));
                        map.put("status", ConstantUtil.goldWithdrawStatus.toMap().get(map.get("status").toString()));
                    }
                    POIUtils poi = new POIUtils();
                    String[] heads = {"用户账号", "代理商",  "经纪人", "提取类型", "数量", "保险费", "物流费", "联系人", "联系电话","收货地址","申请提取时间","发货时间","状态","物流单号"};
                    String[] colums = {"userName", "agentName", "brokerName", "type", "amount", "insurance", "logisticsFee", 
                    		"contactName","contactPhone","deliveryAddress","applyTime","sendTime","status","logisticsNo"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                }
            }
        } catch (Exception e) {
            throw e;
        }
	}
	
	/**
	 * 
	* @Title: selectByGoldWithdrawCount 
	* @Description: 黄金提取查询-黄金、保险费、物流费用统计
	* @param request
	* @param userName    用户账号
	* @param startTime   申请开始时间
	* @param endTime  申请结束时间
	* @param agentName  代理商账号
	* @param brokerName 经纪人账号
	* @param status  状态1:未发货2:已发货
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByGoldWithdrawCount")
    @ResponseBody
    public Object selectByGoldWithdrawCount(HttpServletRequest request, String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer status) throws Exception{
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map = goldWithdrawService.selectByGoldWithdrawCount(userName, startTime, endTime, agentName, brokerName, status);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(map);
                cr.setMsg("操作成功！");
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
            // e.printStackTrace();
        }
        return cr;
    }
	
	/**
	 * 
	* @Title: addLogisticsNoById 
	* @Description: 黄金提取--物流单号添加
	* @param logisticsNo 物流单号
	* @param status 状态1:未发货2:已发货
	* @param sendTime 发货时间
	* @param id id
	* @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/addLogisticsNoById")
    @ResponseBody
    public Object addLogisticsNoById(HttpServletRequest request, @RequestParam String logisticsNo, @RequestParam Short status, @RequestParam String id) {
        CommonResponse cr = new CommonResponse();
        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
        cr.setMsg("操作失败！");
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
        	if (users != null) {
        		if (StringUtil.isNotEmpty(logisticsNo) && StringUtil.isNotEmpty(id)&& status > 0) {
                    int flag = goldWithdrawService.addLogisticsNoById(logisticsNo, status, DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss"), Long.parseLong(id));
                    if (flag > 0) {
                        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
                        cr.setMsg("操作成功！");
                    }
                }
        	} else {
        		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("无操作权限！");
        	}
        } catch (Exception e) {
            throw e;
        }
        return cr;
    }
	
	/**
	 * 
	* @Title: updateLogisticsNoById 
	* @Description:  黄金提取-物流单号变更
	* @param logisticsNo  物流单号
	* @param updateTime 修改时间
	* @param id id
	* @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/updateLogisticsNoById")
    @ResponseBody
    public Object updateLogisticsNoById(HttpServletRequest request, @RequestParam String logisticsNo, @RequestParam String id) {
        CommonResponse cr = new CommonResponse();
        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
        cr.setMsg("操作失败！");
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
        	if (users != null) {
        		if (StringUtil.isNotEmpty(logisticsNo) && StringUtil.isNotEmpty(id)) {
                    int flag = goldWithdrawService.updateLogisticsNoById(logisticsNo, DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss"), Long.parseLong(id));
                    if (flag > 0) {
                        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
                        cr.setMsg("操作成功！");
                    }
                }
        	} else {
        		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
        	}
        } catch (Exception e) {
            throw e;
        }
        return cr;
    }

}
