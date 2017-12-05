/*    */ package com.jinyi.ihome.module.common;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DeviceTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String deviceSid;
/*    */   private String deviceType;
/*    */   private String deviceToken;
/*    */   private int deviceApp;
/*    */   private int appVersion;
/*    */   private double deviceLng;
/*    */   private double deviceLat;
/*    */   private String ownerSid;
/*    */   private String apartmentSid;
/*    */ 
/*    */   public void setDeviceSid(String deviceSid)
/*    */   {
/* 15 */     this.deviceSid = deviceSid; } 
/* 16 */   public String getDeviceSid() { return this.deviceSid; }
/*    */ 
/*    */   public void setDeviceType(String deviceType) {
/* 19 */     this.deviceType = deviceType; } 
/* 20 */   public String getDeviceType() { return this.deviceType; }
/*    */ 
/*    */   public void setDeviceToken(String deviceToken) {
/* 23 */     this.deviceToken = deviceToken; } 
/* 24 */   public String getDeviceToken() { return this.deviceToken; }
/*    */ 
/*    */   public void setDeviceApp(int deviceApp) {
/* 27 */     this.deviceApp = deviceApp; } 
/* 28 */   public int getDeviceApp() { return this.deviceApp; }
/*    */ 
/*    */   public void setAppVersion(int appVersion) {
/* 31 */     this.appVersion = appVersion; } 
/* 32 */   public int getAppVersion() { return this.appVersion; }
/*    */ 
/*    */   public void setDeviceLng(double deviceLng) {
/* 35 */     this.deviceLng = deviceLng; } 
/* 36 */   public double getDeviceLng() { return this.deviceLng; }
/*    */ 
/*    */   public void setDeviceLat(double deviceLat) {
/* 39 */     this.deviceLat = deviceLat; } 
/* 40 */   public double getDeviceLat() { return this.deviceLat; }
/*    */ 
/*    */   public void setOwnerSid(String ownerSid) {
/* 43 */     this.ownerSid = ownerSid; } 
/* 44 */   public String getOwnerSid() { return this.ownerSid; }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid) {
/* 47 */     this.apartmentSid = apartmentSid; } 
/* 48 */   public String getApartmentSid() { return this.apartmentSid; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.common.DeviceTo
 * JD-Core Version:    0.6.2
 */