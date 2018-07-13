package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.InviteRegisterRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface InviteRegisterRecordMapper extends BaseMapper<InviteRegisterRecord>{
    /**
     * 邀请注册记录查询
     * @param map
     * @return
     */
    List<Map<String, Object>> selectAll (Map<String,Object> map);

}
