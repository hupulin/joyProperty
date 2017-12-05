package com.jinyi.ihome.module.newshop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2017/1/10.
 */
public class MyOlderTo implements Serializable{

    /**
     * orderId : 15882966493889536
     * orderTime : 2017-01-10 01:15:52
     * orderStatus : 待付款
     * merchantName : 南都物业
     * storesName : 南都物业
     * actualPayAmount : null
     * orderDetails : 时间等3件商品
     */

    private long orderId;
    private String orderTime;
    private String orderStatus;
    private String merchantName;
    private String storesName;
    private Object actualPayAmount;
    private String orderDetails;
    private String imagePath;
    private List<MyOlderGoods>orderGoodsList;
    private List<ExpressTo>orderExpressList;
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public Object getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(Object actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<MyOlderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<MyOlderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<ExpressTo> getOrderExpressList() {
        return orderExpressList;
    }

    public void setOrderExpressList(List<ExpressTo> orderExpressList) {
        this.orderExpressList = orderExpressList;
    }

    @Override
    public String toString() {
        return "MyOlderTo{" +
                "orderId=" + orderId +
                ", orderTime='" + orderTime + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", storesName='" + storesName + '\'' +
                ", actualPayAmount=" + actualPayAmount +
                ", orderDetails='" + orderDetails + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", orderGoodsList=" + orderGoodsList +
                ", orderExpressList=" + orderExpressList +
                '}';
    }
}
