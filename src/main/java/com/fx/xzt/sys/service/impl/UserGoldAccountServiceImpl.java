package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.UserAccount;
import com.fx.xzt.sys.entity.UserGoldAccount;
import com.fx.xzt.sys.entity.UserGoldAccountRecord;
import com.fx.xzt.sys.entity.UserInfo;
import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.mapper.UserAccountMapper;
import com.fx.xzt.sys.mapper.UserGoldAccountMapper;
import com.fx.xzt.sys.mapper.UserGoldAccountRecordMapper;
import com.fx.xzt.sys.mapper.UserInfoMapper;
import com.fx.xzt.sys.mapper.UserLoginMapper;
import com.fx.xzt.sys.service.UserAccountService;
import com.fx.xzt.sys.service.UserGoldAccountService;
import com.fx.xzt.sys.service.UserInfoService;
import com.fx.xzt.sys.service.UsersService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.fx.xzt.util.IdUtil;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 
* @ClassName: GoldWithdrawServiceImpl 
* @Description: 黄金提取
* @author htt
* @date 2017-10-17 下午2:04:58 
*
 */
@Service
public class UserGoldAccountServiceImpl extends BaseService<UserGoldAccount> implements UserGoldAccountService {
	
	@Resource
	UserGoldAccountMapper userGoldAccountMapper;
	@Resource
	UserGoldAccountRecordMapper userGoldAccountRecordMapper;

	@Resource
	private UserLoginMapper userLoginMapper;

	@Resource
	UserInfoMapper userInfoMapper;

	private final Logger logger = LoggerFactory.getLogger(UserGoldAccountServiceImpl.class);

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/7/3 11:22
	 * @Description：修改用户黄金账户余额
	 * @param gold
	 * @param userName
	 * @return
	 */
	@Transactional
	public CommonResponse updateUserGoldAccount(Users users, Double gold, String  userName,Short type,String description,String operatorName) {
		CommonResponse commonResponse = new CommonResponse();
		logger.info("修改用户的黄金余额：{}，-----用户名：{}",gold,userName);
		if (users == null) {
			commonResponse.setCode(1004);
			commonResponse.setMsg("登录已过期");
			return commonResponse;
		}
		if (gold == null || userName == null){
			commonResponse.setCode(Constant.RESCODE_EXCEPTION);
			commonResponse.setMsg("请输入充值克重");
		}
		UserGoldAccount userGoldAccount = new UserGoldAccount();
		UserLogin userLogin = userLoginMapper.selectByUserName(userName);
		try{
			userGoldAccount.setUserId(userLogin.getUserid());
			userGoldAccount = userGoldAccountMapper.lockForUpdate(userGoldAccount);

		}catch (Exception e){
			e.printStackTrace();
			logger.error("修改用户黄金余额异常");
			commonResponse.setMsg("用户不存在");
			commonResponse.setCode(Constant.RESCODE_EXCEPTION);
			throw new GlobalException("修改用户黄金余额","锁表异常");
		}
		try{
			Double gram = userGoldAccount.getGold()+gold;
			userGoldAccount.setGold(gram);
			userGoldAccount.setUpdateTime(new Date());
			int i = userGoldAccountMapper.updateOne(userGoldAccount);
			if (i>0){
				UserInfo userInfo = new UserInfo();
				userInfo = userInfoMapper.selectOneUserInfo(userLogin.getUserid());
				if (userInfo == null){
					logger.warn("充值黄金失败：id-{},userInfo信息不存在",userLogin.getUserid());
					commonResponse.setMsg("充值黄金失败");
					commonResponse.setCode(Constant.RESCODE_EXCEPTION);
					return commonResponse;
				}

				if (userInfo == null){
					logger.warn("充值黄金失败：用户名-{},信息不存在",userName);
					commonResponse.setMsg("充值黄金失败");
					commonResponse.setCode(Constant.RESCODE_EXCEPTION);
					return commonResponse;
				}
				UserGoldAccountRecord userGoldAccountRecord = new UserGoldAccountRecord();
				userGoldAccountRecord.setId(IdUtil.generateyyyymmddhhMMssSSSAnd2Random());
				userGoldAccountRecord.setUserId(userLogin.getUserid());
				userGoldAccountRecord.setAmount(gold);
				if (userInfo.getAgentId() != null){
					userGoldAccountRecord.setAgentId(Long.valueOf(userInfo.getAgentId()));
				}
				if (userInfo.getBrokerId() != null){
					userGoldAccountRecord.setBrokerId(Long.valueOf(userInfo.getBrokerId()));
				}
				userGoldAccountRecord.setCreateTime(new Date());
				userGoldAccountRecord.setType(type);
				userGoldAccountRecord.setDescription(description);
				userGoldAccountRecord.setUserName(userLogin.getUsername());
				try{
					int m = userGoldAccountRecordMapper.insertOne(userGoldAccountRecord);
				}catch (Exception e){
					logger.warn("充值黄金失败，添加充值记录异常");
					commonResponse.setCode(Constant.RESCODE_EXCEPTION);
					commonResponse.setMsg("充值黄金失败");
					return commonResponse;
				}

				commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
				commonResponse.setMsg("充值成功");
				return commonResponse;

			}else {
				logger.info("用户账户不存在");
				commonResponse.setCode(Constant.RESCODE_EXCEPTION);
				commonResponse.setMsg("充值失败");
			}
		}catch (Exception e){
			e.printStackTrace();
			throw new GlobalException("修改用户黄金余额","修改用户黄金余额异常");
		}
		return commonResponse;
	}
}
