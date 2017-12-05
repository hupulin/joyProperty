/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class NeighborPostParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String postType;
/*    */   private int postTag;
/*    */   private String postContent;
/*    */   private String postImages;
/*    */   private String postOwner;
/*    */   private String refId;
          private String topicTitle;
    /*    */   public String getApartmentSid()
/*    */   {
/* 21 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getPostType()
/*    */   {
/* 26 */     return this.postType;
/*    */   }
/*    */ 
/*    */   public int getPostTag()
/*    */   {
/* 33 */     return this.postTag;
/*    */   }
/*    */ 
/*    */   public String getPostContent()
/*    */   {
/* 38 */     return this.postContent;
/*    */   }
/*    */ 
/*    */   public String getPostImages()
/*    */   {
/* 43 */     return this.postImages;
/*    */   }
/*    */ 
/*    */   public String getPostOwner()
/*    */   {
/* 48 */     return this.postOwner;
/*    */   }
/*    */

    public String getRefId() {
        return refId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    /*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 13 */     this.apartmentSid = apartmentSid; } 
/* 13 */   public void setPostType(String postType) { this.postType = postType; } 
/* 13 */   public void setPostTag(int postTag) { this.postTag = postTag; } 
/* 13 */   public void setPostContent(String postContent) { this.postContent = postContent; } 
/* 13 */   public void setPostImages(String postImages) { this.postImages = postImages; } 
/* 13 */   public void setPostOwner(String postOwner) { this.postOwner = postOwner; } 
/* 13 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NeighborPostParam)) return false; NeighborPostParam other = (NeighborPostParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$postType = getPostType(); Object other$postType = other.getPostType(); if (this$postType == null ? other$postType != null : !this$postType.equals(other$postType)) return false; if (getPostTag() != other.getPostTag()) return false; Object this$postContent = getPostContent(); Object other$postContent = other.getPostContent(); if (this$postContent == null ? other$postContent != null : !this$postContent.equals(other$postContent)) return false; Object this$postImages = getPostImages(); Object other$postImages = other.getPostImages(); if (this$postImages == null ? other$postImages != null : !this$postImages.equals(other$postImages)) return false; Object this$postOwner = getPostOwner(); Object other$postOwner = other.getPostOwner(); return this$postOwner == null ? other$postOwner == null : this$postOwner.equals(other$postOwner); }
/* 13 */   protected boolean canEqual(Object other) { return other instanceof NeighborPostParam; }
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $postType = getPostType(); result = result * 59 + ($postType == null ? 0 : $postType.hashCode()); result = result * 59 + getPostTag(); Object $postContent = getPostContent(); result = result * 59 + ($postContent == null ? 0 : $postContent.hashCode()); Object $postImages = getPostImages(); result = result * 59 + ($postImages == null ? 0 : $postImages.hashCode()); Object $postOwner = getPostOwner(); result = result * 59 + ($postOwner == null ? 0 : $postOwner.hashCode()); return result; } 
/*    */

    @Override
    public String toString() {
        return "NeighborPostParam{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", postType='" + postType + '\'' +
                ", postTag=" + postTag +
                ", postContent='" + postContent + '\'' +
                ", postImages='" + postImages + '\'' +
                ", postOwner='" + postOwner + '\'' +
                ", refId='" + refId + '\'' +
                ", topicTitle='" + topicTitle + '\'' +
                '}';
    }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborPostParam
 * JD-Core Version:    0.6.2
 */