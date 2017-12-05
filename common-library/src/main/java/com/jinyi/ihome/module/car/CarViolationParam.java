/*    */ package com.jinyi.ihome.module.car;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CarViolationParam
/*    */   implements Serializable
/*    */ {
/*    */   private String carNo;
/*    */   private String apartmentSid;
/*    */   private String location;
/*    */   private String remark;
/*    */   private String images;
/*    */   private double lng;
/*    */   private double lat;
/*    */   private String userSid;
/*    */ 
/*    */   public String getCarNo()
/*    */   {
/* 13 */     return this.carNo; } 
/* 14 */   public String getApartmentSid() { return this.apartmentSid; } 
/* 15 */   public String getLocation() { return this.location; } 
/* 16 */   public String getRemark() { return this.remark; } 
/* 17 */   public String getImages() { return this.images; } 
/* 18 */   public double getLng() { return this.lng; } 
/* 19 */   public double getLat() { return this.lat; } 
/* 20 */   public String getUserSid() { return this.userSid; }
/*    */ 
/*    */ 
/*    */   public void setCarNo(String carNo)
/*    */   {
/* 10 */     this.carNo = carNo; } 
/* 10 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 10 */   public void setLocation(String location) { this.location = location; } 
/* 10 */   public void setRemark(String remark) { this.remark = remark; } 
/* 10 */   public void setImages(String images) { this.images = images; } 
/* 10 */   public void setLng(double lng) { this.lng = lng; } 
/* 10 */   public void setLat(double lat) { this.lat = lat; } 
/* 10 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof CarViolationParam)) return false; CarViolationParam other = (CarViolationParam)o; if (!other.canEqual(this)) return false; Object this$carNo = getCarNo(); Object other$carNo = other.getCarNo(); if (this$carNo == null ? other$carNo != null : !this$carNo.equals(other$carNo)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$location = getLocation(); Object other$location = other.getLocation(); if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false; Object this$remark = getRemark(); Object other$remark = other.getRemark(); if (this$remark == null ? other$remark != null : !this$remark.equals(other$remark)) return false; Object this$images = getImages(); Object other$images = other.getImages(); if (this$images == null ? other$images != null : !this$images.equals(other$images)) return false; if (Double.compare(getLng(), other.getLng()) != 0) return false; if (Double.compare(getLat(), other.getLat()) != 0) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); return this$userSid == null ? other$userSid == null : this$userSid.equals(other$userSid); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof CarViolationParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $carNo = getCarNo(); result = result * 59 + ($carNo == null ? 0 : $carNo.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $location = getLocation(); result = result * 59 + ($location == null ? 0 : $location.hashCode()); Object $remark = getRemark(); result = result * 59 + ($remark == null ? 0 : $remark.hashCode()); Object $images = getImages(); result = result * 59 + ($images == null ? 0 : $images.hashCode()); long $lng = Double.doubleToLongBits(getLng()); result = result * 59 + (int)($lng >>> 32 ^ $lng); long $lat = Double.doubleToLongBits(getLat()); result = result * 59 + (int)($lat >>> 32 ^ $lat); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); return result; } 
/* 10 */   public String toString() { return "CarViolationParam(carNo=" + getCarNo() + ", apartmentSid=" + getApartmentSid() + ", location=" + getLocation() + ", remark=" + getRemark() + ", images=" + getImages() + ", lng=" + getLng() + ", lat=" + getLat() + ", userSid=" + getUserSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.car.CarViolationParam
 * JD-Core Version:    0.6.2
 */