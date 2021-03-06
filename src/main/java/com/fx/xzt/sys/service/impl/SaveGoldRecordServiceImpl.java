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

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.FinanceRegulargoldOrder;
import com.fx.xzt.sys.entity.SaveGoldRecord;
import com.fx.xzt.sys.mapper.FinanceRegulargoldOrderMapper;
import com.fx.xzt.sys.mapper.SaveGoldRecordMapper;
import com.fx.xzt.sys.service.SaveGoldRecordService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: SaveGoldRecordServiceImpl 
* @Description: 存金记录
* @author htt
* @date 2018-5-29 下午2:32:15 
*
 */
@Service
public class SaveGoldRecordServiceImpl extends BaseService<SaveGoldRecord> implements SaveGoldRecordService {

	@Resource
	SaveGoldRecordMapper mapper;
	@Resource
	FinanceRegulargoldOrderMapper financeRegulargoldOrderMapper;

	/**
	 * 查询
	 * @throws ParseException 
	 */
	public PageInfo<Map<String, Object>> selectByAll(String userName, String startTime, String endTime, 
			String regStartTime, String regEndTime, String agentName, String brokerName, Short type,
			String isView, Integer pageNum, Integer pageSize) throws ParseException {
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
        map.put("type", type);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = mapper.selectByAll(map);
        handle(list);
        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}

	/**
	 * 导出
	 * @throws ParseException 
	 */
	public List<Map<String, Object>> excelByAll(String userName, String startTime, String endTime, 
			String regStartTime, String regEndTime, String agentName, String brokerName, Short type,
			String isView) throws ParseException {
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
        /*map.put("agentName", agentName);
        map.put("brokerName", brokerName);*/
        map.put("type", type);
        map.put("isView", isView);
        List<Map<String, Object>> list = mapper.selectByAll(map);
        handle(list);
        return list;
	}

	/**
	 * 统计
	 */
	public Map<String, Object> countByAll(String userName, String startTime, String endTime, 
			String regStartTime, String regEndTime, String agentName, String brokerName, Short type) {
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
        map.put("type", type);
        Map<String, Object> map1 = mapper.countByAll(map);
        if (map1 != null && map1.size() > 0) {
			Object rmbAmountSumeObj = map1.get("rmbAmountSum");
			if (rmbAmountSumeObj != null && rmbAmountSumeObj != "") {
				Double rmbAmountSum = Double.valueOf(rmbAmountSumeObj.toString());
				map1.put("rmbAmountSum", rmbAmountSum/100);
			}
			Object profitAndLossSumObj = map1.get("profitAndLossSum");
			if (profitAndLossSumObj != null && profitAndLossSumObj != "") {
				Double profitAndLossSum = Double.valueOf(profitAndLossSumObj.toString());
				map1.put("profitAndLossSum", profitAndLossSum/100);
			}
			Object saleFeeSumObj = map1.get("saleFeeSum");
			if (saleFeeSumObj != null && saleFeeSumObj != "") {
				Double saleFeeSum = Double.valueOf(saleFeeSumObj.toString());
				map1.put("saleFeeSum", saleFeeSum/100);
			}
        }
        return map1;
	}
	
	/**
	 * 
	* @Title: handle 
	* @Description: 数据处理
	* @param list    设定文件 
	* @return void    返回类型 
	 * @throws ParseException 
	* @throws 
	* @author htt
	 */
	public void handle(List<Map<String, Object>> list) throws ParseException {
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Object rmbAmounteObj = map.get("rmbAmount");
				if (rmbAmounteObj != null && rmbAmounteObj != "") {
					Double rmbAmount = Double.valueOf(rmbAmounteObj.toString());
					map.put("rmbAmount", rmbAmount/100);
				}
				Object goldPriceObj = map.get("goldPrice");
				if (goldPriceObj != null && goldPriceObj != "") {
					Double goldPrice = Double.valueOf(goldPriceObj.toString());
					map.put("goldPrice", goldPrice/100);
				}
				Object averagePriceObj = map.get("averagePrice");
				if (averagePriceObj != null && averagePriceObj != "") {
					Double averagePrice = Double.valueOf(averagePriceObj.toString());
					map.put("averagePrice", averagePrice/100);
				}
				Object discountAmountObj = map.get("discountAmount");
				if (discountAmountObj != null && discountAmountObj != "") {
					Double discountAmount = Double.valueOf(discountAmountObj.toString());
					map.put("discountAmount", discountAmount/100);
				}
				Object discountPriceObj = map.get("discountPrice");
				if (discountPriceObj != null && discountPriceObj != "") {
					Double discountPrice = Double.valueOf(discountPriceObj.toString());
					map.put("discountPrice", discountPrice/100);
				}
				Object profitAndLossObj = map.get("profitAndLoss");
				if (profitAndLossObj != null && profitAndLossObj != "") {
					Double profitAndLoss = Double.valueOf(profitAndLossObj.toString());
					map.put("profitAndLoss", profitAndLoss/100);
				}
				Object saleFeeObj = map.get("saleFee");
				if (saleFeeObj != null && saleFeeObj != "") {
					Double saleFee = Double.valueOf(saleFeeObj.toString());
					map.put("saleFee", saleFee/100);
				}
				Object createTimeObj = map.get("createTime");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (createTimeObj != null && createTimeObj != "") {
					map.put("createTime", sdf.format(sdf.parse(createTimeObj.toString())));
				}
				Object registerTimeObj = map.get("registerTime");
				if (registerTimeObj != null && registerTimeObj != "") {
					map.put("registerTime", sdf.format(sdf.parse(registerTimeObj.toString())));
				}
            }
		}
	}

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/11 11:07
	 * @Description：存金宝买入总金额
	 * @return
	 */
	public CommonResponse countBuyGold(){
		CommonResponse commonResponse = new CommonResponse();
		try{
			Double saveGoldAmount = mapper.countBuyGold();
			Float steadyGoldAmount = financeRegulargoldOrderMapper.countGramOnTime();

			Map map = new HashMap();
			if (saveGoldAmount != null){
				map.put("saveGoldAmount",saveGoldAmount);
			}
			if (steadyGoldAmount != null){
				map.put("steadyGoldAmount",steadyGoldAmount);
			}
			commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
			commonResponse.setData(map);
		}catch (Exception e){
			e.printStackTrace();
			throw new GlobalException("对冲套利-金生金与存金宝","对冲套利-金生金与存金宝异常");
		}
		return  commonResponse;
	}
}
