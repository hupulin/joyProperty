/*    */ package com.jinyi.ihome.module.feedback;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class FeedbackReplyParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String feedbackSid;
/*    */   private String feedbackReply;
/*    */   private String replyUser;
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 12 */     if (o == this) return true; if (!(o instanceof FeedbackReplyParam)) return false; FeedbackReplyParam other = (FeedbackReplyParam)o; if (!other.canEqual(this)) return false; Object this$feedbackSid = getFeedbackSid(); Object other$feedbackSid = other.getFeedbackSid(); if (this$feedbackSid == null ? other$feedbackSid != null : !this$feedbackSid.equals(other$feedbackSid)) return false; Object this$feedbackReply = getFeedbackReply(); Object other$feedbackReply = other.getFeedbackReply(); if (this$feedbackReply == null ? other$feedbackReply != null : !this$feedbackReply.equals(other$feedbackReply)) return false; Object this$replyUser = getReplyUser(); Object other$replyUser = other.getReplyUser(); return this$replyUser == null ? other$replyUser == null : this$replyUser.equals(other$replyUser); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof FeedbackReplyParam; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $feedbackSid = getFeedbackSid(); result = result * 59 + ($feedbackSid == null ? 0 : $feedbackSid.hashCode()); Object $feedbackReply = getFeedbackReply(); result = result * 59 + ($feedbackReply == null ? 0 : $feedbackReply.hashCode()); Object $replyUser = getReplyUser(); result = result * 59 + ($replyUser == null ? 0 : $replyUser.hashCode()); return result; } 
/* 12 */   public String toString() { return "FeedbackReplyParam(feedbackSid=" + getFeedbackSid() + ", feedbackReply=" + getFeedbackReply() + ", replyUser=" + getReplyUser() + ")"; }
/*    */ 
/*    */ 
/*    */   public void setFeedbackSid(String feedbackSid)
/*    */   {
/* 17 */     this.feedbackSid = feedbackSid; } 
/* 18 */   public String getFeedbackSid() { return this.feedbackSid; }
/*    */ 
/*    */ 
/*    */   public void setFeedbackReply(String feedbackReply)
/*    */   {
/* 24 */     this.feedbackReply = feedbackReply; } 
/* 25 */   public String getFeedbackReply() { return this.feedbackReply; }
/*    */ 
/*    */ 
/*    */   public void setReplyUser(String replyUser)
/*    */   {
/* 31 */     this.replyUser = replyUser; } 
/* 32 */   public String getReplyUser() { return this.replyUser; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.feedback.FeedbackReplyParam
 * JD-Core Version:    0.6.2
 */