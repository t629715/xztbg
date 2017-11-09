package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.InfoGoldlesson;
import com.fx.xzt.sys.mapper.InfoGoldlessonMapper;
import com.fx.xzt.sys.service.InfoGoldlessonService;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.DateUtils;
import com.fx.xzt.util.IdUtil;
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
        map1.put("title",title);
        if (startTime != null && startTime !=""){
            Date start = null;
            start = DateUtil.convertTimeMillisToDate(Long.valueOf(startTime));
            String st = DateUtils.formatDateByMidLine(start);
            map1.put("startTime",st);
        }
        if (endTime != null && endTime !=""){
            Date end = null;
            end = DateUtil.convertTimeMillisToDate(Long.valueOf(endTime));
            String st = DateUtils.formatDateByMidLine(end);
            map1.put("endTime",st);
        }
        map1.put("state",state);
        map1.put("operator",operator);
        PageHelper.startPage(pageNum, pageSize);
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
    public int modifyGoldLesson(String title, String imagePath, String contentPath,Short state, String operator, Long infoId) {
        Map map = new HashMap();
        map.put("title",title);
        map.put("state",state);
        map.put("operator",operator);
        map.put("infoId",infoId);
        map.put("imagePath",imagePath);
        map.put("contentPath",contentPath);
        if (state==1){
            map.put("releasetime",new Date());
        }

        DateUtil.convertTimeMillisToDate(new Date().getTime());

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
        short s = 0;
        infoGoldlesson.setState(s);
        infoGoldlesson.setCreatetime(new Date());
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

    /**
     * 获取所有的发布人  tianliya
     * @return
     */
    @Override
    public List<Map<String, Object>>getOperators() {
        List<Map<String, Object>> map = infoGoldlessonMapper.selectOperators();
        return map;
    }
}
