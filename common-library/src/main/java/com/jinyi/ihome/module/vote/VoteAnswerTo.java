/*    */ package com.jinyi.ihome.module.vote;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VoteAnswerTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String voteAnswerSid;
/*    */   private String voteAnswer;
/*    */   private String voteItemSid;
/*    */ 
/*    */   public String getVoteAnswerSid()
/*    */   {
/* 14 */     return this.voteAnswerSid;
/*    */   }
/* 16 */   public String getVoteAnswer() { return this.voteAnswer; } 
/*    */   public String getVoteItemSid() {
/* 18 */     return this.voteItemSid;
/*    */   }
/*    */ 
/*    */   public void setVoteAnswerSid(String voteAnswerSid)
/*    */   {
/* 10 */     this.voteAnswerSid = voteAnswerSid; } 
/* 10 */   public void setVoteAnswer(String voteAnswer) { this.voteAnswer = voteAnswer; } 
/* 10 */   public void setVoteItemSid(String voteItemSid) { this.voteItemSid = voteItemSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof VoteAnswerTo)) return false; VoteAnswerTo other = (VoteAnswerTo)o; if (!other.canEqual(this)) return false; Object this$voteAnswerSid = getVoteAnswerSid(); Object other$voteAnswerSid = other.getVoteAnswerSid(); if (this$voteAnswerSid == null ? other$voteAnswerSid != null : !this$voteAnswerSid.equals(other$voteAnswerSid)) return false; Object this$voteAnswer = getVoteAnswer(); Object other$voteAnswer = other.getVoteAnswer(); if (this$voteAnswer == null ? other$voteAnswer != null : !this$voteAnswer.equals(other$voteAnswer)) return false; Object this$voteItemSid = getVoteItemSid(); Object other$voteItemSid = other.getVoteItemSid(); return this$voteItemSid == null ? other$voteItemSid == null : this$voteItemSid.equals(other$voteItemSid); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof VoteAnswerTo; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $voteAnswerSid = getVoteAnswerSid(); result = result * 59 + ($voteAnswerSid == null ? 0 : $voteAnswerSid.hashCode()); Object $voteAnswer = getVoteAnswer(); result = result * 59 + ($voteAnswer == null ? 0 : $voteAnswer.hashCode()); Object $voteItemSid = getVoteItemSid(); result = result * 59 + ($voteItemSid == null ? 0 : $voteItemSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "VoteAnswerTo(voteAnswerSid=" + getVoteAnswerSid() + ", voteAnswer=" + getVoteAnswer() + ", voteItemSid=" + getVoteItemSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.vote.VoteAnswerTo
 * JD-Core Version:    0.6.2
 */