package com.fx.xzt.sys.service.impl;

import javax.annotation.Resource;

import com.fx.xzt.sys.model.UserLoginModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.UserLogin;
import com.fx.xzt.sys.mapper.UserLoginMapper;
import com.fx.xzt.sys.service.UserLoginService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
            String agentsName, String brokerName, String attribution, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("registerFrom", registerFrom);
        map.put("registerIp", registerIp);
        map.put("lastStartTime", lastStartTime);
        map.put("lastEndTime", lastEndTime);
        map.put("lastLoginFrom", lastLoginFrom);
        map.put("agentsName", agentsName);
        map.put("brokerName", brokerName);
        map.put("attribution", attribution);
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = userLoginMapper.getByRegisterMessage(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(list);
        return pagehelper;
    }

    public List<Map<String, Object>> getExcelByRegister(String userName, String startTime, String endTime,
        String registerFrom, String registerIp, String lastStartTime,
        String lastEndTime, String lastLoginFrom,
        String agentsName, String brokerName, String attribution){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("registerFrom", registerFrom);
        map.put("registerIp", registerIp);
        map.put("lastStartTime", lastStartTime);
        map.put("lastEndTime", lastEndTime);
        map.put("lastLoginFrom", lastLoginFrom);
        map.put("agentsName", agentsName);
        map.put("brokerName", brokerName);
        map.put("attribution", attribution);
        List<Map<String, Object>> list = userLoginMapper.getByRegisterMessage(map);
        return list;
    }


}
