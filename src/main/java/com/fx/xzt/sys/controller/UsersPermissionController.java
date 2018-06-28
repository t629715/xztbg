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
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UsersPermissionService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;

/**
 * 
* @ClassName: UsersPermissionController 
* @Description: 菜单管理
* @author htt
* @date 2018-6-11 下午5:35:18 
*
 */
@Controller
@RequestMapping("/usersPermission")
public class UsersPermissionController {

	@Resource
	UsersPermissionService usersPermissionService;
	@Resource
    LogRecordService logRecordService;

	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param request
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByAll")
    @ResponseBody
    public Object selectByAll(HttpServletRequest request) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("系统菜单查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.XTCD.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	Map<String, Object> permission = usersPermissionService.getByUsersPermissionAllNew1();
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(permission);
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
	* @param text 描述
	* @param icon 图标
	* @param label
	* @param translate 现实的功能名 与 text同步
	* @param pid 父级id
	* @param type 层级类型
	* @param sref 地址
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/add")
    @ResponseBody
    public Object add(HttpServletRequest request, @RequestParam  String text, String icon, String label, String translate,
			String pid, String type, String sref) throws Exception{
		
		CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("系统菜单新增");
        log.setContent("新增失败");
        log.setModuleName(ConstantUtil.logRecordModule.XTCD.getName());
        log.setType(ConstantUtil.logRecordType.XZ.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	int flag = usersPermissionService.insert(text, icon, label, translate, pid, type, sref);
            	if (flag > 0) {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
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
	* @param id
	* @param text 描述
	* @param icon 图标
	* @param label
	* @param translate 现实的功能名 与 text同步
	* @param pid 父级id
	* @param type 层级类型
	* @param sref 地址
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/edit")
    @ResponseBody
    public Object edit(HttpServletRequest request, @RequestParam  String id, String text, String icon, String label, String translate,
			String pid, String type, String sref) throws Exception{
		
		CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("系统菜单修改");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.XTCD.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	int flag = usersPermissionService.update(text, icon, label, translate, pid, type, sref, id);
            	if (flag > 0) {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
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
	* @param id id
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/delete")
    @ResponseBody
    public Object delete(HttpServletRequest request, @RequestParam  String id) throws Exception{
		
		CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("系统菜单删除");
        log.setContent("删除失败");
        log.setModuleName(ConstantUtil.logRecordModule.XTCD.getName());
        log.setType(ConstantUtil.logRecordType.WLSC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	int flag = usersPermissionService.deleteById(id);
            	if (flag > 0) {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
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
