package com.fx.xzt.sys.controller;

import com.alibaba.fastjson.JSON;
import com.fx.xzt.sys.service.RealGoldOrderService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value="/selectByRealGoldOrderAll")
    @ResponseBody
    public String selectByRealGoldOrderAll(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName, Integer pageNum, Integer pageSize) {
        CommonResponse cr = new CommonResponse();
        try {
            PageInfo<Map<String, Object>> pageInfo = realGoldOrderService.selectByRealGoldOrderAll(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentName, brokerName, pageNum, pageSize);
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
            cr.setData(pageInfo);
            cr.setMsg("操作成功！");
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
            // e.printStackTrace();
        }
        return JSON.toJSONString(cr);
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
    public void excelRealGoldOrder(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime, String agentName, String brokerName) {
        List<Map<String, Object>> list = realGoldOrderService.excelRealGoldOrder(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentName, brokerName);
        POIUtils poi = new POIUtils();
        String[] heads = {"用户账号","注册时间","代理商","经纪人","交易订单号","合约类型","买入价","黄金克数","买入金额","手续费", "买入时间"};
        String[] colums = {"userName","registerTime","agentName","brokerName","orderNo","productName","buyPrice","gram","rmbAmount","fee", "buyTime"};
        poi.doExport(request, response, list, "实金交易", "实金交易", heads, colums);
    }

    /**
     *  实金交易金额统计
     * @return
     */
    @RequestMapping(value="/selectByRealGoldCount")
    @ResponseBody
    public String selectByRealGoldCount() {
        CommonResponse cr = new CommonResponse();
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map = realGoldOrderService.selectByRealGoldCount();
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
            cr.setData(map);
            cr.setMsg("操作成功！");
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
            // e.printStackTrace();
        }
        return JSON.toJSONString(cr);
    }

}