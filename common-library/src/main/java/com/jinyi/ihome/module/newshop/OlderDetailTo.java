package com.jinyi.ihome.module.newshop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2016/7/25.
 */
public class OlderDetailTo implements Serializable{


    /**
     * orderTime : 2017-01-10 01:15:52
     * merchantName : 南都物业
     * storesName : 南都物业
     * actualPayAmount : 101.0
     * orderNo :
     * goodsReceiverName : 收 货 人 ：张芳
     * goodsReceiverPhone : 13282836542
     * goodsReceiverAddress : 收货地址：杭州市西湖区紫荆花路2号联合大厦
     * totalAmount : 68.0
     * distributionMode : 物业配送
     * distributionCost : 33.0
     * couponAmount : 33.0
     * totanNum : 3
     */

    private String orderTime;
    private String merchantName;
    private String storesName;
    private double actualPayAmount;
    private String orderNo;
    private String goodsReceiverName;
    private String goodsReceiverPhone;
    private String goodsReceiverAddress;
    private double totalAmount;
    private String distributionMode;
    private double distributionCost;
    private double couponAmount;
    private String totanNum;
    private String customerComments;
    private List<OlderDetailGoodsTo>orderGoodsVoList;
    private List<ExpressTo>orderExpressList;



    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getStoresName() {
        return storesName;
    }

    public void setStoresName(String storesName) {
        this.storesName = storesName;
    }

    public double getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(double actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGoodsReceiverName() {
        return goodsReceiverName;
    }

    public void setGoodsReceiverName(String goodsReceiverName) {
        this.goodsReceiverName = goodsReceiverName;
    }

    public String getGoodsReceiverPhone() {
        return goodsReceiverPhone;
    }

    public void setGoodsReceiverPhone(String goodsReceiverPhone) {
        this.goodsReceiverPhone = goodsReceiverPhone;
    }

    public String getGoodsReceiverAddress() {
        return goodsReceiverAddress;
    }

    public void setGoodsReceiverAddress(String goodsReceiverAddress) {
        this.goodsReceiverAddress = goodsReceiverAddress;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(String distributionMode) {
        this.distributionMode = distributionMode;
    }

    public double getDistributionCost() {
        return distributionCost;
    }

    public void setDistributionCost(double distributionCost) {
        this.distributionCost = distributionCost;
    }

    public double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getTotanNum() {
        return totanNum;
    }

    public void setTotanNum(String totanNum) {
        this.totanNum = totanNum;
    }

    public List<OlderDetailGoodsTo> getOrderGoodsVoList() {
        return orderGoodsVoList;
    }

    public void setOrderGoodsVoList(List<OlderDetailGoodsTo> orderGoodsVoList) {
        this.orderGoodsVoList = orderGoodsVoList;
    }

    public List<ExpressTo> getOrderExpressList() {
        return orderExpressList;
    }

    public void setOrderExpressList(List<ExpressTo> orderExpressList) {
        this.orderExpressList = orderExpressList;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public void setCustomerComments(String customerComments) {
        this.customerComments = customerComments;
    }

    @Override
    public String toString() {
        return "OlderDetailTo{" +
                "orderTime='" + orderTime + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", storesName='" + storesName + '\'' +
                ", actualPayAmount=" + actualPayAmount +
                ", orderNo='" + orderNo + '\'' +
                ", goodsReceiverName='" + goodsReceiverName + '\'' +
                ", goodsReceiverPhone='" + goodsReceiverPhone + '\'' +
                ", goodsReceiverAddress='" + goodsReceiverAddress + '\'' +
                ", totalAmount=" + totalAmount +
                ", distributionMode='" + distributionMode + '\'' +
                ", distributionCost=" + distributionCost +
                ", couponAmount=" + couponAmount +
                ", totanNum='" + totanNum + '\'' +
                ", orderGoodsVoList=" + orderGoodsVoList +
                '}';
    }
}
