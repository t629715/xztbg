package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.StandardUser;

/**
 * 
* @ClassName: StandardUserMapper 
* @Description: 标准户统计
* @author htt
* @date 2018-2-3 上午11:16:20 
*
 */
@Repository
public interface StandardUserMapper extends BaseMapper<StandardUser> {

	/**
	 * 
	* @Title: selectByStandardUser 
	* @Description: 标准户统计
	* @param map
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String, Object>> selectByStandardUser (Map<String,Object> map);
	List<Map<String, Object>> selectNetGoldRecords (Long userId);
	List<Map<String, Object>> selectGoldRightGram (Map<String,Object> map);

}
