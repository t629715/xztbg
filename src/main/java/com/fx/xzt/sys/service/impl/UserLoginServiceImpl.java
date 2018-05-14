package com.fx.xzt.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.mapper.UserLoginMapper;
import com.fx.xzt.sys.service.UserLoginService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class UserLoginServiceImpl extends BaseService<UserLogin> implements UserLoginService {

    @Resource
    UserLoginMapper userLoginMapper;

    @Transactional
    public int updateByIdSelective(UserLogin userLogin) {
        return userLoginMapper.updateByIdSelective(userLogin);
    }

    public PageInfo<Map<String, Object>> getByRegisterMessage(String userName, String startTime, String endTime,
            String registerFrom, String registerIp, String lastStartTime, String lastEndTime, String lastLoginFrom,
            String agentName, String brokerName, String attribution, String isView, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("registerFrom", registerFrom);
        map.put("registerIp", registerIp);
        map.put("lastStartTime", lastStartTime);
        map.put("lastEndTime", lastEndTime);
        map.put("lastLoginFrom", lastLoginFrom);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("attributionProvince", attribution);//归属地省份
        map.put("isView", isView);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = userLoginMapper.getByRegisterMessage(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(list);
        return pagehelper;
    }

    public List<Map<String, Object>> getExcelByRegister(String userName, String startTime, String endTime,
        String registerFrom, String registerIp, String lastStartTime,
        String lastEndTime, String lastLoginFrom,
        String agentName, String brokerName, String attribution, String isView){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("registerFrom", registerFrom);
        map.put("registerIp", registerIp);
        map.put("lastStartTime", lastStartTime);
        map.put("lastEndTime", lastEndTime);
        map.put("lastLoginFrom", lastLoginFrom);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("attribution", attribution);
        map.put("isView", isView);
        List<Map<String, Object>> list = userLoginMapper.getByRegisterMessage(map);
        return list;
    }

	/**
	 * 根据userName获取用户账户信息
	 */
	public List<Map<String, Object>> getByAccount(String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        List<Map<String, Object>> list = userLoginMapper.getByAccount(map);
		return list;
	}

	/**
	 * 注册信息查询-归属地获取
	 */
	public List<Map<String, Object>> getByAttributionPro() {
	    List<Map<String, Object>> resultList = new ArrayList<>();
	    List queryList = userLoginMapper.getByAttributionPro();
	    if (queryList != null && queryList != null){
	        Map<String, Object> result = new ConcurrentHashMap<>();
	        result.put("province","全部");
	        resultList.add(result);
	        resultList.addAll(queryList);
        }
		return resultList;
	}

    

}
