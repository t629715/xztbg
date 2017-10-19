package com.fx.xzt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.GoldRedeemConf;
import com.fx.xzt.sys.mapper.GoldRedeemConfMapper;
import com.fx.xzt.sys.service.GoldRedeemConfService;

/**
 * 
* @ClassName: GoldRedeemConfServiceImpl 
* @Description: 黄金赎回配置
* @author htt
* @date 2017-10-19 下午4:32:23 
*
 */
@Service
public class GoldRedeemConfServiceImpl extends BaseService<GoldRedeemConf> implements GoldRedeemConfService {
	
	@Resource
	GoldRedeemConfMapper goldRedeemConfMapper;

	/**
	 * 黄金赎回配置查询
	 */
	public List<Map<String, Object>> selectByGoldRedeemConf(Integer isEnable) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isEnable", isEnable);
        List<Map<String, Object>> list = goldRedeemConfMapper.selectByGoldRedeemConf(map);
        return list;
	}


}
