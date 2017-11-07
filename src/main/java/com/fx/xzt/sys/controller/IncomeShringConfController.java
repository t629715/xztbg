package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.IncomeSharingConf;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.IncomeSharingConfService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author tianliya
 * @Description:
 * @date 2017-10-09 14:30
 */
@Controller
@RequestMapping("/incomeShringConf")
public class IncomeShringConfController {
    private static final Logger logger = LoggerFactory.getLogger(IncomeShringConfController.class);
    @Resource
    IncomeSharingConfService incomeSharingConfService;


    @RequestMapping(value = "/getIncomeSharingConf",method= RequestMethod.POST)
    @ResponseBody
    public Object getIncomeSharingConf(HttpServletRequest request, Long userId) {
        logger.debug("获取理财产品信息接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                IncomeSharingConf incomeSharingConf = incomeSharingConfService.getIncomeSharingConf(userId);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                response.setData(incomeSharingConf);
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
     * 分成设置
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifySharing",method=RequestMethod.POST)
    @ResponseBody
    public Object modifySharing(HttpServletRequest request, IncomeSharingConf incomeSharingConf) {
        logger.debug("获取分成设置接口");
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int b = incomeSharingConfService.updateIncomeShringConf(incomeSharingConf);
                if (b>0){
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
