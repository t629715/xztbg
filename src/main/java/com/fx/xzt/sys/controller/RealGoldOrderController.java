package com.fx.xzt.sys.controller;

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
import com.fx.xzt.sys.service.RealGoldOrderService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * @author htt
 * @ClassName: RealGoldOrderController.java
 * @Description: 实金交易Controller
 * @date 2017-09-26 17:02
 */
@Controller
@RequestMapping("/realGoldOrder")
public class RealGoldOrderController {

    @Resource
    RealGoldOrderService realGoldOrderService;

    /**
     *  实金交易查询
     * @param userName  用户名
     * @param orderNo  订单号
     * @param startTime 买入开始时间
     * @param endTime  买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime 注册结束时间
     * @param agentName 代理商用户名
     * @param brokerName 经纪人用户名
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/selectByRealGoldOrder")
    @ResponseBody
    public Object selectByRealGoldOrder(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                           String agentName, String brokerName, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                PageInfo<Map<String, Object>> pageInfo = realGoldOrderService.selectByRealGoldOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentNameStr, brokerName, pageNum, pageSize);
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
     * 实金交易-导出
     * @param request
     * @param response
     * @param userName  用户名
     * @param orderNo  订单号
     * @param startTime 买入开始时间
     * @param endTime  买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime 注册结束时间
     * @param agentName 代理商用户名
     * @param brokerName 经纪人用户名
     * @return
     */
    @RequestMapping(value="/excelRealGoldOrder")
    @ResponseBody
    public void excelRealGoldOrder(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                   String agentName, String brokerName) {
        try {
            String tieleName = "实金交易";
            String excelName = "实金交易";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                List<Map<String, Object>> list = realGoldOrderService.excelRealGoldOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentNameStr, brokerName);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                        Object rmbAmountObj =  map.get("rmbAmount");
                        Object feeObj =  map.get("fee");
                        Object shareAmountObj = map.get("shareAmount");
                        
                        if (rmbAmountObj != null && rmbAmountObj != "") {
                        	Double rmbAmount = Double.valueOf(rmbAmountObj.toString());
                        	map.put("rmbAmount", rmbAmount/100);
                        }
                        if (feeObj != null && feeObj != "") {
                        	Double fee = Double.valueOf(feeObj.toString());
                        	map.put("fee", fee/100);
                        }
                        if (shareAmountObj != null && shareAmountObj != "") {
                         	Double shareAmount = Double.valueOf(shareAmountObj.toString());
                         	map.put("shareAmount", shareAmount/100);
                         }
                    }
                }
                POIUtils poi = new POIUtils();
                //判断是否为代理商账户
                if (users.getPid() != null &&  users.getPid() == 1) {
                    String[] heads = {"用户账号","注册时间","经纪人","交易订单号","合约类型","买入价","黄金克数","买入金额","手续费", "买入时间", "交易分成"};
                    String[] colums = {"userName","registerTime","brokerName","orderNo","productName","buyPrice","gram","rmbAmount","fee", "buyTime", "shareAmount"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                } else if (users.getPid() == null || users.getPid() == 0){
                    String[] heads = {"用户账号","注册时间","代理商","经纪人","交易订单号","合约类型","买入价","黄金克数","买入金额","手续费", "买入时间"};
                    String[] colums = {"userName","registerTime","agentName","brokerName","orderNo","productName","buyPrice","gram","rmbAmount","fee", "buyTime"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *  实金交易金额统计
     * @return
     */
    @RequestMapping(value="/selectByRealGoldCount")
    @ResponseBody
    public Object selectByRealGoldCount(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName) {
        CommonResponse cr = new CommonResponse();
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map = realGoldOrderService.selectByRealGoldCount(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentNameStr, brokerName);
               // System.out.println("=============:" + map.get("gramSum"));
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

}