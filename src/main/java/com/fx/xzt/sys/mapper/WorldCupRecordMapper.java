package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.WorldCupRecord;


public interface WorldCupRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorldCupRecord record);

    int insertSelective(WorldCupRecord record);

    WorldCupRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorldCupRecord record);

    int updateByPrimaryKey(WorldCupRecord record);
}