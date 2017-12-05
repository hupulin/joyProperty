/*    */
package com.jinyi.ihome.module.common;
/*    */ 
/*    */

import java.io.Serializable;

/*    */
/*    */ public class DeviceParam
/*    */ implements Serializable
/*    */ {
    /*    */   private static final long serialVersionUID = 1L;
    /*    */   private String deviceType;
    /*    */   private String deviceToken;
    /*    */   private int deviceApp;
    /*    */   private int appVersion;
    /*    */   private double deviceLng;
    /*    */   private double deviceLat;
    /*    */   private String ownerSid;
    /*    */   private String apartmentSid;
               private String registrationId;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /*    */   private int type;

    /*    */
/*    */
    public String getDeviceType()
/*    */ {
/* 16 */
        return this.deviceType;
/*    */
    }

    /* 18 */
    public String getDeviceToken() {
        return this.deviceToken;
    }

    /*    */
/*    */ 
/*    */
    public int getDeviceApp()
/*    */ {
/* 30 */
        return this.deviceApp;
/*    */
    }

    /* 32 */
    public int getAppVersion() {
        return this.appVersion;
    }

    /*    */
    public double getDeviceLng() {
/* 34 */
        return this.deviceLng;
/*    */
    }

    /* 36 */
    public double getDeviceLat() {
        return this.deviceLat;
    }

    /*    */
    public String getOwnerSid() {
/* 38 */
        return this.ownerSid;
/*    */
    }

    /* 40 */
    public String getApartmentSid() {
        return this.apartmentSid;
    }

    /*    */
/*    */ 
/*    */
    public void setDeviceType(String deviceType)
/*    */ {
/* 12 */
        this.deviceType = deviceType;
    }

    /* 12 */
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    /* 12 */
    public void setDeviceApp(int deviceApp) {
        this.deviceApp = deviceApp;
    }

    /* 12 */
    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    /* 12 */
    public void setDeviceLng(double deviceLng) {
        this.deviceLng = deviceLng;
    }

    /* 12 */
    public void setDeviceLat(double deviceLat) {
        this.deviceLat = deviceLat;
    }

    /* 12 */
    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    /* 12 */
    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    /* 12 */
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DeviceParam)) return false;
        DeviceParam other = (DeviceParam) o;
        if (!other.canEqual(this)) return false;
        Object this$deviceType = getDeviceType();
        Object other$deviceType = other.getDeviceType();
        if (this$deviceType == null ? other$deviceType != null : !this$deviceType.equals(other$deviceType))
            return false;
        Object this$deviceToken = getDeviceToken();
        Object other$deviceToken = other.getDeviceToken();
        if (this$deviceToken == null ? other$deviceToken != null : !this$deviceToken.equals(other$deviceToken))
            return false;
        if (getDeviceApp() != other.getDeviceApp()) return false;
        if (getAppVersion() != other.getAppVersion()) return false;
        if (Double.compare(getDeviceLng(), other.getDeviceLng()) != 0) return false;
        if (Double.compare(getDeviceLat(), other.getDeviceLat()) != 0) return false;
        Object this$ownerSid = getOwnerSid();
        Object other$ownerSid = other.getOwnerSid();
        if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid))
            return false;
        Object this$apartmentSid = getApartmentSid();
        Object other$apartmentSid = other.getApartmentSid();
        return this$apartmentSid == null ? other$apartmentSid == null : this$apartmentSid.equals(other$apartmentSid);
    }

    /* 12 */
    protected boolean canEqual(Object other) {
        return other instanceof DeviceParam;
    }

    /* 12 */
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $deviceType = getDeviceType();
        result = result * 59 + ($deviceType == null ? 0 : $deviceType.hashCode());
        Object $deviceToken = getDeviceToken();
        result = result * 59 + ($deviceToken == null ? 0 : $deviceToken.hashCode());
        result = result * 59 + getDeviceApp();
        result = result * 59 + getAppVersion();
        long $deviceLng = Double.doubleToLongBits(getDeviceLng());
        result = result * 59 + (int) ($deviceLng >>> 32 ^ $deviceLng);
        long $deviceLat = Double.doubleToLongBits(getDeviceLat());
        result = result * 59 + (int) ($deviceLat >>> 32 ^ $deviceLat);
        Object $ownerSid = getOwnerSid();
        result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode());
        Object $apartmentSid = getApartmentSid();
        result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "DeviceParam{" +
                "deviceType='" + deviceType + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", deviceApp=" + deviceApp +
                ", appVersion=" + appVersion +
                ", deviceLng=" + deviceLng +
                ", deviceLat=" + deviceLat +
                ", ownerSid='" + ownerSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", type=" + type +
                '}';
    }
/*    */ 
/*    */
}

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.common.DeviceParam
 * JD-Core Version:    0.6.2
 */