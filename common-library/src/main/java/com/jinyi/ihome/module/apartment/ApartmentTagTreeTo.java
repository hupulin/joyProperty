/*    */ package com.jinyi.ihome.module.apartment;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ApartmentTagTreeTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String tagSid;
/*    */   private String apartmentSid;
/*    */   private String apartmentTag;
/*    */   private String parentTag;
/*    */   private List<ApartmentTagTreeTo> childList;
/*    */ 
/*    */   public String getTagSid()
/*    */   {
/* 18 */     return this.tagSid;
/*    */   }
/* 20 */   public String getApartmentSid() { return this.apartmentSid; } 
/*    */   public String getApartmentTag() {
/* 22 */     return this.apartmentTag;
/*    */   }
/* 24 */   public String getParentTag() { return this.parentTag; } 
/*    */   public List<ApartmentTagTreeTo> getChildList() {
/* 26 */     return this.childList;
/*    */   }
/*    */ 
/*    */   public void setTagSid(String tagSid)
/*    */   {
/* 13 */     this.tagSid = tagSid; } 
/* 13 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 13 */   public void setApartmentTag(String apartmentTag) { this.apartmentTag = apartmentTag; } 
/* 13 */   public void setParentTag(String parentTag) { this.parentTag = parentTag; } 
/* 13 */   public void setChildList(List<ApartmentTagTreeTo> childList) { this.childList = childList; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.apartment.ApartmentTagTreeTo
 * JD-Core Version:    0.6.2
 */