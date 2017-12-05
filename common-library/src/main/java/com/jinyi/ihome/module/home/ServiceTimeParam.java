/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceTimeParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private int type;
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setType(int type) { this.type = type; }
/*    */ 
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 16 */     return this.apartmentSid;
/*    */   }
/*    */   public int getType() {
/* 19 */     return this.type;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceTimeParam
 * JD-Core Version:    0.6.2
 */