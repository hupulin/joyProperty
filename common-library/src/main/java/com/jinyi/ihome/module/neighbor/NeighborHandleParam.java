/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class NeighborHandleParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String postSid;
/*    */   private String userSid;
/*    */ 
/*    */   public String getPostSid()
/*    */   {
/* 17 */     return this.postSid;
/*    */   }
/*    */ 
/*    */   public String getUserSid() {
/* 21 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public void setPostSid(String postSid)
/*    */   {
/* 10 */     this.postSid = postSid; } 
/* 10 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NeighborHandleParam)) return false; NeighborHandleParam other = (NeighborHandleParam)o; if (!other.canEqual(this)) return false; Object this$postSid = getPostSid(); Object other$postSid = other.getPostSid(); if (this$postSid == null ? other$postSid != null : !this$postSid.equals(other$postSid)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); return this$userSid == null ? other$userSid == null : this$userSid.equals(other$userSid); }
/* 10 */   protected boolean canEqual(Object other) { return other instanceof NeighborHandleParam; }
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $postSid = getPostSid(); result = result * 59 + ($postSid == null ? 0 : $postSid.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "NeighborHandleParam(postSid=" + getPostSid() + ", userSid=" + getUserSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborHandleParam
 * JD-Core Version:    0.6.2
 */