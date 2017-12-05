package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/13.
 */
public class ImmediatelySubmitParam {
    private String userId;
    private String cartId;
    private String receiverName;
    private String receiverPhone;
    private String receiverDetailAddress;
    private String isSeaBuy;
    private String goodsType;
    private String customerComments="";
    private ImmediatelySubmitMerchant merchant;
    private ImmediatelySubmitMerchant activityGoodsOrder;
    private String identityCardNo;
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

    public ImmediatelySubmitMerchant getMerchant() {
        return merchant;
    }

    public void setMerchant(ImmediatelySubmitMerchant merchant) {
        this.merchant = merchant;
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

    public ImmediatelySubmitMerchant getActivityGoodsOrder() {
        return activityGoodsOrder;
    }

    public void setActivityGoodsOrder(ImmediatelySubmitMerchant activityGoodsOrder) {
        this.activityGoodsOrder = activityGoodsOrder;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "ImmediatelySubmitParam{" +
                "userId='" + userId + '\'' +
                ", cartId='" + cartId + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverDetailAddress='" + receiverDetailAddress + '\'' +
                ", isSeaBuy='" + isSeaBuy + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", customerComments='" + customerComments + '\'' +
                ", merchant=" + merchant +
                ", activityGoodsOrder=" + activityGoodsOrder +
                ", identityCardNo='" + identityCardNo + '\'' +
                '}';
    }
}
