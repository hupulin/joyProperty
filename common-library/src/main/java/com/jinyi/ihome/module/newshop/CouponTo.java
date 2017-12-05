package com.jinyi.ihome.module.newshop;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2016/7/22.
 */
public class CouponTo implements Serializable{


    /**
     * couponId : 1
     * couponName : 双十一活动
     * activityStartTime : 1480567356000
     * activityEndTime : 1484887382000
     * useRule : 0
     * discountAmount : 2
     * consumptionAmount : 5
     * freePostage : 不含邮费
     * goodType  商品类型
     */

    private String couponId;
    private String couponName;
    private Date activityStartTime;
    private Date activityEndTime;
    private int useRule;
    private double discountAmount;
    private double consumptionAmount;
    private String freePostage;
    private String canUsegoodsName;
    private int goodsType;
    private int isStart;

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public int getUseRule() {
        return useRule;
    }

    public void setUseRule(int useRule) {
        this.useRule = useRule;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getConsumptionAmount() {
        return consumptionAmount;
    }

    public void setConsumptionAmount(double consumptionAmount) {
        this.consumptionAmount = consumptionAmount;
    }

    public String getFreePostage() {
        return freePostage;
    }

    public void setFreePostage(String freePostage) {
        this.freePostage = freePostage;
    }

    public String getCanUsegoodsName() {
        return canUsegoodsName;
    }

    public void setCanUsegoodsName(String canUsegoodsName) {
        this.canUsegoodsName = canUsegoodsName;
    }

    public int getIsStart() {
        return isStart;
    }

    public void setIsStart(int isStart) {
        this.isStart = isStart;
    }

    @Override
    public String toString() {
        return "CouponTo{" +
                "couponId='" + couponId + '\'' +
                ", couponName='" + couponName + '\'' +
                ", activityStartTime=" + activityStartTime +
                ", activityEndTime=" + activityEndTime +
                ", useRule=" + useRule +
                ", discountAmount=" + discountAmount +
                ", consumptionAmount=" + consumptionAmount +
                ", freePostage='" + freePostage + '\'' +
                ", canUsegoodsName='" + canUsegoodsName + '\'' +
                ", goodsType=" + goodsType +
                ", isStart=" + isStart +
                '}';
    }
}
