package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.UserChannelSource;

import java.util.List;
import java.util.Map;


/**
 * @author WuJiaNan
 * Created time 2018/7/17
 * @Description
 */
public interface UserChannelSourceMapper extends BaseMapper<UserChannelSource>{

    List<Map<String,Object>> coutRegistFrom ();


}
