/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class StoreCategoryTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String categoryCode;
/*    */   private String categoryImage;
/*    */   private String categoryName;
/*    */ 
/*    */   public String getCategoryCode()
/*    */   {
/* 15 */     return this.categoryCode;
/*    */   }
/* 17 */   public String getCategoryImage() { return this.categoryImage; } 
/*    */   public String getCategoryName() {
/* 19 */     return this.categoryName;
/*    */   }
/*    */ 
/*    */   public void setCategoryCode(String categoryCode)
/*    */   {
/* 10 */     this.categoryCode = categoryCode; } 
/* 10 */   public void setCategoryImage(String categoryImage) { this.categoryImage = categoryImage; } 
/* 10 */   public void setCategoryName(String categoryName) { this.categoryName = categoryName; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreCategoryTo)) return false; StoreCategoryTo other = (StoreCategoryTo)o; if (!other.canEqual(this)) return false; Object this$categoryCode = getCategoryCode(); Object other$categoryCode = other.getCategoryCode(); if (this$categoryCode == null ? other$categoryCode != null : !this$categoryCode.equals(other$categoryCode)) return false; Object this$categoryImage = getCategoryImage(); Object other$categoryImage = other.getCategoryImage(); if (this$categoryImage == null ? other$categoryImage != null : !this$categoryImage.equals(other$categoryImage)) return false; Object this$categoryName = getCategoryName(); Object other$categoryName = other.getCategoryName(); return this$categoryName == null ? other$categoryName == null : this$categoryName.equals(other$categoryName); }
/* 10 */   protected boolean canEqual(Object other) { return other instanceof StoreCategoryTo; }
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $categoryCode = getCategoryCode(); result = result * 59 + ($categoryCode == null ? 0 : $categoryCode.hashCode()); Object $categoryImage = getCategoryImage(); result = result * 59 + ($categoryImage == null ? 0 : $categoryImage.hashCode()); Object $categoryName = getCategoryName(); result = result * 59 + ($categoryName == null ? 0 : $categoryName.hashCode()); return result; } 
/* 10 */   public String toString() { return "StoreCategoryTo(categoryCode=" + getCategoryCode() + ", categoryImage=" + getCategoryImage() + ", categoryName=" + getCategoryName() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreCategoryTo
 * JD-Core Version:    0.6.2
 */