package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.util.CommonResponse;

import java.util.List;

public interface WorldCupRecordService {
    /**
     * 统计竞猜各国家队冠军人次 laj
     *
     * @return
     */
    public CommonResponse getCountGuess();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/5 15:49
     * @Description：获取用户猜测世界冠军结果
     * @return
     */
    public CommonResponse getGuessWinner(Users users,Short isGuessing,String userName,Integer pageNum, Integer pageSize);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/5 16:45
     * @Description：导出用户猜测世界冠军结果
     * @param users
     * @param isGuessing
     * @return
     */
    public CommonResponse exportGuessWinner(Users users,Short isGuessing);
}
