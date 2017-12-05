/*    */ package com.jinyi.ihome.module.notice;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class NoticeCommentParam
/*    */   implements Serializable
/*    */ {
/*    */   private String noticeSid;
/*    */   private String content;
/*    */   private String commentUserSid;
/*    */ 
/*    */   public String getNoticeSid()
/*    */   {
/* 15 */     return this.noticeSid;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 19 */     return this.content;
/*    */   }
/*    */ 
/*    */   public String getCommentUserSid() {
/* 23 */     return this.commentUserSid;
/*    */   }
/*    */ 
/*    */   public void setNoticeSid(String noticeSid)
/*    */   {
/* 10 */     this.noticeSid = noticeSid; } 
/* 10 */   public void setContent(String content) { this.content = content; } 
/* 10 */   public void setCommentUserSid(String commentUserSid) { this.commentUserSid = commentUserSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NoticeCommentParam)) return false; NoticeCommentParam other = (NoticeCommentParam)o; if (!other.canEqual(this)) return false; Object this$noticeSid = getNoticeSid(); Object other$noticeSid = other.getNoticeSid(); if (this$noticeSid == null ? other$noticeSid != null : !this$noticeSid.equals(other$noticeSid)) return false; Object this$content = getContent(); Object other$content = other.getContent(); if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false; Object this$commentUserSid = getCommentUserSid(); Object other$commentUserSid = other.getCommentUserSid(); return this$commentUserSid == null ? other$commentUserSid == null : this$commentUserSid.equals(other$commentUserSid); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof NoticeCommentParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $noticeSid = getNoticeSid(); result = result * 59 + ($noticeSid == null ? 0 : $noticeSid.hashCode()); Object $content = getContent(); result = result * 59 + ($content == null ? 0 : $content.hashCode()); Object $commentUserSid = getCommentUserSid(); result = result * 59 + ($commentUserSid == null ? 0 : $commentUserSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "NoticeCommentParam(noticeSid=" + getNoticeSid() + ", content=" + getContent() + ", commentUserSid=" + getCommentUserSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.notice.NoticeCommentParam
 * JD-Core Version:    0.6.2
 */