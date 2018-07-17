package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.UserChannelSource;
import com.fx.xzt.sys.mapper.UserChannelSourceMapper;
import com.fx.xzt.sys.service.UserChannelSourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author WuJiaNan
 * Created time 2018/7/17
 * @Description
 */
@Service
public class UserChannelSourceServiceImpl  extends BaseService<UserChannelSource> implements UserChannelSourceService {


    @Resource
    private UserChannelSourceMapper userChannelSourceMapper;


    @Override
    public PageInfo<Map<String, Object>> coutRegistFrom(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> maps = userChannelSourceMapper.coutRegistFrom();
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(maps);
        return pagehelper;
    }
}
