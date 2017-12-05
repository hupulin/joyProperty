/*    */ package com.jinyi.ihome.module.store;
/*    */ 
/*    */ import com.jinyi.ihome.module.owner.UserBasicTo;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class StoreCommentTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String commentSid;
/*    */   private String aroundSid;
/*    */   private String commentContent;
/*    */   private String commentType;
/*    */   private Date createdOn;
/*    */   private UserBasicTo userBasicTo;
/*    */ 
/*    */   public String getCommentSid()
/*    */   {
/* 17 */     return this.commentSid;
/*    */   }
/*    */   public String getAroundSid() {
/* 20 */     return this.aroundSid;
/*    */   }
/*    */   public String getCommentContent() {
/* 23 */     return this.commentContent;
/*    */   }
/*    */   public String getCommentType() {
/* 26 */     return this.commentType;
/*    */   }
/*    */   public Date getCreatedOn() {
/* 29 */     return this.createdOn;
/*    */   }
/*    */   public UserBasicTo getUserBasicTo() {
/* 32 */     return this.userBasicTo;
/*    */   }
/*    */ 
/*    */   public void setCommentSid(String commentSid)
/*    */   {
/* 13 */     this.commentSid = commentSid; } 
/* 13 */   public void setAroundSid(String aroundSid) { this.aroundSid = aroundSid; } 
/* 13 */   public void setCommentContent(String commentContent) { this.commentContent = commentContent; } 
/* 13 */   public void setCommentType(String commentType) { this.commentType = commentType; } 
/* 13 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 13 */   public void setUserBasicTo(UserBasicTo userBasicTo) { this.userBasicTo = userBasicTo; } 
/* 13 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreCommentTo)) return false; StoreCommentTo other = (StoreCommentTo)o; if (!other.canEqual(this)) return false; Object this$commentSid = getCommentSid(); Object other$commentSid = other.getCommentSid(); if (this$commentSid == null ? other$commentSid != null : !this$commentSid.equals(other$commentSid)) return false; Object this$aroundSid = getAroundSid(); Object other$aroundSid = other.getAroundSid(); if (this$aroundSid == null ? other$aroundSid != null : !this$aroundSid.equals(other$aroundSid)) return false; Object this$commentContent = getCommentContent(); Object other$commentContent = other.getCommentContent(); if (this$commentContent == null ? other$commentContent != null : !this$commentContent.equals(other$commentContent)) return false; Object this$commentType = getCommentType(); Object other$commentType = other.getCommentType(); if (this$commentType == null ? other$commentType != null : !this$commentType.equals(other$commentType)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$userBasicTo = getUserBasicTo(); Object other$userBasicTo = other.getUserBasicTo(); return this$userBasicTo == null ? other$userBasicTo == null : this$userBasicTo.equals(other$userBasicTo); } 
/* 13 */   protected boolean canEqual(Object other) { return other instanceof StoreCommentTo; } 
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $commentSid = getCommentSid(); result = result * 59 + ($commentSid == null ? 0 : $commentSid.hashCode()); Object $aroundSid = getAroundSid(); result = result * 59 + ($aroundSid == null ? 0 : $aroundSid.hashCode()); Object $commentContent = getCommentContent(); result = result * 59 + ($commentContent == null ? 0 : $commentContent.hashCode()); Object $commentType = getCommentType(); result = result * 59 + ($commentType == null ? 0 : $commentType.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $userBasicTo = getUserBasicTo(); result = result * 59 + ($userBasicTo == null ? 0 : $userBasicTo.hashCode()); return result; } 
/* 13 */   public String toString() { return "StoreCommentTo(commentSid=" + getCommentSid() + ", aroundSid=" + getAroundSid() + ", commentContent=" + getCommentContent() + ", commentType=" + getCommentType() + ", createdOn=" + getCreatedOn() + ", userBasicTo=" + getUserBasicTo() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.store.StoreCommentTo
 * JD-Core Version:    0.6.2
 */