package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.UserGoldAccount;

public interface UserGoldAccountMapper extends BaseMapper<UserGoldAccount>{
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 11:15
     * @Description：  行级锁，用于更新前的锁表
     * @return
     */
    UserGoldAccount lockForUpdate(UserGoldAccount userGoldAccount);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/7/3 11:14
     * @Description：修改用户的黄金账户余额
     * @param record
     * @return
     */
    int updateOne(UserGoldAccount record);

}