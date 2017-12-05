/*    */ package com.jinyi.ihome.module.feedback;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class FeedbackParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private int feedbackType;
/*    */   private String feedbackContent;
/*    */   private String feedbackImages;
/*    */   private String feedbackContact;
/*    */   private String feedbackUserSid;
/*    */   private int sharedNeighbor;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 20 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public int getFeedbackType()
/*    */   {
/* 25 */     return this.feedbackType;
/*    */   }
/*    */ 
/*    */   public String getFeedbackContent()
/*    */   {
/* 30 */     return this.feedbackContent;
/*    */   }
/*    */ 
/*    */   public String getFeedbackImages()
/*    */   {
/* 35 */     return this.feedbackImages;
/*    */   }
/*    */ 
/*    */   public String getFeedbackContact()
/*    */   {
/* 40 */     return this.feedbackContact;
/*    */   }
/*    */ 
/*    */   public String getFeedbackUserSid()
/*    */   {
/* 45 */     return this.feedbackUserSid;
/*    */   }
/*    */ 
/*    */   public int getSharedNeighbor()
/*    */   {
/* 52 */     return this.sharedNeighbor;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 12 */     this.apartmentSid = apartmentSid; } 
/* 12 */   public void setFeedbackType(int feedbackType) { this.feedbackType = feedbackType; } 
/* 12 */   public void setFeedbackContent(String feedbackContent) { this.feedbackContent = feedbackContent; } 
/* 12 */   public void setFeedbackImages(String feedbackImages) { this.feedbackImages = feedbackImages; } 
/* 12 */   public void setFeedbackContact(String feedbackContact) { this.feedbackContact = feedbackContact; } 
/* 12 */   public void setFeedbackUserSid(String feedbackUserSid) { this.feedbackUserSid = feedbackUserSid; } 
/* 12 */   public void setSharedNeighbor(int sharedNeighbor) { this.sharedNeighbor = sharedNeighbor; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof FeedbackParam)) return false; FeedbackParam other = (FeedbackParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; if (getFeedbackType() != other.getFeedbackType()) return false; Object this$feedbackContent = getFeedbackContent(); Object other$feedbackContent = other.getFeedbackContent(); if (this$feedbackContent == null ? other$feedbackContent != null : !this$feedbackContent.equals(other$feedbackContent)) return false; Object this$feedbackImages = getFeedbackImages(); Object other$feedbackImages = other.getFeedbackImages(); if (this$feedbackImages == null ? other$feedbackImages != null : !this$feedbackImages.equals(other$feedbackImages)) return false; Object this$feedbackContact = getFeedbackContact(); Object other$feedbackContact = other.getFeedbackContact(); if (this$feedbackContact == null ? other$feedbackContact != null : !this$feedbackContact.equals(other$feedbackContact)) return false; Object this$feedbackUserSid = getFeedbackUserSid(); Object other$feedbackUserSid = other.getFeedbackUserSid(); if (this$feedbackUserSid == null ? other$feedbackUserSid != null : !this$feedbackUserSid.equals(other$feedbackUserSid)) return false; return getSharedNeighbor() == other.getSharedNeighbor(); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof FeedbackParam; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); result = result * 59 + getFeedbackType(); Object $feedbackContent = getFeedbackContent(); result = result * 59 + ($feedbackContent == null ? 0 : $feedbackContent.hashCode()); Object $feedbackImages = getFeedbackImages(); result = result * 59 + ($feedbackImages == null ? 0 : $feedbackImages.hashCode()); Object $feedbackContact = getFeedbackContact(); result = result * 59 + ($feedbackContact == null ? 0 : $feedbackContact.hashCode()); Object $feedbackUserSid = getFeedbackUserSid(); result = result * 59 + ($feedbackUserSid == null ? 0 : $feedbackUserSid.hashCode()); result = result * 59 + getSharedNeighbor(); return result; } 
/* 12 */   public String toString() { return "FeedbackParam(apartmentSid=" + getApartmentSid() + ", feedbackType=" + getFeedbackType() + ", feedbackContent=" + getFeedbackContent() + ", feedbackImages=" + getFeedbackImages() + ", feedbackContact=" + getFeedbackContact() + ", feedbackUserSid=" + getFeedbackUserSid() + ", sharedNeighbor=" + getSharedNeighbor() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.feedback.FeedbackParam
 * JD-Core Version:    0.6.2
 */