package com.fx.xzt.sys.service.impl;

import com.fx.xzt.sys.entity.DeliveryGoldConf;
import com.fx.xzt.sys.mapper.DeliveryGoldConfMapper;
import com.fx.xzt.sys.service.DeliveryGoldConfService;
import com.fx.xzt.sys.util.ConstantUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  liaijiao
 * @date 2018/8/30 14:19
 */
@Service
public class DeliveryGoldConfServiceImpl extends BaseService<DeliveryGoldConf> implements DeliveryGoldConfService {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryGoldConfServiceImpl.class);
    @Resource
    DeliveryGoldConfMapper deliveryGoldConfMapper;

    /**
     * 获取所有的金权交易交割黄金规格信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> getAllDeliveryGoldConf(Integer pageNum, Integer pageSize){
        logger.debug("查询所有金权规则信息");
        PageHelper.startPage(pageNum,pageSize,true);
        List<Map<String, Object>> deliveryGoldConf = deliveryGoldConfMapper.selectAll();

       for (Map m:deliveryGoldConf){
            m.put("id",m.get("id").toString());

        }
        handle(deliveryGoldConf);

        return new PageInfo<Map<String, Object>>(deliveryGoldConf);

    }

    /**
     *根据id修改金权交易交割黄金规格信息
     * @param id
     * @param handlingFee 手续费率
     * @param processingFee 加工费(分)
     * @param invoiceFee 发票费率
     * @param logisticsFee 物流费(分)
     * @param custodyFee 保管费(分) 分/克天
     * @param custodyStartDate 免收保管费天数
     * @param type 交割状态
     * @return
     */
    @Transactional
    public Boolean updateByPrimaryKey(Integer id, BigDecimal handlingFee, Long processingFee, BigDecimal invoiceFee,
                                      Long logisticsFee, Long custodyFee, Integer custodyStartDate, Integer type) {
        Map map = new HashMap();
        map.put("id",id);
        map.put("handlingFee",handlingFee);
        map.put("processingFee",processingFee*100);
        map.put("invoiceFee",invoiceFee);
        map.put("logisticsFee",logisticsFee*100);
        map.put("custodyFee",custodyFee*100);
        map.put("custodyStartDate",custodyStartDate);
        map.put("type",type);
        Date gmtModified=new Date();
        map.put("gmtModified",gmtModified);
      int l=  deliveryGoldConfMapper.modifyDeliveryGoldConf(map);

        if(l!=0){
            return  true;
        }
        return  false;

    }

    /**
     * 根据id查询金权交易交割黄金规格信息
     * @param id
     * @return
     */
    @Override
    public DeliveryGoldConf getDeliveryGold(Integer id) {
        return deliveryGoldConfMapper.selectById(id);
    }

    public void handle(List<Map<String, Object>> list)  {
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                Object typeObj = map.get("type");
                if (typeObj != null && typeObj != "") {
                    map.put("type", ConstantUtil.types.toMap().get(typeObj.toString()));
                }
                Object processingFeeObj = map.get("processingFee");
                if (processingFeeObj != null && processingFeeObj != "") {
                    Double processingFee = Double.valueOf(processingFeeObj.toString());
                    map.put("processingFee",processingFee/100);
                }
                Object logisticsFeeObj = map.get("logisticsFee");
                if (logisticsFeeObj != null && logisticsFeeObj != "") {
                    Double logisticsFee = Double.valueOf(logisticsFeeObj.toString());
                    map.put("logisticsFee",logisticsFee/100);
                }
                Object custodyFeeObj = map.get("custodyFee");
                if (custodyFeeObj != null && custodyFeeObj != "") {
                    Double custodyFee = Double.valueOf(custodyFeeObj.toString());
                    map.put("custodyFee",custodyFee/100);
                }

            }

        }
    }
}
