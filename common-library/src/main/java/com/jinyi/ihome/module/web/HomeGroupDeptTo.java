/*    */ package com.jinyi.ihome.module.web;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class HomeGroupDeptTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String deptSid;
/*    */   private String groupSid;
/*    */   private String deptName;
/*    */   private Integer sortIndex;
/*    */   private String parentDeptSid;
/*    */   private String remark;
/*    */   private Date createdOn;
/*    */   private String createdBy;
/*    */   private Date modifiedOn;
/*    */   private String modifiedBy;
/*    */ 
/*    */   public String getDeptSid()
/*    */   {
/* 19 */     return this.deptSid;
/*    */   }
/*    */ 
/*    */   public String getGroupSid()
/*    */   {
/* 24 */     return this.groupSid;
/*    */   }
/*    */ 
/*    */   public String getDeptName()
/*    */   {
/* 29 */     return this.deptName;
/*    */   }
/*    */ 
/*    */   public Integer getSortIndex()
/*    */   {
/* 34 */     return this.sortIndex;
/*    */   }
/*    */ 
/*    */   public String getParentDeptSid()
/*    */   {
/* 39 */     return this.parentDeptSid;
/*    */   }
/*    */ 
/*    */   public String getRemark()
/*    */   {
/* 44 */     return this.remark;
/*    */   }
/*    */ 
/*    */   public Date getCreatedOn()
/*    */   {
/* 49 */     return this.createdOn;
/*    */   }
/*    */ 
/*    */   public String getCreatedBy()
/*    */   {
/* 54 */     return this.createdBy;
/*    */   }
/*    */ 
/*    */   public Date getModifiedOn()
/*    */   {
/* 59 */     return this.modifiedOn;
/*    */   }
/*    */ 
/*    */   public String getModifiedBy()
/*    */   {
/* 64 */     return this.modifiedBy;
/*    */   }
/*    */ 
/*    */   public void setDeptSid(String deptSid)
/*    */   {
/* 11 */     this.deptSid = deptSid; } 
/* 11 */   public void setGroupSid(String groupSid) { this.groupSid = groupSid; } 
/* 11 */   public void setDeptName(String deptName) { this.deptName = deptName; } 
/* 11 */   public void setSortIndex(Integer sortIndex) { this.sortIndex = sortIndex; } 
/* 11 */   public void setParentDeptSid(String parentDeptSid) { this.parentDeptSid = parentDeptSid; } 
/* 11 */   public void setRemark(String remark) { this.remark = remark; } 
/* 11 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 11 */   public void setCreatedBy(String createdBy) { this.createdBy = createdBy; } 
/* 11 */   public void setModifiedOn(Date modifiedOn) { this.modifiedOn = modifiedOn; } 
/* 11 */   public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof HomeGroupDeptTo)) return false; HomeGroupDeptTo other = (HomeGroupDeptTo)o; if (!other.canEqual(this)) return false; Object this$deptSid = getDeptSid(); Object other$deptSid = other.getDeptSid(); if (this$deptSid == null ? other$deptSid != null : !this$deptSid.equals(other$deptSid)) return false; Object this$groupSid = getGroupSid(); Object other$groupSid = other.getGroupSid(); if (this$groupSid == null ? other$groupSid != null : !this$groupSid.equals(other$groupSid)) return false; Object this$deptName = getDeptName(); Object other$deptName = other.getDeptName(); if (this$deptName == null ? other$deptName != null : !this$deptName.equals(other$deptName)) return false; Object this$sortIndex = getSortIndex(); Object other$sortIndex = other.getSortIndex(); if (this$sortIndex == null ? other$sortIndex != null : !this$sortIndex.equals(other$sortIndex)) return false; Object this$parentDeptSid = getParentDeptSid(); Object other$parentDeptSid = other.getParentDeptSid(); if (this$parentDeptSid == null ? other$parentDeptSid != null : !this$parentDeptSid.equals(other$parentDeptSid)) return false; Object this$remark = getRemark(); Object other$remark = other.getRemark(); if (this$remark == null ? other$remark != null : !this$remark.equals(other$remark)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$createdBy = getCreatedBy(); Object other$createdBy = other.getCreatedBy(); if (this$createdBy == null ? other$createdBy != null : !this$createdBy.equals(other$createdBy)) return false; Object this$modifiedOn = getModifiedOn(); Object other$modifiedOn = other.getModifiedOn(); if (this$modifiedOn == null ? other$modifiedOn != null : !this$modifiedOn.equals(other$modifiedOn)) return false; Object this$modifiedBy = getModifiedBy(); Object other$modifiedBy = other.getModifiedBy(); return this$modifiedBy == null ? other$modifiedBy == null : this$modifiedBy.equals(other$modifiedBy); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof HomeGroupDeptTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $deptSid = getDeptSid(); result = result * 59 + ($deptSid == null ? 0 : $deptSid.hashCode()); Object $groupSid = getGroupSid(); result = result * 59 + ($groupSid == null ? 0 : $groupSid.hashCode()); Object $deptName = getDeptName(); result = result * 59 + ($deptName == null ? 0 : $deptName.hashCode()); Object $sortIndex = getSortIndex(); result = result * 59 + ($sortIndex == null ? 0 : $sortIndex.hashCode()); Object $parentDeptSid = getParentDeptSid(); result = result * 59 + ($parentDeptSid == null ? 0 : $parentDeptSid.hashCode()); Object $remark = getRemark(); result = result * 59 + ($remark == null ? 0 : $remark.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $createdBy = getCreatedBy(); result = result * 59 + ($createdBy == null ? 0 : $createdBy.hashCode()); Object $modifiedOn = getModifiedOn(); result = result * 59 + ($modifiedOn == null ? 0 : $modifiedOn.hashCode()); Object $modifiedBy = getModifiedBy(); result = result * 59 + ($modifiedBy == null ? 0 : $modifiedBy.hashCode()); return result; } 
/* 11 */   public String toString() { return "HomeGroupDeptTo(deptSid=" + getDeptSid() + ", groupSid=" + getGroupSid() + ", deptName=" + getDeptName() + ", sortIndex=" + getSortIndex() + ", parentDeptSid=" + getParentDeptSid() + ", remark=" + getRemark() + ", createdOn=" + getCreatedOn() + ", createdBy=" + getCreatedBy() + ", modifiedOn=" + getModifiedOn() + ", modifiedBy=" + getModifiedBy() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.web.HomeGroupDeptTo
 * JD-Core Version:    0.6.2
 */