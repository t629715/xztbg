package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.UserInfo;
import com.fx.xzt.sys.model.UserInfoModel;
import com.github.pagehelper.PageInfo;

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
     */
    int editUserInfo(int type, Long userId);

    //以下是 优顾认证 type 修改的类型 1 通过 0 不通过

    int editYGUserInfo(int type, Long userId);

    /**
     * 账户信息列表
     */
    PageInfo<Map<String, Object>> getByAccountMessage(String userName, String agentsName, String brokerName, String startTime, String endTime, Integer pageNum, Integer pageSize);

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
    PageInfo<Map<String, Object>> getByRealNameAuth(String userName, String realName, String applyTimeStart, String applyTimeEnd, Integer pageNum, Integer pageSize);

    /**
     *  导出账号信息列表
     * @param userName  用户名
     * @param agentName 代理商用户名
     * @param brokerName 经纪人用户名
     * @param startTime  注册开始时间
     * @param endTime  注册结束时间
     * @return
     */
    List<Map<String, Object>> getExcelAccount(String userName, String agentName, String brokerName, String startTime, String endTime);

    /**
     * 获取账户信息列表金额黄金统计
     * @return
     */
    Map<String,Object> getByAccountCount();
}
