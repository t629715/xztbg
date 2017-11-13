package com.fx.xzt.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Administrator
 * @date 2017/10/27
 */
public class OrderCloseDirective {
    private static final Logger logger = LoggerFactory.getLogger(OrderCloseDirective.class);

    /** 合约代码 **/
    private String contractCode;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 指令 0 不平仓 1 平仓
     */
    private Integer directive;

    private Date directiveDate;

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getDirective() {
        return directive;
    }

    public void setDirective(Integer directive) {
        this.directive = directive;
    }

    public Date getDirectiveDate() {
        return directiveDate;
    }

    public void setDirectiveDate(Date directiveDate) {
        this.directiveDate = directiveDate;
    }
}
