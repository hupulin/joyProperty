package com.jinyi.ihome.module.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xz on 2016/4/9.
 */
public class PropertyBillPayXSCParam implements Serializable {

    private static final long serialVersionUID = 1L;

    //账单编号
    private String billSid;

    // 小区编号
    private String apartmentSid;

    private String billMainSid;

    private String billItemNO;

    private String billName;

    private String billDesc;

    private String roomNo;

    /**
     * 0 帐单已创建，待发放
     * 1 已发放，待支付
     * 2 已支付
     * 9 帐单取消
     */
    private int billStatus;

    private double billMoney;

    private String billDateSpan;

    private String billCreatedTime;


    /***
     * 用户sid
     */
    private String userSid;

    public String getBillSid() {
        return billSid;
    }

    public void setBillSid(String billSid) {
        this.billSid = billSid;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getBillMainSid() {
        return billMainSid;
    }

    public void setBillMainSid(String billMainSid) {
        this.billMainSid = billMainSid;
    }

    public String getBillItemNO() {
        return billItemNO;
    }

    public void setBillItemNO(String billItemNO) {
        this.billItemNO = billItemNO;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillDesc() {
        return billDesc;
    }

    public void setBillDesc(String billDesc) {
        this.billDesc = billDesc;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }

    public double getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(double billMoney) {
        this.billMoney = billMoney;
    }

    public String getBillDateSpan() {
        return billDateSpan;
    }

    public void setBillDateSpan(String billDateSpan) {
        this.billDateSpan = billDateSpan;
    }

    public String getBillCreatedTime() {
        return billCreatedTime;
    }

    public void setBillCreatedTime(String billCreatedTime) {
        this.billCreatedTime = billCreatedTime;
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyBillPayXSCParam that = (PropertyBillPayXSCParam) o;

        if (billStatus != that.billStatus) return false;
        if (Double.compare(that.billMoney, billMoney) != 0) return false;
        if (billSid != null ? !billSid.equals(that.billSid) : that.billSid != null) return false;
        if (apartmentSid != null ? !apartmentSid.equals(that.apartmentSid) : that.apartmentSid != null)
            return false;
        if (billMainSid != null ? !billMainSid.equals(that.billMainSid) : that.billMainSid != null)
            return false;
        if (billItemNO != null ? !billItemNO.equals(that.billItemNO) : that.billItemNO != null)
            return false;
        if (billName != null ? !billName.equals(that.billName) : that.billName != null)
            return false;
        if (billDesc != null ? !billDesc.equals(that.billDesc) : that.billDesc != null)
            return false;
        if (roomNo != null ? !roomNo.equals(that.roomNo) : that.roomNo != null) return false;
        if (billDateSpan != null ? !billDateSpan.equals(that.billDateSpan) : that.billDateSpan != null)
            return false;
        if (billCreatedTime != null ? !billCreatedTime.equals(that.billCreatedTime) : that.billCreatedTime != null)
            return false;
        return !(userSid != null ? !userSid.equals(that.userSid) : that.userSid != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = billSid != null ? billSid.hashCode() : 0;
        result = 31 * result + (apartmentSid != null ? apartmentSid.hashCode() : 0);
        result = 31 * result + (billMainSid != null ? billMainSid.hashCode() : 0);
        result = 31 * result + (billItemNO != null ? billItemNO.hashCode() : 0);
        result = 31 * result + (billName != null ? billName.hashCode() : 0);
        result = 31 * result + (billDesc != null ? billDesc.hashCode() : 0);
        result = 31 * result + (roomNo != null ? roomNo.hashCode() : 0);
        result = 31 * result + billStatus;
        temp = Double.doubleToLongBits(billMoney);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (billDateSpan != null ? billDateSpan.hashCode() : 0);
        result = 31 * result + (billCreatedTime != null ? billCreatedTime.hashCode() : 0);
        result = 31 * result + (userSid != null ? userSid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PropertyBillPayXSCParam{" +
                "billSid='" + billSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", billMainSid='" + billMainSid + '\'' +
                ", billItemNO='" + billItemNO + '\'' +
                ", billName='" + billName + '\'' +
                ", billDesc='" + billDesc + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", billStatus=" + billStatus +
                ", billMoney=" + billMoney +
                ", billDateSpan='" + billDateSpan + '\'' +
                ", billCreatedTime='" + billCreatedTime + '\'' +
                ", userSid='" + userSid + '\'' +
                '}';
    }
}

