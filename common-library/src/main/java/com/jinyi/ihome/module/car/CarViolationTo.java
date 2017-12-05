/*    */ package com.jinyi.ihome.module.car;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class CarViolationTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String violationsSid;
/*    */   private String apartmentName;
/*    */   private String carNo;
/*    */   private Date createdOn;
/*    */   private String createdby;
/*    */   private double lat;
/*    */   private double lng;
/*    */   private String packingLocation;
/*    */   private String violationsImages;
/*    */   private String violationsRemark;
/*    */ 
/*    */   public String getViolationsSid()
/*    */   {
/* 15 */     return this.violationsSid;
/*    */   }
/* 17 */   public String getApartmentName() { return this.apartmentName; } 
/*    */   public String getCarNo() {
/* 19 */     return this.carNo;
/*    */   }
/* 21 */   public Date getCreatedOn() { return this.createdOn; } 
/*    */   public String getCreatedby() {
/* 23 */     return this.createdby;
/*    */   }
/* 25 */   public double getLat() { return this.lat; } 
/*    */   public double getLng() {
/* 27 */     return this.lng;
/*    */   }
/* 29 */   public String getPackingLocation() { return this.packingLocation; } 
/*    */   public String getViolationsImages() {
/* 31 */     return this.violationsImages;
/*    */   }
/* 33 */   public String getViolationsRemark() { return this.violationsRemark; }
/*    */ 
/*    */ 
/*    */   public void setViolationsSid(String violationsSid)
/*    */   {
/* 11 */     this.violationsSid = violationsSid; } 
/* 11 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; } 
/* 11 */   public void setCarNo(String carNo) { this.carNo = carNo; } 
/* 11 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 11 */   public void setCreatedby(String createdby) { this.createdby = createdby; } 
/* 11 */   public void setLat(double lat) { this.lat = lat; } 
/* 11 */   public void setLng(double lng) { this.lng = lng; } 
/* 11 */   public void setPackingLocation(String packingLocation) { this.packingLocation = packingLocation; } 
/* 11 */   public void setViolationsImages(String violationsImages) { this.violationsImages = violationsImages; } 
/* 11 */   public void setViolationsRemark(String violationsRemark) { this.violationsRemark = violationsRemark; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof CarViolationTo)) return false; CarViolationTo other = (CarViolationTo)o; if (!other.canEqual(this)) return false; Object this$violationsSid = getViolationsSid(); Object other$violationsSid = other.getViolationsSid(); if (this$violationsSid == null ? other$violationsSid != null : !this$violationsSid.equals(other$violationsSid)) return false; Object this$apartmentName = getApartmentName(); Object other$apartmentName = other.getApartmentName(); if (this$apartmentName == null ? other$apartmentName != null : !this$apartmentName.equals(other$apartmentName)) return false; Object this$carNo = getCarNo(); Object other$carNo = other.getCarNo(); if (this$carNo == null ? other$carNo != null : !this$carNo.equals(other$carNo)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$createdby = getCreatedby(); Object other$createdby = other.getCreatedby(); if (this$createdby == null ? other$createdby != null : !this$createdby.equals(other$createdby)) return false; if (Double.compare(getLat(), other.getLat()) != 0) return false; if (Double.compare(getLng(), other.getLng()) != 0) return false; Object this$packingLocation = getPackingLocation(); Object other$packingLocation = other.getPackingLocation(); if (this$packingLocation == null ? other$packingLocation != null : !this$packingLocation.equals(other$packingLocation)) return false; Object this$violationsImages = getViolationsImages(); Object other$violationsImages = other.getViolationsImages(); if (this$violationsImages == null ? other$violationsImages != null : !this$violationsImages.equals(other$violationsImages)) return false; Object this$violationsRemark = getViolationsRemark(); Object other$violationsRemark = other.getViolationsRemark(); return this$violationsRemark == null ? other$violationsRemark == null : this$violationsRemark.equals(other$violationsRemark); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof CarViolationTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $violationsSid = getViolationsSid(); result = result * 59 + ($violationsSid == null ? 0 : $violationsSid.hashCode()); Object $apartmentName = getApartmentName(); result = result * 59 + ($apartmentName == null ? 0 : $apartmentName.hashCode()); Object $carNo = getCarNo(); result = result * 59 + ($carNo == null ? 0 : $carNo.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $createdby = getCreatedby(); result = result * 59 + ($createdby == null ? 0 : $createdby.hashCode()); long $lat = Double.doubleToLongBits(getLat()); result = result * 59 + (int)($lat >>> 32 ^ $lat); long $lng = Double.doubleToLongBits(getLng()); result = result * 59 + (int)($lng >>> 32 ^ $lng); Object $packingLocation = getPackingLocation(); result = result * 59 + ($packingLocation == null ? 0 : $packingLocation.hashCode()); Object $violationsImages = getViolationsImages(); result = result * 59 + ($violationsImages == null ? 0 : $violationsImages.hashCode()); Object $violationsRemark = getViolationsRemark(); result = result * 59 + ($violationsRemark == null ? 0 : $violationsRemark.hashCode()); return result; } 
/* 11 */   public String toString() { return "CarViolationTo(violationsSid=" + getViolationsSid() + ", apartmentName=" + getApartmentName() + ", carNo=" + getCarNo() + ", createdOn=" + getCreatedOn() + ", createdby=" + getCreatedby() + ", lat=" + getLat() + ", lng=" + getLng() + ", packingLocation=" + getPackingLocation() + ", violationsImages=" + getViolationsImages() + ", violationsRemark=" + getViolationsRemark() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.car.CarViolationTo
 * JD-Core Version:    0.6.2
 */