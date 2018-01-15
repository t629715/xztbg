package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.FinanceConfNewplayer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author:tianliya
 * @CreateTime:2018/1/12 13:34
 * @Description:新手专属产品的持久层 操作的表为：finance_conf_newplayer
 **/
@Component
public interface FinanceConfNewplayerMapper extends BaseMapper<FinanceConfNewplayer>{
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/1/12 13:36
     * @Description：获取所有的新手专属理财
     * @return
     */
    List<FinanceConfNewplayer> selectAll();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/1/12 13:36
     * @Description：修改选中的新手专属理财
     * @param map
     * @return
     */
    int updateSelective(Map<String,Object> map);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/1/12 15:53
     * @Description：根据id删除
     * @param id
     * @return
     */
    int deleteById(Long id);


}