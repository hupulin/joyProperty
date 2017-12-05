/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import com.jinyi.ihome.module.owner.UserBasicTo;
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
/*    */ 
/*    */ public class ServiceMessageTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String messageSid;
/*    */   private String serviceSid;
/*    */   private int messageType;
/*    */   private String serviceMessage;
/*    */   private Timestamp createdOn;
/*    */   private String createdBy;
/*    */   private String tag;
/*    */   private UserBasicTo userBasic;
/*    */ 
/*    */   public void setMessageSid(String messageSid)
/*    */   {
/* 17 */     this.messageSid = messageSid; } 
/* 18 */   public String getMessageSid() { return this.messageSid; }
/*    */ 
/*    */   public void setServiceSid(String serviceSid) {
/* 21 */     this.serviceSid = serviceSid; } 
/* 22 */   public String getServiceSid() { return this.serviceSid; }
/*    */ 
/*    */   public void setMessageType(int messageType) {
/* 25 */     this.messageType = messageType; } 
/* 26 */   public int getMessageType() { return this.messageType; }
/*    */ 
/*    */   public void setServiceMessage(String serviceMessage) {
/* 29 */     this.serviceMessage = serviceMessage; } 
/* 30 */   public String getServiceMessage() { return this.serviceMessage; }
/*    */ 
/*    */   public void setCreatedOn(Timestamp createdOn) {
/* 33 */     this.createdOn = createdOn; } 
/* 34 */   public Timestamp getCreatedOn() { return this.createdOn; }
/*    */ 
/*    */   public void setCreatedBy(String createdBy) {
/* 37 */     this.createdBy = createdBy; } 
/* 38 */   public String getCreatedBy() { return this.createdBy; }
/*    */ 
/*    */   public void setTag(String tag) {
/* 41 */     this.tag = tag; } 
/* 42 */   public String getTag() { return this.tag; }
/*    */ 
/*    */   public void setUserBasic(UserBasicTo userBasic) {
/* 45 */     this.userBasic = userBasic; } 
/* 46 */   public UserBasicTo getUserBasic() { return this.userBasic; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceMessageTo
 * JD-Core Version:    0.6.2
 */