/*    */ package com.jinyi.ihome.module.web;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class HomeGroupRoleTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String roleSid;
/*    */   private String groupSid;
/*    */   private String roleName;
/*    */   private Boolean enabled;
/*    */   private String remark;
/*    */   private Date createdOn;
/*    */   private String createdBy;
/*    */   private Date modifiedOn;
/*    */   private String modifiedBy;
/*    */ 
/*    */   public String getRoleSid()
/*    */   {
/* 19 */     return this.roleSid;
/*    */   }
/*    */ 
/*    */   public String getGroupSid()
/*    */   {
/* 24 */     return this.groupSid;
/*    */   }
/*    */ 
/*    */   public String getRoleName()
/*    */   {
/* 29 */     return this.roleName;
/*    */   }
/*    */ 
/*    */   public Boolean getEnabled()
/*    */   {
/* 34 */     return this.enabled;
/*    */   }
/*    */ 
/*    */   public String getRemark()
/*    */   {
/* 39 */     return this.remark;
/*    */   }
/*    */ 
/*    */   public Date getCreatedOn()
/*    */   {
/* 44 */     return this.createdOn;
/*    */   }
/*    */ 
/*    */   public String getCreatedBy()
/*    */   {
/* 49 */     return this.createdBy;
/*    */   }
/*    */ 
/*    */   public Date getModifiedOn()
/*    */   {
/* 54 */     return this.modifiedOn;
/*    */   }
/*    */ 
/*    */   public String getModifiedBy()
/*    */   {
/* 59 */     return this.modifiedBy;
/*    */   }
/*    */ 
/*    */   public void setRoleSid(String roleSid)
/*    */   {
/* 11 */     this.roleSid = roleSid; } 
/* 11 */   public void setGroupSid(String groupSid) { this.groupSid = groupSid; } 
/* 11 */   public void setRoleName(String roleName) { this.roleName = roleName; } 
/* 11 */   public void setEnabled(Boolean enabled) { this.enabled = enabled; } 
/* 11 */   public void setRemark(String remark) { this.remark = remark; } 
/* 11 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 11 */   public void setCreatedBy(String createdBy) { this.createdBy = createdBy; } 
/* 11 */   public void setModifiedOn(Date modifiedOn) { this.modifiedOn = modifiedOn; } 
/* 11 */   public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof HomeGroupRoleTo)) return false; HomeGroupRoleTo other = (HomeGroupRoleTo)o; if (!other.canEqual(this)) return false; Object this$roleSid = getRoleSid(); Object other$roleSid = other.getRoleSid(); if (this$roleSid == null ? other$roleSid != null : !this$roleSid.equals(other$roleSid)) return false; Object this$groupSid = getGroupSid(); Object other$groupSid = other.getGroupSid(); if (this$groupSid == null ? other$groupSid != null : !this$groupSid.equals(other$groupSid)) return false; Object this$roleName = getRoleName(); Object other$roleName = other.getRoleName(); if (this$roleName == null ? other$roleName != null : !this$roleName.equals(other$roleName)) return false; Object this$enabled = getEnabled(); Object other$enabled = other.getEnabled(); if (this$enabled == null ? other$enabled != null : !this$enabled.equals(other$enabled)) return false; Object this$remark = getRemark(); Object other$remark = other.getRemark(); if (this$remark == null ? other$remark != null : !this$remark.equals(other$remark)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$createdBy = getCreatedBy(); Object other$createdBy = other.getCreatedBy(); if (this$createdBy == null ? other$createdBy != null : !this$createdBy.equals(other$createdBy)) return false; Object this$modifiedOn = getModifiedOn(); Object other$modifiedOn = other.getModifiedOn(); if (this$modifiedOn == null ? other$modifiedOn != null : !this$modifiedOn.equals(other$modifiedOn)) return false; Object this$modifiedBy = getModifiedBy(); Object other$modifiedBy = other.getModifiedBy(); return this$modifiedBy == null ? other$modifiedBy == null : this$modifiedBy.equals(other$modifiedBy); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof HomeGroupRoleTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $roleSid = getRoleSid(); result = result * 59 + ($roleSid == null ? 0 : $roleSid.hashCode()); Object $groupSid = getGroupSid(); result = result * 59 + ($groupSid == null ? 0 : $groupSid.hashCode()); Object $roleName = getRoleName(); result = result * 59 + ($roleName == null ? 0 : $roleName.hashCode()); Object $enabled = getEnabled(); result = result * 59 + ($enabled == null ? 0 : $enabled.hashCode()); Object $remark = getRemark(); result = result * 59 + ($remark == null ? 0 : $remark.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $createdBy = getCreatedBy(); result = result * 59 + ($createdBy == null ? 0 : $createdBy.hashCode()); Object $modifiedOn = getModifiedOn(); result = result * 59 + ($modifiedOn == null ? 0 : $modifiedOn.hashCode()); Object $modifiedBy = getModifiedBy(); result = result * 59 + ($modifiedBy == null ? 0 : $modifiedBy.hashCode()); return result; } 
/* 11 */   public String toString() { return "HomeGroupRoleTo(roleSid=" + getRoleSid() + ", groupSid=" + getGroupSid() + ", roleName=" + getRoleName() + ", enabled=" + getEnabled() + ", remark=" + getRemark() + ", createdOn=" + getCreatedOn() + ", createdBy=" + getCreatedBy() + ", modifiedOn=" + getModifiedOn() + ", modifiedBy=" + getModifiedBy() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.web.HomeGroupRoleTo
 * JD-Core Version:    0.6.2
 */