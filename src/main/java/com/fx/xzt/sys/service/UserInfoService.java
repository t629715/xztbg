package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.UserInfo;
import com.fx.xzt.sys.model.UserInfoModel;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author SYan
 * @version V1.0
 * @Title: UserInfoService.java
 * @Package com.fx.xzt.sys.service
 * @Description: TODO
 * @date 2017年8月14日 上午9:16:23
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 获取实名认证列表
     *
     * @param userName       账号
     * @param realName       真实姓名
     * @param applyTimeStart 申请开始时间
     * @param applyTimeEnd
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<UserInfoModel> getfindAll(String userName, String realName, String applyTimeStart, String applyTimeEnd, Integer pageNum, Integer pageSize);

    /**
     * 实名认证审核
     *
     * @param type 修改的类型 1 通过 0 不通过
     * @return
     * @throws ParseException 
     */
    int editUserInfo(int type, Long userId, String IDCard) throws ParseException;

    //以下是 优顾认证 type 修改的类型 1 通过 0 不通过

    int editYGUserInfo(int type, Long userId);

    /**
     * 账户信息列表
     * @param isView 
     */
    PageInfo<Map<String, Object>> getByAccountMessage(String userName, String agentsName, String brokerName, String startTime, String endTime, String isView, Integer pageNum, Integer pageSize);

    /**
     * 实名认证
     *
     * @param userName       用户账号
     * @param realName       用户姓名
     * @param applyTimeStart 申请开始时间
     * @param applyTimeEnd   申请结束时间
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> getByRealNameAuth(String userName, String realName, String applyTimeStart, String applyTimeEnd, String isView, Integer pageNum, Integer pageSize);
    
    PageInfo<Map<String, Object>> getByRealNameAuthApprove(String userName, String realName, String state, String applyTimeStart, String applyTimeEnd, String isView, Integer pageNum, Integer pageSize);

    /**
     *  导出账号信息列表
     * @param userName  用户名
     * @param agentName 代理商用户名
     * @param brokerName 经纪人用户名
     * @param startTime  注册开始时间
     * @param endTime  注册结束时间
     * @param isView 
     * @return
     */
    List<Map<String, Object>> getExcelAccount(String userName, String agentName, String brokerName, String startTime, String endTime, String isView);

    /**
     * 获取账户信息列表金额黄金统计
     * @return
     */
    Map<String,Object> getByAccountCount(String userName, String agentsName, String brokerName, String startTime, String endTime);

    /**
     * 获取下级客户信息
     * @param userName
     * @param agentName
     * @param brokerName
     * @param isView 
     * @return
     */
    PageInfo<Map<String,Object>> getSubClients(String userName, String agentName, String brokerName, String isView, Integer pageNum, Integer pageSize);

    /**
     * 导出下级客户信息
     * @param userName
     * @param agentName
     * @param brokerName
     * @param isView 
     * @return
     */
    List<Map<String, Object>> getExcelSubClientsAccount(String userName, String agentName, String brokerName, String isView);

    /**
     * 下级客户 资产统计
     * @param userName
     * @param agentName
     * @param brokerName
     * @return
     */
    Map<String,Object> getSubClientsAccountCount(String userName, String agentName, String brokerName);

    /**
     * 变更客户经纪人
     * @param userId
     * @param brokerId
     * @return
     */
    int changeBroker(Long userId, Long brokerId);
    int alertAgentAndBroker(Long userId, Long brokerId,Long agent);
    
    /**
     * 
    * @Title: getByUserAnalysis 
    * @Description: 用户分析查询
    * @param startTime  开始时间
    * @param endTime    结束时间
    * @param loginFrom  登录来源
    * @param agentName  代理商id
    * @param pageNum
    * @param pageSize
    * @return    设定文件 
    * @return PageInfo<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    PageInfo<Map<String, Object>> getByUserAnalysis(String startTime, String endTime, String loginFrom, String agentName, Integer pageNum, Integer pageSize);
    
    /**
     * 
    * @Title: getByUserAnalysis 
    * @Description: 用户分析查询--导出
    * @param startTime  开始时间
    * @param endTime    结束时间
    * @param loginFrom  登录来源
    * @param agentName  代理商id
    * @return    设定文件 
    * @return PageInfo<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> excelByUserAnalysis(String startTime, String endTime, String loginFrom, String agentName);
    
    /**
     * 
    * @Title: getByUserAnalysisCount 
    * @Description: 用户分析查询--统计
    * @param startTime
    * @param endTime
    * @param loginFrom
    * @param agentName
    * @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> getByUserAnalysisCount(String startTime, String endTime, String loginFrom, String agentName);
	
    /**
     * 
    * @Title: getByUserAttribute 
    * @Description: 用户属性查询
    * @param startTime  开始时间
    * @param endTime    结束时间
    * @param loginFrom  登录来源
    * @param agentName  代理商id
    * @param pageNum
    * @param pageSize
    * @return    设定文件 
    * @return PageInfo<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    PageInfo<Map<String, Object>> getByUserAttribute(String startTime, String endTime, String loginFrom, String agentName, Integer pageNum, Integer pageSize);
    
    /**
     * 
    * @Title: excelByUserAttribute 
    * @Description: 用户属性查询--导出
    * @param startTime  开始时间
    * @param endTime    结束时间
    * @param loginFrom  登录来源
    * @param agentName  代理商id
    * @return    设定文件 
    * @return PageInfo<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> excelByUserAttribute(String startTime, String endTime, String loginFrom, String agentName);
    
    /**
     * 
    * @Title: getByUserAttributeCount 
    * @Description: 用户属性查询--统计
    * @param startTime  开始时间
    * @param endTime    结束时间
    * @param loginFrom  登录来源
    * @param agentName  代理商id
    * @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws 
    * @author htt
     */
    List<Map<String, Object>> getByUserAttributeCount(String startTime, String endTime, String loginFrom, String agentName);
    
    List<Map<String, Object>> selectUserInfoById(Long userID);
}
