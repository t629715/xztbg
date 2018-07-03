package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.WorldCupRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface WorldCupRecordMapper extends BaseMapper<WorldCupRecord>{
    /**
     * 通过比赛id和竞猜类型得到竞猜记录list
     * @param competitionId 比赛ID
     * @param type 竞猜类型 1:常规比赛竞猜,2:八队冠军竞猜 只有guessing_winner有值
     * @return List<WorldCupRecord>
     */
    List<WorldCupRecord> findByCompetitionIdAndType(@Param("competitionId") Long competitionId, @Param("type")short type);

    /**
     * 通过比赛ID和userID来修改竞猜记录计算状态：由0变为1
     * @param competitionId 比赛ID
     * @param userId 竞猜类型 1:常规比赛竞猜,2:八队冠军竞猜 只有guessing_winner有值
     * @return 修改条数
     */
    int updateSettlement(@Param("competitionId") Long competitionId,@Param("userId") Long userId);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 14:31
     * @Description：竞猜结果统计
     * @return
     */
    List<Map<String, Object>>  selectGuessResult();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 15:16
     * @Description：获取
     * @param competitionId
     * @return
     */
    List<WorldCupRecord> selectSettlement(Long competitionId);
}