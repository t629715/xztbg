package com.fx.xzt.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
			String endTime, String regStartTime, String regEndTime, String bzh, String isView, 
			Integer pageNum, Integer pageSize) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
		if(!StringUtils.isBlank(agentName))	{
			String [ ] agentNames=agentName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentName", list);
			}
		}
		if(!StringUtils.isBlank(brokerName)){
			String [ ] brokerNames=brokerName.split(",");
			if(brokerNames !=null || brokerNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:brokerNames) {
					list.add(s);
				}
				map.put("brokerName", list);
			}
		}
       /* map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("bzh", bzh);
        map.put("isView", isView);
		List<Map<String, Object>> list = new ArrayList<>();
		int pages = 0;
		int total = 0;
        if ("".equals(bzh)){
			PageHelper.startPage(pageNum,pageSize);
			list = standardUserMapper.selectGoldRightGram(map);

			PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
			list = handle(list,bzh);
			pagehelper.setList(list);
			return pagehelper;
		}else {
			list = standardUserMapper.selectGoldRightGram(map);
			list = handle(list,bzh);
			pages = list.size()%pageNum == 0?list.size()/pageSize:(list.size()/pageSize+1);
			total = list.size();
			if (list.size() != 0){
				if ((pageNum-1)*pageSize<total){
					list = list.subList((pageNum-1)*pageSize,pageSize*pageNum>total?total:pageSize*pageNum);
				}else {
					list = new ArrayList<>();
				}
			}
		}
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        pagehelper.setTotal(total);
        pagehelper.setPages(pages);
        return pagehelper;
	}

	/**
	 * 导出
	 * @throws ParseException 
	 */
	public List<Map<String, Object>> excelByStandardUser(String userName,
			String agentName, String brokerName, String startTime,
			String endTime, String regStartTime, String regEndTime, String bzh, String isView) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("regStartTime", regStartTime);
        map.put("regEndTime", regEndTime);
		if(!StringUtils.isBlank(agentName))	{
			String [ ] agentNames=agentName.split(",");
			if(agentNames !=null || agentNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:agentNames) {
					list.add(s);
				}
				map.put("agentName", list);
			}
		}
		if(!StringUtils.isBlank(brokerName)){
			String [ ] brokerNames=brokerName.split(",");
			if(brokerNames !=null || brokerNames.length!=0 ){
				List<String> list = new ArrayList();
				for(String s:brokerNames) {
					list.add(s);
				}
				map.put("brokerName", list);
			}
		}
       /* map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("bzh", bzh);
        map.put("isView", isView);
		List<Map<String, Object>> list = standardUserMapper.selectGoldRightGram(map);
        list = handle(list,bzh);
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
	public List<Map<String,Object>> handle(List<Map<String, Object>> list,String bzh) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> users = new ArrayList<>();
		List<Map<String, Object>> isBZH = new ArrayList<>();
		List<Map<String, Object>> notBZH = new ArrayList<>();
		if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
            	List<Map<String,Object>> records = standardUserMapper.selectNetGoldRecords(new Long(map.get("UserID").toString()));
				float gram = 0;
				if (map.get("cjyNum") != null){
					gram = new Float(map.get("cjyNum").toString());
				}
				float sum = 0;
				if (gram >= 50){
					for (Map<String, Object> record : records){
						Float amount = new Float(record.get("amount").toString()) ;
						int state = Integer.parseInt(record.get("state").toString());
						if (state == 1){
							sum += amount;
						}else {
							sum -= amount;
						}
						if (sum >= 10000){
							break;
						}
					}
					if ( sum >= 10000){
						map.put("bzh", "是");
						isBZH.add(map);
					}else {
						map.put("bzh", "否");
						notBZH.add(map);
					}
				}else {
					map.put("bzh", "否");
					notBZH.add(map);
				}
				map.put("rjCount",sum);
				users.add(map);
            }
			if ("0".equals(bzh)){
				list = notBZH;
			}else if ("1".equals(bzh)){
				list = isBZH;
			}else {
				list = users;
			}
		}
		return list;
	}


	public void removeRecord(List<Map<String, Object>> list,String bzh) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (list != null && list.size() > 0) {
			List<Map<String,Object>> records = new ArrayList<>();
			for (Map<String, Object> map : list) {
				if ("1".equals(bzh)){
					if (map.get("bzh").toString().equals("否")){
						records.add(map);
					}
				}else if ("0".equals(bzh)){
					if (map.get("bzh").toString().equals("是")){
						records.add(map);
					}
				}

			}
			list.removeAll(records);
		}
	}
}
