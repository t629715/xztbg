package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.UserChannelSourceService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author WuJiaNan
 * Created time 2018/7/17
 * @Description
 */
@RestController
@RequestMapping("/registFrom")
public class RegistCountController {
    private static final Logger logger = LoggerFactory.getLogger(GoldRightDealConfController.class);
    @Resource
    private UserChannelSourceService userChannelSourceService;
    @RequestMapping(value = "/countRegistFrom", method = RequestMethod.POST)
    public CommonResponse countRegistFrom(HttpServletRequest request,Integer pageNum, Integer pageSize) {

        CommonResponse cr = new CommonResponse();
        try {
//            HttpSession httpSession = request.getSession();
//            Users users = (Users) httpSession.getAttribute("currentUser");
//           if(users!=null){
               PageInfo<Map<String, Object>> mapPageInfo = userChannelSourceService.coutRegistFrom(pageNum, pageSize);
               cr.setCode(Constant.RESCODE_SUCCESS);
               cr.setMsg("查询成功!");
               cr.setData(mapPageInfo);
//           }
//            logger.debug("没有登录");
//            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
//            cr.setData("{}");
//            cr.setMsg("没有登录！");
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败-请求异常！");
            throw e;
        }
        return cr;
    }


}
