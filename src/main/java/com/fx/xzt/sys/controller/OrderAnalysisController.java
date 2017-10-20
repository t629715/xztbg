package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InfoXioudeService;
import com.fx.xzt.sys.service.OrderAnalysisService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by tianliya on 2017/10/15.
 */
@Controller
@RequestMapping("/analysis")
public class OrderAnalysisController {
    @Autowired
    OrderAnalysisService orderAnalysisService;

    /**
     * 获取黄金课堂信息  tianliya
     * @param request
     * @param startTime
     * @param endTime
     * @param agentName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/orderAnalysis",method= RequestMethod.POST)
    @ResponseBody
    public CommonResponse getXioude(HttpServletRequest request, String startTime,
                                            String endTime,  String agentName,Short upOrDown,
                                            Short orderState, Short profitLoss,
                                            Integer pageNum, Integer pageSize){
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = orderAnalysisService.orderAnalysis(startTime,endTime,
                                                                        agentName,upOrDown,orderState,profitLoss,
                                                                        pageNum, pageSize);
                response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                response.setData(pageInfo);
                response.setMsg("操作成功！");
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
