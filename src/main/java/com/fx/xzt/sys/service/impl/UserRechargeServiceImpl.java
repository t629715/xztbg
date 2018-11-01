package com.fx.xzt.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.UserAccount;
import com.fx.xzt.sys.entity.UserAccountRecord;
import com.fx.xzt.sys.entity.UserInfo;
import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.entity.UserRecharge;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.mapper.UserAccountMapper;
import com.fx.xzt.sys.mapper.UserAccountRecordMapper;
import com.fx.xzt.sys.mapper.UserInfoMapper;
import com.fx.xzt.sys.mapper.UserLoginMapper;
import com.fx.xzt.sys.mapper.UserRechargeMapper;
import com.fx.xzt.sys.model.UserRechargeModel;
import com.fx.xzt.sys.service.UserRechargeService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.RechargeChannelEnum;
import com.fx.xzt.sys.util.RechargeStatus;
import com.fx.xzt.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserRechargeServiceImpl extends BaseService<UserRecharge> implements UserRechargeService{
	@Resource
	UserRechargeMapper userRechargeMapper;
	@Resource
	private UserLoginMapper userLoginMapper;

	@Resource
	private UserAccountMapper userAccountMapper;

	@Resource
	private UserAccountRecordMapper userAccountRecordMapper;

	@Resource

	private UserInfoMapper userInfoMapper;


	public PageInfo<UserRechargeModel> getAll(String username, String rechargeid, String merchantordernum, String startTime,
			String endTime, String rechargechannel, Short status, Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("rechargeid", rechargeid);
		map.put("merchantordernum", merchantordernum);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("rechargechannel", rechargechannel);
		map.put("status", status);
		PageHelper.startPage(pageNum,pageSize);
		List<UserRechargeModel> list = userRechargeMapper.getByAll(map);
		for(UserRechargeModel m : list){
			if(!StringUtils.isBlank(m.getRechargechannel()))
			m.setRechargeChannelName(RechargeChannelEnum.getName(m.getRechargechannel()));
			if(m.getStatus()!=null)
			m.setStatusName(RechargeStatus.getName(m.getStatus()));
		}
		return new PageInfo<UserRechargeModel>(list);
	}

	public List<UserRechargeModel> getExcelAll(String username, String rechargeid,
			String merchantordernum, String startTime, String endTime, String rechargechannel, Short status) {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("rechargeid", rechargeid);
		map.put("merchantordernum", merchantordernum);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("rechargechannel", rechargechannel);
		map.put("status", status);
		List<UserRechargeModel> list = userRechargeMapper.getByAll(map);
		for(UserRechargeModel m : list){
			if(!StringUtils.isBlank(m.getRechargechannel()))
			m.setRechargeChannelName(RechargeChannelEnum.getName(m.getRechargechannel()));
			if(m.getStatus()!=null)
			m.setStatusName(RechargeStatus.getName(m.getStatus()));
			if(m.getRechargetime()!=null)
			m.setFormatRechargetime(sdf.format(m.getRechargetime()));
		}
		return list;
	}

	/**
	 * 现金充值记录查询
	 * @throws ParseException 
	 */
	@Override
	public PageInfo<Map<String, Object>> selectByRecharge(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, String rechargechannel,String PlatformName, Integer status, String isView ,
			Integer pageNum, Integer pageSize) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("rechargechannel", rechargechannel);
		map.put("PlatformName", PlatformName);
        map.put("status", status);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = userRechargeMapper.selectByRecharge(map);
        handleRecharge(list);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 现金充值记录导出
	 * @throws ParseException 
	 */
	@Override
	public List<Map<String, Object>> excelRecharge(String userName,
												   String startTime, String endTime, String agentName,
												   String brokerName, String rechargechannel,String PlatformName, Integer status, String isView ) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("rechargechannel", rechargechannel);
		map.put("PlatformName", PlatformName);
		map.put("status", status);
        map.put("isView", isView);
        List<Map<String, Object>> list = userRechargeMapper.selectByRecharge(map);
        handleRecharge(list);
        return list;
	}
	
	/**
	 * 
	* @Title: handleRecharge 
	* @Description: 现金充值记录数据处理
	* @param list
	* @throws ParseException    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	public void handleRecharge(List<Map<String, Object>> list) throws ParseException {
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Object RMBAmtObj = map.get("RMBAmt");
				if (RMBAmtObj != null && RMBAmtObj != "") {
					Double RMBAmt = Double.valueOf(RMBAmtObj.toString());
					map.put("RMBAmt", RMBAmt/100);
				}
				Object RechargeTimeObj = map.get("RechargeTime");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       		 	if (RechargeTimeObj != null && RechargeTimeObj != "") {
       		 		map.put("RechargeTime", sdf.format(sdf.parse(RechargeTimeObj.toString())));
                }
       		 	Object StatusObj = map.get("Status");
       		 	if (StatusObj != null && StatusObj != "") {
       		 		map.put("Status", ConstantUtil.rechargeStatus.toMap().get(map.get("Status").toString()));
       		 	}
       		 	Object RechargeChannelObj = map.get("RechargeChannel");
    		 	if (RechargeChannelObj != null && RechargeChannelObj != "") {
    		 		map.put("RechargeChannel", ConstantUtil.rechargeChannel.toMap().get(map.get("RechargeChannel").toString()));
    		 	}
			}
		}
	}

	/**
	 * 现金充值查询-统计
	 */
	public Map<String, Object> selectByRechargeCount(String userName,
			String startTime, String endTime, String agentName,
			String brokerName, String rechargechannel,String PlatformName, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("rechargechannel", rechargechannel);
		map.put("PlatformName", PlatformName);

        map.put("status", status);
        Map<String, Object> map1 = userRechargeMapper.selectByRechargeCount(map);
        if (map1 != null && map1.size() > 0) {
        	Object rmbAmtSumObj = map1.get("rmbAmtSum");
			if (rmbAmtSumObj != null && rmbAmtSumObj != "") {
				Double rmbAmtSum = Double.valueOf(rmbAmtSumObj.toString());
				map1.put("rmbAmtSum", rmbAmtSum/100);
			}
        }
        return map1;
	}

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/4/23 10:28
	 * @Description：人工充值
	 * @param users 操作人
	 * @param rechargeAccount 充值账户
	 * @param money 充值金额
	 * @param payWay 支付方式
	 * @param thirdName   充值说明
	 * @param rechargeNo 充值单号
	 * @return
	 */
	@Override
	@Transactional
	public CommonResponse manualRecharge(Users users, String rechargeAccount, String money, String payWay, String rechargeNo,String thirdName) throws GlobalException {
		CommonResponse commonResponse = new CommonResponse();
		//验证操作人是否登录
		if (users == null){
			commonResponse.setMsg("请登录");
			commonResponse.setCode(Constant.RESCODE_NOAUTH);
			return commonResponse;
		}
		UserLogin userLogin = null;
		try{
			userLogin = userLoginMapper.selectByUserName(rechargeAccount);
		}catch (Exception e){
			e.printStackTrace();
			throw new GlobalException("人工充值","根据用户名获取用户异常");
		}
		//验证充值账号是否存在
		if (userLogin == null){
			commonResponse.setCode(Constant.RESCODE_NOEXIST);
			commonResponse.setMsg("账号不存在");
			return  commonResponse;
		}
		List<Map<String, Object>> userInfo = userInfoMapper.selectUserInfoById(userLogin.getUserid());
		//添加充值记录
		try{
			UserRecharge userRecharge = new UserRecharge();
			//生成主键
			userRecharge.setRechargeid(IdUtil.generateyyyymmddhhMMssSSSAnd2Random()+"");
			//设置充值金额
			userRecharge.setRmbamt(Integer.parseInt(money));
			//设置充值用户的id
			userRecharge.setUserid(userLogin.getUserid());
			//设置充值用户的用户名
			userRecharge.setUsername(userLogin.getUsername());
			//设置充值的时间
			userRecharge.setRechargetime(new Date());
			//设置充值的渠道
			userRecharge.setRechargechannel(payWay);
			//设置充值的设备--PC
			userRecharge.setPlatformname("PC-人工");
			//设置平台订单号
			//设置充值的状态
			userRecharge.setStatus(Short.parseShort("1"));
			//设置代理商
			if (userInfo.get(0).get("agentId") != null){
				userRecharge.setAgentId(Long.valueOf(userInfo.get(0).get("agentId").toString()));
			}

			//设置经纪人
			if (userInfo.get(0).get("brokerId") != null){
				userRecharge.setBrokerId(Long.valueOf(userInfo.get(0).get("brokerId").toString()));
			}

			//设置交易号
			userRecharge.setTradeNo(rechargeNo);
			//设置三方平台的名称 -- 本方法为  人工充值
			if ("1".equals(thirdName)) {
				userRecharge.setThirdName("充值");
			}else {
				userRecharge.setThirdName("运费退回");
			}

			//设置记录生成时间
			userRecharge.setCreateTime(new Date());
			int i = userRechargeMapper.insertOneRecord(userRecharge);
			if (i<=0){
				commonResponse.setCode(Constant.RESCODE_NOEXIST);
				commonResponse.setMsg("充值失败");
				return commonResponse;
			}
		}catch (Exception e){
			e.printStackTrace();
			throw new GlobalException("添加充值记录","添加充值记录异常");

		}
		//添加账户记录
		try{
			UserAccountRecord userAccountRecord = new UserAccountRecord();
			//生成主键
			userAccountRecord.setId(IdUtil.generateyyyymmddhhMMssSSSAnd2Random());
			//设置userId
			userAccountRecord.setUserId(userLogin.getUserid());
			//设置用户名
			userAccountRecord.setUserName(userLogin.getUsername());
			//设置资金流动方向进出方向 I：进 O：出
			userAccountRecord.setSide("I");
			//设置行为行为 10：充值 20：提现 30：理财收益 40：实金兑换 50:交易收益 60:冻结，70：活期理财赎回，80：理财,90:黄金赎回,100提现失败后返还，110取消实物提金
			userAccountRecord.setAction("10");
			//设置金额
			userAccountRecord.setRmb(Long.valueOf(money));
			//设置创建时间
			userAccountRecord.setCreateTime(new Date());
			//设置状态
			userAccountRecord.setStatus(1);
			//设置描述信息
			if ("1".equals(thirdName)) {
				userAccountRecord.setDescription("后台管理-人工充值");

			}else {
				userAccountRecord.setDescription("运费退回");
			}

			//设置代理商id
			if (userInfo.get(0).get("agentId") != null){
				userAccountRecord.setAgentId(Long.valueOf(userInfo.get(0).get("agentId").toString()));
			}

			//设置经纪人id
			if (userInfo.get(0).get("brokerId") != null){
				userAccountRecord.setBrokerId(Long.valueOf(userInfo.get(0).get("brokerId").toString()));
			}

			int i = userAccountRecordMapper.add(userAccountRecord);
			if (i<=0){
				commonResponse.setCode(Constant.RESCODE_NOEXIST);
				commonResponse.setMsg("充值失败");
				throw new GlobalException("修改账户余额","修改账户余额异常");
			}
		}catch (Exception e){
			e.printStackTrace();
			throw new GlobalException("添加账户记录","添加账户记录异常");
		}

		//修改账户余额
		try{
			UserAccount userAccount = userAccountMapper.lockUserAccount(userLogin.getUserid());
			//验证账户信息是否存在
			if (userAccount == null){
				throw new GlobalException("充值","账户信息不存在");
			}
			Long currentMoney = userAccount.getRmb();
			Long afterMoney = currentMoney+Long.valueOf(money);
			userAccount.setRmb(afterMoney);
			int i = userAccountMapper.updateForRecharge(userAccount);
			if (i>0){
				commonResponse.setCode(Constant.RESCODE_SUCCESS);
				commonResponse.setMsg("充值成功");
			}else {
				commonResponse.setCode(Constant.RESCODE_NOEXIST);
				commonResponse.setMsg("充值失败");
				throw new GlobalException("修改账户余额","修改账户余额异常");
			}
		}catch (Exception e){
			e.printStackTrace();
			throw new GlobalException("修改账户余额","修改账户余额异常");
		}
		return commonResponse;
	}



}
