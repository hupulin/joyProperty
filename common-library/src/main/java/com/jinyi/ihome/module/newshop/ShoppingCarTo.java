package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2016/7/21.
 */
public class ShoppingCarTo implements Serializable {


    /**
     * goodsImage : http://192.168.1.149:8080/ihome/typeImage/1.png
     * goodsName : 生活像一把无情刻刀,改变了我们模样，未曾绽放就要枯萎吗，我有过梦想
     * trafficExpense : 7.0
     * primePrice : 999.0
     * price : 890.0
     * discount : 8.5
     * canUse : true
     * purchaseNumber : 4
     * online : true
     */

    private String goodsImage;
    private String goodsName;
    private double trafficExpense;
    private double primePrice;
    private double price;
    private double discount;
    private boolean canUse;
    private int purchaseNumber;
    private boolean online;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getTrafficExpense() {
        return trafficExpense;
    }

    public void setTrafficExpense(double trafficExpense) {
        this.trafficExpense = trafficExpense;
    }

    public double getPrimePrice() {
        return primePrice;
    }

    public void setPrimePrice(double primePrice) {
        this.primePrice = primePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isCanUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(int purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "ShoppingCarTo{" +
                "goodsImage='" + goodsImage + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", trafficExpense=" + trafficExpense +
                ", primePrice=" + primePrice +
                ", price=" + price +
                ", discount=" + discount +
                ", canUse=" + canUse +
                ", purchaseNumber=" + purchaseNumber +
                ", online=" + online +
                ", isSelect=" + isSelect +
                '}';
    }
}
