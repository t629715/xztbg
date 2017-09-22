package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.Riskaccess;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.mapper.RiskaccessMapper;
import com.fx.xzt.sys.service.RiskaccessService;
import com.fx.xzt.util.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author htt
 * @ClassName: RiskaccessServiceImpl.java
 * @Description:
 * @date 2017-09-22 10:51
 */
@Service
public class RiskaccessServiceImpl extends BaseService<Riskaccess> implements RiskaccessService {

    @Resource
    RiskaccessMapper riskaccessMapper;

    /**
     *  查询风险评测信息
     * @param userName  用户名
     * @param realName  真实姓名
     * @param startTime  评测开始时间
     * @param endTime    评测结束时间
     * @param accessLevel  评测等级
     * @param pageNum
     * @param pageSize
     * @return
     */

    public PageInfo<Map<String, Object>> getByRiskaccessAll(String userName, String realName, String startTime, String endTime, String accessLevel, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("realName", realName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("accessLevel", accessLevel);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = riskaccessMapper.selectByRiskaccessAll(map);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(list);
        return  pagehelper;
    }

    @Transactional
    public int updateLevelById(String level, Integer accessId) {
        Riskaccess r = new Riskaccess();
        r.setAccessLevel(level);
        r.setAccessId(accessId);
        return riskaccessMapper.updateById(r);
    }
}
