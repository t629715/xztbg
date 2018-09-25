package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.DeliveryGoldConf;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @author   liaijiao
 * @date 2018/8/30 14:19
 */
public interface DeliveryGoldConfService extends IService<DeliveryGoldConf> {

    /**
     * 获取所有的金权交易交割黄金规格信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Map<String, Object>> getAllDeliveryGoldConf(Integer pageNum, Integer pageSize);

    /**
     * 根据id修改金权交易交割黄金规格信息
     * @param handlingFee 手续费率
     * @param processingFee 加工费(分)
     * @param invoiceFee 发票费率
     * @param logisticsFee 物流费(分)
     * @param custodyFee 保管费(分) 分/克天
     * @param custodyStartDate 免收保管费天数
     * @param type 交割状态
     * @return
     */
    Boolean updateByPrimaryKey(Integer id, BigDecimal handlingFee, String  processingFee, BigDecimal invoiceFee, String logisticsFee,
                               String custodyFee, Integer custodyStartDate, Integer type);

    /**
     * 根据id查询金权交易交割黄金规格信息
     * @param id
     * @return
     */
    DeliveryGoldConf getDeliveryGold(Integer id);
}
