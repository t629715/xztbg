package com.fx.xzt.sys.mapper;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.InvestGoldOrderItem;

/**
 * 
* @ClassName: InVestGoldOrderItemMapper 
* @Description: 金条订单操作记录
* @author htt
* @date 2018-5-30 下午2:59:13 
*
 */
@Repository
public interface InVestGoldOrderItemMapper extends BaseMapper<InvestGoldOrderItem> {
	
	/**
	 * 
	* @Title: add 
	* @Description: 新增操作记录
	* @param item
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int add(InvestGoldOrderItem item);

}
