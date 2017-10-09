package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.FinanceConf;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.FinanceConfService;
import com.fx.xzt.sys.service.FinanceOrderService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @ClassName: FinanceConfController.java
 * @Description:
 * @date 2017-10-09 14:30
 */
@Controller
@RequestMapping("/financeConf")
public class FinanceConfController {
    private static final Logger logger = LoggerFactory.getLogger(FinanceConfController.class);

    @Resource
    FinanceConfService financeConfService;
    @RequestMapping(value = "/getAllFinanceConf")
    @ResponseBody
    public Object getAllFinanceConf(HttpServletRequest request, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        logger.debug("获取理财产品信息接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<FinanceConf> pageInfo = financeConfService.getFinanceConfs(pageNum, pageSize);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                response.setData(pageInfo);
                response.setMsg("操作成功！");
            } else {
                logger.debug("没有登录");
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                response.setData("{}");
                response.setMsg("操作失败！");
            }
        } catch (Exception e) {
            logger.debug("没有登录");
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData("{}");
            response.setMsg("操作失败！");
            throw e;
        }
        return response;
    }

    @RequestMapping(value = "/removeFinanceConfByProductNo")
    @ResponseBody
    public Object removeFinanceConfByProductNo(HttpServletRequest request, Integer productNo) {
        logger.debug("获取删除理财产品信息接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Boolean b = financeConfService.removeFinanceConfByProductNo(productNo);
                if (b){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(b);
                    response.setMsg("操作成功！");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                    response.setData(b);
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
        return response;
    }
}
