package com.fx.xzt.sys.controller;

import com.alibaba.fastjson.JSON;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InfoXioudeService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.OrderAnalysisService;
import com.fx.xzt.sys.util.*;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by tianliya on 2017/10/15.
 */
@Controller
@RequestMapping("/analysis")
public class OrderAnalysisController {
    @Autowired
    OrderAnalysisService orderAnalysisService;
    @Resource
    LogRecordService logRecordService;

    /**
     * 获取黄金课堂信息  tianliya
     * @param request
     * @param startTime
     * @param endTime
     * @param agentName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/orderAnalysis")
    @ResponseBody
    public CommonResponse orderAnalysis(HttpServletRequest request, Integer time,String startTime,
                                            String endTime,  String agentName,Integer upOrDown,
                                        Integer orderState, Integer profitLoss,Long agentId,
                                            Integer pageNum, Integer pageSize) throws ParseException {
        System.out.println("=========================================================================upOrDown="+upOrDown+" orderState="+orderState+" profitLoss="+profitLoss+" endTime="+endTime+" startTime="+startTime);
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("查询交易分析");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.JYFX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null){
                if (startTime == "" && endTime == ""){
                    long current=System.currentTimeMillis();//当前时间毫秒数
                    long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();
                    Long mills = new Timestamp(zero).getTime()+24*3600*1000;
                    if (time == 1){
                        startTime = new Timestamp(zero).getTime()+"";
                        endTime = (new Timestamp(zero).getTime()+24*3600*1000)+"";

                    }else if (time == 2){
                        Date start = DateUtil.modify(new Timestamp(zero),0,0,-1,0,0,0);
                        startTime = start.getTime()+"";
                        endTime = new Timestamp(zero).getTime()+"";

                    }else if (time == 3){
                        Date start = DateUtil.modify(new Timestamp(zero),0,0,-7,0,0,0);
                        startTime = start.getTime()+"";
                        endTime = mills+"";

                    }else {
                        Date start = DateUtil.modify(new Timestamp(zero),0,0,-30,0,0,0);
                        startTime = start.getTime()+"";
                        endTime = mills+"";
                    }
                }
                PageInfo<Map<String, Object>> pageInfo = orderAnalysisService.orderAnalysis(startTime,endTime,
                        agentName,upOrDown,orderState,profitLoss,agentId,
                        pageNum, pageSize);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                response.setData(pageInfo);
                response.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("查询成功");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }

    @RequestMapping(value="/orderAnalysisCount")
    @ResponseBody
    public String orderAnalysisCount(HttpServletRequest request, Integer time,String startTime,
                                             String endTime,  String agentName,Integer upOrDown,
                                     Integer orderState, Integer profitLoss,Long agentId,
                                             Integer pageNum, Integer pageSize) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("交易分析统计");
        log.setContent("统计失败");
        log.setModuleName(ConstantUtil.logRecordModule.JYFX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null){
                if (startTime == "" && endTime == ""){
                    long current=System.currentTimeMillis();//当前时间毫秒数
                    long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();
                    Long mills = new Timestamp(zero).getTime()+24*3600*1000;
                    if (time == 1){
                        startTime = new Timestamp(zero).getTime()+"";
                        endTime = (new Timestamp(zero).getTime()+24*3600*1000)+"";

                    }else if (time == 2){
                        Date start = DateUtil.modify(new Timestamp(zero),0,0,-1,0,0,0);
                        startTime = start.getTime()+"";
                        endTime = new Timestamp(zero).getTime()+"";

                    }else if (time == 3){
                        Date start = DateUtil.modify(new Timestamp(zero),0,0,-7,0,0,0);
                        startTime = start.getTime()+"";
                        endTime = mills+"";

                    }else {
                        Date start = DateUtil.modify(new Timestamp(zero),0,0,-30,0,0,0);
                        startTime = start.getTime()+"";
                        endTime = mills+"";
                    }
                }
                Map map = orderAnalysisService.orderAnalysisCount(startTime,
                        endTime,  upOrDown,
                        orderState,  profitLoss,agentId,
                        pageNum,  pageSize);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                response.setData(map);
                response.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("统计成功");
            }

        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return JSON.toJSONString(response);
    }


    @RequestMapping(value="/exportAnalysis")
    @ResponseBody
    public void exportAnalysis(HttpServletRequest request, HttpServletResponse response,Integer time, String startTime,
                               String endTime, String agentName, Integer upOrDown,
                               Integer orderState, Integer profitLoss, Long agentId,
                               Integer pageNum, Integer pageSize) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("导出交易分析");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.JYFX.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null){
                String tieleName = "交易分析";
                String excelName = "交易分析";
                if (startTime == "" && endTime == ""){
                    long current=System.currentTimeMillis();//当前时间毫秒数
                    long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();
                    Long mills = new Timestamp(zero).getTime()+24*3600*1000;
                    if (time == 1){
                        startTime = new Timestamp(zero).getTime()+"";
                        endTime = (new Timestamp(zero).getTime()+24*3600*1000)+"";

                    }else if (time == 2){
                        Date start = DateUtil.modify(new Timestamp(zero),0,0,-1,0,0,0);
                        startTime = start.getTime()+"";
                        endTime = new Timestamp(zero).getTime()+"";

                    }else if (time == 3){
                        Date start = DateUtil.modify(new Timestamp(zero),0,0,-7,0,0,0);
                        startTime = start.getTime()+"";
                        endTime = mills+"";

                    }else {
                        Date start = DateUtil.modify(new Timestamp(zero),0,0,-30,0,0,0);
                        startTime = start.getTime()+"";
                        endTime = mills+"";

                    }

                }
                List list = orderAnalysisService.exportAnalysis(startTime,endTime,
                        agentName,upOrDown,orderState,profitLoss,agentId
                );
                POIUtils poi = new POIUtils();
                String[] heads = {"时间", "交易用户","交易金额","金生金用户",  "金生金克重",  "金权交易用户", "金权交易金额","金权交割用户","金权交割金额",
                        "黄金用户","黄金买入克重","黄金买入金额","黄金卖出克重","黄金卖出金额"};
                String[] colums = {"date", "perCount","perAmount","buyUserAmount", "gram",  "goldRightUserAmount","goldRightAmount","goldDeliveryUserAmount","goldDeliveryAmount"
                        ,"goldUserAmount","buyGoldAmount","buyRmbAmount","saleGoldAmount","saleRmbAmount"};
                poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                log.setUserId(users.getId());
                log.setContent("导出成功");
            }

        } catch (Exception e) {

        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
    }

}
