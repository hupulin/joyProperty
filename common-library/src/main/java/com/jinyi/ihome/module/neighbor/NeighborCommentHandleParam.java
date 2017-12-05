/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class NeighborCommentHandleParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String postSid;
/*    */   private String commentSid;
/*    */   private String userSid;
/*    */ 
/*    */   public String getPostSid()
/*    */   {
/* 14 */     return this.postSid; } 
/* 15 */   public String getCommentSid() { return this.commentSid; } 
/* 16 */   public String getUserSid() { return this.userSid; }
/*    */ 
/*    */ 
/*    */   public void setPostSid(String postSid)
/*    */   {
/* 10 */     this.postSid = postSid; } 
/* 10 */   public void setCommentSid(String commentSid) { this.commentSid = commentSid; } 
/* 10 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NeighborCommentHandleParam)) return false; NeighborCommentHandleParam other = (NeighborCommentHandleParam)o; if (!other.canEqual(this)) return false; Object this$postSid = getPostSid(); Object other$postSid = other.getPostSid(); if (this$postSid == null ? other$postSid != null : !this$postSid.equals(other$postSid)) return false; Object this$commentSid = getCommentSid(); Object other$commentSid = other.getCommentSid(); if (this$commentSid == null ? other$commentSid != null : !this$commentSid.equals(other$commentSid)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); return this$userSid == null ? other$userSid == null : this$userSid.equals(other$userSid); }
/* 10 */   protected boolean canEqual(Object other) { return other instanceof NeighborCommentHandleParam; }
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $postSid = getPostSid(); result = result * 59 + ($postSid == null ? 0 : $postSid.hashCode()); Object $commentSid = getCommentSid(); result = result * 59 + ($commentSid == null ? 0 : $commentSid.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "NeighborCommentHandleParam(postSid=" + getPostSid() + ", commentSid=" + getCommentSid() + ", userSid=" + getUserSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborCommentHandleParam
 * JD-Core Version:    0.6.2
 */