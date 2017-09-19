package com.fx.xzt.sys.service;


import java.util.List;

import com.fx.xzt.sys.entity.UserRecharge;
import com.fx.xzt.sys.model.UserRechargeModel;
import com.github.pagehelper.PageInfo;

public interface UserRechargeService extends IService<UserRecharge>{
	/**
	 * 查询充值集合
	 */
	PageInfo<UserRechargeModel> getAll(String username,String rechargeid,String merchantordernum,String startTime,String endTime,String rechargechannel,Short status,Integer pageNum,Integer pageSize);
	/**
	 * 条件查询不分页的 为了 Excel导出
	 */
	List<UserRechargeModel> getExcelAll(String username,String rechargeid,String merchantordernum,String startTime,String endTime,String rechargechannel,Short status);
}
