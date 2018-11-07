package com.fx.xzt.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sfserver.service.SfExpressResponse;
import com.fx.xzt.sfserver.service.SfServiceImpl;
import com.fx.xzt.sys.mapper.ConfigParamMapper;
import com.fx.xzt.sys.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fx.xzt.sys.entity.InVestGoldOrder;
import com.fx.xzt.sys.entity.InvestGoldOrderItem;
import com.fx.xzt.sys.mapper.InVestGoldOrderItemMapper;
import com.fx.xzt.sys.mapper.InVestGoldOrderMapper;
import com.fx.xzt.sys.service.InVestGoldOrderService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: InVestGoldOrderServiceImpl 
* @Description: 金条投资订单
* @author htt
* @date 2018-4-19 下午3:43:02 
*
 */
@Service
public class InVestGoldOrderServiceImpl extends BaseService<InVestGoldOrder> implements InVestGoldOrderService {
	
	@Resource
	InVestGoldOrderMapper mapper;
	@Resource
	InVestGoldOrderItemMapper itemMapper;

	@Resource
	SfServiceImpl sfService;
	@Resource
	private ConfigParamMapper configParamMapper;

	/**
	 * 查询
	 * @throws ParseException 
	 */
	@Override
	public PageInfo<Map<String, Object>> selectByAll(String userName, String startTime, String endTime, String agentName,
			String brokerName, Integer status, Integer payType, String isView, Integer pageNum, Integer pageSize) throws ParseException {
		Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
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
        map.put("status", status);
        map.put("payType", payType);
        map.put("isView", isView);
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> list = mapper.selectByAll(map);
        handle(list);

        PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
        return pagehelper;
	}
	/**
	 * 金权交割查询
	 * @throws ParseException
	 */
	@Override
	public PageInfo<Map<String, Object>> selectByAllDelivery(String userName,Long investGoldId, String startTime, String endTime, String agentName,
															 String brokerName, Integer status, Integer payType, String isView, Integer pageNum, Integer pageSize) throws ParseException {
		Map<String, Object> map = new HashMap<>(16);
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
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
		map.put("status", status);
		map.put("payType", payType);
		map.put("isView", isView);
		map.put("investGoldId",investGoldId);
		PageHelper.startPage(pageNum,pageSize);

		List<Map<String, Object>> list = mapper.selectByAllDelivery(map);
		handle(list);
		PageInfo<Map<String, Object>> pagehelper = new PageInfo<>(list);
		return pagehelper;
	}
	/**
	 * 导出
	 * @throws ParseException 
	 */
	@Override
	public List<Map<String, Object>> excelByAll(String userName, String startTime, String endTime, String agentName,
			String brokerName, Integer status, Integer payType, String isView) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
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
        map.put("status", status);
        map.put("payType", payType);
        map.put("isView", isView);
        List<Map<String, Object>> list = mapper.selectByAll(map);
        handle(list);
        return list;
	}
	/**
	 * 交割导出
	 * @throws ParseException
	 */
	@Override
	public List<Map<String, Object>> excelByAllDelivery(String userName,Long investGoldId, String startTime, String endTime, String agentName,
												String brokerName, Integer status, Integer payType, String isView) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
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
		map.put("status", status);
		map.put("payType", payType);
		map.put("isView", isView);
        map.put("investGoldId",investGoldId);
		List<Map<String, Object>> list = mapper.selectByAllDelivery(map);
		handle(list);
		return list;
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
				Object payTypeObj = map.get("payType");
				if (payTypeObj != null && payTypeObj != "" ) {
					map.put("payType", ConstantUtil.payType.toMap().get(payTypeObj.toString()));
				}

				Object statusObj = map.get("status");
				if (statusObj != null && statusObj != "") {
					map.put("status", ConstantUtil.inVestGoldOrderStatus.toMap().get(statusObj.toString()));

				}
				Object actualPaymentObj = map.get("actualPayment");
				if (actualPaymentObj != null && actualPaymentObj != "") {
						Double actualPayment = Double.valueOf(actualPaymentObj.toString());
						map.put("actualPayment", actualPayment/100);
				}
				Object goldBasePriceObj = map.get("goldBasePrice");
				if (goldBasePriceObj != null && goldBasePriceObj != "") {
					Double goldBasePrice = Double.valueOf(goldBasePriceObj.toString());
					map.put("goldBasePrice", goldBasePrice/100);
				}
				Object invoiceServiceObj = map.get("invoiceService");
				if (invoiceServiceObj != null && invoiceServiceObj != "") {
					Double invoiceService = Double.valueOf(invoiceServiceObj.toString());
					map.put("invoiceService",invoiceService/100);
				}
				Object processingServiceObj = map.get("processingService");
				if (processingServiceObj != null && processingServiceObj != "") {
					Double processingService = Double.valueOf(processingServiceObj.toString());
					map.put("processingService",processingService/100);
				}
				Object totalMoneyObj = map.get("totalMoney");
				if (totalMoneyObj != null && totalMoneyObj != "") {
					Double totalMoney = Double.valueOf(totalMoneyObj.toString());
					map.put("totalMoney",totalMoney/100);
				}
				Object discountAmountObj = map.get("discountAmount");
				if (discountAmountObj != null && discountAmountObj != "") {
					Double discountAmount = Double.valueOf(discountAmountObj.toString());
					map.put("discountAmount",discountAmount/100);
				}
				Object goldMoneyObj = map.get("goldMoney");
				if (goldMoneyObj != null && goldMoneyObj != "") {
					Double goldMoney = Double.valueOf(goldMoneyObj.toString());
					map.put("goldMoney", goldMoney/100);
				}
				
				Object serviceMoneyObj = map.get("serviceMoney");
				if (serviceMoneyObj != null && serviceMoneyObj != "") {
					Double serviceMoney = Double.valueOf(serviceMoneyObj.toString());
					map.put("serviceMoney", serviceMoney/100);
				}
				Object poundageObj = map.get("poundage");
				if (poundageObj != null && poundageObj != "") {
					Double poundage = Double.valueOf(poundageObj.toString());
					map.put("poundage", poundage/100);
				}
				Object investGoldServiceObj = map.get("investGoldService");
				if (investGoldServiceObj != null && investGoldServiceObj != "") {
					Double investGoldService = Double.valueOf(investGoldServiceObj.toString());
					map.put("investGoldService", investGoldService/100);
				}
				Object logisticsFeeObj = map.get("logisticsFee");
				if (logisticsFeeObj != null && logisticsFeeObj != "") {
					Double logisticsFee = Double.valueOf(logisticsFeeObj.toString());
					map.put("logisticsFee", logisticsFee/100);
				}
				Object createTimeObj = map.get("createTime");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (createTimeObj != null && createTimeObj != "") {
					map.put("createTime", sdf.format(sdf.parse(createTimeObj.toString())));
				}
				Object sendTimeObj = map.get("sendTime");
				if (sendTimeObj != null && sendTimeObj != "") {
					map.put("sendTime", sdf.format(sdf.parse(sendTimeObj.toString())));
				}
            }
		}
	}

	/**
	 * 金条订单统计
	 */
	@Override
	public Map<String, Object> countByAll(String userName, String startTime,
			String endTime, String agentName, String brokerName,
			Integer status, Integer payType) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
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
        map.put("status", status);
        map.put("payType", payType);
        Map<String, Object> map1 = mapper.countByAll(map);
        if (map1 != null && map1.size() > 0) {

        	Object logisticsFeeObj = map1.get("logisticsFeeSum");
			if (logisticsFeeObj != null && logisticsFeeObj != "") {
				Double logisticsFeeSum = Double.valueOf(logisticsFeeObj.toString());
				map1.put("logisticsFeeSum", logisticsFeeSum/100);
			}
			Object serviceMoneySumObj = map1.get("serviceMoneySum");
			if (serviceMoneySumObj != null && serviceMoneySumObj != "") {
				Double serviceMoneySum = Double.valueOf(serviceMoneySumObj.toString());
				map1.put("serviceMoneySum", serviceMoneySum/100);
			}

        }
        return map1;
	}
	/**
	 * 交割订单统计
	 */
	@Override
	public Map<String, Object> countByAllDelivery(String userName,Long investGoldId, String startTime,
												  String endTime, String agentName, String brokerName,
												  Integer status, Integer payType) {
		Map<String, Object> map = new HashMap<>();
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
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
		map.put("status", status);
		map.put("payType", payType);
		map.put("investGoldId", investGoldId);
		Map<String, Object> map1 = mapper.countByAllDelivery(map);
		if (map1 != null && map1.size() > 0) {
			Object investGoldServiceSumObj = map1.get("investGoldServiceSum");
			if (investGoldServiceSumObj != null && investGoldServiceSumObj != "") {
				Double investGoldServiceSum = Double.valueOf(investGoldServiceSumObj.toString());
				map1.put("investGoldServiceSum", investGoldServiceSum/100);
			}

			Object logisticsFeeObj = map1.get("logisticsFeeSum");
			if (logisticsFeeObj != null && logisticsFeeObj != "") {
				Double logisticsFeeSum = Double.valueOf(logisticsFeeObj.toString());
				map1.put("logisticsFeeSum", logisticsFeeSum/100);
			}
			Object goldMoneySumObj = map1.get("goldMoneySum");
			if (goldMoneySumObj != null && goldMoneySumObj != "") {
				Double goldMoneySum = Double.valueOf(goldMoneySumObj.toString());
				map1.put("goldMoneySum", goldMoneySum/100);
			}
			Object processingServiceSumObj = map1.get("processingServiceSum");
			if (processingServiceSumObj != null && processingServiceSumObj != "") {
				Double processingServiceSum = Double.valueOf(processingServiceSumObj.toString());
				map1.put("processingServiceSum", processingServiceSum/100);
			}
			Object invoiceServiceSumObj = map1.get("invoiceServiceSum");
			if (invoiceServiceSumObj != null && invoiceServiceSumObj != "") {
				Double invoiceServiceSum = Double.valueOf(invoiceServiceSumObj.toString());
				map1.put("invoiceServiceSum", invoiceServiceSum/100);
			}
			Object poundageSumObj = map1.get("poundageSum");
			if (poundageSumObj != null && poundageSumObj != "") {
				Double poundageSum = Double.valueOf(poundageSumObj.toString());
				map1.put("poundageSum", poundageSum/100);
			}
			Object totalPoundageObj = map1.get("totalPoundage");
			if (totalPoundageObj != null && totalPoundageObj != "") {
				Double totalPoundage = Double.valueOf(totalPoundageObj.toString());
				map1.put("totalPoundage", totalPoundage/100);
			}
		}
		return map1;
	}
	/**
	 * 新增物流单号
	 * @throws ParseException 
	 */
	@Override
	@Transactional
	public int addLogisticsNoById(String logisticsNo, Long id, Long operatorId, String operatorName) throws ParseException {
		int flag = 0;
		InVestGoldOrder order = new InVestGoldOrder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtil.isNotEmpty(logisticsNo) && id != null) {
			order.setId(id);
			order.setLogisticsNo(logisticsNo);
			order.setSendTime(sdf.parse(sdf.format(new Date())));
			order.setStatus(Short.valueOf(ConstantUtil.inVestGoldOrderStatus.YFH.toString()));
			flag = mapper.updateById(order);
			if (flag > 0) {
				InvestGoldOrderItem item = new InvestGoldOrderItem();
				item.setId(IdUtil.generateyymmddhhMMssSSSAnd4Random());
				item.setOrderStatus(Short.valueOf(ConstantUtil.inVestGoldOrderStatus.YFH.toString()));
				item.setOrderId(id);
				item.setOperatorId(operatorId);
				item.setOperatorName(operatorName);
				item.setCreateTime(sdf.parse(sdf.format(new Date())));
				itemMapper.add(item);
			}
		}
		return flag;
	}

	/**
	 * 修改物流单号
	 * @throws ParseException 
	 */
	@Override
	@Transactional
	public int updateLogisticsNoById(String logisticsNo, Long id, Long operatorId, String operatorName) throws ParseException {
		int flag = 0;
		InVestGoldOrder order = new InVestGoldOrder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtil.isNotEmpty(logisticsNo) && id != null) {
			order.setId(id);
			order.setLogisticsNo(logisticsNo);
			order.setUpdateTimeStr(DateUtils.formatDateByMidLine(new Date()));
			flag = mapper.updateById(order);
			if (flag > 0) {
				InvestGoldOrderItem item = new InvestGoldOrderItem();
				item.setId(IdUtil.generateyymmddhhMMssSSSAnd4Random());
				item.setOrderStatus(Short.valueOf(ConstantUtil.inVestGoldOrderStatus.YFH.toString()));
				item.setOrderId(id);
				item.setOperatorId(operatorId);
				item.setOperatorName(operatorName);
				item.setCreateTime(sdf.parse(sdf.format(new Date())));
				itemMapper.add(item);
			}
		}
		return flag;
	}

	/**
	 * 修改订单状态
	 * @throws ParseException 
	 */
	@Override
	@Transactional
	public int updateStatusById(String status, Long id, Long operatorId, String operatorName) throws ParseException {
		int flag = 0;
		InVestGoldOrder order = new InVestGoldOrder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtil.isNotEmpty(status) && id != null) {
			order.setId(id);
			order.setStatus(Short.valueOf(status));
			order.setUpdateTimeStr(DateUtils.formatDateByMidLine(new Date()));
			flag = mapper.updateById(order);
			if (flag > 0) {
				InvestGoldOrderItem item = new InvestGoldOrderItem();
				item.setId(IdUtil.generateyymmddhhMMssSSSAnd4Random());
				item.setOrderStatus(Short.valueOf(status));
				item.setOrderId(id);
				item.setOperatorId(operatorId);
				item.setOperatorName(operatorName);
				item.setCreateTime(sdf.parse(sdf.format(new Date())));
				itemMapper.add(item);
			}
		}
		return flag;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String,String> sendCargo(String name, String phone, String address, String userId, String orderIdList) {
		String mailNo = null;
		Map<String,String> resultMap = new ConcurrentHashMap<>();
		Map<String,String> checkResult = this.addressResolution(address);
		if (checkResult.get("result").equals("ERR")){
			return checkResult;
		}

		Map<String, String> params = new ConcurrentHashMap<>(16);
		params.put("orderNo",userId);
		params.put("jContact", configParamMapper.getValueByName("send_contact"));
		params.put("jTel", configParamMapper.getValueByName("send_tel"));
		params.put("jAddress", configParamMapper.getValueByName("send_address"));
		params.put("jCompany", configParamMapper.getValueByName("send_name"));
		params.put("dContact", name);
		params.put("dTel", phone);
		params.put("dAddress", address);
		params.put("itemName", "黄金");
		params.put("itemCount", "1");
		params.put("custid",configParamMapper.getValueByName("SF_CUST_ID"));
		SfExpressResponse sfExpressResponse = this.sfService.generatorOrder(params);
		if ("OK".equals(sfExpressResponse.getHead())) {
			if (sfExpressResponse.getBody() != null){
				if (sfExpressResponse.getBody().getOrderResponse() != null){
					if (sfExpressResponse.getBody().getOrderResponse().getMailNo() != null){
						mailNo = sfExpressResponse.getBody().getOrderResponse().getMailNo();
					}
				}
			}
		} else {
			resultMap.put("result","ERR");
			resultMap.put("msg","调用顺丰异常");
		}
		try{
			int result = this.mapper.updateToSended(orderIdList,mailNo,new Date());
			if (result>0){
				resultMap.put("result","OK");

			}else {
				resultMap.put("result","ERR");
				resultMap.put("msg","失败");
			}
		}catch (Exception e){
			this.sfService.cancelOrder(userId);
			e.printStackTrace();
		}

		return resultMap;
	}
	/*	*//**
	 * 交割修改订单状态
	 * @throws ParseException
	 *//*
	@Transactional
	public int updateStatusByIdDelivery(String status, Long id, Long operatorId, String operatorName) throws ParseException {
		int flag = 0;
		InVestGoldOrder order = new InVestGoldOrder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtil.isNotEmpty(status) && id != null) {
			order.setId(id);
			order.setStatus(Short.valueOf(status));
			order.setUpdateTime(sdf.parse(sdf.format(new Date())));
			flag = mapper.updateById(order);
			if (flag > 0) {
				InvestGoldOrderItem item = new InvestGoldOrderItem();
				item.setId(IdUtil.generateyymmddhhMMssSSSAnd4Random());
				item.setOrderStatus(Short.valueOf(status));
				item.setOrderId(id);
				item.setOperatorId(operatorId);
				item.setOperatorName(operatorName);
				item.setCreateTime(sdf.parse(sdf.format(new Date())));
				itemMapper.add(item);
			}
		}
		return flag;
	}*/

	private  Map<String, String> addressResolution(String address){
		String regex="((?<province>[^省]+省|.+自治区)|上海|北京|天津|重庆)(?<city>[^市]+市|.+自治州)(?<county>[^县]+县|.+区|.+镇|.+局)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
		Matcher m= Pattern.compile(regex).matcher(address);
		String province=null,city=null,county=null,town=null,village=null;
		Map<String, String> res=new ConcurrentHashMap<>(6);
		while(m.find()){
			province=m.group("province");
			city=m.group("city");
//            county=m.group("county");
//            town=m.group("town");
//            village=m.group("village");
		}
		if (province == null && city == null){
			res.put("result","ERR");
			res.put("msg","收货地址不是：XX省XX市格式");
			return res;
		}else if (province == null && city != null){
			province = city.substring(city.length());
			res.put("result","SUC");
			res.put("province",province);
			return res;

		}else if (province != null && city == null){
			res.put("result","ERR");
			res.put("msg","收货地址不是：XX省XX市格式");
			return res;
		}else {
			res.put("result","OK");
		}
		return res;
	}
}
