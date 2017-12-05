/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserBasicParam
/*    */   implements Serializable
/*    */ {
/*    */   private String sid;
/*    */   private String name;
/*    */   private String roomNo;
/*    */ 
/*    */   public String getSid()
/*    */   {
/* 14 */     return this.sid;
/*    */   }
/* 16 */   public String getName() { return this.name; } 
/*    */   public String getRoomNo() {
/* 18 */     return this.roomNo;
/*    */   }
/*    */ 
/*    */   public void setSid(String sid)
/*    */   {
/* 11 */     this.sid = sid; } 
/* 11 */   public void setName(String name) { this.name = name; } 
/* 11 */   public void setRoomNo(String roomNo) { this.roomNo = roomNo; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof UserBasicParam)) return false; UserBasicParam other = (UserBasicParam)o; if (!other.canEqual(this)) return false; Object this$sid = getSid(); Object other$sid = other.getSid(); if (this$sid == null ? other$sid != null : !this$sid.equals(other$sid)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$roomNo = getRoomNo(); Object other$roomNo = other.getRoomNo(); return this$roomNo == null ? other$roomNo == null : this$roomNo.equals(other$roomNo); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof UserBasicParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $sid = getSid(); result = result * 59 + ($sid == null ? 0 : $sid.hashCode()); Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $roomNo = getRoomNo(); result = result * 59 + ($roomNo == null ? 0 : $roomNo.hashCode()); return result; } 
/* 11 */   public String toString() { return "UserBasicParam(sid=" + getSid() + ", name=" + getName() + ", roomNo=" + getRoomNo() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.UserBasicParam
 * JD-Core Version:    0.6.2
 */