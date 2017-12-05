/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class LoginParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String uid;
/*    */   private String phoneNo;
/*    */   private String pwd;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 16 */     return this.apartmentSid;
/*    */   }
/* 18 */   public String getUid() { return this.uid; } 
/*    */   public String getPhoneNo() {
/* 20 */     return this.phoneNo;
/*    */   }
/* 22 */   public String getPwd() { return this.pwd; }
/*    */ 
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setUid(String uid) { this.uid = uid; } 
/* 11 */   public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; } 
/* 11 */   public void setPwd(String pwd) { this.pwd = pwd; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof LoginParam)) return false; LoginParam other = (LoginParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$uid = getUid(); Object other$uid = other.getUid(); if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false; Object this$phoneNo = getPhoneNo(); Object other$phoneNo = other.getPhoneNo(); if (this$phoneNo == null ? other$phoneNo != null : !this$phoneNo.equals(other$phoneNo)) return false; Object this$pwd = getPwd(); Object other$pwd = other.getPwd(); return this$pwd == null ? other$pwd == null : this$pwd.equals(other$pwd); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof LoginParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $uid = getUid(); result = result * 59 + ($uid == null ? 0 : $uid.hashCode()); Object $phoneNo = getPhoneNo(); result = result * 59 + ($phoneNo == null ? 0 : $phoneNo.hashCode()); Object $pwd = getPwd(); result = result * 59 + ($pwd == null ? 0 : $pwd.hashCode()); return result; } 
/* 11 */   public String toString() { return "LoginParam(apartmentSid=" + getApartmentSid() + ", uid=" + getUid() + ", phoneNo=" + getPhoneNo() + ", pwd=" + getPwd() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.LoginParam
 * JD-Core Version:    0.6.2
 */