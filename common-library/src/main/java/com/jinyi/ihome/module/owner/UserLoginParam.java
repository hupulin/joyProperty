/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserLoginParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String userNo;
/*    */   private String userPwd;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 16 */     return this.apartmentSid;
/*    */   }
/* 18 */   public String getUserNo() { return this.userNo; } 
/*    */   public String getUserPwd() {
/* 20 */     return this.userPwd;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setUserNo(String userNo) { this.userNo = userNo; } 
/* 11 */   public void setUserPwd(String userPwd) { this.userPwd = userPwd; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof UserLoginParam)) return false; UserLoginParam other = (UserLoginParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$userNo = getUserNo(); Object other$userNo = other.getUserNo(); if (this$userNo == null ? other$userNo != null : !this$userNo.equals(other$userNo)) return false; Object this$userPwd = getUserPwd(); Object other$userPwd = other.getUserPwd(); return this$userPwd == null ? other$userPwd == null : this$userPwd.equals(other$userPwd); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof UserLoginParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $userNo = getUserNo(); result = result * 59 + ($userNo == null ? 0 : $userNo.hashCode()); Object $userPwd = getUserPwd(); result = result * 59 + ($userPwd == null ? 0 : $userPwd.hashCode()); return result; } 
/* 11 */   public String toString() { return "UserLoginParam(apartmentSid=" + getApartmentSid() + ", userNo=" + getUserNo() + ", userPwd=" + getUserPwd() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.UserLoginParam
 * JD-Core Version:    0.6.2
 */