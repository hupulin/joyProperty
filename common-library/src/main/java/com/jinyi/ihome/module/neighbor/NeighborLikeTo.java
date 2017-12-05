/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */

import com.jinyi.ihome.module.owner.UserBasicTo;

import java.io.Serializable;
import java.util.Date;

/*    */
/*    */

/*    */
/*    */ public class NeighborLikeTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String likeSid;
/*    */   private String postSid;
/*    */   private UserBasicTo likeOwner;
/*    */   private Date likeTime;
/*    */   private NeighborPostTo postTo;

    public NeighborPostTo getPostTo() {
        return postTo;
    }

    public void setPostTo(NeighborPostTo postTo) {
        this.postTo = postTo;
    }

    /*    */   public String getLikeSid()
/*    */   {
/* 22 */     return this.likeSid;
/*    */   }
/*    */ 
/*    */   public String getPostSid()
/*    */   {
/* 27 */     return this.postSid;
/*    */   }
/*    */ 
/*    */   public UserBasicTo getLikeOwner()
/*    */   {
/* 32 */     return this.likeOwner;
/*    */   }
/*    */ 
/*    */   public Date getLikeTime()
/*    */   {
/* 37 */     return this.likeTime;
/*    */   }
/*    */ 
/*    */   public void setLikeSid(String likeSid)
/*    */   {
/* 15 */     this.likeSid = likeSid; } 
/* 15 */   public void setPostSid(String postSid) { this.postSid = postSid; } 
/* 15 */   public void setLikeOwner(UserBasicTo likeOwner) { this.likeOwner = likeOwner; }
/* 15 */   public void setLikeTime(Date likeTime) { this.likeTime = likeTime; } 
/* 15 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NeighborLikeTo)) return false; NeighborLikeTo other = (NeighborLikeTo)o; if (!other.canEqual(this)) return false; Object this$likeSid = getLikeSid(); Object other$likeSid = other.getLikeSid(); if (this$likeSid == null ? other$likeSid != null : !this$likeSid.equals(other$likeSid)) return false; Object this$postSid = getPostSid(); Object other$postSid = other.getPostSid(); if (this$postSid == null ? other$postSid != null : !this$postSid.equals(other$postSid)) return false; Object this$likeOwner = getLikeOwner(); Object other$likeOwner = other.getLikeOwner(); if (this$likeOwner == null ? other$likeOwner != null : !this$likeOwner.equals(other$likeOwner)) return false; Object this$likeTime = getLikeTime(); Object other$likeTime = other.getLikeTime(); return this$likeTime == null ? other$likeTime == null : this$likeTime.equals(other$likeTime); }
/* 15 */   protected boolean canEqual(Object other) { return other instanceof NeighborLikeTo; }
/* 15 */   public int hashCode() { int PRIME = 59; int result = 1; Object $likeSid = getLikeSid(); result = result * 59 + ($likeSid == null ? 0 : $likeSid.hashCode()); Object $postSid = getPostSid(); result = result * 59 + ($postSid == null ? 0 : $postSid.hashCode()); Object $likeOwner = getLikeOwner(); result = result * 59 + ($likeOwner == null ? 0 : $likeOwner.hashCode()); Object $likeTime = getLikeTime(); result = result * 59 + ($likeTime == null ? 0 : $likeTime.hashCode()); return result; } 
/* 15 */   public String toString() { return "NeighborLikeTo(likeSid=" + getLikeSid() + ", postSid=" + getPostSid() + ", likeOwner=" + getLikeOwner() + ", likeTime=" + getLikeTime() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborLikeTo
 * JD-Core Version:    0.6.2
 */