package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fx.xzt.sys.entity.ShareRegisterRecord;

/**
 * 
* @ClassName: ShareRegisterRecordMapper 
* @Description: 分享注册记录
* @author htt
* @date 2018-3-5 上午10:39:51 
*
 */
@Repository
public interface ShareRegisterRecordMapper extends BaseMapper<ShareRegisterRecord> {
	
	List<Map<String, Object>> selectByAll (Map<String,Object> map);

}
