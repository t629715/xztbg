package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.InfoNotice;
import com.fx.xzt.sys.entity.InfoPush;
import com.fx.xzt.sys.mapper.InfoNoticeMapper;
import com.fx.xzt.sys.mapper.InfoPushMapper;
import com.fx.xzt.sys.service.InfoNoticeService;
import com.fx.xzt.sys.service.InfoPushService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.DateUtils;
import com.fx.xzt.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class InfoPushServiceImpl extends BaseService<InfoPush> implements InfoPushService{
	
	@Resource
	InfoPushMapper infoPushMapper;

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/5 14:22
	 * @Description：获取所有的推送
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public CommonResponse getAllPushes(Integer pageNum, Integer pageSize) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			List<InfoPush> infoPushList = infoPushMapper.selectAllInfoPush();
			commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
			commonResponse.setData(infoPushList);
		}catch (Exception e){
			e.printStackTrace();
			commonResponse.setCode(Constant.RESCODE_EXCEPTION);
			throw new GlobalException("获取首页资讯推送","获取首页资讯推送异常");
		}
		return commonResponse;
	}

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/5 14:22
	 * @Description：增加一条推送
	 * @param infoPush
	 * @return
	 */
	@Override
	@Transactional
	public CommonResponse addOnePush(InfoPush infoPush) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			infoPush.setId(IdUtil.generateyyyymmddhhMMssSSSAnd2Random()+"");
			infoPush.setStatus(1);
			infoPush.setCreateTime(new Date());
			List<InfoPush> infoPushList = infoPushMapper.selectAll();
			if (infoPushList.size() != 0){
				int sortNo = infoPushList.get(infoPushList.size()-1).getSortNo()+1;
				infoPushMapper.deleteInfoPushById(infoPushList.get(infoPushList.size()-1).getId());
				infoPush.setSortNo(1);
			}else {
				infoPush.setSortNo(1);
			}

			int m = infoPushMapper.insertOne(infoPush);
			if (m > 0){
				commonResponse.setCode(Constant.RESCODE_SUCCESS);
			}else {
				commonResponse.setCode(Constant.RESCODE_EXCEPTION);
			}
		}catch (Exception e){
			e.printStackTrace();
			commonResponse.setCode(Constant.RESCODE_EXCEPTION);
			throw new GlobalException("添加首页推送资讯","添加首页推送资讯异常");
		}
		return commonResponse;
	}

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/5 14:23
	 * @Description：删除一条推送
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public CommonResponse removeById(String id) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			int m = infoPushMapper.deleteInfoPushById(id);
			if (m > 0){
				commonResponse.setCode(Constant.RESCODE_SUCCESS);
			}else {
				commonResponse.setCode(Constant.RESCODE_EXCEPTION);
			}
		}catch (Exception e){
			e.printStackTrace();
			commonResponse.setCode(Constant.RESCODE_EXCEPTION);
			throw new GlobalException("删除选中的首页推送资讯","删除选中的首页推送资讯异常");
		}
		return commonResponse;
	}
}
