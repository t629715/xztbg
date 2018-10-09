package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.InvestGoldConf;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface InvestGoldConfMapper extends BaseMapper<InvestGoldConf>{

    List<Map<String, Object>> selectAllInvestGoldConf();
    InvestGoldConf selectById(Long id);

    int updateById(Map<String, Object> map);

}