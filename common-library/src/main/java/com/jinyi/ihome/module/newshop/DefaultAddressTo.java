package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by usb on 2017/2/22.
 */
public class DefaultAddressTo implements Serializable {
    private String id;
    private String identityCardNo;
    private String receiverName;
    private String receiverPhone;
    private String receiverArea;
    private String receiverDetailAddress;

    @Override
    public String toString() {
        return "DefaultAddressTo{" +
                "id='" + id + '\'' +
                ", identityCardNo='" + identityCardNo + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverArea='" + receiverArea + '\'' +
                ", receiverDetailAddress='" + receiverDetailAddress + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
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

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }
}
