package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by usb on 2017/2/20.
 */
public class AddAddressParam implements Serializable {

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    private String userId;
    private String receiverName;
    private String receiverPhone;
    private String receiverArea;
    private String receiverDetailAddress;
    private String identityCardNo;


    @Override
    public String toString() {
        return "AddAddressParam{" +
                "userId='" + userId + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverArea='" + receiverArea + '\'' +
                ", identityCardNo='" + identityCardNo + '\'' +
                ", receiverDetailAddress='" + receiverDetailAddress + '\'' +
                '}';
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

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String idCard) {
        this.identityCardNo = idCard;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

}
