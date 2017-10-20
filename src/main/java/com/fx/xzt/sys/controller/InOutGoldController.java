package com.fx.xzt.sys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InOutGoldService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: InOutGoldController 
* @Description: 出入金管理
* @author htt
* @date 2017-10-20 下午2:40:45 
*
 */
@Controller
@RequestMapping("/inOutGold")
public class InOutGoldController {

	@Resource
	InOutGoldService inOutGoldService;
	
	@RequestMapping(value="/selectByInOutGold")
    @ResponseBody
    public Object selectByInOutGold(HttpServletRequest request, String userName,  String agentName, String brokerName, 
    		@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        CommonResponse cr = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = inOutGoldService.selectByDealOrder(userName, agentName, brokerName, pageNum, pageSize);
                cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(pageInfo);
                cr.setMsg("操作成功！");
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

	@RequestMapping(value="/excelInOutGold")
    @ResponseBody
    public void excelInOutGold(HttpServletRequest request, HttpServletResponse response, String userName, String agentName, String brokerName){
        try {
            String tieleName = "出入金查询";
            String excelName = "出入金查询";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                String agentNameStr = agentName;
                if (users.getPid() != null &&  users.getPid() == 1) {
                    agentNameStr = users.getUserName();
                }
                List<Map<String, Object>> list = inOutGoldService.excelDealOrder(userName, agentNameStr, brokerName);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                    	map.put("cj", StringUtil.fundsHandle(map.get("cj")));
                    	map.put("rj", StringUtil.fundsHandle(map.get("rj")));
                    	map.put("finance", StringUtil.fundsHandle(map.get("finance")));
                    	map.put("totalIncome", StringUtil.fundsHandle(map.get("totalIncome")));
                    	map.put("sjAmount", StringUtil.fundsHandle(map.get("sjAmount")));
                    	map.put("cbf", StringUtil.fundsHandle(map.get("cbf")));
                    }
                    POIUtils poi = new POIUtils();
                    //判断是否为代理商账户
                    String[] heads = {"姓名", "用户账号",  "代理商", "经纪人", "入金", "出金", "成本", "利息", "理财", "买入黄金资金"};
                    String[] colums = {"realName", "userName", "agentName", "brokerName", "cj", "rj", "cbf", "totalIncome", "finance","sjAmount"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
