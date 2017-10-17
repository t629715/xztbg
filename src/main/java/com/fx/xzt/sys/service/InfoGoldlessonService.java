package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.InfoGoldlesson;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @Description:
 * @date 15:51 2017/10/13
 */
public interface InfoGoldlessonService extends IService<InfoGoldlesson>{
    /**
     * @Author tianliya
     * @Decription
     * @Date 2017/10/15 21:46
     * @param title
     * @param releasesettimeStart
     * @param releasesettimeEnd
     * @param state
     * @param operator
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>>  getGoldLesson(String title, String releasesettimeStart,
                                                 String releasesettimeEnd, Short state,
                                                 String operator, Integer pageNum, Integer pageSize);

    /**
     * @param infoId
     * @return int
     * @Author: tianliya
     * @Decription:根据infoId删除黄金课堂
     * @Date 2017/10/15 21:48
    */
    int deleteGoldLessonById(Long infoId);

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
    int modifyGoldLesson(String title, Short state, String operator, Long infoId);

    /**
     * @param infoId 黄金课堂主键
     * @return
     * @Author: tianliya
     * @Decription:获取选中的黄金课堂的数据
     * @Date 2017/10/15 22:27
    */
    Map<String, Object> getOneByInfoId(Long infoId);

    /**
     * 发布黄金课堂  tianliya
     * @param infoGoldlesson
     * @return
     */
    int posted(InfoGoldlesson infoGoldlesson);

    /**
     * 获取一条数据
     * @param infoId
     * @return
     */
    Map<String, Object> getOne(Long infoId);
}
