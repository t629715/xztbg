package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.FinanceNewplayerOrder;

/**
 * 
* @ClassName: FinanceNewplayerOrderMapper 
* @Description: 新手理财订单
* @author htt
* @date 2018-1-11 下午3:47:26 
*
 */
@Repository
public interface FinanceNewplayerOrderMapper extends BaseMapper<FinanceNewplayerOrder> {

    /**
     *  新手理财查询
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByAll (Map<String,Object> map);

    /**
     *  新手理财统计
     * @return
     */
    Map<String, Object> selectByAllCount(Map<String,Object> map);
 }
