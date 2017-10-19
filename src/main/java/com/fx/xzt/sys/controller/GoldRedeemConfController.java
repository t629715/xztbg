package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.GoldRedeemConfService;
import com.fx.xzt.sys.service.UserLoginInfoService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.StringUtil;

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
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	Integer isEnable = ConstantUtil.GOLD_REDEEM_CONF_ISENABLE_QY;
                List<Map<String, Object>> list = goldRedeemConfService.selectByGoldRedeemConf(isEnable);
                if (list != null && list.size() == 1) {
                	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(list.get(0));
                    cr.setMsg("操作成功！");
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
	* @param userName  账号
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/calculationGoldRedeem")
    @ResponseBody
	public Object calculationGoldRedeem(HttpServletRequest request, @RequestParam Integer price, @RequestParam Double gram, 
			@RequestParam Double poundagePercent, @RequestParam String userName) throws Exception {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	if (price > 0 && gram > 0 && poundagePercent > 0 && StringUtil.isNotEmpty(userName)) {
            		Double amount = price * gram;
            		Double poundage = amount * poundagePercent;
            		Double total = amount - poundage;
            		Map<String, Object> map = new HashMap<String, Object>();
            		map.put("amount", amount);
            		map.put("poundage", poundage);
            		map.put("total", total);
            		cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(map);
                    cr.setMsg("操作成功！");
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
        return cr;
    }
	
}
