package com.fx.xzt.sys.controller;

import com.alibaba.fastjson.JSON;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InfoXioudeService;
import com.fx.xzt.sys.service.OrderAnalysisService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.DateUtils;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
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
                                            Integer pageNum, Integer pageSize){
        CommonResponse response = new CommonResponse();
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
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
        }
        return response;
    }

    @RequestMapping(value="/orderAnalysisCount")
    @ResponseBody
    public String orderAnalysisCount(HttpServletRequest request, Integer time,String startTime,
                                             String endTime,  String agentName,Integer upOrDown,
                                     Integer orderState, Integer profitLoss,Long agentId,
                                             Integer pageNum, Integer pageSize){
        CommonResponse response = new CommonResponse();
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
                        endTime = mills+"";

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
            }

        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
        }
        return JSON.toJSONString(response);
    }


    @RequestMapping(value="/exportAnalysis")
    @ResponseBody
    public void exportAnalysis(HttpServletRequest request, HttpServletResponse response,Integer time, String startTime,
                               String endTime, String agentName, Integer upOrDown,
                               Integer orderState, Integer profitLoss, Long agentId,
                               Integer pageNum, Integer pageSize){
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null){
                String tieleName = "黄金赎回记录";
                String excelName = "黄金赎回记录";
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
                String[] heads = {"时间", "交易用户","交易金额","黄金稳赚交易用户",  "黄金稳赚交易金额", "随意存交易用户", "随意存交易金额", "金权交易用户",
                        "金权交易金额","实物黄金交易用户","实物黄金交易金额"};
                String[] colums = {"date", "perCount","perAmount","goldUpCount", "goldUpAmount", "financeCount", "financeAmount", "goldRightCount",
                        "goldRightAmount","realGoldCount","realGoldAmount"};
                poi.doExport(request, response, list, tieleName, excelName, heads, colums);
            }

        } catch (Exception e) {

        }

    }

}
