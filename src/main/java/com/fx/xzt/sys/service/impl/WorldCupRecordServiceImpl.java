package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.mapper.WorldCupRecordMapper;
import com.fx.xzt.sys.service.WorldCupRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@Service
public class WorldCupRecordServiceImpl implements WorldCupRecordService {
    @Resource
    WorldCupRecordMapper worldCupRecordMapper;

    /**
     * 统计竞猜各国家队冠军人次 laj
     */
    @Override
    public CommonResponse getCountGuess() {
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<Map<String, Object>> list = worldCupRecordMapper.countGuess();
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            commonResponse.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            commonResponse.setMsg("查询失败");
            throw new GlobalException("统计竞猜各国家队冠军人次", "统计竞猜各国家队冠军人次异常");
        }
        return commonResponse;
    }

    @Override
    public CommonResponse getGuessWinner(Users users, Short isGuessing, Integer pageNum, Integer pageSize) {
        CommonResponse commonResponse = new CommonResponse();
        if (users == null) {
            commonResponse.setCode(Constant.RESCODE_NOAUTH);
            commonResponse.setMsg("登录过期");
        }
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Map<String, Object>> list = worldCupRecordMapper.guessWinnerCount(isGuessing);
            if (list.size() != 0) {
                if (isGuessing.toString().equals("3")) {
                    for (Map map : list) {
                        map.put("state", "猜对");
                    }
                } else if (isGuessing.toString().equals("4")) {
                    for (Map map : list) {
                        map.put("state", "猜错");
                    }
                }
            }
            PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            commonResponse.setData(pagehelper);
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            commonResponse.setMsg("查询失败");
            throw new GlobalException("统计用户猜测冠军结果", "统计用户猜测冠军结果异常");
        }
        return commonResponse;
    }

    @Override
    public CommonResponse exportGuessWinner(Users users, Short isGuessing) {
        CommonResponse commonResponse = new CommonResponse();
        if (users == null) {
            commonResponse.setCode(Constant.RESCODE_NOAUTH);
            commonResponse.setMsg("登录过期");
        }
        try {
            List<Map<String, Object>> list = worldCupRecordMapper.guessWinnerCount(isGuessing);
            if (list.size() != 0) {

                for (Map map : list) {
                    if (isGuessing.toString().equals("3")) {
                        map.put("state", "猜对");
                    } else if (isGuessing.toString().equals("4")) {
                        map.put("state", "猜错");
                    }
                }
            }
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            commonResponse.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            commonResponse.setMsg("查询失败");
            throw new GlobalException("统计用户猜测冠军结果", "统计用户猜测冠军结果异常");
        }
        return commonResponse;
    }
}
