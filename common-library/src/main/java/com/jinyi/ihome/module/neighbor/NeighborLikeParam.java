/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ @Deprecated
/*    */ public class NeighborLikeParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String postSid;
/*    */   private String likeOwnerSid;
/*    */ 
/*    */   public String getPostSid()
/*    */   {
/* 22 */     return this.postSid;
/*    */   }
/*    */ 
/*    */   public String getLikeOwnerSid() {
/* 26 */     return this.likeOwnerSid;
/*    */   }
/*    */ 
/*    */   public void setPostSid(String postSid)
/*    */   {
/* 14 */     this.postSid = postSid; } 
/* 14 */   public void setLikeOwnerSid(String likeOwnerSid) { this.likeOwnerSid = likeOwnerSid; } 
/* 14 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NeighborLikeParam)) return false; NeighborLikeParam other = (NeighborLikeParam)o; if (!other.canEqual(this)) return false; Object this$postSid = getPostSid(); Object other$postSid = other.getPostSid(); if (this$postSid == null ? other$postSid != null : !this$postSid.equals(other$postSid)) return false; Object this$likeOwnerSid = getLikeOwnerSid(); Object other$likeOwnerSid = other.getLikeOwnerSid(); return this$likeOwnerSid == null ? other$likeOwnerSid == null : this$likeOwnerSid.equals(other$likeOwnerSid); }
/* 14 */   protected boolean canEqual(Object other) { return other instanceof NeighborLikeParam; }
/* 14 */   public int hashCode() { int PRIME = 59; int result = 1; Object $postSid = getPostSid(); result = result * 59 + ($postSid == null ? 0 : $postSid.hashCode()); Object $likeOwnerSid = getLikeOwnerSid(); result = result * 59 + ($likeOwnerSid == null ? 0 : $likeOwnerSid.hashCode()); return result; } 
/* 14 */   public String toString() { return "NeighborLikeParam(postSid=" + getPostSid() + ", likeOwnerSid=" + getLikeOwnerSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborLikeParam
 * JD-Core Version:    0.6.2
 */