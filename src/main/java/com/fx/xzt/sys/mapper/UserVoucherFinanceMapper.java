package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserVoucherFinance;

/**
 * 
* @ClassName: UserVoucherFinanceMapper 
* @Description: 加息券
* @author htt
* @date 2017-11-8 下午3:33:54 
*
 */
@Repository
public interface UserVoucherFinanceMapper extends BaseMapper<UserVoucherFinance> {

    /**
     *  金权交易查询
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByUserVoucherFinance (Map<String,Object> map);


 }
