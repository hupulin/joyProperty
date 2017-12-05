/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */

import java.io.Serializable;
import java.util.Date;

/*    */

/*    */
/*    */ public class NeighborMessageParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String userSid;
/*    */   private Date lastTime;
/*    */   private int pageIndex;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 18 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getUserSid() {
/* 22 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public Date getLastTime() {
/* 26 */     return this.lastTime;
/*    */   }
/*    */ 
/*    */   public int getPageIndex() {
/* 30 */     return this.pageIndex;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 11 */   public void setLastTime(Date lastTime) { this.lastTime = lastTime; } 
/* 11 */   public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NeighborMessageParam)) return false; NeighborMessageParam other = (NeighborMessageParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$lastTime = getLastTime(); Object other$lastTime = other.getLastTime(); if (this$lastTime == null ? other$lastTime != null : !this$lastTime.equals(other$lastTime)) return false; return getPageIndex() == other.getPageIndex(); }
/* 11 */   protected boolean canEqual(Object other) { return other instanceof NeighborMessageParam; }
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $lastTime = getLastTime(); result = result * 59 + ($lastTime == null ? 0 : $lastTime.hashCode()); result = result * 59 + getPageIndex(); return result; } 
/* 11 */   public String toString() { return "NeighborMessageParam(apartmentSid=" + getApartmentSid() + ", userSid=" + getUserSid() + ", lastTime=" + getLastTime() + ", pageIndex=" + getPageIndex() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborMessageParam
 * JD-Core Version:    0.6.2
 */