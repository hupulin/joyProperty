/*    */ package com.jinyi.ihome.module.vote;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VoteItemParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String userSid;
/*    */   private String voteSid;
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 14 */     return this.userSid;
/*    */   }
/* 16 */   public String getVoteSid() { return this.voteSid; }
/*    */ 
/*    */ 
/*    */   public void setUserSid(String userSid)
/*    */   {
/* 10 */     this.userSid = userSid; } 
/* 10 */   public void setVoteSid(String voteSid) { this.voteSid = voteSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof VoteItemParam)) return false; VoteItemParam other = (VoteItemParam)o; if (!other.canEqual(this)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$voteSid = getVoteSid(); Object other$voteSid = other.getVoteSid(); return this$voteSid == null ? other$voteSid == null : this$voteSid.equals(other$voteSid); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof VoteItemParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $voteSid = getVoteSid(); result = result * 59 + ($voteSid == null ? 0 : $voteSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "VoteItemParam(userSid=" + getUserSid() + ", voteSid=" + getVoteSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.vote.VoteItemParam
 * JD-Core Version:    0.6.2
 */