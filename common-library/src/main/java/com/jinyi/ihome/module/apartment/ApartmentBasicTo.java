/*    */ package com.jinyi.ihome.module.apartment;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class ApartmentBasicTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String apartmentName;
/*    */   private String image;
    /*    */
/*    */   public String getApartmentSid()
/*    */   {
/* 20 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getApartmentName()
/*    */   {
/* 26 */     return this.apartmentName;
/*    */   }
/*    */ 
/*    */   public String getImage()
/*    */   {
/* 31 */     return this.image;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 12 */     this.apartmentSid = apartmentSid; } 
/* 12 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; } 
/* 12 */   public void setImage(String image) { this.image = image; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ApartmentBasicTo)) return false; ApartmentBasicTo other = (ApartmentBasicTo)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$apartmentName = getApartmentName(); Object other$apartmentName = other.getApartmentName(); if (this$apartmentName == null ? other$apartmentName != null : !this$apartmentName.equals(other$apartmentName)) return false; Object this$image = getImage(); Object other$image = other.getImage(); return this$image == null ? other$image == null : this$image.equals(other$image); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof ApartmentBasicTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $apartmentName = getApartmentName(); result = result * 59 + ($apartmentName == null ? 0 : $apartmentName.hashCode()); Object $image = getImage(); result = result * 59 + ($image == null ? 0 : $image.hashCode()); return result; } 
/* 12 */   public String toString() { return "ApartmentBasicTo(apartmentSid=" + getApartmentSid() + ", apartmentName=" + getApartmentName() + ", image=" + getImage() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.apartment.ApartmentBasicTo
 * JD-Core Version:    0.6.2
 */