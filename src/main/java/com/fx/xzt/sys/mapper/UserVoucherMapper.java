package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserVoucher;

/**
 * 
* @ClassName: UserVoucherMapper 
* @Description: 金权交易优惠券
* @author htt
* @date 2017-11-8 下午3:32:22 
*
 */
@Repository
public interface UserVoucherMapper extends BaseMapper<UserVoucher> {

    /**
     *  优惠券查询
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByUserVoucher (Map<String,Object> map);


 }
