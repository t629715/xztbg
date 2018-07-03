package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.WorldCupCompetition;

import java.util.List;

public interface WorldCupCompetitionMapper extends BaseMapper<WorldCupCompetition>{
    int deleteOne(Long id);


    int insertOne(WorldCupCompetition record);

    WorldCupCompetition selectOne(Long id);

    List<WorldCupCompetition> selectCompetitions();

    int updateOne(WorldCupCompetition record);
}