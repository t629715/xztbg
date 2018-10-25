package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InviteRegisterRecordService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/InviteFriendsToReturnHome")
public class InviteFriendsToReturnHomeController {
    @Resource
    LogRecordService logRecordService;
    @Resource
    InviteRegisterRecordService inviteRegisterRecordService;

    /**
     * @Description：邀友返佣 记录查询
     * @author ZhuFeng
     * @param request
     * @param userName 用户账号
     * @param startTime
     * @param endTime
     * @param acceptPrize
     * @param agentName 代理商
     * @param brokerName 经纪人
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException
     */
    @RequestMapping("/getSelectAll")
    @ResponseBody
    public Object getSelectAll(HttpServletRequest request,String userName, String startTime,
                               String endTime, String acceptPrize,String agentName ,String brokerName, @RequestParam Integer pageNum, @RequestParam Integer pageSize)throws ParseException {
        CommonResponse cr = new CommonResponse();

        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("邀友返佣明细查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.TJYHMX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                String isView = "0";
                if (role != null && role.get("roleIsView") != null) {
                    isView = role.get("roleIsView").toString();
                }
                PageInfo<Map<String, Object>> pageInfo = inviteRegisterRecordService.getSelectInviteFriendsAll(userName, startTime, endTime,acceptPrize,agentName ,brokerName, isView, pageNum, pageSize);
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
     * 导出
     * @param request
     * @param userName
     * @param startTime
     * @param response
     * @param endTime
     * @param acceptPrize
     * @param agentName
     * @param brokerName
     * @throws Exception
     */
    @RequestMapping(value="/excelAll")
    @ResponseBody
    public void excelAll(HttpServletRequest request,String userName, String startTime,HttpServletResponse response,
                         String endTime, String acceptPrize,String agentName ,String brokerName) throws Exception{
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("邀友返佣明细导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.TJYHMX.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));

        try {
            String tieleName = "邀友返佣明细";
            String excelName = "邀友返佣明细";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                String isView = "0";
                if (role != null && role.get("roleIsView") != null) {
                    isView = role.get("roleIsView").toString();
                }
                List<Map<String, Object>> list = inviteRegisterRecordService.exportInviteFriendsRecords(userName, startTime, endTime, isView, acceptPrize,agentName ,brokerName);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {

                        Object expireTimeObj = map.get("expireTime");
                        if (expireTimeObj != null && expireTimeObj != "") {
                            map.put("expireTime", sdf.format(sdf.parse(expireTimeObj.toString())));
                        }

                        Object buyTimeObj = map.get("buyTime");
                        if (buyTimeObj != null && buyTimeObj != "") {
                            map.put("expireTime", sdf.format(sdf.parse(buyTimeObj.toString())));
                        }
                    }
                    POIUtils poi = new POIUtils();
                    //判断是否为代理商账户
                    String[] heads = {"推荐人", "被推荐人", "注册时间", "首笔交易时间", "交易返佣金额（元）"};
                    String[] colums = {"inviterName", "userName", "expireTime", "buyTime", "rewardMoney"};
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

}
