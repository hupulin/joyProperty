package com.joy.property.DataStatistics;

import java.io.Serializable;

/**
 * Created by xz on 2016/6/20.
 */
public class OwnerLogTo implements Serializable {


    private String logid  ;


    private String systemType;


    private String ownerSid;



    private String content;


    private String deviceModel;


    private String createdOn;


    private String modifyTime ;


    private String remark;

    private String osType;

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Override
    public String toString() {
        return "OwnerLogTo{" +
                "logid='" + logid + '\'' +
                ", systemType='" + systemType + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", content='" + content + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", createdOn=" + createdOn +
                ", modifyTime=" + modifyTime +
                ", remark='" + remark + '\'' +
                ", osType='" + osType + '\'' +
                '}';
    }
}
