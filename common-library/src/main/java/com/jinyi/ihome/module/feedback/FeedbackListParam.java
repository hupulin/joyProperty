/*    */ package com.jinyi.ihome.module.feedback;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class FeedbackListParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int page;
/*    */   private String apartmentSid;
/*    */   private int feedbackType;
/*    */   private String userSid;
/*    */ 
/*    */   public void setPage(int page)
/*    */   {
/* 11 */     this.page = page; } 
/* 11 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 11 */   public void setFeedbackType(int feedbackType) { this.feedbackType = feedbackType; } 
/* 11 */   public void setUserSid(String userSid) { this.userSid = userSid; }
/*    */ 
/*    */ 
/*    */   public int getPage()
/*    */   {
/* 18 */     return this.page;
/*    */   }
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 23 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public int getFeedbackType()
/*    */   {
/* 28 */     return this.feedbackType;
/*    */   }
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 34 */     return this.userSid;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.feedback.FeedbackListParam
 * JD-Core Version:    0.6.2
 */