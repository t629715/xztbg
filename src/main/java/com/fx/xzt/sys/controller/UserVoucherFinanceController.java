package com.fx.xzt.sys.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.fx.xzt.sys.service.UserVoucherFinanceService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: UserVoucherFinanceController 
* @Description: 加息券信息
* @author htt
* @date 2017-11-8 下午4:13:01 
*
 */
@Controller
@RequestMapping("/userVoucherFinance")
public class UserVoucherFinanceController {

    @Resource
    UserVoucherFinanceService userVoucherFinanceService;

    /**
     * 
    * @Title: selectByUserVoucherFinance
    * @Description: 加息券查询
    * @param request
    * @param userName   用户账号
    * @param startTime  领取开始时间
    * @param endTime    领取结束时间
    * @param useStartTime  使用开始时间
    * @param useEndTime  使用结束时间
    * @param agentName  运行商账号
    * @param brokerName  经纪人账号
    * @param useState  使用状态
    * @param pageNum
    * @param pageSize
    * @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    * @author htt
     */
    @RequestMapping(value="/selectByUserVoucherFinance")
    @ResponseBody
    public Object selectByUserVoucherFinance(HttpServletRequest request, String userName, String startTime, String endTime, 
    		String useStartTime, String useEndTime, String agentName, String brokerName, Integer useStatus,
    		@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                
                PageInfo<Map<String, Object>> pageInfo = userVoucherFinanceService.selectByUserVoucherFinance(userName, startTime, endTime, 
                		useStartTime, useEndTime, agentName, brokerName, useStatus, pageNum, pageSize);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(pageInfo);
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
    * @Title: excelUserVoucherFinance 
    * @Description: 加息券导出
    * @param request
    * @param response
    * @param userName   用户账号
    * @param startTime  领取开始时间
    * @param endTime    领取结束时间
    * @param useStartTime  使用开始时间
    * @param useEndTime  使用结束时间
    * @param agentName  运行商账号
    * @param brokerName  经纪人账号
    * @param useState  使用状态
    * @return void    返回类型 
     * @throws Exception 
    * @throws 
    * @author htt
     */
    @RequestMapping(value="/excelUserVoucherFinance")
    @ResponseBody
    public void excelUserVoucherFinance(HttpServletRequest request, HttpServletResponse response, String userName, String startTime, String endTime, 
    		String useStartTime, String useEndTime, String agentName, String brokerName, Integer useState) throws Exception{
        try {
            String tieleName = "加息券信息";
            String excelName = "加息券信息";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                List<Map<String, Object>> list = userVoucherFinanceService.excelUserVoucherFinance(userName, startTime, endTime, 
                		useStartTime, useEndTime, agentName, brokerName, useState);
                if (list != null && list.size() > 0) {
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    for (Map<String, Object> map : list) {
                    	Object useStatusObje = map.get("useStatus");
                    	Object deductionPercentObj =  map.get("deductionPercent");
                        Object useTime = map.get("useTime");
                        Object createTime = map.get("createTime");
                        if (deductionPercentObj != null && deductionPercentObj != "") {
                        	Double deductionPercent = Double.valueOf(deductionPercentObj.toString());
                        	DecimalFormat df = new DecimalFormat("00.00%");
                        	map.put("deductionPercent", df.format(deductionPercent));
                        }
                        if (useStatusObje != null && useStatusObje != "") {
                        	map.put("useStatus", ConstantUtil.userVoucherUseStatus.toMap().get(useStatusObje.toString()));
                        }
                        if (useTime != null && useTime != "") {
                        	map.put("useTime", sdf.format(sdf.parse(map.get("useTime").toString())));
                        }
                        if (createTime != null && createTime != "") {
                        	map.put("createTime", sdf.format(sdf.parse(map.get("createTime").toString())));
                        }
                    }
                    POIUtils poi = new POIUtils();
                    String[] heads = {"用户账号", "代理商",  "经纪人", "加息券名称", "加息券收益", "加息券天数", "使用时间", "发放时间", "来源","状态"};
                    String[] colums = {"userName", "agentName", "brokerName", "description", "deductionPercent", "validateDays", "useTime", "createTime", "source", "useStatus"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
