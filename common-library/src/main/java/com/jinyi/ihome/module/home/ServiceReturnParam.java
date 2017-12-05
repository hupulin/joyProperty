/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceReturnParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String serviceSid;
/*    */   private String returnDesc;
/*    */   private String ownerSid;
/*    */ 
/*    */   public void setServiceSid(String serviceSid)
/*    */   {
/* 15 */     this.serviceSid = serviceSid; } 
/* 16 */   public String getServiceSid() { return this.serviceSid; }
/*    */ 
/*    */ 
/*    */   public void setReturnDesc(String returnDesc)
/*    */   {
/* 22 */     this.returnDesc = returnDesc; } 
/* 23 */   public String getReturnDesc() { return this.returnDesc; }
/*    */ 
/*    */ 
/*    */   public void setOwnerSid(String ownerSid)
/*    */   {
/* 29 */     this.ownerSid = ownerSid; } 
/* 30 */   public String getOwnerSid() { return this.ownerSid; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceReturnParam
 * JD-Core Version:    0.6.2
 */