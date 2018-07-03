package com.fx.xzt.sys.service.impl;
import com.fx.xzt.sys.entity.WorldCupCompetition;
import com.fx.xzt.sys.mapper.WorldCupCompetitionMapper;
import com.fx.xzt.sys.service.WorldCupCompetitionService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class WorldCupCompetitionServiceImpl implements WorldCupCompetitionService {
    @Resource
    WorldCupCompetitionMapper worldCupCompetitionMapper;
    /**
     * 统计两队信息  laj
     * @param record
     * @return
     */
    @Override
    public CommonResponse updateByPrimaryKeySelective(WorldCupCompetition record) {
        CommonResponse commonResponse = new CommonResponse();

        int l= worldCupCompetitionMapper.updateOne(record);
       if(l>0){
            commonResponse.setCode(Constant.RESCODE_SUCCESS);

        }else {
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
        }
        return commonResponse;
    }
}
