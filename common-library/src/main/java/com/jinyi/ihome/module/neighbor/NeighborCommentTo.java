/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */

import com.jinyi.ihome.module.owner.UserBasicTo;

import java.io.Serializable;
import java.util.Date;

/*    */
/*    */

/*    */
/*    */ public class NeighborCommentTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String commentSid;
/*    */   private String postSid;
/*    */   private String commentContent;
/*    */   private UserBasicTo atOwner;
/*    */   private UserBasicTo commentOwner;
           private UserBasicTo postOwner;
/*    */   private Date commentTime;
/*    */   private String commentTimeStr;
/*    */   private String postContent;
           private Date postTime;
           private String subComment;
           private boolean isExpand;
/*    */   public String getCommentSid()
/*    */   {
/* 22 */     return this.commentSid;
/*    */   }
/*    */ 
/*    */   public String getPostSid()
/*    */   {
/* 27 */     return this.postSid;
/*    */   }
/*    */ 
/*    */   public String getCommentContent()
/*    */   {
/* 32 */     return this.commentContent;
/*    */   }
/*    */ 
/*    */   public UserBasicTo getAtOwner()
/*    */   {
/* 37 */     return this.atOwner;
/*    */   }
/*    */ 
/*    */   public UserBasicTo getCommentOwner()
/*    */   {
/* 42 */     return this.commentOwner;
/*    */   }
/*    */ 
/*    */   public Date getCommentTime()
/*    */   {
/* 47 */     return this.commentTime;
/*    */   }
/*    */ 
/*    */   public String getCommentTimeStr()
/*    */   {
/* 52 */     return this.commentTimeStr;
/*    */   }
/*    */ 
/*    */   public void setCommentSid(String commentSid)
/*    */   {
/* 15 */     this.commentSid = commentSid; }

    public boolean isExpand() {
        return isExpand;
    }

    public void setIsExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    /* 15 */   public void setPostSid(String postSid) { this.postSid = postSid; }
/* 15 */   public void setCommentContent(String commentContent) { this.commentContent = commentContent; } 
/* 15 */   public void setAtOwner(UserBasicTo atOwner) { this.atOwner = atOwner; }
/* 15 */   public void setCommentOwner(UserBasicTo commentOwner) { this.commentOwner = commentOwner; }
/* 15 */   public void setCommentTime(Date commentTime) { this.commentTime = commentTime; } 
/* 15 */   public void setCommentTimeStr(String commentTimeStr) { this.commentTimeStr = commentTimeStr; } 
/* 15 */

    public String getPostContent() {
        return postContent;
    }

    public String getSubComment() {
        return subComment;
    }

    public void setSubComment(String subComment) {
        this.subComment = subComment;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public UserBasicTo getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(UserBasicTo postOwner) {
        this.postOwner = postOwner;
    }

    public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NeighborCommentTo)) return false; NeighborCommentTo other = (NeighborCommentTo)o; if (!other.canEqual(this)) return false; Object this$commentSid = getCommentSid(); Object other$commentSid = other.getCommentSid(); if (this$commentSid == null ? other$commentSid != null : !this$commentSid.equals(other$commentSid)) return false; Object this$postSid = getPostSid(); Object other$postSid = other.getPostSid(); if (this$postSid == null ? other$postSid != null : !this$postSid.equals(other$postSid)) return false; Object this$commentContent = getCommentContent(); Object other$commentContent = other.getCommentContent(); if (this$commentContent == null ? other$commentContent != null : !this$commentContent.equals(other$commentContent)) return false; Object this$atOwner = getAtOwner(); Object other$atOwner = other.getAtOwner(); if (this$atOwner == null ? other$atOwner != null : !this$atOwner.equals(other$atOwner)) return false; Object this$commentOwner = getCommentOwner(); Object other$commentOwner = other.getCommentOwner(); if (this$commentOwner == null ? other$commentOwner != null : !this$commentOwner.equals(other$commentOwner)) return false; Object this$commentTime = getCommentTime(); Object other$commentTime = other.getCommentTime(); if (this$commentTime == null ? other$commentTime != null : !this$commentTime.equals(other$commentTime)) return false; Object this$commentTimeStr = getCommentTimeStr(); Object other$commentTimeStr = other.getCommentTimeStr(); return this$commentTimeStr == null ? other$commentTimeStr == null : this$commentTimeStr.equals(other$commentTimeStr); }
/* 15 */   protected boolean canEqual(Object other) { return other instanceof NeighborCommentTo; }
/* 15 */   public int hashCode() { int PRIME = 59; int result = 1; Object $commentSid = getCommentSid(); result = result * 59 + ($commentSid == null ? 0 : $commentSid.hashCode()); Object $postSid = getPostSid(); result = result * 59 + ($postSid == null ? 0 : $postSid.hashCode()); Object $commentContent = getCommentContent(); result = result * 59 + ($commentContent == null ? 0 : $commentContent.hashCode()); Object $atOwner = getAtOwner(); result = result * 59 + ($atOwner == null ? 0 : $atOwner.hashCode()); Object $commentOwner = getCommentOwner(); result = result * 59 + ($commentOwner == null ? 0 : $commentOwner.hashCode()); Object $commentTime = getCommentTime(); result = result * 59 + ($commentTime == null ? 0 : $commentTime.hashCode()); Object $commentTimeStr = getCommentTimeStr(); result = result * 59 + ($commentTimeStr == null ? 0 : $commentTimeStr.hashCode()); return result; } 
/*    */

    @Override
    public String toString() {
        return "NeighborCommentTo{" +
                "commentSid='" + commentSid + '\'' +
                ", postSid='" + postSid + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", atOwner=" + atOwner +
                ", commentOwner=" + commentOwner +
                ", postOwner=" + postOwner +
                ", commentTime=" + commentTime +
                ", commentTimeStr='" + commentTimeStr + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postTime=" + postTime +
                ", subComment='" + subComment + '\'' +
                ", isExpand=" + isExpand +
                '}';
    }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborCommentTo
 * JD-Core Version:    0.6.2
 */