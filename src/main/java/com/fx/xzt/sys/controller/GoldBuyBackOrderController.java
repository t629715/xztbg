package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.GoldBuyBackOrderService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liaijiao
 * @Description:
 * @date 2018/10/30
 */
@Controller
@RequestMapping("/goldBuyBackOrder")
public class GoldBuyBackOrderController {
    @Resource
    private GoldBuyBackOrderService GoldBuyBackOrderService;
    @Resource
    LogRecordService logRecordService;

    /**
     * 回购订单查询
     * @param request
     * @param
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException
     */
    @RequestMapping(value="/selectGoldBuyBackOrder")
    @ResponseBody
    public Object selectGoldBuyBackOrder(HttpServletRequest request,String userName, String orderNo, String agentName, String brokerName,
                                           String gtmStartTime, String gtmEndTime, String comStartTime, String comEndTime, String subStartTime,
                                           String subEndTime, String expStartTime, String expEndTime, Integer pageNum, Integer pageSize)throws ParseException {
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("回购订单查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HGDD.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = GoldBuyBackOrderService.selectGoldBuyBackOrder(userName,orderNo,agentName,brokerName,gtmStartTime,gtmEndTime,comStartTime,comEndTime,subStartTime,
                        subEndTime, expStartTime, expEndTime,pageNum,pageSize);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(pageInfo);
                cr.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("查询成功");
            }else{
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }

        }catch (Exception e){
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;

        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }

    /**
     * 回购订单导出
     * @param request
     * @param response
     * @param userName
     * @param orderNo
     * @param agentName
     * @param brokerName
     * @param gtmStartTime
     * @param gtmEndTime
     * @param comStartTime
     * @param comEndTime
     * @param subStartTime
     * @param subEndTime
     * @param expStartTime
     * @param expEndTime
     * @throws Exception
     */
    @RequestMapping(value="/excelGoldBuyBackOrder")
    @ResponseBody
    public void excelGoldBuyBack(HttpServletRequest request, HttpServletResponse response, String userName, String orderNo, String agentName, String brokerName,
                                   String gtmStartTime, String gtmEndTime, String comStartTime, String comEndTime, String subStartTime,
                                   String subEndTime, String expStartTime, String expEndTime) throws Exception{
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("回购订单导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.HGDD.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));

        try {
            String tieleName = "回购订单";
            String excelName = "回购订单";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {

                List<Map<String, Object>> list = GoldBuyBackOrderService.excelGoldBuyBackOrder(userName,orderNo,agentName,brokerName,gtmStartTime,gtmEndTime,comStartTime,comEndTime,subStartTime,
                        subEndTime, expStartTime, expEndTime);
                if (list != null && list.size() > 0) {

                    POIUtils poi = new POIUtils();
                    //判断是否为代理商账户
                    String[] heads = {"订单号", "账户名","代理商", "经纪人", "订单克数", "预约价格", "数量", "回购金额","寄件人",
                    "联系电话","订单状态","物流单号","创建时间","过期时间","提交时间","确认时间"};
                    String[] colums = {"orderNo", "userName","agentName", "brokerName", "goldWeight", "recoverPrice", "goldNum", "repurchaseAmount","senderName",
                    "senderPhone","orderState","trackingNum","gtmCreate","expireTime","submitTime","completeTime"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    log.setUserId(users.getId());
                    log.setContent("导出成功，共：" + list.size() + "条数据");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
    }

    /**
     * 收货确认无误
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateState")
    @ResponseBody
    public Object updateState(HttpServletRequest request,String id)throws Exception{
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("收货确认无误");
        log.setContent("操作失败");
        log.setModuleName(ConstantUtil.logRecordModule.HGDD.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                int l = GoldBuyBackOrderService.updateState(id);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(l);
                cr.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("操作成功");
            }else{
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }

        }catch (Exception e){
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;

        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;

    }

    /**
     * 取消
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cancelState")
    @ResponseBody
    public Object cancelState(HttpServletRequest request,String id)throws Exception{
        CommonResponse cr = new CommonResponse();
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("取消");
        log.setContent("取消失败");
        log.setModuleName(ConstantUtil.logRecordModule.HGDD.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                int l = GoldBuyBackOrderService.cancelState(id);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(l);
                cr.setMsg("取消成功！");
                log.setUserId(users.getId());
                log.setContent("取消成功");
            }else{
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("取消失败！");
            }

        }catch (Exception e){
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("取消失败！");
            throw e;

        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;

    }
}
