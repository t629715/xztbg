package com.fx.xzt.sys.controller;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.InfoGoldlessonService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by tianliya on 2017/10/15.
 */
@Controller
@RequestMapping("/goldLesson")
public class InfoGoldLessonController {
    @Autowired
    InfoGoldlessonService infoGoldlessonService;

    /**
     * 获取黄金课堂信息  tianliya
     * @param request
     * @param title
     * @param startTime
     * @param endTime
     * @param state
     * @param operator
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/getGoldLesson")
    @ResponseBody
    public CommonResponse getInfoGoldLesson(HttpServletRequest request, String title, String startTime,
                                            String endTime, Short state, String operator,
                                            Integer pageNum, Integer pageSize){
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                PageInfo<Map<String, Object>> pageInfo = infoGoldlessonService.getGoldLesson(title, startTime, endTime,
                                                                        state,operator, pageNum, pageSize);
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

    /**
     * @param request
     * @param infoId
     * @return
     * @Author: tianliya
     * @Decription:删除选中的黄金课堂
     * @Date 2017/10/15 22:13
    */
    @RequestMapping(value="/deleteGoldLesson")
    @ResponseBody
    public CommonResponse deleteInfoGoldLesson(HttpServletRequest request, Long infoId){
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int i = infoGoldlessonService.deleteGoldLessonById(infoId);
                if (i != 0){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
                    response.setMsg("操作成功！");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
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
     * @param request
     * @param infoId
     * @param title
     * @param operator
     * @param state
     * @return
     * @Author: tianliya
     * @Decription:修改选中的数据
     * @Date 2017/10/15 22:31
    */
    @RequestMapping(value="/modifyGoldLesson")
    @ResponseBody
    public CommonResponse modifyInfoGoldLesson(HttpServletRequest request, Long infoId, String title, String operator, Short state){
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int i = infoGoldlessonService.modifyGoldLesson(title, state, operator, infoId);
                if (i != 0){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
                    response.setMsg("操作成功！");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
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
     * @param request
     * @param infoId
     * @return
     * @Author: tianliya
     * @Decription:  预览选中的黄金课堂信息
     * @Date 2017/10/15 22:34
    */
    @RequestMapping(value="/getOneGoldLesson")
    @ResponseBody
    public CommonResponse getOneGoldLesson(HttpServletRequest request, Long infoId){
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                Map map  = infoGoldlessonService.getOneByInfoId(infoId);
                if (map != null){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(map);
                    response.setMsg("操作成功！");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(map);
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
     * 发布
     * @param request
     * @param infoId
     * @return
     */
    @RequestMapping(value="/releaseGoldLesson")
    @ResponseBody
    public CommonResponse releaseGoldLesson(HttpServletRequest request, Long infoId){
        CommonResponse response = new CommonResponse();
        try {
            HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
                int i  = infoGoldlessonService.releaseGoldLesson(infoId,users.getUserName());
                if (i != 0){
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
                    response.setMsg("操作成功！");
                }else {
                    response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                    response.setData(i);
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
