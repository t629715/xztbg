package com.fx.xzt.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.ConfigParamService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: ConfigParamController 
* @Description: 系统参数
* @author htt
* @date 2018-6-11 上午10:56:02 
*
 */
@Controller
@RequestMapping("/configParam")
public class ConfigParamController {
	
	@Resource
	ConfigParamService configParamService;
	@Resource
    LogRecordService logRecordService;
	
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param request
	* @param paramName
	* @param pageNum
	* @param pageSize
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByAll")
    @ResponseBody
    public Object selectByAll(HttpServletRequest request, String paramName, 
    		@RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("系统参数查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.XTCS.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = configParamService.selectByAll(paramName, pageNum, pageSize);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(pageInfo);
                cr.setMsg("操作成功！");
                log.setUserId(users.getId());
                log.setContent("查询成功");
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
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }
	
	/**
	 * 
	* @Title: add 
	* @Description: 新增
	* @param request
	* @param paramName 参数名
	* @param paramValue 参数值
	* @param valueType 类型
	* @param description 描述
	* @param cacheRegion 缓存
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/add")
    @ResponseBody
    public Object add(HttpServletRequest request, @RequestParam  String paramName, String paramValue, String valueType,
			String description, String cacheRegion) throws Exception{
		
		CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("系统参数查询");
        log.setContent("新增失败");
        log.setModuleName(ConstantUtil.logRecordModule.XTCS.getName());
        log.setType(ConstantUtil.logRecordType.XZ.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	int flag = configParamService.insert(paramName, paramValue, valueType, description, cacheRegion);
            	if (flag > 0) {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData("{}");
                    cr.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("新增成功");
            	} else {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
                    cr.setData("{}");
                    cr.setMsg("操作失败！");
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
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }
	
	/**
	 * 
	* @Title: edit 
	* @Description: 修改
	* @param request
	* @param paramName 参数名
	* @param paramValue 参数值
	* @param valueType 类型
	* @param description 描述
	* @param cacheRegion 存储类型
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/edit")
    @ResponseBody
    public Object edit(HttpServletRequest request, @RequestParam  String paramName, String paramValue, String valueType,
			String description, String cacheRegion) throws Exception{
		
		CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("系统参数查询");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.XTCS.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	int flag = configParamService.update(paramName, paramValue, valueType, description, cacheRegion);
            	if (flag > 0) {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData("{}");
                    cr.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("修改成功");
            	} else {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
                    cr.setData("{}");
                    cr.setMsg("操作失败！");
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
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }
	
	/**
	 * 
	* @Title: delete 
	* @Description: 删除
	* @param request 
	* @param paramName 参数名
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public Object delete(HttpServletRequest request, @RequestParam  String paramName) throws Exception{
		
		CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("系统参数查询");
        log.setContent("删除失败");
        log.setModuleName(ConstantUtil.logRecordModule.XTCS.getName());
        log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	int flag = configParamService.deleteByName(paramName);
            	if (flag > 0) {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData("{}");
                    cr.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("删除成功");
            	} else {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
                    cr.setData("{}");
                    cr.setMsg("操作失败！");
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
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }


}
