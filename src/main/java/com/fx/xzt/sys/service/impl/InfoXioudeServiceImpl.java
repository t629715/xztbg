package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.InfoXioude;
import com.fx.xzt.sys.mapper.InfoXioudeMapper;
import com.fx.xzt.sys.service.InfoXioudeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianliya
 * @Description:
 */
@Service
public class InfoXioudeServiceImpl extends BaseService<InfoXioude> implements InfoXioudeService {
    @Resource
    private InfoXioudeMapper infoXioudeMapper;
    /**
    * @Author:  tianliya
    * @Description:获取西欧的数据
    * @Date:17:11 2017/10/13
    */
    @Override
    public PageInfo<Map<String, Object>> getInfoXioude(String title, String startTime,
                                  String endTime, Short state, String operator,
                                  Integer pageNum, Integer pageSize) {
        Map map1 = new HashMap();
        PageHelper.startPage(pageNum, pageSize);
        map1.put("title",title);

        map1.put("startTime",startTime);
        map1.put("endTime",endTime);
        map1.put("state",state);
        map1.put("operator",operator);
        List list = infoXioudeMapper.selectByInfoXioude(map1);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
    }

    /**
     * @param infoId
     * @return
     * @Author: tianliya
     * @Decription:根据infoID删除西欧的数据
     * @Date 2017/10/15 21:53
    */
    @Transactional
    public int deleteXioudeById(Long infoId) {
       int i = infoXioudeMapper.deleteXioude(infoId);
        return i;
    }

    /**
     * @param title 标题
     * @param state 状态
     * @param operator 发布人
     * @param infoId 主键
     * @return
     * @Author: tianliya
     * @Decription:  修改西欧的的信息
     * @Date 2017/10/15 22:08
    */
    @Transactional
    public int modifyXioude(String title, Short state, String operator, Long infoId, String contentpath, String imagepath) {
        Map map = new HashMap();
        map.put("title",title);
        map.put("state",state);
        map.put("operator",operator);
        map.put("infoId",infoId);
        map.put("contentpath",contentpath);
        map.put("imagepath",imagepath);
        return infoXioudeMapper.edit(map);
    }

    /**
     * @param infoId 西欧的中心数据主键
     * @return
     * @Author: tianliya
     * @Decription:
     * @Date 2017/10/15 22:29
    */
    @Override
    public Map<String, Object> getOneByInfoId(Long infoId) {
        Map map = infoXioudeMapper.selectOneByInfoId(infoId);
        return map;
    }

    /**
     * 发布xioude  tianliya
     * @param infoId
     * @return
     */
    @Override
    public int releaseXioude(Long infoId, String operator) {
        Map map = new HashMap();
        map.put("infoId",infoId);
        map.put("operator",operator);
        return infoXioudeMapper.releaseXioude(map);
    }

    /**
     * 获取一条数据
     * @param infoId
     * @return
     */
    @Override
    public Map<String, Object> getOne(Long infoId) {
        Map map = infoXioudeMapper.selectOneByInfoId(infoId);
        return map;
    }
}
