package com.fx.xzt.sys.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.GoldRedeemConfService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.service.UserLoginInfoService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.sys.util.log.AuditLog;

/**
 * 
* @ClassName: GoldRedeemConfController 
* @Description: 黄金赎回配置
* @author htt
* @date 2017-10-19 下午4:34:55 
*
 */
@Controller
@RequestMapping("/goldRedeemConf")
public class GoldRedeemConfController {

	@Resource
	GoldRedeemConfService goldRedeemConfService;
	@Resource
	UserLoginInfoService userLoginInfoService;
	@Resource
	RedisTemplate redisTemplate;
	@Resource
    LogRecordService logRecordService;
	
	/**
	 * 
	* @Title: selectByGoldRedeemConfEnable 
	* @Description: 获取已启用的黄金赎回配置
	* @param request
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByGoldRedeemConfEnable")
    @ResponseBody
	public Object selectByGoldRedeemConfEnable(HttpServletRequest request) throws Exception {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("获取已启用的黄金赎回配置");
        log.setContent("获取失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJSHJL.getName());
        log.setType(ConstantUtil.logRecordType.CK.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	//获取黄金实时价格
            	String price = "";
            	String marketString = (String)redisTemplate.opsForValue().get("gold_latest_market");
                if (StringUtil.isNotEmpty(marketString)) {
                	JSONObject  json = JSONObject.parseObject(marketString);
                    price = json.getString("price");
                }
            	Integer isEnable = ConstantUtil.GOLD_REDEEM_CONF_ISENABLE_QY;
                List<Map<String, Object>> list = goldRedeemConfService.selectByGoldRedeemConf(isEnable);
                if (list != null && list.size() == 1) {
                	Map<String, Object> data = list.get(0);
                	if (StringUtil.isNotEmpty(price)) {
                		data.put("price", price);
                	}
                	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(data);
                    cr.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("获取成功");
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
	
	/**
	 * 
	* @Title: calculationGoldRedeem 
	* @Description: 黄金赎回计算
	* @param request
	* @param price  赎回价格
	* @param gram   赎回黄金克重
	* @param poundagePercent  手续费百分比
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/calculationGoldRedeem")
    @ResponseBody
	public Object calculationGoldRedeem(HttpServletRequest request, @RequestParam Double price, @RequestParam Double gram, 
			@RequestParam Double poundagePercent) throws Exception {
        CommonResponse cr = new CommonResponse();
        //操作日志
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogRecord log = new LogRecord();
        log.setTitle("黄金赎回计算");
        log.setContent("计算失败");
        log.setModuleName(ConstantUtil.logRecordModule.HJSHJL.getName());
        log.setType(ConstantUtil.logRecordType.JS.getIndex());
        log.setIp(IPUtil.getHost(request));
        log.setCreateTime(sdf.parse(sdf.format(new Date())));
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	if (price > 0 && gram > 0 && poundagePercent > 0) {
            		DecimalFormat df = new DecimalFormat("#.00");
            		Double amount = price * gram;
            		Double poundage = amount * poundagePercent;
            		Double total = amount - poundage;
            		Map<String, Object> map = new HashMap<String, Object>();
            		map.put("amount", df.format(amount));
            		map.put("poundage", df.format(poundage));
            		map.put("total", df.format(total));
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(map);
                    cr.setMsg("操作成功！");
                    log.setUserId(users.getId());
                    log.setContent("计算成功；信息：amount:" + amount + ";;poundage:" + poundage + ";;total:" + total);
            	} else {
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
                    cr.setData("{}");
                    cr.setMsg("参数错误！");
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
