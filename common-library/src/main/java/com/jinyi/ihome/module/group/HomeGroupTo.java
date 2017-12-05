/*    */ package com.jinyi.ihome.module.group;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class HomeGroupTo
/*    */   implements Serializable
/*    */ {
/*    */   private String groupName;
/*    */   private String groupUrl;
/*    */   private String remark;
/*    */   private String groupContact;
/*    */ 
/*    */   public String getGroupName()
/*    */   {
/* 15 */     return this.groupName;
/*    */   }
/* 17 */   public String getGroupUrl() { return this.groupUrl; } 
/*    */   public String getRemark() {
/* 19 */     return this.remark;
/*    */   }
/* 21 */   public String getGroupContact() { return this.groupContact; }
/*    */ 
/*    */ 
/*    */   public void setGroupName(String groupName)
/*    */   {
/* 12 */     this.groupName = groupName; } 
/* 12 */   public void setGroupUrl(String groupUrl) { this.groupUrl = groupUrl; } 
/* 12 */   public void setRemark(String remark) { this.remark = remark; } 
/* 12 */   public void setGroupContact(String groupContact) { this.groupContact = groupContact; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof HomeGroupTo)) return false; HomeGroupTo other = (HomeGroupTo)o; if (!other.canEqual(this)) return false; Object this$groupName = getGroupName(); Object other$groupName = other.getGroupName(); if (this$groupName == null ? other$groupName != null : !this$groupName.equals(other$groupName)) return false; Object this$groupUrl = getGroupUrl(); Object other$groupUrl = other.getGroupUrl(); if (this$groupUrl == null ? other$groupUrl != null : !this$groupUrl.equals(other$groupUrl)) return false; Object this$remark = getRemark(); Object other$remark = other.getRemark(); if (this$remark == null ? other$remark != null : !this$remark.equals(other$remark)) return false; Object this$groupContact = getGroupContact(); Object other$groupContact = other.getGroupContact(); return this$groupContact == null ? other$groupContact == null : this$groupContact.equals(other$groupContact); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof HomeGroupTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $groupName = getGroupName(); result = result * 59 + ($groupName == null ? 0 : $groupName.hashCode()); Object $groupUrl = getGroupUrl(); result = result * 59 + ($groupUrl == null ? 0 : $groupUrl.hashCode()); Object $remark = getRemark(); result = result * 59 + ($remark == null ? 0 : $remark.hashCode()); Object $groupContact = getGroupContact(); result = result * 59 + ($groupContact == null ? 0 : $groupContact.hashCode()); return result; } 
/* 12 */   public String toString() { return "HomeGroupTo(groupName=" + getGroupName() + ", groupUrl=" + getGroupUrl() + ", remark=" + getRemark() + ", groupContact=" + getGroupContact() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.group.HomeGroupTo
 * JD-Core Version:    0.6.2
 */