package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.InfoXioude;
import com.github.pagehelper.PageInfo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @Description:
 * @date 15:51 2017/10/13
 */
public interface InfoXioudeService extends IService<InfoXioude>{
    /**
     * @Author:  tianliya
     * @Description:
     * @Date:16:35 2017/10/16
     * @param title
     * @param state
     * @param operator
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>>  getInfoXioude(String title, String releaseimeStart,
                                                 String releasetimeEnd, Short state,
                                                 String operator, Integer pageNum, Integer pageSize);

    /**
     * @param infoId
     * @return int
     * @Author:  tianliya
     * @Description:
     * @Date:16:34 2017/10/16
     * */
    int deleteXioudeById(Long infoId);

    /**
     * @param title 标题
     * @param state 状态
     * @param operator 发布人
     * @param infoId 主键
     * @return
     * @Author: tianliya
     * @Decription:
     * @Date 2017/10/15 22:04
    */
    int modifyXioude(String title, Short state, String operator, Long infoId, String contentpath, String imagepath);

    /**
     * @param infoId xioude主键
      * @Author:  tianliya
      * @Description:
      * @Date:16:35 2017/10/16
     */
    Map<String, Object> getOneByInfoId(Long infoId);

    /**
     * 发布xioude  tianliya
     * @param infoId
     * @return
     */
    int releaseXioude(Long infoId, String operator);

    /**
     * 获取一条数据
     * @param infoId
     * @return
     */
    Map<String, Object> getOne(Long infoId);
    /**
     * 获取所有发布人
     * @return
     */
    List<Map<String, Object>> getOperators();
}
