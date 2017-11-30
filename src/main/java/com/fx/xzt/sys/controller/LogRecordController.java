package com.fx.xzt.sys.controller;

import java.text.ParseException;
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
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.util.POIUtils;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: LogRecordController 
* @Description: 操作日志
* @author htt
* @date 2017-11-30 下午4:12:24 
*
 */
@Controller
@RequestMapping("/logRecord")
public class LogRecordController {

	@Resource
    LogRecordService logRecordService;
	
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param request
	* @param userName  用户名
	* @param type  操作类型
	* @param moduleName 模块名称
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @param pageNum
	* @param pageSize
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByAll")
    @ResponseBody
    public Object selectByAll(HttpServletRequest request, String userName, String type, String moduleName, 
			String startTime, String endTime, @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws ParseException {

        CommonResponse cr = new CommonResponse();
        
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = logRecordService.selectByAll(userName, type, moduleName, startTime, endTime, pageNum, pageSize);
                List<Map<String, Object>> list = pageInfo.getList();
                if (list != null && list.size() > 0) {
                	for(Map<String, Object> map : list) {
                		map.put("type", ConstantUtil.logRecordType.toMap().get(map.get("type")));
                	}
                }
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
	
	/**
	 * 
	* @Title: excelByAll 
	* @Description: 导出
	* @param request
	* @param response
	* @param userName  用户名
	* @param type  操作类型
	* @param moduleName 模块名称
	* @param startTime 开始时间
	* @param endTime 结束时间
	* @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/excelByAll")
    @ResponseBody
    public void excelByAll(HttpServletRequest request, HttpServletResponse response, String userName, 
    		String type, String moduleName, String startTime, String endTime) throws Exception{
    	try {
            String tieleName = "审计日志";
            String excelName = "审计日志";
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                List<Map<String, Object>> list = logRecordService.excelByAll(userName, type, moduleName, startTime, endTime);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                        map.put("type", ConstantUtil.logRecordType.toMap().get(map.get("type")));
                    }
                    POIUtils poi = new POIUtils();
                    String[] heads = {"用户账号", "模块名称", "操作类型", "标题", "内容", "操作IP", "操作时间"};
                    String[] colums = {"userName","moduleName","type","title","content","ip","createTime"};
                    poi.doExport(request, response, list, tieleName, excelName, heads, colums);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
