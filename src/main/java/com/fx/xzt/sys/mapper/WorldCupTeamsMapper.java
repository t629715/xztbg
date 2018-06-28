package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.WorldCupTeams;

public interface WorldCupTeamsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorldCupTeams record);

    int insertSelective(WorldCupTeams record);

    WorldCupTeams selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorldCupTeams record);

    int updateByPrimaryKey(WorldCupTeams record);
}