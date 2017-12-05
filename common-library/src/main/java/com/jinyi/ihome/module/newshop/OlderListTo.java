package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/12.
 */
public class OlderListTo {

    /**
     * orderId : 58
     * merchantId : 5
     * merchantName : mock
     * storesName : mock
     */

    private String orderId;
    private String merchantId;
    private String merchantName;
    private String storesName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    @Override
    public String toString() {
        return "OlderListTo{" +
                "orderId=" + orderId +
                ", merchantId=" + merchantId +
                ", merchantName='" + merchantName + '\'' +
                ", storesName='" + storesName + '\'' +
                '}';
    }
}
