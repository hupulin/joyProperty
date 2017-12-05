/*    */ package com.jinyi.ihome.module.bill;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PropertyBillReleaseParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String billItemSid;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 16 */     return this.apartmentSid;
/*    */   }
/*    */   public String getBillItemSid() {
/* 19 */     return this.billItemSid;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 10 */     this.apartmentSid = apartmentSid; } 
/* 10 */   public void setBillItemSid(String billItemSid) { this.billItemSid = billItemSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof PropertyBillReleaseParam)) return false; PropertyBillReleaseParam other = (PropertyBillReleaseParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$billItemSid = getBillItemSid(); Object other$billItemSid = other.getBillItemSid(); return this$billItemSid == null ? other$billItemSid == null : this$billItemSid.equals(other$billItemSid); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof PropertyBillReleaseParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $billItemSid = getBillItemSid(); result = result * 59 + ($billItemSid == null ? 0 : $billItemSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "PropertyBillReleaseParam(apartmentSid=" + getApartmentSid() + ", billItemSid=" + getBillItemSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.bill.PropertyBillReleaseParam
 * JD-Core Version:    0.6.2
 */