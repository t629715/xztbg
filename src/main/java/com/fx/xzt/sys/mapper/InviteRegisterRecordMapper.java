package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.InviteRegisterRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface InviteRegisterRecordMapper extends BaseMapper<InviteRegisterRecord>{
    /**
     * 邀请注册记录查询
     * @param map
     * @return
     */
    List<Map<String, Object>> selectAllRecords (Map<String,Object> map);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/19 13:44
     * @Description：获取已领奖的最新的交易时间
     * @param map
     * @return
     */
    String getLastTime(Map<String,Object> map);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/19 14:11
     * @Description：查询未领取的克重
     * @param map
     * @return
     */
    Map<String, Object> getUnreceivedGram(Map<String, Object> map);
    Map<String, Object> getUnreceivedGramForGold(Map<String, Object> map);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/19 14:41
     * @Description：getReceivedGram
     * @param userId
     * @return
     */
    Map<String, Object> getReceivedGram(Long userId);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/19 14:42
     * @Description：getReceivedMoney
     * @param userId
     * @return
     */
    Map<String, Object> getReceivedMoney(Long userId);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/19 16:22
     * @Description：获取点差
     * @return
     */
    List<Map<String,Object>> selectPointCont();

}
