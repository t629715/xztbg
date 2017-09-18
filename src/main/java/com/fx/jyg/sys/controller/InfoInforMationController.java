package com.fx.jyg.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.jyg.sys.entity.InfoInforMation;
import com.fx.jyg.sys.entity.Users;
import com.fx.jyg.sys.service.InfoInforMationService;
import com.github.pagehelper.PageInfo;

/**\
 * 
* @Title: InfoInforMationController.java 
* @Package com.fx.jyg.sys.controller 
* @Description: TODO
* @author SYan  
* @date 2017年8月14日 下午5:01:37 
* @version V1.0
 */
@Controller
@RequestMapping(value="/infoInforMation")
public class InfoInforMationController {
	@Resource
	InfoInforMationService infoInforMationService;
	/**
	 * 获取信息发布 集合
	 * @param title 标题
	 * @param startTime 时间
	 * @param endTime 
	 * @param state 状态
	 * @param operator 操作人
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/selectByInfoInforMation")
	@ResponseBody
	public PageInfo<InfoInforMation> selectByInfoInforMation(String title,String startTime,String endTime,Integer state,String operator,Integer pageNum,Integer pageSize){
		return infoInforMationService.getByAll(title, startTime, endTime, state, operator, pageNum, pageSize);
	}
	/**
	 * 接口局部完成
	 * @param infoInforMation
	 * @return
	 */
	@RequestMapping(value="/posted")
	@ResponseBody
	public Map<String,Object> posted(InfoInforMation infoInforMation,HttpSession httpSession){
		Map<String,Object> map = new HashMap<String,Object>();
		Users u=(Users) httpSession.getAttribute("currentUser");
		infoInforMation.setOperator(u.getPhone()+"");
		int msg = infoInforMationService.posted(infoInforMation);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 查看
	 */
	@RequestMapping(value="/selectBySerialNo")
	@ResponseBody
	public InfoInforMation selectBySerialNo(Long serialNo){
		return infoInforMationService.getById(serialNo);
	}
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(InfoInforMation infoInforMation){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoInforMationService.edit(infoInforMation);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteById")
	@ResponseBody
	public Map<String,Object> deleteById(Long serialNo){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoInforMationService.deleteById(serialNo);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 置顶/取消
	 * 传递 id 和 置顶状态
	 */
	@RequestMapping(value="/editTopState")
	@ResponseBody
	public Map<String,Object> editTopState(InfoInforMation infoInforMation){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = infoInforMationService.editTopState(infoInforMation);
		map.put("msg", msg);
		return map;
	}
}
