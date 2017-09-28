package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.FinanceOrderService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: FinanceOrderController.java
 * @Description:
 * @date 2017-09-27 14:13
 */
@Controller
@RequestMapping("/financeOrder")
public class FinanceOrderController {

    @Resource
    FinanceOrderService financeOrderService;

    /**
     * 理财交易查询
     *
     * @param userName     用户名
     * @param orderNo      订单号
     * @param startTime    买入开始时间
     * @param endTime      买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime   注册结束时间
     * @param agentName    代理商用户名
     * @param brokerName   经纪人用户名
     * @param status       订单状态 1：持有中；2：已赎回
     * @param type         类型 1：随意存；2：黄金看涨
     * @param nper         期数
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/selectByFinanceOrder")
    @ResponseBody
    public Object selectByFinanceOrder(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                       String agentName, String brokerName, Integer status, @RequestParam Integer type, String nper, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getUserName();
                }
                PageInfo<Map<String, Object>> pageInfo = financeOrderService.selectByFinanceOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime,
                        agentNameStr, brokerName, status, type, nper, pageNum, pageSize);
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
       // return JSON.toJSONString(cr);
        return cr;
    }

    /**
     * 理财交易查询-导出
     *
     * @param request
     * @param response
     * @param userName     用户名
     * @param orderNo      订单号
     * @param startTime    买入开始时间
     * @param endTime      买入结束时间
     * @param regStartTime 注册开始时间
     * @param regEndTime   注册结束时间
     * @param agentName    代理商用户名
     * @param brokerName   经纪人用户名
     * @param status       订单状态 1：持有中；2：已赎回
     * @param type         类型 1：理财产品；2：黄金看涨
     * @param nper         期数
     */
    @RequestMapping(value = "/excelFinanceOrder")
    @ResponseBody
    public void excelFinanceOrder(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                  String agentName, String brokerName, Integer status, @RequestParam Integer type, String nper) throws ParseException {
        try {
            String tieleName = "";
            String excelName = "";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getUserName();
                }
                List<Map<String, Object>> list = financeOrderService.excelFinanceOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentNameStr, brokerName, status, type, nper);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                        map.put("status", ConstantUtil.financeOrderStatus.toMap().get(map.get("status").toString()));
                    }
                }
                POIUtils poi = new POIUtils();
                //理财产品交易导出
                if (type != null && type > 0 && type == ConstantUtil.FINANCE_TYPE_LCCP ) {
                    tieleName = "理财交易";
                    excelName = "理财交易";
                    //判断是否为代理商账户
                    if (users.getPid() != null &&  users.getPid() == 1) {
                        String[] heads = {"用户账号", "注册时间", "经纪人", "交易订单号", "产品编号", "产品名称", "周期", "收益率", "买入金额",
                                "买入时间", "赎回时间", "状态", "收益支出"};
                        String[] colums = {"userName", "registerTime", "brokerName", "orderNo", "productNo", "productName", "cycle", "yearIncomPercent", "buyAmount",
                                "buyTime", "redeemTime", "status", "income"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0){
                        if (status != null && status == 2) {
                            tieleName = "理财收益结算";
                            excelName = "理财收益结算";
                        }
                        String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "交易订单号", "产品编号", "产品名称", "周期", "收益率", "买入金额",
                                "买入时间", "赎回时间", "状态", "收益支出"};
                        String[] colums = {"userName", "registerTime", "agentName", "brokerName", "orderNo", "productNo", "productName", "cycle", "yearIncomPercent", "buyAmount",
                                "buyTime", "redeemTime", "status", "income"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    }
                }
                //黄金稳赚交易导出
                if (type != null && type > 0 && type == ConstantUtil.FINANCE_TYPE_HJWZ) {
                    tieleName = "黄金稳赚交易";
                    excelName = "黄金稳赚交易";
                    //判断当时账户是否为代理商
                    if (users.getPid() != null &&  users.getPid() == 1) {
                        String[] heads = {"用户账号", "注册时间", "经纪人", "交易订单号", "产品编号", "产品名称", "期数", "周期", "收益率", "赎回金额",
                                "买入时间", "赎回时间", "状态", "收益支出"};
                        String[] colums = {"userName", "registerTime", "brokerName", "orderNo", "productNo", "productName", "nper", "cycle", "yearIncomPercent", "buyAmount",
                                "buyTime", "redeemTime", "status", "income"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0) {
                        if (status != null && status == 2) {
                            tieleName = "黄金稳赚看涨结算";
                            excelName = "黄金稳赚看涨结算";
                        }
                        String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "交易订单号", "产品编号", "产品名称", "期数", "周期", "收益率", "赎回金额",
                                "买入时间", "赎回时间", "状态", "收益支出"};
                        String[] colums = {"userName", "registerTime", "agentName", "brokerName", "orderNo", "productNo", "productName", "nper", "cycle", "yearIncomPercent", "buyAmount",
                                "buyTime", "redeemTime", "status", "income"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 理财交易查询-统计
     *
     * @return
     */
    @RequestMapping(value = "/selectByFinanceOrderCount")
    @ResponseBody
    public Object selectByFinanceOrderCount(HttpServletRequest request, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,
                                            String agentName, String brokerName, Integer status, @RequestParam Integer type, String nper) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getUserName();
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map = financeOrderService.selectByFinanceOrderCount(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentNameStr, brokerName, status, type, nper);
                if (map != null && !map.isEmpty() && map.size() > 0) {
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(map);
                    cr.setMsg("操作成功！");
                } else {
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData("{}");
                    cr.setMsg("操作成功！");
                }
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
