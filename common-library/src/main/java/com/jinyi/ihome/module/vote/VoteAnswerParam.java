/*    */ package com.jinyi.ihome.module.vote;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class VoteAnswerParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String userSid;
/*    */   private String voteSid;
/*    */   private List<Map<String, String>> answerList;
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 17 */     return this.userSid;
/*    */   }
/* 19 */   public String getVoteSid() { return this.voteSid; }
/*    */ 
/*    */   public List<Map<String, String>> getAnswerList()
/*    */   {
/* 23 */     return this.answerList;
/*    */   }
/*    */ 
/*    */   public void setUserSid(String userSid)
/*    */   {
/* 13 */     this.userSid = userSid; } 
/* 13 */   public void setVoteSid(String voteSid) { this.voteSid = voteSid; } 
/* 13 */   public void setAnswerList(List<Map<String, String>> answerList) { this.answerList = answerList; } 
/* 13 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof VoteAnswerParam)) return false; VoteAnswerParam other = (VoteAnswerParam)o; if (!other.canEqual(this)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$voteSid = getVoteSid(); Object other$voteSid = other.getVoteSid(); if (this$voteSid == null ? other$voteSid != null : !this$voteSid.equals(other$voteSid)) return false; Object this$answerList = getAnswerList(); Object other$answerList = other.getAnswerList(); return this$answerList == null ? other$answerList == null : this$answerList.equals(other$answerList); } 
/* 13 */   protected boolean canEqual(Object other) { return other instanceof VoteAnswerParam; } 
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $voteSid = getVoteSid(); result = result * 59 + ($voteSid == null ? 0 : $voteSid.hashCode()); Object $answerList = getAnswerList(); result = result * 59 + ($answerList == null ? 0 : $answerList.hashCode()); return result; } 
/* 13 */   public String toString() { return "VoteAnswerParam(userSid=" + getUserSid() + ", voteSid=" + getVoteSid() + ", answerList=" + getAnswerList() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.vote.VoteAnswerParam
 * JD-Core Version:    0.6.2
 */