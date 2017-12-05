package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/10.
 */
public class ExpressTo {


    /**
     * deliverTime : 2017-01-15 16:26:56
     * expressCompany : 顺丰快递
     * expressNo : 20170115001
     * packageName : 包裹1
     * deliverName : 小王
     * deliverPhone : 13003620998
     * orderPacketGoodsList : [{"goodsName":"琵琶果"}]
     */

    private String deliverTime;
    private String expressCompany;
    private String expressNo;
    private String packageName;
    private String deliverName;
    private String deliverPhone;
    /**
     * goodsName : 琵琶果
     */

    private List<OrderPacketGoodsListBean> orderPacketGoodsList;

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverPhone() {
        return deliverPhone;
    }

    public void setDeliverPhone(String deliverPhone) {
        this.deliverPhone = deliverPhone;
    }

    public List<OrderPacketGoodsListBean> getOrderPacketGoodsList() {
        return orderPacketGoodsList;
    }

    public void setOrderPacketGoodsList(List<OrderPacketGoodsListBean> orderPacketGoodsList) {
        this.orderPacketGoodsList = orderPacketGoodsList;
    }

    public static class OrderPacketGoodsListBean {
        private String goodsName;

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }
    }

    @Override
    public String toString() {
        return "ExpressTo{" +
                "deliverTime='" + deliverTime + '\'' +
                ", expressCompany='" + expressCompany + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", packageName='" + packageName + '\'' +
                ", deliverName='" + deliverName + '\'' +
                ", deliverPhone='" + deliverPhone + '\'' +
                ", orderPacketGoodsList=" + orderPacketGoodsList +
                '}';
    }
}
