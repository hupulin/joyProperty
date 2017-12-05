/*    */ package com.jinyi.ihome.module.bill;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PropertyBillPayParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String userSid;
/*    */   private String billSid;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 18 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 23 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public String getBillSid()
/*    */   {
/* 28 */     return this.billSid;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 10 */     this.apartmentSid = apartmentSid; } 
/* 10 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 10 */   public void setBillSid(String billSid) { this.billSid = billSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof PropertyBillPayParam)) return false; PropertyBillPayParam other = (PropertyBillPayParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$billSid = getBillSid(); Object other$billSid = other.getBillSid(); return this$billSid == null ? other$billSid == null : this$billSid.equals(other$billSid); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof PropertyBillPayParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $billSid = getBillSid(); result = result * 59 + ($billSid == null ? 0 : $billSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "PropertyBillPayParam(apartmentSid=" + getApartmentSid() + ", userSid=" + getUserSid() + ", billSid=" + getBillSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.bill.PropertyBillPayParam
 * JD-Core Version:    0.6.2
 */