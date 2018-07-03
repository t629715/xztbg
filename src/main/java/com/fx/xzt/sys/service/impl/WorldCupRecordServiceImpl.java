package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.mapper.WorldCupRecordMapper;
import com.fx.xzt.sys.service.WorldCupRecordService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WorldCupRecordServiceImpl implements WorldCupRecordService {
    @Resource
    WorldCupRecordMapper worldCupRecordMapper;

    /**
     * 统计竞猜各国家队冠军人次 laj
     *
     * @return
     */
    @Override
    public List getCountGuess() {
        return  worldCupRecordMapper.countGuess();
           }
}
