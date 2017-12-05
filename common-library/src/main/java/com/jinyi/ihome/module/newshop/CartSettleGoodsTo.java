package com.jinyi.ihome.module.newshop;

import android.widget.EditText;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2017/1/9.
 */
public class CartSettleGoodsTo implements Serializable{

    /**
     * merchantId : mock
     * merchantName : mock
     * storesName : mock
     * cartId : 18
     * distributionMode : mock
     * distributionCost : mock
     * goodsTotalAmount : mock
     * actualPayAmount : mock
     * isMail : mock
     */

    private String merchantId;
    private String merchantName;
    private String storesName;
    private String cartId;
    private String distributionMode;
    private double distributionCost;
    private double goodsTotalAmount;
    private String actualPayAmount;
    private String isMail;
    private String couponId;
    private double couponCost;
    private List<CarGoodsInfo>cartMerchantGoodsVolist;
    private List<CarGoodsInfo>cartActivityGoodsList;
    private List<CouponTo>couponList;
    private double payAmount;
    private boolean isCalculatePrice;
    private double expressCost;
  //  private EditText remark;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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

    public double getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(double goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public String getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(String actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public String getIsMail() {
        return isMail;
    }

    public void setIsMail(String isMail) {
        this.isMail = isMail;
    }

    public List<CarGoodsInfo> getCartMerchantGoodsVolist() {
        return cartMerchantGoodsVolist;
    }

    public void setCartMerchantGoodsVolist(List<CarGoodsInfo> cartMerchantGoodsVolist) {
        this.cartMerchantGoodsVolist = cartMerchantGoodsVolist;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public double getCouponCost() {
        return couponCost;
    }

    public void setCouponCost(double couponCost) {
        this.couponCost = couponCost;
    }

    public List<CarGoodsInfo> getCartActivityGoodsList() {
        return cartActivityGoodsList;
    }

    public void setCartActivityGoodsList(List<CarGoodsInfo> cartActivityGoodsList) {
        this.cartActivityGoodsList = cartActivityGoodsList;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }
//    public EditText getRemark() {
//        return remark;
//    }
//
//    public void setRemark(EditText remark) {
//        this.remark = remark;
//    }

    public List<CouponTo> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponTo> couponList) {
        this.couponList = couponList;
    }

    public boolean isCalculatePrice() {
        return isCalculatePrice;
    }

    public void setIsCalculatePrice(boolean isCalculatePrice) {
        this.isCalculatePrice = isCalculatePrice;
    }

    public double getExpressCost() {
        return expressCost;
    }

    public void setExpressCost(double expressCost) {
        this.expressCost = expressCost;
    }

    @Override
    public String toString() {
        return "CartSettleGoodsTo{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", storesName='" + storesName + '\'' +
                ", cartId='" + cartId + '\'' +
                ", distributionMode='" + distributionMode + '\'' +
                ", distributionCost='" + distributionCost + '\'' +
                ", goodsTotalAmount='" + goodsTotalAmount + '\'' +
                ", actualPayAmount='" + actualPayAmount + '\'' +
                ", isMail='" + isMail + '\'' +
                ", cartMerchantGoodsVolist=" + cartMerchantGoodsVolist +
                '}';
    }
}
