package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.WorldCupCompetition;

public interface WorldCupCompetitionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorldCupCompetition record);

    int insertSelective(WorldCupCompetition record);

    WorldCupCompetition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorldCupCompetition record);

    int updateByPrimaryKey(WorldCupCompetition record);
}