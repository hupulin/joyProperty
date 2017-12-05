package com.joy.property.DataStatistics;

import java.io.Serializable;

/**
 * Created by xz on 2016/6/20.
 */
public class OwnerLogParam implements Serializable {


    /***
     * 系统 0=悦嘉家 1=悦服务 2=看板 3=JoyHome后台管理系统
     */

    private String systemType;
    /***
     * 操作员编号
     */

    private String ownerSid;

    /***
     * 系統內容
     */
    private String content;
    /***
     * 登陆设备型号 (手机型号)
     */
    private String deviceModel;

    /***
     * 操作系统类型  IOS  + ANDROID
     */
    private String osType;

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

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Override
    public String toString() {
        return "OwnerLogParam{" +
                "systemType='" + systemType + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", content='" + content + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", osType='" + osType + '\'' +
                '}';
    }
}
