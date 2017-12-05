/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceCloseParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String groupUserSid;
/*    */   private String serviceSid;
/*    */   private String remark;
/*    */ 
/*    */   public String getGroupUserSid()
/*    */   {
/* 18 */     return this.groupUserSid;
/*    */   }
/*    */ 
/*    */   public String getServiceSid() {
/* 22 */     return this.serviceSid;
/*    */   }
/*    */ 
/*    */   public String getRemark()
/*    */   {
/* 27 */     return this.remark;
/*    */   }
/*    */ 
/*    */   public void setGroupUserSid(String groupUserSid)
/*    */   {
/* 10 */     this.groupUserSid = groupUserSid; } 
/* 10 */   public void setServiceSid(String serviceSid) { this.serviceSid = serviceSid; } 
/* 10 */   public void setRemark(String remark) { this.remark = remark; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceCloseParam)) return false; ServiceCloseParam other = (ServiceCloseParam)o; if (!other.canEqual(this)) return false; Object this$groupUserSid = getGroupUserSid(); Object other$groupUserSid = other.getGroupUserSid(); if (this$groupUserSid == null ? other$groupUserSid != null : !this$groupUserSid.equals(other$groupUserSid)) return false; Object this$serviceSid = getServiceSid(); Object other$serviceSid = other.getServiceSid(); if (this$serviceSid == null ? other$serviceSid != null : !this$serviceSid.equals(other$serviceSid)) return false; Object this$remark = getRemark(); Object other$remark = other.getRemark(); return this$remark == null ? other$remark == null : this$remark.equals(other$remark); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ServiceCloseParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $groupUserSid = getGroupUserSid(); result = result * 59 + ($groupUserSid == null ? 0 : $groupUserSid.hashCode()); Object $serviceSid = getServiceSid(); result = result * 59 + ($serviceSid == null ? 0 : $serviceSid.hashCode()); Object $remark = getRemark(); result = result * 59 + ($remark == null ? 0 : $remark.hashCode()); return result; } 
/* 10 */   public String toString() { return "ServiceCloseParam(groupUserSid=" + getGroupUserSid() + ", serviceSid=" + getServiceSid() + ", remark=" + getRemark() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceCloseParam
 * JD-Core Version:    0.6.2
 */