package com.fx.xzt.sys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.InfoBanner;
import com.fx.xzt.sys.service.InfoBannerService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/infoBanner")
public class InfoBannerController {
	
	@Resource
	InfoBannerService infoBannerService;

	/**
	 * 添加
	 * @param infoBanner
	 * @return
	 */
	@RequestMapping(value="/insertBanner",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse insertBanner(HttpServletRequest request, InfoBanner infoBanner){

		CommonResponse response = new CommonResponse();
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				infoBanner.setOperator(users.getUserName());
				int msg = infoBannerService.add(infoBanner);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(msg);
				response.setMsg("操作成功！");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		return response;
	}

	/**
	 * 编辑
	 * @param infoBanner
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse edit(HttpServletRequest request, InfoBanner infoBanner){

		CommonResponse response = new CommonResponse();
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				infoBanner.setOperator(users.getUserName());
				int msg = infoBannerService.edit(infoBanner);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(msg);
				response.setMsg("操作成功！");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		return response;
	}

	/**
	 * 删除
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse delete(HttpServletRequest request, Long serialNo){
		CommonResponse response = new CommonResponse();
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				int msg = infoBannerService.deleteById(serialNo);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(msg);
				response.setMsg("操作成功！");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		return response;
	}

	/**
	 * 修改图片显示的顺序
	 * @param downSortNo 交换的上一条
	 * @param downSortNo 交换的另一条
	 * @return
	 */
	@RequestMapping(value="/upDown",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse upDown(HttpServletRequest request, Integer upSortNo,Integer downSortNo){

		CommonResponse response = new CommonResponse();
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				int msg = infoBannerService.up(upSortNo, downSortNo);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(msg);
				response.setMsg("操作成功！");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		return response;
	}
	/**
	 * 根据page查询列表
	 */
	@RequestMapping(value="/selectByPageAll",method=RequestMethod.POST)
	@ResponseBody
	public CommonResponse selectByPageAll(HttpServletRequest request, Integer page,Integer pageNum,Integer pageSize){
		CommonResponse response = new CommonResponse();
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				PageInfo<InfoBanner> pageInfo = infoBannerService.getByPageAll(page, pageNum, pageSize);
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				response.setData(pageInfo);
				response.setMsg("操作成功！");
			} else {
				response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
				response.setData("{}");
				response.setMsg("操作失败-没有权限");
			}
		} catch (Exception e) {
			response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			response.setData("{}");
			response.setMsg("操作失败-请求异常！");
			throw e;
		}
		return response;
	}

	/**
	 * 获取广告图片  tianliya
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/getAdPic1",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getAdPic1(Short page){
		return infoBannerService.getAdPic(page,8);
	}
	@RequestMapping(value="/getAdPic2",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getAdPic2(Short page){
		return infoBannerService.getAdPic(page,1);
	}

	@RequestMapping(value="/getOneBySerialNo",method=RequestMethod.POST)
	@ResponseBody
	public InfoBanner getOneBySerialNo(Long serialNo){
		return infoBannerService.selectById(serialNo);
	}
}
