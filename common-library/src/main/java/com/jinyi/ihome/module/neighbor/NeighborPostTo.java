/*     */ package com.jinyi.ihome.module.neighbor;
/*     */ 
/*     */

import com.jinyi.ihome.module.owner.UserBasicTo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*     */
/*     */
/*     */

/*     */
/*     */ public class NeighborPostTo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String postSid;
/*     */   private String apartmentSid;
/*     */   private String postTypeName;
/*     */   private String postContent;
/*     */   private String postImages;
/*     */   private UserBasicTo postOwner;
/*     */   private Date postTime;
/*     */   private String postTimeStr;
/*     */   private String postSubject;
            private String postUrl;
            private String topicTitle;
            private String refId;
            private int topicRemind;
/*     */   @Deprecated
/*     */   private List<NeighborCommentTo> commentList;
/*     */ 
/*     */   @Deprecated
/*     */   private List<NeighborLikeTo> likeList;
/*     */   private int postTag;
/*     */   private int postSticky;
/*     */   private Date stickyEndTime;
/*     */   private int countComment;
/*     */   private int countLike;
/*     */   private int countShare;
/*     */   private boolean userLike;
/*     */   private String subPostContent;
/*     */   public String getPostSid()
/*     */   {
/*  25 */     return this.postSid;
/*     */   }
/*     */ 
/*     */   public String getApartmentSid()
/*     */   {
/*  30 */     return this.apartmentSid;
/*     */   }
/*     */ 
/*     */   public String getPostTypeName()
/*     */   {
/*  35 */     return this.postTypeName;
/*     */   }
/*     */ 
/*     */   public String getPostContent()
/*     */   {
/*  40 */     return this.postContent;
/*     */   }
/*     */ 
/*     */   public String getPostImages()
/*     */   {
/*  45 */     return this.postImages;
/*     */   }
/*     */ 
/*     */   public UserBasicTo getPostOwner()
/*     */   {
/*  50 */     return this.postOwner;
/*     */   }
/*     */ 
/*     */   public Date getPostTime()
/*     */   {
/*  55 */     return this.postTime;
/*     */   }
/*     */ 
/*     */   public String getPostTimeStr()
/*     */   {
/*  60 */     return this.postTimeStr;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public List<NeighborCommentTo> getCommentList()
/*     */   {
/*  67 */     return this.commentList;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public List<NeighborLikeTo> getLikeList()
/*     */   {
/*  73 */     return this.likeList;
/*     */   }
/*     */ 
/*     */   public int getPostTag()
/*     */   {
/*  80 */     return this.postTag;
/*     */   }
/*     */

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    /*     */   public int getPostSticky()
/*     */   {
/*  87 */     return this.postSticky;
/*     */   }
/*     */ 
/*     */   public Date getStickyEndTime()
/*     */   {
/*  92 */     return this.stickyEndTime;
/*     */   }
/*     */ 
/*     */   public int getCountComment()
/*     */   {
/*  98 */     return this.countComment;
/*     */   }
/*     */ 
/*     */   public int getCountLike()
/*     */   {
/* 103 */     return this.countLike;
/*     */   }
/*     */ 
/*     */   public int getCountShare()
/*     */   {
/* 108 */     return this.countShare;
/*     */   }
/*     */ 
/*     */   public boolean isUserLike()
/*     */   {
/* 113 */     return this.userLike;
/*     */   }
/*     */ 
/*     */   public void setPostSid(String postSid)
/*     */   {
/*  17 */     this.postSid = postSid; } 
/*  17 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/*  17 */   public void setPostTypeName(String postTypeName) { this.postTypeName = postTypeName; } 
/*  17 */   public void setPostContent(String postContent) { this.postContent = postContent; } 
/*  17 */   public void setPostImages(String postImages) { this.postImages = postImages; } 
/*  17 */   public void setPostOwner(UserBasicTo postOwner) { this.postOwner = postOwner; }
/*  17 */   public void setPostTime(Date postTime) { this.postTime = postTime; } 
/*  17 */   public void setPostTimeStr(String postTimeStr) { this.postTimeStr = postTimeStr; }

    public String getPostSubject() {
        return postSubject;
    }

    public void setPostSubject(String postSubject) {
        this.postSubject = postSubject;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public int getTopicRemind() {
        return topicRemind;
    }

    public void setTopicRemind(int topicRemind) {
        this.topicRemind = topicRemind;
    }

    /*  17 */   @Deprecated
/*     */   public void setCommentList(List<NeighborCommentTo> commentList) { this.commentList = commentList; }
/*  17 */   @Deprecated
/*     */   public void setLikeList(List<NeighborLikeTo> likeList) { this.likeList = likeList; }
/*  17 */   public void setPostTag(int postTag) { this.postTag = postTag; } 
/*  17 */   public void setPostSticky(int postSticky) { this.postSticky = postSticky; } 
/*  17 */   public void setStickyEndTime(Date stickyEndTime) { this.stickyEndTime = stickyEndTime; } 
/*  17 */   public void setCountComment(int countComment) { this.countComment = countComment; } 
/*  17 */   public void setCountLike(int countLike) { this.countLike = countLike; } 
/*  17 */   public void setCountShare(int countShare) { this.countShare = countShare; } 
/*  17 */   public void setUserLike(boolean userLike) { this.userLike = userLike; } 
/*  17 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NeighborPostTo)) return false; NeighborPostTo other = (NeighborPostTo)o; if (!other.canEqual(this)) return false; Object this$postSid = getPostSid(); Object other$postSid = other.getPostSid(); if (this$postSid == null ? other$postSid != null : !this$postSid.equals(other$postSid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$postTypeName = getPostTypeName(); Object other$postTypeName = other.getPostTypeName(); if (this$postTypeName == null ? other$postTypeName != null : !this$postTypeName.equals(other$postTypeName)) return false; Object this$postContent = getPostContent(); Object other$postContent = other.getPostContent(); if (this$postContent == null ? other$postContent != null : !this$postContent.equals(other$postContent)) return false; Object this$postImages = getPostImages(); Object other$postImages = other.getPostImages(); if (this$postImages == null ? other$postImages != null : !this$postImages.equals(other$postImages)) return false; Object this$postOwner = getPostOwner(); Object other$postOwner = other.getPostOwner(); if (this$postOwner == null ? other$postOwner != null : !this$postOwner.equals(other$postOwner)) return false; Object this$postTime = getPostTime(); Object other$postTime = other.getPostTime(); if (this$postTime == null ? other$postTime != null : !this$postTime.equals(other$postTime)) return false; Object this$postTimeStr = getPostTimeStr(); Object other$postTimeStr = other.getPostTimeStr(); if (this$postTimeStr == null ? other$postTimeStr != null : !this$postTimeStr.equals(other$postTimeStr)) return false; Object this$commentList = getCommentList(); Object other$commentList = other.getCommentList(); if (this$commentList == null ? other$commentList != null : !this$commentList.equals(other$commentList)) return false; Object this$likeList = getLikeList(); Object other$likeList = other.getLikeList(); if (this$likeList == null ? other$likeList != null : !this$likeList.equals(other$likeList)) return false; if (getPostTag() != other.getPostTag()) return false; if (getPostSticky() != other.getPostSticky()) return false; Object this$stickyEndTime = getStickyEndTime(); Object other$stickyEndTime = other.getStickyEndTime(); if (this$stickyEndTime == null ? other$stickyEndTime != null : !this$stickyEndTime.equals(other$stickyEndTime)) return false; if (getCountComment() != other.getCountComment()) return false; if (getCountLike() != other.getCountLike()) return false; if (getCountShare() != other.getCountShare()) return false; return isUserLike() == other.isUserLike(); }
/*  17 */   protected boolean canEqual(Object other) { return other instanceof NeighborPostTo; }
/*  17 */   public int hashCode() { int PRIME = 59; int result = 1; Object $postSid = getPostSid(); result = result * 59 + ($postSid == null ? 0 : $postSid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $postTypeName = getPostTypeName(); result = result * 59 + ($postTypeName == null ? 0 : $postTypeName.hashCode()); Object $postContent = getPostContent(); result = result * 59 + ($postContent == null ? 0 : $postContent.hashCode()); Object $postImages = getPostImages(); result = result * 59 + ($postImages == null ? 0 : $postImages.hashCode()); Object $postOwner = getPostOwner(); result = result * 59 + ($postOwner == null ? 0 : $postOwner.hashCode()); Object $postTime = getPostTime(); result = result * 59 + ($postTime == null ? 0 : $postTime.hashCode()); Object $postTimeStr = getPostTimeStr(); result = result * 59 + ($postTimeStr == null ? 0 : $postTimeStr.hashCode()); Object $commentList = getCommentList(); result = result * 59 + ($commentList == null ? 0 : $commentList.hashCode()); Object $likeList = getLikeList(); result = result * 59 + ($likeList == null ? 0 : $likeList.hashCode()); result = result * 59 + getPostTag(); result = result * 59 + getPostSticky(); Object $stickyEndTime = getStickyEndTime(); result = result * 59 + ($stickyEndTime == null ? 0 : $stickyEndTime.hashCode()); result = result * 59 + getCountComment(); result = result * 59 + getCountLike(); result = result * 59 + getCountShare(); result = result * 59 + (isUserLike() ? 79 : 97); return result; } 
/*     */

    public String getSubPostContent() {
        return subPostContent;
    }

    public void setSubPostContent(String subPostContent) {
        this.subPostContent = subPostContent;
    }

    @Override
    public String toString() {
        return "NeighborPostTo{" +
                "postSid='" + postSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", postTypeName='" + postTypeName + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postImages='" + postImages + '\'' +
                ", postOwner=" + postOwner +
                ", postTime=" + postTime +
                ", postTimeStr='" + postTimeStr + '\'' +
                ", postSubject='" + postSubject + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", commentList=" + commentList +
                ", likeList=" + likeList +
                ", postTag=" + postTag +
                ", postSticky=" + postSticky +
                ", stickyEndTime=" + stickyEndTime +
                ", countComment=" + countComment +
                ", countLike=" + countLike +
                ", countShare=" + countShare +
                ", userLike=" + userLike +
                ", subPostContent='" + subPostContent + '\'' +
                '}';
    }
/*     */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborPostTo
 * JD-Core Version:    0.6.2
 */