/*    */ package com.jinyi.ihome.module.web;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GroupRegisterParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String validateCode;
/*    */   private String groupAdminNo;
/*    */   private String groupAdminPwd;
/*    */   private String groupName;
/*    */   private String groupContact;
/*    */   private String groupSid;
/*    */ 
/*    */   public String getValidateCode()
/*    */   {
/* 18 */     return this.validateCode;
/*    */   }
/*    */ 
/*    */   public String getGroupAdminNo()
/*    */   {
/* 23 */     return this.groupAdminNo;
/*    */   }
/*    */ 
/*    */   public String getGroupAdminPwd()
/*    */   {
/* 28 */     return this.groupAdminPwd;
/*    */   }
/*    */ 
/*    */   public String getGroupName()
/*    */   {
/* 33 */     return this.groupName;
/*    */   }
/*    */ 
/*    */   public String getGroupContact()
/*    */   {
/* 38 */     return this.groupContact;
/*    */   }
/*    */ 
/*    */   public String getGroupSid()
/*    */   {
/* 43 */     return this.groupSid;
/*    */   }
/*    */ 
/*    */   public void setValidateCode(String validateCode)
/*    */   {
/* 10 */     this.validateCode = validateCode; } 
/* 10 */   public void setGroupAdminNo(String groupAdminNo) { this.groupAdminNo = groupAdminNo; } 
/* 10 */   public void setGroupAdminPwd(String groupAdminPwd) { this.groupAdminPwd = groupAdminPwd; } 
/* 10 */   public void setGroupName(String groupName) { this.groupName = groupName; } 
/* 10 */   public void setGroupContact(String groupContact) { this.groupContact = groupContact; } 
/* 10 */   public void setGroupSid(String groupSid) { this.groupSid = groupSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof GroupRegisterParam)) return false; GroupRegisterParam other = (GroupRegisterParam)o; if (!other.canEqual(this)) return false; Object this$validateCode = getValidateCode(); Object other$validateCode = other.getValidateCode(); if (this$validateCode == null ? other$validateCode != null : !this$validateCode.equals(other$validateCode)) return false; Object this$groupAdminNo = getGroupAdminNo(); Object other$groupAdminNo = other.getGroupAdminNo(); if (this$groupAdminNo == null ? other$groupAdminNo != null : !this$groupAdminNo.equals(other$groupAdminNo)) return false; Object this$groupAdminPwd = getGroupAdminPwd(); Object other$groupAdminPwd = other.getGroupAdminPwd(); if (this$groupAdminPwd == null ? other$groupAdminPwd != null : !this$groupAdminPwd.equals(other$groupAdminPwd)) return false; Object this$groupName = getGroupName(); Object other$groupName = other.getGroupName(); if (this$groupName == null ? other$groupName != null : !this$groupName.equals(other$groupName)) return false; Object this$groupContact = getGroupContact(); Object other$groupContact = other.getGroupContact(); if (this$groupContact == null ? other$groupContact != null : !this$groupContact.equals(other$groupContact)) return false; Object this$groupSid = getGroupSid(); Object other$groupSid = other.getGroupSid(); return this$groupSid == null ? other$groupSid == null : this$groupSid.equals(other$groupSid); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof GroupRegisterParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $validateCode = getValidateCode(); result = result * 59 + ($validateCode == null ? 0 : $validateCode.hashCode()); Object $groupAdminNo = getGroupAdminNo(); result = result * 59 + ($groupAdminNo == null ? 0 : $groupAdminNo.hashCode()); Object $groupAdminPwd = getGroupAdminPwd(); result = result * 59 + ($groupAdminPwd == null ? 0 : $groupAdminPwd.hashCode()); Object $groupName = getGroupName(); result = result * 59 + ($groupName == null ? 0 : $groupName.hashCode()); Object $groupContact = getGroupContact(); result = result * 59 + ($groupContact == null ? 0 : $groupContact.hashCode()); Object $groupSid = getGroupSid(); result = result * 59 + ($groupSid == null ? 0 : $groupSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "GroupRegisterParam(validateCode=" + getValidateCode() + ", groupAdminNo=" + getGroupAdminNo() + ", groupAdminPwd=" + getGroupAdminPwd() + ", groupName=" + getGroupName() + ", groupContact=" + getGroupContact() + ", groupSid=" + getGroupSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.web.GroupRegisterParam
 * JD-Core Version:    0.6.2
 */