/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ChangePwdParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String ownerSid;
/*    */   private String oldPassword;
/*    */   private String newPassword;
/*    */ 
/*    */   public String getOwnerSid()
/*    */   {
/* 15 */     return this.ownerSid;
/*    */   }
/*    */   public String getOldPassword() {
/* 18 */     return this.oldPassword;
/*    */   }
/*    */   public String getNewPassword() {
/* 21 */     return this.newPassword;
/*    */   }
/*    */ 
/*    */   public void setOwnerSid(String ownerSid)
/*    */   {
/* 10 */     this.ownerSid = ownerSid; } 
/* 10 */   public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; } 
/* 10 */   public void setNewPassword(String newPassword) { this.newPassword = newPassword; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ChangePwdParam)) return false; ChangePwdParam other = (ChangePwdParam)o; if (!other.canEqual(this)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$oldPassword = getOldPassword(); Object other$oldPassword = other.getOldPassword(); if (this$oldPassword == null ? other$oldPassword != null : !this$oldPassword.equals(other$oldPassword)) return false; Object this$newPassword = getNewPassword(); Object other$newPassword = other.getNewPassword(); return this$newPassword == null ? other$newPassword == null : this$newPassword.equals(other$newPassword); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ChangePwdParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $oldPassword = getOldPassword(); result = result * 59 + ($oldPassword == null ? 0 : $oldPassword.hashCode()); Object $newPassword = getNewPassword(); result = result * 59 + ($newPassword == null ? 0 : $newPassword.hashCode()); return result; } 
/* 10 */   public String toString() { return "ChangePwdParam(ownerSid=" + getOwnerSid() + ", oldPassword=" + getOldPassword() + ", newPassword=" + getNewPassword() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.ChangePwdParam
 * JD-Core Version:    0.6.2
 */