package com.fx.xzt.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fx.xzt.sys.entity.StandardUser;
import com.fx.xzt.sys.mapper.StandardUserMapper;
import com.fx.xzt.sys.service.StandardUserService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: StandardUserServiceImpl 
* @Description: 标准户查询统计
* @author htt
* @date 2018-2-3 下午2:22:06 
*
 */
@Service
public class StandardUserServiceImpl extends BaseService<StandardUser> implements StandardUserService {
	
	@Resource
	StandardUserMapper standardUserMapper;

	/**
	 * 查询统计
	 * @throws ParseException 
	 */
	public PageInfo<Map<String, Object>> getByStandardUser(String userName,
			String agentName, String brokerName, String startTime,
			String endTime, String regStartTime, String regEndTime, String bzh,
			Integer pageNum, Integer pageSize) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("bzh", bzh);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = standardUserMapper.selectByStandardUser(map);
        handle(list);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 导出
	 * @throws ParseException 
	 */
	public List<Map<String, Object>> excelByStandardUser(String userName,
			String agentName, String brokerName, String startTime,
			String endTime, String regStartTime, String regEndTime, String bzh) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("bzh", bzh);
        List<Map<String, Object>> list = standardUserMapper.selectByStandardUser(map);
        handle(list);
        return list;
	}

	/**
	 * 
	* @Title: handle 
	* @Description: 数据处理
	* @param list
	* @throws ParseException    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author htt
	 */
	public void handle(List<Map<String, Object>> list) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                map.put("bzh", ConstantUtil.isStandardUser.toMap().get(map.get("bzh").toString()));
                Object registerTimeObj = map.get("RegisterTime");
       		 	if (registerTimeObj != null && registerTimeObj != "") {
       		 		map.put("RegisterTime", sdf.format(sdf.parse(registerTimeObj.toString())));
                }
	       		Object cjCountObj = map.get("cjCount");
	    		if (cjCountObj != null && cjCountObj != "") {
	    		 	Double cjCount = Double.valueOf(cjCountObj.toString());
                    map.put("cjCount", cjCount/100);
	            }
	    		Object rjCountObj = map.get("rjCount");
	    		if (rjCountObj != null && rjCountObj != "") {
	    		 	Double rjCount = Double.valueOf(rjCountObj.toString());
                    map.put("rjCount", rjCount/100);
	            }
            }
		}
	}
}
