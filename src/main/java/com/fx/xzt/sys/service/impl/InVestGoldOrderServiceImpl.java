package com.fx.xzt.sys.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

	/**
	 * 查询
	 * @throws ParseException 
	 */
	public PageInfo<Map<String, Object>> selectByAll(String userName, String startTime, String endTime, String agentName,
			String brokerName, Integer status, Integer payType, String isView, Integer pageNum, Integer pageSize) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
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
	 * 导出
	 * @throws ParseException 
	 */
	public List<Map<String, Object>> excelByAll(String userName, String startTime, String endTime, String agentName,
			String brokerName, Integer status, Integer payType, String isView) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("payType", payType);
        map.put("isView", isView);
        List<Map<String, Object>> list = mapper.selectByAll(map);
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
				if (payTypeObj != null && payTypeObj != "") {
					map.put("payType", ConstantUtil.payType.toMap().get(payTypeObj.toString()));
				}
				Object statusObj = map.get("status");
				if (statusObj != null && statusObj != "") {
					map.put("status", ConstantUtil.inVestGoldOrderStatus.toMap().get(statusObj.toString()));
				}
				Object goldBasePriceObj = map.get("goldBasePrice");
				if (goldBasePriceObj != null && goldBasePriceObj != "") {
					Double goldBasePrice = Double.valueOf(goldBasePriceObj.toString());
					map.put("goldBasePrice", goldBasePrice/100);
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
	 * 统计
	 */
	public Map<String, Object> countByAll(String userName, String startTime,
			String endTime, String agentName, String brokerName,
			Integer status, Integer payType) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("agentName", agentName);
        map.put("brokerName", brokerName);
        map.put("status", status);
        map.put("payType", payType);
        Map<String, Object> map1 = mapper.countByAll(map);
        if (map1 != null && map1.size() > 0) {
        	Object serviceMoneyObj = map1.get("serviceMoneySum");
			if (serviceMoneyObj != null && serviceMoneyObj != "") {
				Double serviceMoney = Double.valueOf(serviceMoneyObj.toString());
				map1.put("serviceMoneySum", serviceMoney/100);
			}
        	Object logisticsFeeObj = map1.get("logisticsFeeSum");
			if (logisticsFeeObj != null && logisticsFeeObj != "") {
				Double logisticsFee = Double.valueOf(logisticsFeeObj.toString());
				map1.put("logisticsFeeSum", logisticsFee/100);
			}
        }
        return map1;
	}

	/**
	 * 新增物流单号
	 * @throws ParseException 
	 */
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
	@Transactional
	public int updateLogisticsNoById(String logisticsNo, Long id, Long operatorId, String operatorName) throws ParseException {
		int flag = 0;
		InVestGoldOrder order = new InVestGoldOrder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtil.isNotEmpty(logisticsNo) && id != null) {
			order.setId(id);
			order.setLogisticsNo(logisticsNo);
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
	@Transactional
	public int updateStatusById(String status, Long id, Long operatorId, String operatorName) throws ParseException {
		int flag = 0;
		InVestGoldOrder order = new InVestGoldOrder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtil.isNotEmpty(status) && id != null) {
			order.setId(id);
			order.setStatus(Short.valueOf(status));
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
	


}