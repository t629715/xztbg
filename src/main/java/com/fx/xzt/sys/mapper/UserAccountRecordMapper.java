package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.UserAccountRecord;

public interface UserAccountRecordMapper extends BaseMapper<UserAccountRecord>{
	List<UserAccountRecord> getAll(Map<String,Object> map);
	/**
	 * 奖励发放记录
	 * @param map
	 * @return
	 */
	List<UserAccountRecord> getGameAward(Map<String,Object> map);
	/**
	 * 添加
	 */
	int add(UserAccountRecord userAccountRecord);
	/**
	 * 修改
	 */
	int updateByIdSelective(UserAccountRecord userAccountRecord);
}
