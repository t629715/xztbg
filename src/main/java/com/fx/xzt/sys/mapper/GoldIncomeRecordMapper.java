package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.GoldIncomeRecord;

/**
 * 
* @ClassName: GoldIncomeRecordMapper 
* @Description: 黄金收益记录
* @author htt
* @date 2017-9-30 下午2:10:28 
*
 */
@Repository
public interface GoldIncomeRecordMapper extends BaseMapper<GoldIncomeRecord> {

	/**
     *  黄金收益记录
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByGoldIncome (Map<String,Object> map);

    /**
     *  黄金收益记录
     * @return
     */
    Map<String, Object> selectByGoldIncomeCount(Map<String,Object> map);
    /**
     * 
    * @Title: selectByGoldGramCount 
    * @Description: 黄金总重
    * @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    * @author htt
     */
    Map<String, Object> selectByGoldGramCount();
}
