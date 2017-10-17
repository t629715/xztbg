package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.InfoGoldlesson;
import com.fx.xzt.sys.mapper.InfoGoldlessonMapper;
import com.fx.xzt.sys.service.InfoGoldlessonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @Description:
 */
@Service
public class InfoGoldlessonServiceImpl extends BaseService<InfoGoldlesson> implements InfoGoldlessonService {
    @Resource
    private InfoGoldlessonMapper infoGoldlessonMapper;
    /**
    * @Author:  tianliya
    * @Description:获取黄金课堂信息
    * @Date:17:11 2017/10/13
    */
    @Override
    public PageInfo<Map<String, Object>> getGoldLesson(String title, String startTime,
                                  String endTime, Short state, String operator,
                                  Integer pageNum, Integer pageSize) {
        Map map1 = new HashMap();
        PageHelper.startPage(pageNum, pageSize);
        map1.put("title",title);
        map1.put("startTime",startTime);
        map1.put("endTime",endTime);
        map1.put("state",state);
        map1.put("operator",operator);
        List list = infoGoldlessonMapper.selectByInfoGoldlesson(map1);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
    }

    /**
     * @param infoId
     * @return
     * @Author: tianliya
     * @Decription:根据infoID删除黄金课堂信息
     * @Date 2017/10/15 21:53
    */
    @Transactional
    public int deleteGoldLessonById(Long infoId) {
       int i = infoGoldlessonMapper.deleteGoldLesson(infoId);
        return i;
    }

    /**
     * @param title 标题
     * @param state 状态
     * @param operator 发布人
     * @param infoId 主键
     * @return
     * @Author: tianliya
     * @Decription:  修改黄金课堂的信息
     * @Date 2017/10/15 22:08
    */
    @Transactional
    public int modifyGoldLesson(String title, Short state, String operator, Long infoId) {
        Map map = new HashMap();
        map.put("title",title);
        map.put("state",state);
        map.put("operator",operator);
        map.put("infoId",infoId);
        return infoGoldlessonMapper.edit(map);
    }

    /**
     * @param infoId 黄金课堂主键
     * @return
     * @Author: tianliya
     * @Decription:根据infoId预览黄金课堂信息
     * @Date 2017/10/15 22:29
    */
    @Override
    public Map<String, Object> getOneByInfoId(Long infoId) {
        Map map = infoGoldlessonMapper.selectOneByInfoId(infoId);
        return map;
    }

    /**
     * 发布黄金课堂  tianliya
     * @param infoGoldlesson
     * @return
     */
    @Transactional
    public int posted(InfoGoldlesson infoGoldlesson) {
        return infoGoldlessonMapper.posted(infoGoldlesson);
    }

    /**
     * 获取一条数据
     * @param infoId
     * @return
     */
    @Override
    public Map<String, Object> getOne(Long infoId) {
        Map map = infoGoldlessonMapper.selectOneByInfoId(infoId);
        return map;
    }
}
