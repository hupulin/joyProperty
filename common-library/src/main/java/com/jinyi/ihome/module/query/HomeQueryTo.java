/*    */ package com.jinyi.ihome.module.query;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class HomeQueryTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String querySid;
/*    */   private String queryName;
/*    */   private String queryUrl;
/*    */   private String queryImage;
/*    */   private Integer paddingTop;
/*    */ 
/*    */   public void setQuerySid(String querySid)
/*    */   {
/* 15 */     this.querySid = querySid; } 
/* 16 */   public String getQuerySid() { return this.querySid; }
/*    */ 
/*    */ 
/*    */   public void setQueryName(String queryName)
/*    */   {
/* 22 */     this.queryName = queryName; } 
/* 23 */   public String getQueryName() { return this.queryName; }
/*    */ 
/*    */ 
/*    */   public void setQueryUrl(String queryUrl)
/*    */   {
/* 29 */     this.queryUrl = queryUrl; } 
/* 30 */   public String getQueryUrl() { return this.queryUrl; }
/*    */ 
/*    */ 
/*    */   public void setQueryImage(String queryImage)
/*    */   {
/* 36 */     this.queryImage = queryImage; } 
/* 37 */   public String getQueryImage() { return this.queryImage; }
/*    */ 
/*    */ 
/*    */   public void setPaddingTop(Integer paddingTop)
/*    */   {
/* 43 */     this.paddingTop = paddingTop; } 
/* 44 */   public Integer getPaddingTop() { return this.paddingTop; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.query.HomeQueryTo
 * JD-Core Version:    0.6.2
 */