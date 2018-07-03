package com.fx.xzt.sys.service;

import com.fx.xzt.sys.util.CommonResponse;

import java.util.List;

public interface WorldCupRecordService {
    /**
     * 统计竞猜各国家队冠军人次 laj
     *
     * @return
     */
    public List getCountGuess();
}
