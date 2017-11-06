package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.mapper.DealOrderMapper;
import com.fx.xzt.sys.mapper.FinanceOrderMapper;
import com.fx.xzt.sys.mapper.RealGoldOrderMapper;
import com.fx.xzt.sys.service.OrderAnalysisService;
import com.fx.xzt.sys.util.DateUtil;
import com.fx.xzt.sys.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tianliya
 * @Description:
 * @date 15:56 2017/10/19
 */
@Service
public class OrderAnalysisServiceImpl implements OrderAnalysisService {
    @Resource
    private FinanceOrderMapper financeOrderMapper;
    @Resource
    DealOrderMapper dealOrderMapper;
    @Resource
    RealGoldOrderMapper realGoldOrderMapper;
    @Override
    public PageInfo<Map<String, Object>> orderAnalysis(String startTime, String endTime,  String agentName,
                                                       Short upOrDown,Short orderState, Short profitLoss,
                                                       Integer pageNum, Integer pageSize) {
        Map map = new HashMap();
        map.put("startTime",startTime);
        map.put("upOrDown",upOrDown);
        map.put("orderState",orderState);
        map.put("profitLoss",profitLoss);
        Date start = null;
        Date end = null;
        if (startTime != null && startTime !=""){
            start = DateUtil.convertTimeMillisToDate(Long.valueOf(startTime));
        }
        if (endTime != null && endTime !=""){
            end = DateUtil.convertTimeMillisToDate(Long.valueOf(endTime));
        }
        List<String> dates = new ArrayList<String>();
        for (Date date = start;date.before(end);date = DateUtil.modify(date,0,0,1,0,0,0)){
            String time = DateUtils.formatDateByMidLine1(date);
            dates.add(time);
        }
        map.put("dates",dates);
        map.put("start",(pageNum-1)*pageSize);
        map.put("size",pageSize);
        //PageHelper.startPage(pageNum,pageSize);
        List list = financeOrderMapper.goldUpAnalysis(map);
        /*int pageTotal = list.size()/pageSize;
        int pageStart = (pageNum-1)*pageSize;
        int pageEnd = pageNum*pageSize;
        if (pageEnd >= list.size()){
            pageEnd = list.size()-1;
        }
        List listDto = new ArrayList();
        for (int i = pageStart;i< pageEnd;i++){
            listDto.add(list.get(i));
        }*/
        PageInfo pageInfo = new PageInfo(list);
        /*pageInfo.setPageNum(pageTotal);*/
       return pageInfo;
    }
}
