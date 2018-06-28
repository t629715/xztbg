package com.fx.xzt.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.SaveGoldRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: SaveGoldRecordController 
* @Description: 存金记录
* @author htt
* @date 2018-5-29 下午3:08:42 
*
 */
@Controller
@RequestMapping("/saveGoldRecord")
public class SaveGoldRecordController {
	
	@Resource
    LogRecordService logRecordService;
	@Resource
	SaveGoldRecordService saveGoldRecordService;
	
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param request
	* @param userName 用户名
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param agentName 代理商id
	* @param brokerName 经纪人id
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
    public Object selectByAll(HttpServletRequest request, String userName, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, Short type, 
    		@RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        if (type.toString().equals(ConstantUtil.saveGoldRecordType.B.toString())) {
        	log.setTitle("黄金交易（买）查询");
        	log.setModuleName(ConstantUtil.logRecordModule.HJJYB.getName());
        } else if (type.toString().equals(ConstantUtil.saveGoldRecordType.S.toString())) {
        	log.setTitle("黄金交易（卖）查询");
        	log.setModuleName(ConstantUtil.logRecordModule.HJJYS.getName());
        }
        log.setContent("查询失败");
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                String agentNameStr = agentName;
                String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                PageInfo<Map<String, Object>> pageInfo = saveGoldRecordService.selectByAll(userName, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, type, isView, pageNum, pageSize);
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
	* @Title: excelAll 
	* @Description: 导出
	* @param request
	* @param response
	* @param userName
	* @param startTime
	* @param endTime
	* @param regStartTime
	* @param regEndTime
	* @param agentName
	* @param brokerName
	* @param type
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelAll")
    @ResponseBody
    public void excelAll(HttpServletRequest request, HttpServletResponse response, 
    		String userName, String startTime, String endTime, String regStartTime, String regEndTime, 
    		String agentName, String brokerName, Short type) throws Exception{
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        if (type.toString().equals(ConstantUtil.saveGoldRecordType.B.toString())) {
        	log.setTitle("黄金交易（买）导出");
        	log.setModuleName(ConstantUtil.logRecordModule.HJJYB.getName());
        } else if (type.toString().equals(ConstantUtil.saveGoldRecordType.S.toString())) {
        	log.setTitle("黄金交易（卖）导出");
        	log.setModuleName(ConstantUtil.logRecordModule.HJJYS.getName());
        }
        log.setContent("导出失败");
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
    	try {
    		String tieleName = "黄金交易";
            String excelName = "黄金交易";
    		if (type.toString().equals(ConstantUtil.saveGoldRecordType.B.toString())) {
    			tieleName = "黄金交易（买）";
                excelName = "黄金交易（买）";
            } else if (type.toString().equals(ConstantUtil.saveGoldRecordType.S.toString())) {
            	tieleName = "黄金交易（卖）";
                excelName = "黄金交易（卖）";
            }
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
            	String isView = "0";
		        if (role != null && role.get("roleIsView") != null) {
		            isView = role.get("roleIsView").toString();
		        }
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                List<Map<String, Object>> list = saveGoldRecordService.excelByAll(userName, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, type, isView);
                    POIUtils poi = new POIUtils();
                    //判断是否为代理商账户
                    if (users.getPid() != null && users.getPid() == 1) {
                    	if (type.toString().equals(ConstantUtil.saveGoldRecordType.B.toString())) {
                    		String[] heads = {"用户账号", "注册时间", "经纪人", "买入价", "黄金克重", "买入金额", "买入时间"};
                            String[] colums = {"userName", "registerTime", "brokerName", "goldPrice", "amount", "rmbAmount", "createTime"};
                            poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                        } else if (type.toString().equals(ConstantUtil.saveGoldRecordType.S.toString())) {
                        	String[] heads = {"用户账号", "注册时间", "经纪人", "单位成本价", "卖出价", "黄金克重", "卖出金额", "手续费", "卖出时间", "盈亏合计"};
                            String[] colums = {"userName", "registerTime", "brokerName", "averagePrice", "goldPrice", "amount", "rmbAmount", "saleFee", "profitAndLoss", "createTime"};
                            poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                        }
                    } else if (users.getPid() == null || users.getPid() == 0) {
                    	if (type.toString().equals(ConstantUtil.saveGoldRecordType.B.toString())) {
                    		String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "买入价", "黄金克重", "买入金额", "买入时间"};
                            String[] colums = {"userName", "registerTime", "agentName", "brokerName", "goldPrice", "amount", "rmbAmount", "createTime"};
                            poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                        } else if (type.toString().equals(ConstantUtil.saveGoldRecordType.S.toString())) {
                        	String[] heads = {"用户账号", "注册时间", "代理商", "经纪人", "单位成本价", "卖出价", "黄金克重", "卖出金额", "手续费", "卖出时间", "盈亏合计"};
                            String[] colums = {"userName", "registerTime", "agentName", "brokerName", "averagePrice", "goldPrice", "amount", "rmbAmount", "saleFee", "createTime", "profitAndLoss"};
                            poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                        }
                    }
                    log.setUserId(users.getId());
                    log.setContent("导出成功，共：" + list.size() + "条数据");
                }
        } catch (Exception e) {
            throw e;
        }
    	logRecordService.add(log);
        AuditLog.info(log.toString());
    }
	
	/**
	 * 
	* @Title: countByAll 
	* @Description: 统计
	* @param request
	* @param userName 用户名
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param regStartTime 注册开始时间
	* @param regEndTime 注册结束时间
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param type 类型；1：买入；4：卖出
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/countByAll")
    @ResponseBody
    public Object countByAll(HttpServletRequest request, String userName, String startTime, String endTime, 
    		String regStartTime, String regEndTime, String agentName, String brokerName, Short type) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        if (type.toString().equals(ConstantUtil.saveGoldRecordType.B.toString())) {
        	log.setTitle("黄金交易（买）统计查询");
        	log.setModuleName(ConstantUtil.logRecordModule.HJJYB.getName());
        } else if (type.toString().equals(ConstantUtil.saveGoldRecordType.S.toString())) {
        	log.setTitle("黄金交易（卖）统计查询");
        	log.setModuleName(ConstantUtil.logRecordModule.HJJYS.getName());
        }
        log.setContent("查询失败");
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            Map<String, Object> role = (Map<String, Object>)httpSession.getAttribute("currentUserRole");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getId().toString();
                }
                Map<String, Object> map = saveGoldRecordService.countByAll(userName, startTime, endTime, 
                		regStartTime, regEndTime, agentNameStr, brokerName, type);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(map);
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
    @RequestMapping(value="/getSaveGoldAmount",method= RequestMethod.POST)
    @ResponseBody
    public Object getSaveGoldAmount(HttpServletRequest request) throws ParseException {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse = saveGoldRecordService.countBuyGold();
        return commonResponse;
    }

}
