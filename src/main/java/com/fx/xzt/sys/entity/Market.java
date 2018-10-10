package com.fx.xzt.sys.entity;

/**
 * 行情数据
 */
public class Market {

    /** 合约代码 **/
    private String contractCode;

    /** 日期 **/
    private Integer date;

    /** 时间 **/
    private Integer time;

    /** 昨收价 **/
    private Double prevClosePx;

    /** 开盘价 **/
    private Double openPx;

    /** 最新价 **/
    private Double price;

    /** 最高价 **/
    private Double highPx;

    /** 最低价 **/
    private Double lowPx;

    /** 涨跌额 **/
    private Double priceChange;

    /** 涨跌幅 **/
    private Double priceChangePct;

    /** 成交量 **/
    private Long tradeVolume;

    /** 成交总量 **/
    private Long totalVolumeTrade;

    /** 成交额 **/
    private Double tradeAmt;

    /** 成交总金额 **/
    private Double totalValueTrade;

    /** 持仓量 **/
    private Long openInterest;

    /** 日增仓 **/
    private Long dailyIncreaseInterest;

    /** 买价 **/
    private Double buyPx;

    /** 卖价 **/
    private Double sellPx;

    /** 买量 **/
    private Integer buyVolume;

    /** 卖量 **/
    private Integer sellVolume;

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Double getPrevClosePx() {
        return prevClosePx;
    }

    public void setPrevClosePx(Double prevClosePx) {
        this.prevClosePx = prevClosePx;
    }

    public Double getOpenPx() {
        return openPx;
    }

    public void setOpenPx(Double openPx) {
        this.openPx = openPx;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getHighPx() {
        return highPx;
    }

    public void setHighPx(Double highPx) {
        this.highPx = highPx;
    }

    public Double getLowPx() {
        return lowPx;
    }

    public void setLowPx(Double lowPx) {
        this.lowPx = lowPx;
    }

    public Double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Double priceChange) {
        this.priceChange = priceChange;
    }

    public Double getPriceChangePct() {
        return priceChangePct;
    }

    public void setPriceChangePct(Double priceChangePct) {
        this.priceChangePct = priceChangePct;
    }

    public Long getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(Long tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public Double getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(Double tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public Long getOpenInterest() {
        return openInterest;
    }

    public void setOpenInterest(Long openInterest) {
        this.openInterest = openInterest;
    }

    public Long getDailyIncreaseInterest() {
        return dailyIncreaseInterest;
    }

    public void setDailyIncreaseInterest(Long dailyIncreaseInterest) {
        this.dailyIncreaseInterest = dailyIncreaseInterest;
    }

    public Long getTotalVolumeTrade() {
        return totalVolumeTrade;
    }

    public void setTotalVolumeTrade(Long totalVolumeTrade) {
        this.totalVolumeTrade = totalVolumeTrade;
    }

    public Double getTotalValueTrade() {
        return totalValueTrade;
    }

    public void setTotalValueTrade(Double totalValueTrade) {
        this.totalValueTrade = totalValueTrade;
    }

    public Double getBuyPx() {
        return buyPx;
    }

    public void setBuyPx(Double buyPx) {
        this.buyPx = buyPx;
    }

    public Double getSellPx() {
        return sellPx;
    }

    public void setSellPx(Double sellPx) {
        this.sellPx = sellPx;
    }

    public Integer getBuyVolume() {
        return buyVolume;
    }

    public void setBuyVolume(Integer buyVolume) {
        this.buyVolume = buyVolume;
    }

    public Integer getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(Integer sellVolume) {
        this.sellVolume = sellVolume;
    }

    @Override
    public String toString() {
        return "Market [contractCode=" + contractCode + ", date=" + date + ", time=" + time
                + ", prevClosePx=" + prevClosePx + ", openPx=" + openPx + ", price=" + price
                + ", highPx=" + highPx + ", lowPx=" + lowPx + ", priceChange=" + priceChange
                + ", priceChangePct=" + priceChangePct + ", tradeVolume=" + tradeVolume
                + ", totalVolumeTrade=" + totalVolumeTrade + ", tradeAmt=" + tradeAmt
                + ", totalValueTrade=" + totalValueTrade + ", openInterest=" + openInterest
                + ", dailyIncreaseInterest=" + dailyIncreaseInterest + ", buyPx=" + buyPx
                + ", sellPx=" + sellPx + ", buyVolume=" + buyVolume + ", sellVolume=" + sellVolume
                + "]";
    }
}
