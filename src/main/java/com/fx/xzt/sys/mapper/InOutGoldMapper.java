package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.InOutGold;

/**
 * 
* @ClassName: InOutGoldMapper 
* @Description: 出入金管理
* @author htt
* @date 2017-10-20 下午2:33:32 
*
 */
@Repository
public interface InOutGoldMapper extends BaseMapper<InOutGold> {

	List<Map<String, Object>> selectByInOutGold (Map<String,Object> map);
}
