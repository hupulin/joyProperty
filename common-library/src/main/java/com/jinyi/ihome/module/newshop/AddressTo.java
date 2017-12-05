package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2016/7/25.
 */
public class AddressTo implements Serializable{

    /**
     *

     */
    private String id;
    private String identityCardNo;

    @Override
    public String toString() {
        return "AddressTo{" +
                "id='" + id + '\'' +
                ", identityCardNo='" + identityCardNo + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverArea='" + receiverArea + '\'' +
                ", receiverDetailAddress='" + receiverDetailAddress + '\'' +
                ", isDefaultAddress='" + isDefaultAddress + '\'' +
                '}';
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    private String receiverName;
    private String receiverPhone;
    private String receiverArea;
    private String receiverDetailAddress;
    private String isDefaultAddress;


    public String getIsDefaultAddress() {
        return isDefaultAddress;
    }

    public void setIsDefaultAddress(String isDefaultAddress) {
        this.isDefaultAddress = isDefaultAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }



}
