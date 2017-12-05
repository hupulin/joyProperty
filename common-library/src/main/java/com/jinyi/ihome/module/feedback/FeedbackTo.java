/*    */ package com.jinyi.ihome.module.feedback;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
/*    */ 
/*    */ public class FeedbackTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String feedbackSid;
/*    */   private String apartmentSid;
/*    */   private String apartmentName;
/*    */   private Integer feedbackType;
/*    */   private String feedbackContent;
/*    */   private String feedbackImages;
/*    */   private String feedbackContact;
/*    */   private String feedbackReply;
/*    */   private String feedbackUser;
/*    */   private Timestamp feedbackTime;
/*    */   private String replyUser;
/*    */   private Timestamp replyTime;
/*    */   private String feedbackOwnerNo;
/*    */   private String feedbackOwnerPhone;
/*    */ 
/*    */   public void setFeedbackSid(String feedbackSid)
/*    */   {
/* 13 */     this.feedbackSid = feedbackSid; } 
/* 13 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 13 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; } 
/* 13 */   public void setFeedbackType(Integer feedbackType) { this.feedbackType = feedbackType; } 
/* 13 */   public void setFeedbackContent(String feedbackContent) { this.feedbackContent = feedbackContent; } 
/* 13 */   public void setFeedbackImages(String feedbackImages) { this.feedbackImages = feedbackImages; } 
/* 13 */   public void setFeedbackContact(String feedbackContact) { this.feedbackContact = feedbackContact; } 
/* 13 */   public void setFeedbackReply(String feedbackReply) { this.feedbackReply = feedbackReply; } 
/* 13 */   public void setFeedbackUser(String feedbackUser) { this.feedbackUser = feedbackUser; } 
/* 13 */   public void setFeedbackTime(Timestamp feedbackTime) { this.feedbackTime = feedbackTime; } 
/* 13 */   public void setReplyUser(String replyUser) { this.replyUser = replyUser; } 
/* 13 */   public void setReplyTime(Timestamp replyTime) { this.replyTime = replyTime; }
/*    */ 
/*    */ 
/*    */   public String getFeedbackSid()
/*    */   {
/* 19 */     return this.feedbackSid;
/*    */   }
/* 21 */   public String getApartmentSid() { return this.apartmentSid; } 
/*    */   public String getApartmentName() {
/* 23 */     return this.apartmentName;
/*    */   }
/* 25 */   public Integer getFeedbackType() { return this.feedbackType; } 
/*    */   public String getFeedbackContent() {
/* 27 */     return this.feedbackContent;
/*    */   }
/* 29 */   public String getFeedbackImages() { return this.feedbackImages; } 
/*    */   public String getFeedbackContact() {
/* 31 */     return this.feedbackContact;
/*    */   }
/* 33 */   public String getFeedbackReply() { return this.feedbackReply; } 
/*    */   public String getFeedbackUser() {
/* 35 */     return this.feedbackUser;
/*    */   }
/* 37 */   public Timestamp getFeedbackTime() { return this.feedbackTime; } 
/*    */   public String getReplyUser() {
/* 39 */     return this.replyUser;
/*    */   }
/* 41 */   public Timestamp getReplyTime() { return this.replyTime; } 
/*    */   public void setFeedbackOwnerNo(String feedbackOwnerNo) {
/* 43 */     this.feedbackOwnerNo = feedbackOwnerNo; } 
/* 44 */   public String getFeedbackOwnerNo() { return this.feedbackOwnerNo; }
/*    */ 
/*    */   public void setFeedbackOwnerPhone(String feedbackOwnerPhone) {
/* 47 */     this.feedbackOwnerPhone = feedbackOwnerPhone; } 
/* 48 */   public String getFeedbackOwnerPhone() { return this.feedbackOwnerPhone; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.feedback.FeedbackTo
 * JD-Core Version:    0.6.2
 */