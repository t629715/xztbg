package com.fx.xzt.sys.controller;

import com.fx.xzt.redis.RedisService;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import java.util.Set;

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
    RedisService redisService;
    @Resource
    FinanceConfService financeConfService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 获取所有的理财产品信息
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getAllFinanceConf",method= RequestMethod.POST)
    @ResponseBody
    public Object getAllFinanceConf(HttpServletRequest request, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        logger.debug("获取理财产品信息接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String,Object>> pageInfo = financeConfService.getFinanceConfs(pageNum, pageSize);
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

    /**
     * 根据id删除理财产品
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeFinanceConfById",method=RequestMethod.POST)
    @ResponseBody
    public Object removeFinanceConfByProductNo(HttpServletRequest request, Long id,Integer type) {
        logger.debug("获取删除理财产品信息接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Boolean b = financeConfService.removeFinanceConfById(id,type);
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

    /**
     * 修改选中的理财产品
     * @param request
     * @param id
     * @param productNo
     * @param productName
     * @param yearIncomPercent
     * @param cycle
     * @param minMoney
     * @param calcMethod
     * @param redeemMethod
     * @param settleMethod
     * @return
     */
    @RequestMapping(value = "/modifyFinanceConf",method=RequestMethod.POST)
    @ResponseBody
    public Object modifyFinanceConf(HttpServletRequest request, Integer id, String productNo, String productName,
                                    Float yearIncomPercent, Integer cycle, Float minMoney,
                                    Integer calcMethod, Short redeemMethod, Short settleMethod,Integer type) {
        logger.debug("获取修改理财产品信息接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Boolean b = financeConfService.modifyFinanceConf(id, productNo, productName,
                        yearIncomPercent, cycle, minMoney,
                        calcMethod, redeemMethod, settleMethod, type);
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
