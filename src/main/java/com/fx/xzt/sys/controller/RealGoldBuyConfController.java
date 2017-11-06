package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.RealGoldBuyConfService;
import com.fx.xzt.sys.service.RealGoldConfService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tianliya
 * @ClassName: RealGoldBuyConfController.java
 * @Description: 贵金属设定Controller
 * @date 2017-10-16 13:02
 */
@Controller
@RequestMapping("/realGoldBuyConf")
public class RealGoldBuyConfController {

    @Resource
    RealGoldBuyConfService realGoldBuyConfService;

    /**
     * 获取实金买卖设定
     * @param request
     * @return
     */
    @RequestMapping(value="/getRealGoldBuyConf",method= RequestMethod.POST)
    @ResponseBody
    public Object getRealGoldBuyConf(HttpServletRequest request) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                List list = realGoldBuyConfService.selectAll();
                if (list != null && list.size() != 0){
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(list);
                    cr.setMsg("操作成功！");
                }else {
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(list);
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
        }
        return cr;
    }

    /**
     * 编辑实金买卖设定
     * @param request
     * @param id
     * @param name
     * @param productNo
     * @param cycle
     * @param redeemMethod
     * @param settleMethod
     * @param calcMethod
     * @param calcStartPoint
     * @return
     */
    @RequestMapping(value="/editRealGoldBuyConf",method=RequestMethod.POST)
    @ResponseBody
    public Object eidtRealGoldBuyConf(HttpServletRequest request, Long id, String name, String productNo,
                                      Integer cycle, Short redeemMethod, Short settleMethod,
                                      Short calcMethod, Float calcStartPoint) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int i = realGoldBuyConfService.modify(id, name, productNo,
                        cycle, redeemMethod, settleMethod,
                        calcMethod, calcStartPoint);
                if (i != 0){
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(true);
                    cr.setMsg("操作成功！");
                }else {
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(false);
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
        }
        return cr;
    }
    @RequestMapping(value="/removeRealGoldBuyConf",method=RequestMethod.POST)
    @ResponseBody
    public Object removeRealGoldBuyConf(HttpServletRequest request, Long id) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int i = realGoldBuyConfService.deleteById(id);
                if (i != 0){
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(i);
                    cr.setMsg("操作成功！");
                }else {
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    cr.setData(i);
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
        }
        return cr;
    }
}