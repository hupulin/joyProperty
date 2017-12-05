package com.jinyi.ihome.module.home;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2016/6/16.
 */
public class ServiceMainExtTo implements Serializable {
  private String createdBy;
    private Date createdOn ;
    private  String modifiedBy ;
    private  Date modifiedOn;
    private  String  payBilll;
    private float  payMoney ;
    private  String payResult;
    private  String  payStatus ;
    private  String  payTime ;
    private int payType;
    private int remark ;
    private  String serviceSid ;
    private String statusStr;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getPayBilll() {
        return payBilll;
    }

    public void setPayBilll(String payBilll) {
        this.payBilll = payBilll;
    }

    public float getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(float payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    public String getServiceSid() {
        return serviceSid;
    }

    public void setServiceSid(String serviceSid) {
        this.serviceSid = serviceSid;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    public String toString() {
        return "ServiceMainExtTo{" +
                "createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", modifiedBy=" + modifiedBy +
                ", modifiedOn=" + modifiedOn +
                ", payBilll='" + payBilll + '\'' +
                ", payMoney=" + payMoney +
                ", payResult='" + payResult + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", payTime='" + payTime + '\'' +
                ", payType=" + payType +
                ", remark=" + remark +
                ", serviceSid='" + serviceSid + '\'' +
                ", statusStr='" + statusStr + '\'' +
                '}';
    }
}
