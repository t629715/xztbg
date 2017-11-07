package com.fx.xzt.sys.service;


/**
 * 
* @ClassName: UserAccountService 
* @Description: 用户账户余额
* @author htt
* @date 2017-11-7 下午4:24:21 
*
 */
public interface UserAccountService {
	
    /**
     * 
    * @Title: updateByGoldReedm 
    * @Description: 黄金赎回成功更改账户余额
    * @param rmb
    * @param id
    * @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author htt
     */
    int updateByGoldReedm(Long rmb, Long id);

}
