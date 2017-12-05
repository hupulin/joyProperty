/*    */ package com.jinyi.ihome.module.guide;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceGuideTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String guideSid;
/*    */   private String apartmentSid;
/*    */   private String guideTitle;
/*    */   private String guideAbstract;
/*    */   private String guideContent;
/*    */ 
/*    */   public void setGuideSid(String guideSid)
/*    */   {
/* 15 */     this.guideSid = guideSid; } 
/* 16 */   public String getGuideSid() { return this.guideSid; }
/*    */ 
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 22 */     this.apartmentSid = apartmentSid; } 
/* 23 */   public String getApartmentSid() { return this.apartmentSid; }
/*    */ 
/*    */ 
/*    */   public void setGuideTitle(String guideTitle)
/*    */   {
/* 29 */     this.guideTitle = guideTitle; } 
/* 30 */   public String getGuideTitle() { return this.guideTitle; }
/*    */ 
/*    */ 
/*    */   public void setGuideAbstract(String guideAbstract)
/*    */   {
/* 36 */     this.guideAbstract = guideAbstract; } 
/* 37 */   public String getGuideAbstract() { return this.guideAbstract; }
/*    */ 
/*    */ 
/*    */   public void setGuideContent(String guideContent)
/*    */   {
/* 43 */     this.guideContent = guideContent; } 
/* 44 */   public String getGuideContent() { return this.guideContent; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.guide.ServiceGuideTo
 * JD-Core Version:    0.6.2
 */