package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/9.
 */
public class SubmitParam {

    /**
     * userId :
     * cartId :
     * receiverName :
     * receiverPhone :
     * receiverDetailAddress :
     */

    private String userId;
    private String cartId;
    private String receiverName;
    private String receiverPhone;
    private String receiverDetailAddress;
    private String isSeaBuy;
    private String identityCardNo;
    private List<SubmitMerchant>merchantList;
    private ActivitySubmitMerchant activityGoodsOrder;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public List<SubmitMerchant> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<SubmitMerchant> merchantList) {
        this.merchantList = merchantList;
    }

    public String getIsSeaBuy() {
        return isSeaBuy;
    }

    public void setIsSeaBuy(String isSeaBuy) {
        this.isSeaBuy = isSeaBuy;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public ActivitySubmitMerchant getActivityGoodsOrder() {
        return activityGoodsOrder;
    }

    public void setActivityGoodsOrder(ActivitySubmitMerchant activityGoodsOrder) {
        this.activityGoodsOrder = activityGoodsOrder;
    }

    @Override
    public String toString() {
        return "SubmitParam{" +
                "userId='" + userId + '\'' +
                ", cartId='" + cartId + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverDetailAddress='" + receiverDetailAddress + '\'' +
                ", isSeaBuy='" + isSeaBuy + '\'' +
                ", identityCardNo='" + identityCardNo + '\'' +
                ", merchantList=" + merchantList +
                ", activityGoodsOrder=" + activityGoodsOrder +
                '}';
    }
}
