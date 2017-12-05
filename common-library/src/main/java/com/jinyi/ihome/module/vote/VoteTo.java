/*    */ package com.jinyi.ihome.module.vote;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class VoteTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String voteSid;
/*    */   private String apartmentSid;
/*    */   private Date createdOn;
/*    */   private String createdby;
/*    */   private String dueDate;
/*    */   private Date modifiedOn;
/*    */   private String modifiedby;
/*    */   private String voteContent;
/*    */   private String voteSubject;
/*    */ 
/*    */   public String getVoteSid()
/*    */   {
/* 17 */     return this.voteSid;
/*    */   }
/* 19 */   public String getApartmentSid() { return this.apartmentSid; } 
/*    */   public Date getCreatedOn() {
/* 21 */     return this.createdOn;
/*    */   }
/* 23 */   public String getCreatedby() { return this.createdby; } 
/*    */   public String getDueDate() {
/* 25 */     return this.dueDate;
/*    */   }
/* 27 */   public Date getModifiedOn() { return this.modifiedOn; } 
/*    */   public String getModifiedby() {
/* 29 */     return this.modifiedby;
/*    */   }
/* 31 */   public String getVoteContent() { return this.voteContent; } 
/*    */   public String getVoteSubject() {
/* 33 */     return this.voteSubject;
/*    */   }
/*    */ 
/*    */   public void setVoteSid(String voteSid)
/*    */   {
/* 13 */     this.voteSid = voteSid; } 
/* 13 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 13 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 13 */   public void setCreatedby(String createdby) { this.createdby = createdby; } 
/* 13 */   public void setDueDate(String dueDate) { this.dueDate = dueDate; } 
/* 13 */   public void setModifiedOn(Date modifiedOn) { this.modifiedOn = modifiedOn; } 
/* 13 */   public void setModifiedby(String modifiedby) { this.modifiedby = modifiedby; } 
/* 13 */   public void setVoteContent(String voteContent) { this.voteContent = voteContent; } 
/* 13 */   public void setVoteSubject(String voteSubject) { this.voteSubject = voteSubject; } 
/* 13 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof VoteTo)) return false; VoteTo other = (VoteTo)o; if (!other.canEqual(this)) return false; Object this$voteSid = getVoteSid(); Object other$voteSid = other.getVoteSid(); if (this$voteSid == null ? other$voteSid != null : !this$voteSid.equals(other$voteSid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$createdby = getCreatedby(); Object other$createdby = other.getCreatedby(); if (this$createdby == null ? other$createdby != null : !this$createdby.equals(other$createdby)) return false; Object this$dueDate = getDueDate(); Object other$dueDate = other.getDueDate(); if (this$dueDate == null ? other$dueDate != null : !this$dueDate.equals(other$dueDate)) return false; Object this$modifiedOn = getModifiedOn(); Object other$modifiedOn = other.getModifiedOn(); if (this$modifiedOn == null ? other$modifiedOn != null : !this$modifiedOn.equals(other$modifiedOn)) return false; Object this$modifiedby = getModifiedby(); Object other$modifiedby = other.getModifiedby(); if (this$modifiedby == null ? other$modifiedby != null : !this$modifiedby.equals(other$modifiedby)) return false; Object this$voteContent = getVoteContent(); Object other$voteContent = other.getVoteContent(); if (this$voteContent == null ? other$voteContent != null : !this$voteContent.equals(other$voteContent)) return false; Object this$voteSubject = getVoteSubject(); Object other$voteSubject = other.getVoteSubject(); return this$voteSubject == null ? other$voteSubject == null : this$voteSubject.equals(other$voteSubject); } 
/* 13 */   protected boolean canEqual(Object other) { return other instanceof VoteTo; } 
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $voteSid = getVoteSid(); result = result * 59 + ($voteSid == null ? 0 : $voteSid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $createdby = getCreatedby(); result = result * 59 + ($createdby == null ? 0 : $createdby.hashCode()); Object $dueDate = getDueDate(); result = result * 59 + ($dueDate == null ? 0 : $dueDate.hashCode()); Object $modifiedOn = getModifiedOn(); result = result * 59 + ($modifiedOn == null ? 0 : $modifiedOn.hashCode()); Object $modifiedby = getModifiedby(); result = result * 59 + ($modifiedby == null ? 0 : $modifiedby.hashCode()); Object $voteContent = getVoteContent(); result = result * 59 + ($voteContent == null ? 0 : $voteContent.hashCode()); Object $voteSubject = getVoteSubject(); result = result * 59 + ($voteSubject == null ? 0 : $voteSubject.hashCode()); return result; } 
/* 13 */   public String toString() { return "VoteTo(voteSid=" + getVoteSid() + ", apartmentSid=" + getApartmentSid() + ", createdOn=" + getCreatedOn() + ", createdby=" + getCreatedby() + ", dueDate=" + getDueDate() + ", modifiedOn=" + getModifiedOn() + ", modifiedby=" + getModifiedby() + ", voteContent=" + getVoteContent() + ", voteSubject=" + getVoteSubject() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.vote.VoteTo
 * JD-Core Version:    0.6.2
 */