package com.fx.xzt.sys.controller;

import com.alibaba.fastjson.JSON;
import com.fx.xzt.sys.service.RiskaccessService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author htt
 * @ClassName: RiskaccessController.java
 * @Description: 风险评测Controller
 * @date 2017-09-22 13:03
 */
@Controller
@RequestMapping("/riskaccess")
public class RiskaccessController {

    @Resource
    private RiskaccessService riskaccessService;

    /**
     *  风险评测列表
     * @param userName   用户名
     * @param realName   真实姓名
     * @param startTime  评测开始时间
     * @param endTime    评测结束时间
     * @param accessLevel 评测等级
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/selectByRiskaccessAll")
    @ResponseBody
    public String selectByRiskaccessAll(String userName, String realName, String startTime, String endTime, String accessLevel, @RequestParam Integer pageNum, @RequestParam  Integer pageSize) {
        CommonResponse cr = new CommonResponse();
        try {
            PageInfo<Map<String, Object>> pageInfo = riskaccessService.getByRiskaccessAll(userName, realName, startTime, endTime, accessLevel, pageNum, pageSize);
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
            cr.setData(pageInfo);
            cr.setMsg("操作成功！");
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
           // e.printStackTrace();
        }
        return JSON.toJSONString(cr);
    }

    /**
     *  更改风险评测等级
     * @param level
     * @param accessId
     * @return
     */
    @RequestMapping(value="/updateLevelById")
    @ResponseBody
    public String updateLevelById(@RequestParam String level, @RequestParam Integer accessId) {
        CommonResponse cr = new CommonResponse();
        cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
        cr.setData("{}");
        cr.setMsg("操作失败！");
        try {
            if (level != null && !level.equals("") && level.length() > 0 && accessId > 0) {
                int flag = riskaccessService.updateLevelById(level, accessId);
                if (flag > 0) {
                    cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS);
                    cr.setData("{}");
                    cr.setMsg("操作成功！");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return JSON.toJSONString(cr);
    }
}
