package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.Riskaccess;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author htt
 * @ClassName: RiskaccessService.java
 * @Description:
 * @date 2017-09-22 10:45
 */
public interface RiskaccessService {

    PageInfo<Map<String, Object>> getByRiskaccessAll(String userName, String realName, String startTime, String endTime, String accessLevel, Integer pageNum, Integer pageSize);

    int updateLevelById(String level, String accessId);
}
