package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.WorldCupTeams;

public interface WorldCupTeamsMapper extends BaseMapper<WorldCupTeams>{
    int deleteOne(Long id);

    int insertOne(WorldCupTeams record);


    WorldCupTeams selectOne(Long id);

    int updateOneSelective(WorldCupTeams record);

    int updateOne(WorldCupTeams record);
}