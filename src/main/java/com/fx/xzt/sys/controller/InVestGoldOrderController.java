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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.UserMessage;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InVestGoldOrderService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserMessageService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.IdUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: InVestGoldOrderController 
* @Description: 投资金条订单
* @author htt
* @date 2018-4-23 下午2:41:11 
*
 */
@Controller
@RequestMapping("/inVestGoldOrder")
public class InVestGoldOrderController {
	
	@Resource
    LogRecordService logRecordService;
	@Resource
	InVestGoldOrderService inVestGoldOrderService;
	@Resource
	UserMessageService userMessageService;
	
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
	* @param status 订单状态 0:待支付  10:未发货 30 ：已完成
	* @param payType 提取类型  1:实金提取  2：实金兑换
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
    		String agentName, String brokerName, Integer status, Integer payType, 
    		@RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("金条订单交易查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
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
                PageInfo<Map<String, Object>> pageInfo = inVestGoldOrderService.selectByAll(userName, startTime, endTime, 
                		agentNameStr, brokerName, status, payType, isView, pageNum, pageSize);
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
	* @param userName 用户名
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param status 状态
	* @param payType 支付方式
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelAll")
    @ResponseBody
    public void excelAll(HttpServletRequest request, HttpServletResponse response, 
    		String userName, String startTime, String endTime, String agentName, String brokerName, 
    		Integer status, Integer payType) throws Exception{
    	//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("金条订单交易导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
    	try {
            String tieleName = "金条订单";
            String excelName = "金条订单";
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
                List<Map<String, Object>> list = inVestGoldOrderService.excelByAll(userName, startTime, endTime, 
                		agentNameStr, brokerName, status, payType, isView);
                    POIUtils poi = new POIUtils();
                    //判断是否为代理商账户
                    if (users.getPid() != null && users.getPid() == 1) {
                    	String[] heads = {"用户账号", "经纪人","提取类型" ,"数量","克重", "买入价", "买入金额", "手续费",
                                "物流费", "联系人", "联系电话", "收货地址","申请提取时间", "发货时间","状态", "物流单号"};
                        String[] colums = {"userName", "brokerName", "payType","goldNum", "goldTotalWeight", "goldBasePrice", "goldMoney", "serviceMoney",
                                "logisticsFee", "contactName","contactPhone","deliveryAddress", "createTime", "sendTime","status",  "logisticsNo"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                    } else if (users.getPid() == null || users.getPid() == 0) {
                        String[] heads = {"用户账号", "代理商", "经纪人","提取类型" ,"数量",  "克重", "买入价", "买入金额", "手续费",
                                "物流费", "联系人", "联系电话", "收货地址","申请提取时间", "发货时间","状态", "物流单号"};
                        String[] colums = {"userName", "agentName", "brokerName", "payType","goldNum", "goldTotalWeight", "goldBasePrice", "goldMoney", "serviceMoney",
                                "logisticsFee", "contactName","contactPhone","deliveryAddress", "createTime", "sendTime","status", "logisticsNo"};
                        poi.doExport(request, response, list, tieleName, excelName, heads, colums);
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
     *金权交割
     * @Title: selectByAll
     * @Description: 查询
     * @param request
     * @param userName 用户名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param agentName 代理商id
     * @param brokerName 经纪人id
     * @param status 订单状态 0:待支付  10:未发货 30 ：已完成
     * @param payType 交割状态 3:金权交易手动交割 4:自动交割 5:选择交割
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException    设定文件
     * @return Object    返回类型
     * @throws
     * @author  liaijiao
     */
    @RequestMapping(value="/selectByAllDelivery")
    @ResponseBody
    public Object selectByAllDelivery(HttpServletRequest request, String userName, String startTime, String endTime,
                              String agentName, String brokerName, Integer status, Integer payType,
                              @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {

        CommonResponse cr = new CommonResponse();

        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("金条订单交易查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
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
                PageInfo<Map<String, Object>> pageInfo = inVestGoldOrderService.selectByAllDelivery(userName, startTime, endTime,
                        agentNameStr, brokerName, status, payType, isView, pageNum, pageSize);
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
     * @Description: 金权交割导出
     * @param request
     * @param response
     * @param userName 用户名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param agentName 代理商id
     * @param brokerName 经纪人id
     * @param status 状态
     * @param payType 支付方式
     * @throws Exception    设定文件
     * @return void    返回类型
     * @throws
     * @author htt
     */
    @RequestMapping(value="/excelAllDelivery")
    @ResponseBody
    public void excelAllDelivery(HttpServletRequest request, HttpServletResponse response,
                         String userName, String startTime, String endTime, String agentName, String brokerName,
                         Integer status, Integer payType) throws Exception{
        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("金权交割订单交易导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            String tieleName = "金权交割订单";
            String excelName = "金权交割订单";
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
                List<Map<String, Object>> list = inVestGoldOrderService.excelByAllDelivery(userName, startTime, endTime,
                        agentNameStr, brokerName, status, payType, isView);
                POIUtils poi = new POIUtils();
                //判断是否为代理商账户
                if (users.getPid() != null && users.getPid() == 1) {
                    String[] heads = {"用户账号", "经纪人", "克重", "买入价", "合约金额", "手续费","加工费",
                            "物流费","发票","订单金额","优惠金额","实际支付", "联系人", "联系电话", "收货地址","支付时间", "发货时间","完成时间","订单状态", "交割状态", "物流单号"};
                    String[] colums = {"userName", "brokerName",  "goldTotalWeight", "goldBasePrice", "goldMoney", "investGoldService","processingService",
                            "logisticsFee","invoiceService","totalMoney","discountAmount","actualPayment", "contactName","contactPhone","deliveryAddress", "payTime", "sendTime","endTime","status", "payType", "logisticsNo"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                } else if (users.getPid() == null || users.getPid() == 0) {
                    String[] heads = {"用户账号", "代理商", "经纪人",  "克重", "买入价", "合约金额", "手续费","加工费",
                            "物流费","发票","订单金额","优惠金额","实际支付", "联系人", "联系电话", "收货地址","支付时间", "发货时间","完成时间","订单状态", "交割状态", "物流单号"};
                    String[] colums = {"userName", "agentName", "brokerName", "goldTotalWeight", "goldBasePrice", "goldMoney", "investGoldService","processingService",
                            "logisticsFee","invoiceService","totalMoney","discountAmount","actualPayment", "contactName","contactPhone","deliveryAddress", "payTime", "sendTime","endTime","status", "payType", "logisticsNo"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
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
	* @param agentName 代理商id
	* @param brokerName 经纪人id
	* @param status 状态
	* @param payType 支付方式
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/countByAll")
    @ResponseBody
    public Object countByAll(HttpServletRequest request, String userName, String startTime, String endTime, 
    		String agentName, String brokerName, Integer status, Integer payType) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("金条订单统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
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
                Map<String, Object> map = inVestGoldOrderService.countByAll(userName, startTime, endTime, agentNameStr, brokerName, status, payType);
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
    /**
     *
     * @Title: countByAll
     * @Description: 统计
     * @param request
     * @param userName 用户名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param agentName 代理商id
     * @param brokerName 经纪人id
     * @param status 状态
     * @param payType 支付方式
     * @return
     * @throws ParseException    设定文件
     * @return Object    返回类型
     * @throws
     * @author htt
     */
    @RequestMapping(value="/countByAllDelivery")
    @ResponseBody
    public Object countByAllDelivery(HttpServletRequest request, String userName, String startTime, String endTime,
                             String agentName, String brokerName, Integer status, Integer payType) throws ParseException {

        CommonResponse cr = new CommonResponse();

        //操作日志
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("金条订单统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
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
                Map<String, Object> map = inVestGoldOrderService.countByAllDelivery(userName, startTime, endTime, agentNameStr, brokerName, status, payType);
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

	/**
	 * 
	* @Title: addLogisticsNoById 
	* @Description: 填写物流单号
	* @param request
	* @param logisticsNo 物流单号
	* @param id 订单id
	* @param userId 用户id
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/addLogisticsNoById")
    @ResponseBody
    public Object addLogisticsNoById(HttpServletRequest request, @RequestParam String logisticsNo, 
    		@RequestParam String id, @RequestParam Long userId) throws ParseException {
        CommonResponse cr = new CommonResponse();
        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
        cr.setMsg("操作失败！");
        
        //系统消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserMessage message = new UserMessage();
        message.setMsgID(IdUtil.generateyyyymmddhhMMssSSSAnd2Random());
        message.setMsgTypeID(ConstantUtil.USER_MESSAGE_TYPE_XT);
        message.setMsgTime(sdf.parse(sdf.format(new Date())));
        message.setUserID(userId);
        
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("金条订单单号添加");
        log.setContent("添加失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
        	if (users != null) {
        		if (StringUtil.isNotEmpty(logisticsNo) && StringUtil.isNotEmpty(id)) {
        			int flag = inVestGoldOrderService.addLogisticsNoById(logisticsNo, Long.valueOf(id), users.getId(), users.getUserName());
                    if (flag > 0) {
                        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
                        cr.setMsg("操作成功！");
                        //系统消息
    					message.setMsgContent("您的金条订单已发货，物流单号为：" + logisticsNo + "！");
    					userMessageService.add(message);
                    	//操作日志
                        log.setUserId(users.getId());
                        log.setContent("添加成功，信息：id:" + id + ";;logisticsNo:" + logisticsNo);
                    }
                }
        	} else {
        		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("无操作权限！");
        	}
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }
	
	/**
	 * 
	* @Title: updateLogisticsNoById 
	* @Description:  黄金提取-物流单号变更
	* @param logisticsNo  物流单号
	* @param updateTime 修改时间
	* @param id id
	* @return    设定文件 
	* @return Object    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/updateLogisticsNoById")
    @ResponseBody
    public Object updateLogisticsNoById(HttpServletRequest request, @RequestParam String logisticsNo, 
    		@RequestParam String id, @RequestParam Long userId) throws ParseException {
        CommonResponse cr = new CommonResponse();
        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
        cr.setMsg("操作失败！");
        
        //系统消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserMessage message = new UserMessage();
        message.setMsgID(IdUtil.generateyyyymmddhhMMssSSSAnd2Random());
        message.setMsgTypeID(ConstantUtil.USER_MESSAGE_TYPE_XT);
        message.setMsgTime(sdf.parse(sdf.format(new Date())));
        message.setUserID(userId);
        
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("金条订单物流单号修改");
        log.setContent("修改失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
        	if (users != null) {
        		if (StringUtil.isNotEmpty(logisticsNo) && StringUtil.isNotEmpty(id)) {
                    int flag = inVestGoldOrderService.updateLogisticsNoById(logisticsNo, Long.valueOf(id), users.getId(), users.getUserName());
                    if (flag > 0) {
                        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
                        cr.setMsg("操作成功！");
                        //系统消息
    					message.setMsgContent("您的金条订单物流单号已更改，新物流单号为：" + logisticsNo + "！");
    					userMessageService.add(message);
                    	//操作日志
                        log.setUserId(users.getId());
                        log.setContent("添加成功，信息：id:" + id + ";;logisticsNo:" + logisticsNo);
                    }
                }
        	} else {
        		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
        	}
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }
	
	/**
	 * 
	* @Title: completeOrderById 
	* @Description: 订单完成
	* @param request
	* @param id id
	* @param userId 用户id
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/completeOrderById")
    @ResponseBody
    public Object completeOrderById(HttpServletRequest request, @RequestParam String id, 
    		@RequestParam Long userId) throws ParseException {
        CommonResponse cr = new CommonResponse();
        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
        cr.setMsg("操作失败！");
        
        //系统消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserMessage message = new UserMessage();
        message.setMsgID(IdUtil.generateyyyymmddhhMMssSSSAnd2Random());
        message.setMsgTypeID(ConstantUtil.USER_MESSAGE_TYPE_XT);
        message.setMsgTime(sdf.parse(sdf.format(new Date())));
        message.setUserID(userId);
        
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("更新金条订单状态为已完成");
        log.setContent("更新失败");
        log.setModuleName(ConstantUtil.logRecordModule.SJTZGL.getName());
        log.setType(ConstantUtil.logRecordType.XG.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
        	if (users != null) {
        		if (StringUtil.isNotEmpty(id)) {
                    int flag = inVestGoldOrderService.updateStatusById(ConstantUtil.inVestGoldOrderStatus.YWC.toString(), 
                    		Long.valueOf(id), users.getId(), users.getUserName());
                    if (flag > 0) {
                        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
                        cr.setMsg("操作成功！");
                        //系统消息
    					message.setMsgContent("您的金条订单已完成！");
    					userMessageService.add(message);
                    	//操作日志
                        log.setUserId(users.getId());
                        log.setContent("更新成功！");
                    }
                }
        	} else {
        		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
        	}
        } catch (Exception e) {
            throw e;
        }
        logRecordService.add(log);
        AuditLog.info(log.toString());
        return cr;
    }

}
