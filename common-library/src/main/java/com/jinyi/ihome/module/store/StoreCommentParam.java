/*    */ package com.jinyi.ihome.module.store;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class StoreCommentParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String aroundSid;
/*    */   private String commentContent;
/*    */   private String commentType;
/*    */   private String userSid;
/*    */ 
/*    */   public String getAroundSid()
/*    */   {
/* 15 */     return this.aroundSid;
/*    */   }
/*    */   public String getCommentContent() {
/* 18 */     return this.commentContent;
/*    */   }
/*    */   public String getCommentType() {
/* 21 */     return this.commentType;
/*    */   }
/*    */   public String getUserSid() {
/* 24 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public void setAroundSid(String aroundSid)
/*    */   {
/* 11 */     this.aroundSid = aroundSid; } 
/* 11 */   public void setCommentContent(String commentContent) { this.commentContent = commentContent; } 
/* 11 */   public void setCommentType(String commentType) { this.commentType = commentType; } 
/* 11 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreCommentParam)) return false; StoreCommentParam other = (StoreCommentParam)o; if (!other.canEqual(this)) return false; Object this$aroundSid = getAroundSid(); Object other$aroundSid = other.getAroundSid(); if (this$aroundSid == null ? other$aroundSid != null : !this$aroundSid.equals(other$aroundSid)) return false; Object this$commentContent = getCommentContent(); Object other$commentContent = other.getCommentContent(); if (this$commentContent == null ? other$commentContent != null : !this$commentContent.equals(other$commentContent)) return false; Object this$commentType = getCommentType(); Object other$commentType = other.getCommentType(); if (this$commentType == null ? other$commentType != null : !this$commentType.equals(other$commentType)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); return this$userSid == null ? other$userSid == null : this$userSid.equals(other$userSid); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof StoreCommentParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $aroundSid = getAroundSid(); result = result * 59 + ($aroundSid == null ? 0 : $aroundSid.hashCode()); Object $commentContent = getCommentContent(); result = result * 59 + ($commentContent == null ? 0 : $commentContent.hashCode()); Object $commentType = getCommentType(); result = result * 59 + ($commentType == null ? 0 : $commentType.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); return result; } 
/* 11 */   public String toString() { return "StoreCommentParam(aroundSid=" + getAroundSid() + ", commentContent=" + getCommentContent() + ", commentType=" + getCommentType() + ", userSid=" + getUserSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.store.StoreCommentParam
 * JD-Core Version:    0.6.2
 */