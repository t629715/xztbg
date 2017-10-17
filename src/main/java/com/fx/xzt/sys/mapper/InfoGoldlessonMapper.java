package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.InfoGoldlesson;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @ClassName: InfoGoldlessonMapper.java
 * @Description: 黄金课堂
 * @date 2017-10-013 15:24
 */
@Repository
public interface InfoGoldlessonMapper extends BaseMapper<InfoGoldlesson>{
    /**
    * @Author:  tianliya
    * @Description:  查询
    * @Date:15:55 15:55
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByInfoGoldlesson(Map<String, Object> map);
    /**
    * @Author:  tianliya
    * @Description: 增加
    * @Date:16:02 16:02
    */
    int insert(Map<String, Object> map);
    /**
    * @Author:  tianliya
    * @Description: 编辑
    * @Date:16:02 16:02
    */
    int edit(Map<String, Object> map);
    /**
    * @Author:  tianliya
    * @Description: 删除
    * @Date:16:02 16:02
    */
    int deleteGoldLesson(Long infoId);

    /**
     * @param infoId
     * @return
     * @Author: tianliya
     * @Decription:根据infoId查询一条黄金课堂信息
     * @Date 2017/10/15 22:18
    */
    Map<String, Object> selectOneByInfoId(Long infoId);

    /**
     * 发布
     * @param
     * @return
     */
    int posted(InfoGoldlesson infoGoldlesson);
}