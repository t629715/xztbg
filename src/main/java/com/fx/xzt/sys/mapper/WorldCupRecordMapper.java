package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.WorldCupRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
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
     * 统计竞猜各国家队冠军人次 
     *
     * @return
     */
    List<WorldCupRecord> countGuess();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/4 9:00
     * @Description：查询某一场比赛是否已结算
     * @param competitionId
     * @return
     */
    List<WorldCupRecord> selectSettlement(Long competitionId);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/4 8:59
     * @Description：获取竞猜的统计结果：竞猜人数、猜胜人数、猜负人数、发放卡券数
     * @return
     */
    List<Map<String, Object>> selectGuessResult();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/4 9:04
     * @Description：修改用户的竞猜状态为猜胜
     * @return
     */
    int updateUserGuessing0(Long competitionId);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/4 9:33
     * @Description：修改用户竞猜状态为负
     * @param competitionId
     * @return
     */
    int updateUserGuessing1(Long competitionId);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/4 9:33
     * @Description：修改用户竞猜比分为胜
     * @param competitionId
     * @return
     */
    int updateUserGuesse2(Long competitionId);
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/4 9:33
     * @Description：修改竞猜 冠军正确的 (参数 最后一场比赛id)
     * @param competitionId
     * @return
     */
    int updateUserGuesse3(Long competitionId);
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/4 9:33
     * @Description：修改竞猜冠军错误的  (参数 最后一场比赛id)
     * @param competitionId
     * @return
     */
    int updateUserGuesse4(Long competitionId);




}