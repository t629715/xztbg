package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InfoGoldlessonService;
import com.fx.xzt.sys.service.InfoXioudeService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tianliya on 2017/10/15.
 */
@Controller
@RequestMapping("/xioude")
public class InfoxioudeController {
    @Autowired
    InfoXioudeService xioudeService;
    @Resource
    LogRecordService logRecordService;

    /**
     * 获取黄金课堂信息  tianliya
     * @param request
     * @param title
     * @param startTime
     * @param endTime
     * @param state
     * @param operator
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/getXioude",method= RequestMethod.POST)
    @ResponseBody
    public CommonResponse getXioude(HttpServletRequest request, String title, String startTime,
                                            String endTime, Short state, String operator,
                                            Integer pageNum, Integer pageSize) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("查询希欧德信息");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.XODZX.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = xioudeService.getInfoXioude(title, startTime, endTime,
                                                                        state,operator, pageNum, pageSize);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                response.setData(pageInfo);
                response.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("查询成功");
            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }

    /**
     * @param request
     * @param infoId
     * @return
     * @Author: tianliya
     * @Decription:删除选中的黄金课堂
     * @Date 2017/10/15 22:13
    */
    @RequestMapping(value="/deleteXioude",method=RequestMethod.POST)
    @ResponseBody
    public CommonResponse deleteXioude(HttpServletRequest request, Long infoId) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("删除信息");
        log.setContent("删除失败");
        log.setModuleName(ConstantUtil.logRecordModule.XODZX.getName());
        log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int i = xioudeService.deleteXioudeById(infoId);
                if (i != 0){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
                    response.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("删除成功");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
                    response.setMsg("操作失败！");
                }

            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }

    /**
     * @param request
     * @param infoId
     * @param title
     * @param state
     * @return
     * @Author: tianliya
     * @Decription:修改选中的数据
     * @Date 2017/10/15 22:31
    */
    @RequestMapping(value="/modifyInfoXioude",method=RequestMethod.POST)
    @ResponseBody
    public CommonResponse modifyInfoXioude(HttpServletRequest request, String title, Short state, Long infoId, String contentPath, String imagePath) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("修改信息");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.XODZX.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String operator = users.getUserName();
                int i = xioudeService.modifyXioude(title, state, operator, infoId,contentPath, imagePath);
                if (i != 0){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
                    response.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("修改成功");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
                    response.setMsg("操作失败！");
                }

            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }
    /**
     * @param request
     * @param infoId
     * @return
     * @Author: tianliya
     * @Decription:  预览选中的黄金课堂信息
     * @Date 2017/10/15 22:34
    */
    @RequestMapping(value="/getOneXioude")
    @ResponseBody
    public CommonResponse getOneXioude(HttpServletRequest request, Long infoId) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("查询一条希欧德信息");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map map  = xioudeService.getOneByInfoId(infoId);
                if (map != null){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(map);
                    response.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("查询成功");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(map);
                    response.setMsg("操作失败！");
                }

            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }

    /**
     * 发布
     * @param request
     * @return
     */
    @RequestMapping(value="/releaseXioude")
    @ResponseBody
    public CommonResponse releaseXioude(HttpServletRequest request, String title,String contentPath,String imagePath) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("发布希欧德信息");
        log.setContent("发布失败");
        log.setModuleName(ConstantUtil.logRecordModule.XODZX.getName());
        log.setType(ConstantUtil.logRecordType.XZ.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int i  = xioudeService.releaseXioude(title,contentPath,imagePath,users.getUserName());
                if (i != 0){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
                    response.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("发布成功");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
                    response.setMsg("操作失败！");
                }

            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return response;
    }

    /**
     * 获取发布人
     * @param request
     * @return
     */
    @RequestMapping(value="/getOperators")
    @ResponseBody
    public CommonResponse getOperators(HttpServletRequest request) throws ParseException {
        CommonResponse response = new CommonResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("查询发布人信息");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.LCJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                List list = xioudeService.getOperators();
                if (list != null && list.size() != 0){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(list);
                    response.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("查询成功");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(list);
                    response.setMsg("操作失败！");
                }

            } else {
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
            throw e;
        }
        AuditLog.info(log.toString());
        return response;
    }
}
