package com.fx.xzt.sys.service;


import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.util.CommonResponse;

/**
 * 
* @ClassName: UserGoldAccountService
* @Description: 用户账户余额
* @author htt
* @date 2017-11-7 下午4:24:21 
*
 */
public interface UserGoldAccountService {

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 11:20
     * @Description：修改用户黄金账户余额
     * @param gold
     * @param userName
     * @return
     */
    CommonResponse updateUserGoldAccount(Users users, Double gold,String  userName,Short type,String description,String operatorName);

}
