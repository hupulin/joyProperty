/*    */ package com.jinyi.ihome.module.car;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CarFindParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String userSid;
/*    */   private String carNo;
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 18 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public String getCarNo() {
/* 22 */     return this.carNo;
/*    */   }
/*    */ 
/*    */   public void setUserSid(String userSid)
/*    */   {
/* 10 */     this.userSid = userSid; } 
/* 10 */   public void setCarNo(String carNo) { this.carNo = carNo; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof CarFindParam)) return false; CarFindParam other = (CarFindParam)o; if (!other.canEqual(this)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$carNo = getCarNo(); Object other$carNo = other.getCarNo(); return this$carNo == null ? other$carNo == null : this$carNo.equals(other$carNo); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof CarFindParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $carNo = getCarNo(); result = result * 59 + ($carNo == null ? 0 : $carNo.hashCode()); return result; } 
/* 10 */   public String toString() { return "CarFindParam(userSid=" + getUserSid() + ", carNo=" + getCarNo() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.car.CarFindParam
 * JD-Core Version:    0.6.2
 */