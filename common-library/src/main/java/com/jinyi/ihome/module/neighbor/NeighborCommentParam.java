/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class NeighborCommentParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String postSid;
/*    */   private String commentContent;
/*    */   private String atOwnerSid;
/*    */   private String commentOwnerSid;
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 13 */     if (o == this) return true; if (!(o instanceof NeighborCommentParam)) return false; NeighborCommentParam other = (NeighborCommentParam)o; if (!other.canEqual(this)) return false; Object this$postSid = getPostSid(); Object other$postSid = other.getPostSid(); if (this$postSid == null ? other$postSid != null : !this$postSid.equals(other$postSid)) return false; Object this$commentContent = getCommentContent(); Object other$commentContent = other.getCommentContent(); if (this$commentContent == null ? other$commentContent != null : !this$commentContent.equals(other$commentContent)) return false; Object this$atOwnerSid = getAtOwnerSid(); Object other$atOwnerSid = other.getAtOwnerSid(); if (this$atOwnerSid == null ? other$atOwnerSid != null : !this$atOwnerSid.equals(other$atOwnerSid)) return false; Object this$commentOwnerSid = getCommentOwnerSid(); Object other$commentOwnerSid = other.getCommentOwnerSid(); return this$commentOwnerSid == null ? other$commentOwnerSid == null : this$commentOwnerSid.equals(other$commentOwnerSid); }
/* 13 */   protected boolean canEqual(Object other) { return other instanceof NeighborCommentParam; }
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $postSid = getPostSid(); result = result * 59 + ($postSid == null ? 0 : $postSid.hashCode()); Object $commentContent = getCommentContent(); result = result * 59 + ($commentContent == null ? 0 : $commentContent.hashCode()); Object $atOwnerSid = getAtOwnerSid(); result = result * 59 + ($atOwnerSid == null ? 0 : $atOwnerSid.hashCode()); Object $commentOwnerSid = getCommentOwnerSid(); result = result * 59 + ($commentOwnerSid == null ? 0 : $commentOwnerSid.hashCode()); return result; } 
/* 13 */   public String toString() { return "NeighborCommentParam(postSid=" + getPostSid() + ", commentContent=" + getCommentContent() + ", atOwnerSid=" + getAtOwnerSid() + ", commentOwnerSid=" + getCommentOwnerSid() + ")"; }
/*    */ 
/*    */   public void setPostSid(String postSid)
/*    */   {
/* 17 */     this.postSid = postSid; } 
/* 18 */   public String getPostSid() { return this.postSid; }
/*    */ 
/*    */ 
/*    */   public void setCommentContent(String commentContent)
/*    */   {
/* 24 */     this.commentContent = commentContent; } 
/* 25 */   public String getCommentContent() { return this.commentContent; }
/*    */ 
/*    */ 
/*    */   public void setAtOwnerSid(String atOwnerSid)
/*    */   {
/* 31 */     this.atOwnerSid = atOwnerSid; } 
/* 32 */   public String getAtOwnerSid() { return this.atOwnerSid; }
/*    */ 
/*    */ 
/*    */   public void setCommentOwnerSid(String commentOwnerSid)
/*    */   {
/* 38 */     this.commentOwnerSid = commentOwnerSid; } 
/* 39 */   public String getCommentOwnerSid() { return this.commentOwnerSid; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborCommentParam
 * JD-Core Version:    0.6.2
 */