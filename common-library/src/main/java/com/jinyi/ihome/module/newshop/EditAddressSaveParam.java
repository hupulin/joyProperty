package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by usb on 2017/2/21.
 */
public class EditAddressSaveParam implements Serializable {
    @Override
    public String toString() {
        return "EditAddressSaveParam{" +
                "id='" + id + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverArea='" + receiverArea + '\'' +
                ", receiverDetailAddress='" + receiverDetailAddress + '\'' +
                ", identityCardNo='" + identityCardNo + '\'' +
                ", isDefaultAddress='" + isDefaultAddress + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsDefaultAddress() {
        return isDefaultAddress;
    }

    public void setIsDefaultAddress(String isDefaultAddress) {
        this.isDefaultAddress = isDefaultAddress;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    private String id;
    private String receiverName;
    private String receiverPhone;
    private String receiverArea;
    private String receiverDetailAddress;
    private String identityCardNo;
    private String isDefaultAddress;



}
