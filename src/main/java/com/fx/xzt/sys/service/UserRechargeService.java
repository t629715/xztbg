package com.fx.xzt.sys.service;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.UserRecharge;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.model.UserRechargeModel;
import com.fx.xzt.sys.util.CommonResponse;
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
	
	
	/**
	 * 
	* @Title: selectByRecharge 
	* @Description: 现金充值记录
	* @param userName   用户账号
	* @param startTime  开始时间
	* @param endTime    结束时间
	* @param agentName  代理商用户名
	* @param brokerName  经纪人用户名
	* @param rechargechannel  充值渠道
	* @param status  充值状态  0：失败 1：成功
	 * @param isView 
	* @param pageNum
	* @param pageSize
	* @return    设定文件 
	* @return PageInfo<Map<String,Object>>    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	PageInfo<Map<String, Object>> selectByRecharge(String userName, String startTime, String endTime, String agentName, String brokerName, 
			String rechargechannel, Integer status, String isView, Integer pageNum, Integer pageSize) throws ParseException;

	/**
	 * 
	* @Title: excelRecharge 
	* @Description: 现金充值记录导出
	* @param userName    用户账号
	* @param startTime   开始时间
	* @param endTime     结束时间
	* @param agentName   代理商用户名
	* @param brokerName  经纪人用户名
	* @param rechargechannel  充值渠道
	* @param status  充值状态  0：失败 1：成功
	 * @param isView 
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
    List<Map<String, Object>> excelRecharge(String userName, String startTime, String endTime, String agentName, String brokerName, 
			String rechargechannel, Integer status, String isView) throws ParseException;

    /**
     * 
    * @Title: selectByRechargeCount 
    * @Description: 现金充值记录查询-统计
    * @param userName   用户账号
    * @param startTime  开始时间
    * @param endTime    结束时间
    * @param agentName  代理商用户名
    * @param brokerName 经纪人用户名
    * @param rechargechannel  充值渠道
    * @param status 充值状态  0：失败 1：成功
    * @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    * @author htt
     */
    Map<String, Object> selectByRechargeCount(String userName, String startTime, String endTime, String agentName, String brokerName, 
			String rechargechannel, Integer status);

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/4/23 10:10
	 * @Description：人工充值
	 * @param users 操作人
	 * @param rechargeAccount 充值账户
	 * @param money 充值金额
	 * @param payWay 支付方式
	 * @param thirdName   充值说明
	 * @param rechargeNo 充值单号
	 * @return
	 */
	CommonResponse manualRecharge(Users users, String rechargeAccount, String money, String payWay, String rechargeNo,String thirdName) throws GlobalException;

}
