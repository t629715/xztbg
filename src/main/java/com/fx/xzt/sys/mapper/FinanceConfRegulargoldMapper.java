package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.FinanceConfRegulargold;

import java.util.List;

public interface FinanceConfRegulargoldMapper extends BaseMapper<FinanceConfRegulargold>{
    int deleteOne(Long id);

    int insertOne(FinanceConfRegulargold record);


    List<FinanceConfRegulargold> getByAll();
    int modifyOneSelective(FinanceConfRegulargold record);

}