/*    */ package com.jinyi.ihome.module.guide;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceGuideParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String guideTitle;
/*    */   private String guideContent;
/*    */   private String createdBy;
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 15 */     this.apartmentSid = apartmentSid; } 
/* 16 */   public String getApartmentSid() { return this.apartmentSid; }
/*    */ 
/*    */ 
/*    */   public void setGuideTitle(String guideTitle)
/*    */   {
/* 22 */     this.guideTitle = guideTitle; } 
/* 23 */   public String getGuideTitle() { return this.guideTitle; }
/*    */ 
/*    */ 
/*    */   public void setGuideContent(String guideContent)
/*    */   {
/* 30 */     this.guideContent = guideContent; } 
/* 31 */   public String getGuideContent() { return this.guideContent; }
/*    */ 
/*    */ 
/*    */   public void setCreatedBy(String createdBy)
/*    */   {
/* 37 */     this.createdBy = createdBy; } 
/* 38 */   public String getCreatedBy() { return this.createdBy; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.guide.ServiceGuideParam
 * JD-Core Version:    0.6.2
 */