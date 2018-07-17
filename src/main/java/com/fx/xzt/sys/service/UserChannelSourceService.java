package com.fx.xzt.sys.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author WuJiaNan
 * Created time 2018/7/17
 * @Description
 */
public interface UserChannelSourceService {

    PageInfo<Map<String, Object>>  coutRegistFrom(Integer pageNum, Integer pageSize);




}
