/*    */ package com.jinyi.ihome.module.notice;
/*    */ 
/*    */ import com.jinyi.ihome.module.owner.UserBasicTo;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class HomeNoticeCommentTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String commentSid;
/*    */   private String noticeSid;
/*    */   private String commentContent;
/*    */   private Date commentDate;
/*    */   private UserBasicTo user;
/*    */ 
/*    */   public void setCommentSid(String commentSid)
/*    */   {
/* 18 */     this.commentSid = commentSid; } 
/* 19 */   public String getCommentSid() { return this.commentSid; }
/*    */ 
/*    */ 
/*    */   public void setNoticeSid(String noticeSid)
/*    */   {
/* 25 */     this.noticeSid = noticeSid; } 
/* 26 */   public String getNoticeSid() { return this.noticeSid; }
/*    */ 
/*    */ 
/*    */   public void setCommentContent(String commentContent)
/*    */   {
/* 32 */     this.commentContent = commentContent; } 
/* 33 */   public String getCommentContent() { return this.commentContent; }
/*    */ 
/*    */ 
/*    */   public void setCommentDate(Date commentDate)
/*    */   {
/* 39 */     this.commentDate = commentDate; } 
/* 40 */   public Date getCommentDate() { return this.commentDate; }
/*    */ 
/*    */ 
/*    */   public UserBasicTo getUser()
/*    */   {
/* 49 */     return this.user; } 
/* 50 */   public void setUser(UserBasicTo user) { this.user = user; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.notice.HomeNoticeCommentTo
 * JD-Core Version:    0.6.2
 */