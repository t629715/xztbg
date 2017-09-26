package com.fx.xzt.sys.controller;

import com.alibaba.fastjson.JSON;
import com.fx.xzt.sys.service.DealOrderService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: DealOrderController.java
 * @Description: 金权交易Controller
 * @date 2017-09-25 14:02
 */
@Controller
@RequestMapping("/dealOrder")
public class DealOrderController {

    @Resource
    DealOrderService dealOrderService;

    /**
     * 金权交易查询
     * @param userName   用户名
     * @param orderNo    订单号
     * @param startTime  建仓开始时间
     * @param endTime    建仓结束时间
     * @param agentName  代理商用户名
     * @param brokerName 经纪人用户名
     * @param orderState 订单状态
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/selectByDealOrderAll")
    @ResponseBody
    public String selectByDealOrderAll(String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,  String agentName, String brokerName, Integer orderState, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        CommonResponse cr = new CommonResponse();
        try {
            PageInfo<Map<String, Object>> pageInfo = dealOrderService.selectByDealOrderAll(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentName, brokerName, orderState, pageNum, pageSize);
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
     * 金权交易导出
     * @param request
     * @param response
     * @param userName
     * @param orderNo
     * @param startTime
     * @param endTime
     * @param regStartTime
     * @param regEndTime
     * @param agentName
     * @param brokerName
     * @param orderState
     */
    @RequestMapping(value="/excelDealOrderMessage")
    @ResponseBody
    public void excelDealOrderMessage(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String startTime, String endTime, String regStartTime, String regEndTime,  String agentName, String brokerName, Integer orderState){
        List<Map<String, Object>> list = dealOrderService.excelDealOrderMessage(userName, orderNo, startTime, endTime, regStartTime, regEndTime, agentName, brokerName, orderState);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                map.put("upOrDown", ConstantUtil.dealOrderUpOrDown.toMap().get(map.get("upOrDown")));
            }
        }
        POIUtils poi = new POIUtils();
        String[] heads = {"用户账号","注册时间","代理商","经纪人","交易订单号","合约类型","方向","黄金克数","建仓前余额","建仓后余额",
                "合约金额","买入金额","交易成本","买入点数","卖出点数","建仓时间","平仓时间","盈亏"};
        String[] colums = {"userName","registerTime","agentName","brokerName","orderNo","productName","upOrDown","handNumber","","",
                "ensureAmount","ensureAmount","ensureAmount","openPositionPrice","closePositionPrice","createTime","endTime", "profitLossNumber"};
        poi.doExport(request, response, list, "金权交易", "金权交易", heads, colums);
    }

    /**
     *  金权交易查询-金额统计
     * @return
     */
    @RequestMapping(value="/selectByDealOrderCount")
    @ResponseBody
    public String selectByDealOrderCount(){
        CommonResponse cr = new CommonResponse();
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map = dealOrderService.selectByDealOrderCount();
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
