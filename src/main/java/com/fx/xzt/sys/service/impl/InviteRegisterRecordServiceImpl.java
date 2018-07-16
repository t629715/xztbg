package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.mapper.InviteRegisterRecordMapper;
import com.fx.xzt.sys.service.InviteRegisterRecordService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InviteRegisterRecordServiceImpl implements InviteRegisterRecordService {
   @Resource
    InviteRegisterRecordMapper inviteRegisterRecordMapper;

    /**
     * 邀请注册记录  liaijiao
     * @param userName
     * @param startTime
     * @param endTime
     * @param isView
     * @param acceptPrize
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> getSelectAll(String userName, String startTime,
                                                      String endTime,String agentName ,String brokerName,String isView, String acceptPrize, Integer pageNum, Integer pageSize) {
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userName",userName);
           // map.put("newUserName",newUserName);
            map.put("startTime",startTime);
            map.put("endTime",endTime);
            map.put("acceptPrize",acceptPrize);
            map.put("agentName",agentName);
            map.put("brokerName",brokerName);
            map.put("isView",isView);
            PageHelper.startPage(pageNum,pageSize);
            List<Map<String, Object>> list=inviteRegisterRecordMapper.selectAllRecords(map);

            if (list != null && list.size() > 0) {
                for (Map<String, Object> map1 : list) {
                    map1.put("acceptPrize", ConstantUtil.acceptPrize.toMap().get(map1.get("acceptPrize").toString()));
                }
            }
            PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
            return pagehelper;

        }catch (Exception e){
            e.printStackTrace();
            throw new GlobalException("邀请注册记录查询", "邀请注册记录查询出现异常");
        }

    }

    /**
     * 导出
     * @param userName
     * @param startTime
     * @param endTime
     * @param isView
     * @param acceptPrize
     * @param agentName
     * @param brokerName
     * @return
     */

    @Override
    public List<Map<String, Object>> exportAllRecords(String userName,  String startTime,
                                                      String endTime,String isView, String acceptPrize,String agentName ,String brokerName) {
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userName",userName);
          //  map.put("newUserName",newUserName);
            map.put("startTime",startTime);
            map.put("endTime",endTime);
            map.put("acceptPrize",acceptPrize);
            map.put("agentName",agentName);
            map.put("brokerName",brokerName);
            map.put("isView",isView);

            List<Map<String, Object>> list=inviteRegisterRecordMapper.selectAllRecords(map);
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map1 : list) {
                    map1.put("acceptPrize", ConstantUtil.acceptPrize.toMap().get(map1.get("acceptPrize").toString()));
                }
            }
            return list;
        }catch (Exception e){
            throw new GlobalException("邀请注册记录查询", "邀请注册记录查询出现异常");
        }

    }
}
