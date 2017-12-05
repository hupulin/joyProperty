/*    */ package com.jinyi.ihome.module.vote;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class VoteItemTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String voteItemSid;
/*    */   private String voteItem;
/*    */   private int voteItemType;
/*    */   private String voteSid;
/*    */   private List<VoteAnswerTo> answerList;
/*    */ 
/*    */   public String getVoteItemSid()
/*    */   {
/* 17 */     return this.voteItemSid;
/*    */   }
/* 19 */   public String getVoteItem() { return this.voteItem; }
/*    */ 
/*    */ 
/*    */   public int getVoteItemType()
/*    */   {
/* 24 */     return this.voteItemType;
/*    */   }
/* 26 */   public String getVoteSid() { return this.voteSid; } 
/*    */   public List<VoteAnswerTo> getAnswerList() {
/* 28 */     return this.answerList;
/*    */   }
/*    */ 
/*    */   public void setVoteItemSid(String voteItemSid)
/*    */   {
/* 13 */     this.voteItemSid = voteItemSid; } 
/* 13 */   public void setVoteItem(String voteItem) { this.voteItem = voteItem; } 
/* 13 */   public void setVoteItemType(int voteItemType) { this.voteItemType = voteItemType; } 
/* 13 */   public void setVoteSid(String voteSid) { this.voteSid = voteSid; } 
/* 13 */   public void setAnswerList(List<VoteAnswerTo> answerList) { this.answerList = answerList; } 
/* 13 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof VoteItemTo)) return false; VoteItemTo other = (VoteItemTo)o; if (!other.canEqual(this)) return false; Object this$voteItemSid = getVoteItemSid(); Object other$voteItemSid = other.getVoteItemSid(); if (this$voteItemSid == null ? other$voteItemSid != null : !this$voteItemSid.equals(other$voteItemSid)) return false; Object this$voteItem = getVoteItem(); Object other$voteItem = other.getVoteItem(); if (this$voteItem == null ? other$voteItem != null : !this$voteItem.equals(other$voteItem)) return false; if (getVoteItemType() != other.getVoteItemType()) return false; Object this$voteSid = getVoteSid(); Object other$voteSid = other.getVoteSid(); if (this$voteSid == null ? other$voteSid != null : !this$voteSid.equals(other$voteSid)) return false; Object this$answerList = getAnswerList(); Object other$answerList = other.getAnswerList(); return this$answerList == null ? other$answerList == null : this$answerList.equals(other$answerList); } 
/* 13 */   protected boolean canEqual(Object other) { return other instanceof VoteItemTo; } 
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $voteItemSid = getVoteItemSid(); result = result * 59 + ($voteItemSid == null ? 0 : $voteItemSid.hashCode()); Object $voteItem = getVoteItem(); result = result * 59 + ($voteItem == null ? 0 : $voteItem.hashCode()); result = result * 59 + getVoteItemType(); Object $voteSid = getVoteSid(); result = result * 59 + ($voteSid == null ? 0 : $voteSid.hashCode()); Object $answerList = getAnswerList(); result = result * 59 + ($answerList == null ? 0 : $answerList.hashCode()); return result; } 
/* 13 */   public String toString() { return "VoteItemTo(voteItemSid=" + getVoteItemSid() + ", voteItem=" + getVoteItem() + ", voteItemType=" + getVoteItemType() + ", voteSid=" + getVoteSid() + ", answerList=" + getAnswerList() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.vote.VoteItemTo
 * JD-Core Version:    0.6.2
 */