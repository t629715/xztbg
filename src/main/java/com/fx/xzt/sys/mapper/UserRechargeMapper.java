package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.UserRecharge;
import com.fx.xzt.sys.model.UserRechargeModel;

/**
 * 
* @Title: UserRechargeMapper.java 
* @Package com.fx.xzt.sys.mapper
* @Description: TODO
* @author SYan  
* @date 2017年8月22日 上午9:27:49 
* @version V1.0
 */
@Repository
public interface UserRechargeMapper extends BaseMapper<UserRecharge>{
	List<UserRechargeModel> getByAll(Map<String,Object> map);
}
