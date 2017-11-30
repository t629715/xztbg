package com.fx.xzt.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.GoldRedeem;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.UserMessage;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.GoldRedeemService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserLoginService;
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
* @ClassName: GoldRedeemController 
* @Description: 黄金赎回
* @author htt
* @date 2017-10-19 下午3:06:50 
*
 */
@Controller
@RequestMapping("/goldRedeem")
public class GoldRedeemController {

	@Resource
	GoldRedeemService goldRedeemService;
	@Resource
	UserLoginService userLoginService;
	@Resource
    LogRecordService logRecordService;
	@Resource
	UserMessageService userMessageService;
	
	/**
	 * 
	* @Title: selectByGoldRedeem 
	* @Description: 黄金赎回查询
	* @param request
	* @param userName   用户账号
	* @param startTime  赎回开始时间
	* @param endTime  赎回结束时间
	* @param channelName  渠道商用户账号
	* @param pageNum  页数
	* @param pageSize  条数
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByGoldRedeem")
    @ResponseBody
	public Object selectByGoldRedeem(HttpServletRequest request, String userName, String startTime, String endTime, String channelName,
			@RequestParam Integer pageNum, @RequestParam Integer pageSize) throws Exception {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金赎回查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJSHJL.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = goldRedeemService.selectByGoldRedeem(userName, startTime, endTime, channelName, pageNum, pageSize);
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
	* @Title: excelGoldRedeem 
	* @Description: 黄金赎回查询-导出
	* @param request
	* @param response
	* @param userName  用户账号
	* @param startTime  赎回开始时间
	* @param endTime  赎回结束时间
	* @param channelName 渠道商账号
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelGoldRedeem")
    @ResponseBody
    public void excelGoldRedeem(HttpServletRequest request, HttpServletResponse response, String userName, String startTime, String endTime, 
    		String channelName) throws Exception{
		//操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金赎回记录导出");
        log.setContent("导出失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJSHJL.getName());
        log.setType(ConstantUtil.logRecordType.DC.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
            String tieleName = "黄金赎回记录";
            String excelName = "黄金赎回记录";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                List<Map<String, Object>> list = goldRedeemService.excelGoldRedeem(userName, startTime, endTime, channelName);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                    	Object priceObj =  map.get("price");
                    	Object amountObj =  map.get("amount");
                    	Object poundageObj =  map.get("poundage");
                    	Object createTimeObj = map.get("createTme");
                    	
	               		if (createTimeObj != null && createTimeObj != "") {
	               			map.put("createTme", sdf.format(sdf.parse(createTimeObj.toString())));
	                    }
                    	
                        if (priceObj != null && priceObj != "") {
                        	Double price = Double.valueOf(priceObj.toString());
                        	map.put("price", price/100);
                        }
                        if (amountObj != null && amountObj != "") {
                        	Double amount = Double.valueOf(amountObj.toString());
                        	map.put("amount", amount/100);
                        }
                        if (poundageObj != null && poundageObj != "") {
                        	Double poundage = Double.valueOf(poundageObj.toString());
                        	map.put("poundage", poundage/100);
                        }
                    }
                    POIUtils poi = new POIUtils();
                    String[] heads = {"用户账号", "赎回克重",  "赎回价格", "赎回金额", "手续费", "赎回时间", "渠道商账号"};
                    String[] colums = {"userName", "gram", "price", "amount", "poundage", "createTme", "channelName"};
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
	 * 
	* @Title: selectByGoldRedeemCount 
	* @Description: 黄金赎回--黄金总克重
	* @param request
	* @param userName   用户账号
	* @param startTime  赎回开始时间
	* @param endTime  赎回结束时间
	* @param channelName  渠道商账号
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByGoldRedeemCount")
    @ResponseBody
    public Object selectByGoldRedeemCount(HttpServletRequest request, String userName, String startTime, String endTime, 
    		String channelName) throws Exception{
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金赎回记录统计查询");
        log.setContent("查询失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJSHJL.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map = goldRedeemService.selectByGoldRedeemCount(userName, startTime, endTime, channelName);
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
	* @Title: checkUserName 
	* @Description: 验证用户名是否存在，存在返回用户账户信息
	* @param request
	* @param userName
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/checkUserName")
    @ResponseBody
	public Object checkUserName(HttpServletRequest request, @RequestParam String userName) throws Exception {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金赎回计算验证用户名是否存在");
        log.setContent("验证失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJWZJY.getName());
        log.setType(ConstantUtil.logRecordType.CX.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	if (StringUtil.isNotEmpty(userName)) {
            		List<Map<String, Object>> list = userLoginService.getByAccount(userName);
            		if (list != null && list.size() == 1 && 
            				list.get(0).get("id") != null && list.get(0).get("id") != "") {
            			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                        cr.setData(list.get(0));
                        cr.setMsg("操作成功！");
                        log.setUserId(users.getId());
                        log.setContent("验证成功；信息：userName:" + userName);
            		} 
            	} 
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("无操作权限！");
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
	* @Title: saveGoldRedeem 
	* @Description: 黄金赎回记录--新增
	* @param request
	* @param goldRedeem
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/saveGoldRedeem")
    @ResponseBody
	public Object saveGoldRedeem(HttpServletRequest request, @ModelAttribute GoldRedeem goldRedeem) throws Exception {
        CommonResponse cr = new CommonResponse();
        
        //系统消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserMessage message = new UserMessage();
        message.setMsgID(IdUtil.generateyyyymmddhhMMssSSSAnd2Random());
        message.setMsgTypeID(ConstantUtil.USER_MESSAGE_TYPE_XT);
        message.setMsgTime(sdf.parse(sdf.format(new Date())));
        message.setUserID(Long.parseLong(goldRedeem.getUserIdString()));
        
        //操作日志
        LogRecord log = new LogRecord();
        log.setTitle("黄金赎回记录新增");
        log.setContent("新增失败；信息：" + goldRedeem.toString());
        log.setModuleName(ConstantUtil.logRecordModule.HJSHJL.getName());
        log.setType(ConstantUtil.logRecordType.XZ.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	goldRedeem.setId(IdUtil.generateyymmddhhMMssSSSAnd4Random());
            	goldRedeem.setUserId(Long.parseLong(goldRedeem.getUserIdString()));
            	/*goldRedeem.setAmount(goldRedeem.getAmount() * 100);
            	goldRedeem.setPoundage(goldRedeem.getPoundage() * 100);*/
            	int flag = goldRedeemService.insertGoldRedeem(goldRedeem);
            	if (flag > 0) {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
                    cr.setData("{}");
                    cr.setMsg("操作成功！");
                    //系统消息
					message.setMsgContent("您的黄金已被成功赎回，赎回款项：" + Double.parseDouble(goldRedeem.getAmount().toString()) / 100 + "元已成功充值至您的账户！");
					userMessageService.add(message);
                	//操作日志
                    log.setUserId(users.getId());
                    log.setContent("新增成功；信息：" + goldRedeem.toString());
            	} else {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
                    cr.setData("{}");
                    cr.setMsg("操作失败！");
            	}
            } else {
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("无操作权限！");
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
