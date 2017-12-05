/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */

import com.jinyi.ihome.module.owner.UserBasicTo;

import java.io.Serializable;
import java.util.Date;

/*    */
/*    */

/*    */
/*    */ public class StoreItemCommentTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String commentSid;
/*    */   private UserBasicTo user;
/*    */   private String content;
/*    */   private Date createOn;
/*    */ 
/*    */   public String getCommentSid()
/*    */   {
/* 18 */     return this.commentSid;
/*    */   }
/* 20 */   public UserBasicTo getUser() { return this.user; }
/*    */ 
/*    */   public String getContent() {
/* 23 */     return this.content;
/*    */   }
/* 25 */   public Date getCreateOn() { return this.createOn; }
/*    */ 
/*    */ 
/*    */   public void setCommentSid(String commentSid)
/*    */   {
/* 12 */     this.commentSid = commentSid; } 
/* 12 */   public void setUser(UserBasicTo user) { this.user = user; }
/* 12 */   public void setContent(String content) { this.content = content; } 
/* 12 */   public void setCreateOn(Date createOn) { this.createOn = createOn; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreItemCommentTo)) return false; StoreItemCommentTo other = (StoreItemCommentTo)o; if (!other.canEqual(this)) return false; Object this$commentSid = getCommentSid(); Object other$commentSid = other.getCommentSid(); if (this$commentSid == null ? other$commentSid != null : !this$commentSid.equals(other$commentSid)) return false; Object this$user = getUser(); Object other$user = other.getUser(); if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false; Object this$content = getContent(); Object other$content = other.getContent(); if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false; Object this$createOn = getCreateOn(); Object other$createOn = other.getCreateOn(); return this$createOn == null ? other$createOn == null : this$createOn.equals(other$createOn); }
/* 12 */   protected boolean canEqual(Object other) { return other instanceof StoreItemCommentTo; }
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $commentSid = getCommentSid(); result = result * 59 + ($commentSid == null ? 0 : $commentSid.hashCode()); Object $user = getUser(); result = result * 59 + ($user == null ? 0 : $user.hashCode()); Object $content = getContent(); result = result * 59 + ($content == null ? 0 : $content.hashCode()); Object $createOn = getCreateOn(); result = result * 59 + ($createOn == null ? 0 : $createOn.hashCode()); return result; } 
/* 12 */   public String toString() { return "StoreItemCommentTo(commentSid=" + getCommentSid() + ", user=" + getUser() + ", content=" + getContent() + ", createOn=" + getCreateOn() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreItemCommentTo
 * JD-Core Version:    0.6.2
 */