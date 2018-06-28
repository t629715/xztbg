package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.FinanceConfNewplayer;
import com.fx.xzt.sys.mapper.FinanceConfNewplayerMapper;
import com.fx.xzt.sys.service.FinanceConfNewplayerService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @author:tianliya
 * @CreateTime:2018-01-12 13:49
 * @Description:新手理财产品业务层实现
 **/
@Service
public class FinanceConfNewpalyerServiceImpl implements FinanceConfNewplayerService {
    @Autowired
    private FinanceConfNewplayerMapper financeConfNewplayerMapper;

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/1/12 13:46
     * @Description：获取所有的新手理财产品
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public CommonResponse getAllFinanceConfsNew(Integer pageNum, Integer pageSize) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<FinanceConfNewplayer> financeConfNewplayers = financeConfNewplayerMapper.selectAll();
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            commonResponse.setMsg("获取所有新手理财产品成功");
            commonResponse.setData(financeConfNewplayers);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_NOEXIST);
            commonResponse.setMsg("获取所有新手理财产品失败");
        }
        return commonResponse;
    }
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/1/12 13:46
     * @Description：根据id修改新手专属理财产品
     * @param financeConfNewplayer
     * @return
     */
    @Override
    public CommonResponse modifyFinanceConf(FinanceConfNewplayer financeConfNewplayer) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Map map = new ConcurrentHashMap();
            map.put("id",financeConfNewplayer.getId());
            map.put("yearIncomPercent",financeConfNewplayer.getYearIncomPercent());
            Integer success = financeConfNewplayerMapper.updateSelective(map);
            if (success > 0){
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("修改新手理财产品成功");
                commonResponse.setData("{}");
            }else {
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("修改新手理财产品失败，理财产品不存在");
                commonResponse.setData("{}");
            }

        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_NOEXIST);
            commonResponse.setMsg("修改新手理财产品失败");
            commonResponse.setData("{}");
        }
        return commonResponse;
    }

    @Override
    public CommonResponse removeById(Long id) {
        CommonResponse commonResponse = new CommonResponse();
        try{
            Integer success = financeConfNewplayerMapper.deleteById(id);
            if (success > 0){
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("删除新手理财产品成功");
                commonResponse.setData("{}");
            }else {
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("删除新手理财产品失败，理财产品不存在");
                commonResponse.setData("{}");
            }
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_NOEXIST);
            commonResponse.setMsg("删除新手理财产品失败");
            commonResponse.setData("{}");
        }
        return commonResponse;
    }
}
