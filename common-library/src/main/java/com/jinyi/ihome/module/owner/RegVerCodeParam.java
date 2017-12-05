/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RegVerCodeParam
/*    */   implements Serializable
/*    */ {
/*    */   private String uid;
/*    */   private String apartmentSid;
/*    */   private String phoneNo;
/*    */ 
/*    */   public String getUid()
/*    */   {
/* 19 */     return this.uid;
/*    */   }
/*    */ 
/*    */   public String getApartmentSid() {
/* 23 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getPhoneNo() {
/* 27 */     return this.phoneNo;
/*    */   }
/*    */ 
/*    */   public void setUid(String uid)
/*    */   {
/* 14 */     this.uid = uid; } 
/* 14 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 14 */   public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; } 
/* 14 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof RegVerCodeParam)) return false; RegVerCodeParam other = (RegVerCodeParam)o; if (!other.canEqual(this)) return false; Object this$uid = getUid(); Object other$uid = other.getUid(); if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$phoneNo = getPhoneNo(); Object other$phoneNo = other.getPhoneNo(); return this$phoneNo == null ? other$phoneNo == null : this$phoneNo.equals(other$phoneNo); } 
/* 14 */   protected boolean canEqual(Object other) { return other instanceof RegVerCodeParam; } 
/* 14 */   public int hashCode() { int PRIME = 59; int result = 1; Object $uid = getUid(); result = result * 59 + ($uid == null ? 0 : $uid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $phoneNo = getPhoneNo(); result = result * 59 + ($phoneNo == null ? 0 : $phoneNo.hashCode()); return result; } 
/* 14 */   public String toString() { return "RegVerCodeParam(uid=" + getUid() + ", apartmentSid=" + getApartmentSid() + ", phoneNo=" + getPhoneNo() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.RegVerCodeParam
 * JD-Core Version:    0.6.2
 */