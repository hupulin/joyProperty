/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceAssignParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String typeSid;
/*    */   private String groupUserSid;
/*    */ 
/*    */   public String getTypeSid()
/*    */   {
/* 18 */     return this.typeSid;
/*    */   }
/*    */ 
/*    */   public String getGroupUserSid()
/*    */   {
/* 23 */     return this.groupUserSid;
/*    */   }
/*    */ 
/*    */   public void setTypeSid(String typeSid)
/*    */   {
/* 10 */     this.typeSid = typeSid; } 
/* 10 */   public void setGroupUserSid(String groupUserSid) { this.groupUserSid = groupUserSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceAssignParam)) return false; ServiceAssignParam other = (ServiceAssignParam)o; if (!other.canEqual(this)) return false; Object this$typeSid = getTypeSid(); Object other$typeSid = other.getTypeSid(); if (this$typeSid == null ? other$typeSid != null : !this$typeSid.equals(other$typeSid)) return false; Object this$groupUserSid = getGroupUserSid(); Object other$groupUserSid = other.getGroupUserSid(); return this$groupUserSid == null ? other$groupUserSid == null : this$groupUserSid.equals(other$groupUserSid); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ServiceAssignParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $typeSid = getTypeSid(); result = result * 59 + ($typeSid == null ? 0 : $typeSid.hashCode()); Object $groupUserSid = getGroupUserSid(); result = result * 59 + ($groupUserSid == null ? 0 : $groupUserSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "ServiceAssignParam(typeSid=" + getTypeSid() + ", groupUserSid=" + getGroupUserSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceAssignParam
 * JD-Core Version:    0.6.2
 */