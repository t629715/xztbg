package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.FinanceConf;
import com.fx.xzt.sys.entity.GoldRightDealConf;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.FinanceConfService;
import com.fx.xzt.sys.service.GoldRightDealConfService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author tianliya
 * @ClassName: GoldRightDealConfController.java
 * @Description:
 * @date 2017-10-10 13:30
 */
@Controller
@RequestMapping("/goldRightDealConf")
public class GoldRightDealConfController {
    private static final Logger logger = LoggerFactory.getLogger(GoldRightDealConfController.class);

    @Resource
    GoldRightDealConfService goldRightDealConfService;
    @RequestMapping(value = "/getAllGoldRight")
    @ResponseBody
    public Object getAllGoldRight(HttpServletRequest request, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        logger.debug("获取理财产品信息接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<GoldRightDealConf> pageInfo = goldRightDealConfService.getAllGoldRight(pageNum,pageSize);
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



    @RequestMapping(value = "/modifyGoldRightDealConf")
    @ResponseBody
    public Object modifyGoldRightDealConf(HttpServletRequest request, Integer id, String name, Integer contract,
                                    Integer buyPercent, Double pointCount, Integer volatility,
                                    Integer minGramPerOrder, Integer maxGramPerOrder, Integer maxPositionCount,
                                    Integer maxBuyCountPerDay, Double stopProfitSet, Integer blowingUpSet) {
        logger.debug("获取修改进群规则信息接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Boolean b = goldRightDealConfService.updateByPrimaryKey(id, name, contract,
                         buyPercent, pointCount, volatility,
                         minGramPerOrder, maxGramPerOrder, maxPositionCount,
                         maxBuyCountPerDay, stopProfitSet, blowingUpSet);
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
