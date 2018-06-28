package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.InVestGoldOrder;

/**
 * 
* @ClassName: InVestGoldOrderMapper 
* @Description: 金条投资订单
* @author htt
* @date 2018-4-19 下午3:49:42 
*
 */
@Repository
public interface InVestGoldOrderMapper extends BaseMapper<InVestGoldOrder> {
	
	/**
	 * 
	* @Title: selectByAll 
	* @Description: 查询
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByAll (Map<String,Object> map);
	
	/**
	 * 
	* @Title: excelByAll 
	* @Description: 查询统计
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	Map<String, Object> countByAll (Map<String,Object> map);

	/**
	 * 
	* @Title: updateById 
	* @Description: 修改
	* @param inVestGoldOrder
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author htt
	 */
	int updateById(InVestGoldOrder inVestGoldOrder);
}
