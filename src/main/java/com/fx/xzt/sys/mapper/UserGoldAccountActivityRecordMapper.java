package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserGoldAccountActivityRecord;

/**
 * 
* @ClassName: UserGoldAccountActivityRecordMapper 
* @Description: 活动--黄金领取信息
* @author htt
* @date 2017-12-5 下午5:23:17 
*
 */
@Repository
public interface UserGoldAccountActivityRecordMapper extends BaseMapper<UserGoldAccountActivityRecord> {

	/**
	 * 
	* @Title: selectByDealOrder 
	* @Description: 黄金领取查询
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByAll (Map<String,Object> map);

}
