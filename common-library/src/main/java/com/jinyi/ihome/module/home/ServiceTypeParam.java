/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceTypeParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String categorySid;
/*    */   private String apartmentSid;
/*    */ 
/*    */   public void setCategorySid(String categorySid)
/*    */   {
/* 12 */     this.categorySid = categorySid; } 
/* 12 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; }
/*    */ 
/*    */ 
/*    */   public String getCategorySid()
/*    */   {
/* 29 */     return this.categorySid;
/*    */   }
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 34 */     return this.apartmentSid;
/*    */   }

    @Override
    public String toString() {
        return "ServiceTypeParam{" +
                "categorySid='" + categorySid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                '}';
    }

    /*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceTypeParam
 * JD-Core Version:    0.6.2
 */