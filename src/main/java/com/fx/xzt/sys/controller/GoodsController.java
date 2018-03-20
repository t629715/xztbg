package com.fx.xzt.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.Goods;
import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.model.GoodsModel;
import com.fx.xzt.sys.service.GoodsService;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.IPUtil;
import com.fx.xzt.sys.util.log.AuditLog;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/goods")
public class GoodsController {
	@Resource
	GoodsService goodsService;
	@Resource
	LogRecordService logRecordService;
	
	/**
	 * 获取列表
	 * @return
	 */
	@RequestMapping(value="/selectByGoodsAll")
	@ResponseBody
	public PageInfo<GoodsModel> selectByGoodsAll(String goodsName, String startTime, String endTime, Short status,
			String goodsType, Integer pageNum, Integer pageSize){
		return goodsService.getByGoodsAll(goodsName, startTime, endTime, status, goodsType, pageNum, pageSize);
				
	}
	/**
	 * 上线下线
	 * @param goodsID
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/offlineOrOnline")
	@ResponseBody
	public Map<String,Object> offlineOrOnline(HttpServletRequest request ,String goodsID, Integer type) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("商品上下线控制");
		log.setContent("修改失败");
		log.setModuleName(ConstantUtil.logRecordModule.HJTQ.getName());
		log.setType(ConstantUtil.logRecordType.XG.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		Map<String,Object> map = new HashMap<String,Object>();
		if (users != null){
			int msg = goodsService.offlineOrOnline(goodsID, type);
			map.put("msg", msg);
			log.setContent("修改成功");
			log.setUserId(users.getId());
		}

		logRecordService.add(log);
		AuditLog.info(log.toString());

		return map;
	}
	/**
	 * 添加
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(Goods goods){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = goodsService.add(goods);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 编辑
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Map<String,Object> edit(Goods goods){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = goodsService.edit(goods);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,Object> delete(String goodsID){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = goodsService.deleteById(goodsID);
		map.put("msg", msg);
		return map;
	}
}
