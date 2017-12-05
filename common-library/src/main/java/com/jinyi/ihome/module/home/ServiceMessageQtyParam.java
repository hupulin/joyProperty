/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class ServiceMessageQtyParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String serviceSid;
/*    */   private String userSid;
/*    */   private Date lastReadTime;
/*    */ 
/*    */   public void setServiceSid(String serviceSid)
/*    */   {
/* 12 */     this.serviceSid = serviceSid; } 
/* 12 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 12 */   public void setLastReadTime(Date lastReadTime) { this.lastReadTime = lastReadTime; }
/*    */ 
/*    */ 
/*    */   public String getServiceSid()
/*    */   {
/* 18 */     return this.serviceSid; } 
/* 19 */   public String getUserSid() { return this.userSid; } 
/* 20 */   public Date getLastReadTime() { return this.lastReadTime; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceMessageQtyParam
 * JD-Core Version:    0.6.2
 */