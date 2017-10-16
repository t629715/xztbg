package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.RealGoldConf;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.RealGoldConfService;
import com.fx.xzt.sys.service.RealGoldOrderService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @ClassName: RealGoldConfController.java
 * @Description: 实金买卖设定Controller
 * @date 2017-10-16 13:02
 */
@Controller
@RequestMapping("/realGoldConf")
public class RealGoldConfController {

    @Resource
    RealGoldConfService realGoldConfService;

    /**
     * 获取实金买卖设定
     * @param request
     * @return
     */
    @RequestMapping(value="/getRealGoldConf")
    @ResponseBody
    public Object getRealGoldConf(HttpServletRequest request) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                List list = realGoldConfService.getRealGoldConf();
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
     * @param buyPoundage
     * @param insurance
     * @param logisticsFee
     * @param sellPoundage
     * @param maxBuyCount
     * @return
     */
    @RequestMapping(value="/editRealGoldConf")
    @ResponseBody
    public Object eidtRealGoldConf(HttpServletRequest request, Long id, String name, Integer buyPoundage,
                                   Integer insurance, Integer logisticsFee,
                                   Integer sellPoundage, Long maxBuyCount) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int i = realGoldConfService.edit(id, name, buyPoundage,
                         insurance, logisticsFee,
                         sellPoundage, maxBuyCount);
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