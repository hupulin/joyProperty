/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceMessageParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String serviceSid;
/*    */   private int messageType;
/*    */   private String serviceMessage;
/*    */   private String createdBy;
/*    */ 
/*    */   public void setServiceSid(String serviceSid)
/*    */   {
/* 16 */     this.serviceSid = serviceSid; } 
/* 17 */   public String getServiceSid() { return this.serviceSid; }
/*    */ 
/*    */ 
/*    */   public void setMessageType(int messageType)
/*    */   {
/* 23 */     this.messageType = messageType; } 
/* 24 */   public int getMessageType() { return this.messageType; }
/*    */ 
/*    */ 
/*    */   public void setServiceMessage(String serviceMessage)
/*    */   {
/* 30 */     this.serviceMessage = serviceMessage; } 
/* 31 */   public String getServiceMessage() { return this.serviceMessage; }
/*    */ 
/*    */ 
/*    */   public void setCreatedBy(String createdBy)
/*    */   {
/* 37 */     this.createdBy = createdBy; } 
/* 38 */   public String getCreatedBy() { return this.createdBy; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceMessageParam
 * JD-Core Version:    0.6.2
 */