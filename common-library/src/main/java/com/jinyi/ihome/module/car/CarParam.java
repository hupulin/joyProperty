/*    */ package com.jinyi.ihome.module.car;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CarParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String carBrand;
/*    */   private String carColor;
/*    */   private String carFrameId;
/*    */   private String carImages;
/*    */   private String carModel;
/*    */   private String carNo;
/*    */   private String carOwner;
/*    */   private String carPhone;
/*    */   private String carYear;
/*    */   private String userSid;
/*    */   private String carPlace;
//      * 小区id

    private String apartmentSid;

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    /*    */
/*    */   public String getCarBrand()
/*    */   {
/* 17 */     return this.carBrand;
/*    */   }
/*    */   public String getCarColor() {
/* 20 */     return this.carColor;
/*    */   }
/*    */   public String getCarFrameId() {
/* 23 */     return this.carFrameId;
/*    */   }
/*    */   public String getCarImages() {
/* 26 */     return this.carImages;
/*    */   }
/*    */   public String getCarModel() {
/* 29 */     return this.carModel;
/*    */   }
/*    */   public String getCarNo() {
/* 32 */     return this.carNo;
/*    */   }
/*    */   public String getCarOwner() {
/* 35 */     return this.carOwner;
/*    */   }
/*    */   public String getCarPhone() {
/* 38 */     return this.carPhone;
/*    */   }
/*    */   public String getCarYear() {
/* 41 */     return this.carYear;
/*    */   }
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 46 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public String getCarPlace()
/*    */   {
/* 51 */     return this.carPlace;
/*    */   }
/*    */ 
/*    */   public void setCarBrand(String carBrand)
/*    */   {
/* 10 */     this.carBrand = carBrand; } 
/* 10 */   public void setCarColor(String carColor) { this.carColor = carColor; } 
/* 10 */   public void setCarFrameId(String carFrameId) { this.carFrameId = carFrameId; } 
/* 10 */   public void setCarImages(String carImages) { this.carImages = carImages; } 
/* 10 */   public void setCarModel(String carModel) { this.carModel = carModel; } 
/* 10 */   public void setCarNo(String carNo) { this.carNo = carNo; } 
/* 10 */   public void setCarOwner(String carOwner) { this.carOwner = carOwner; } 
/* 10 */   public void setCarPhone(String carPhone) { this.carPhone = carPhone; } 
/* 10 */   public void setCarYear(String carYear) { this.carYear = carYear; } 
/* 10 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 10 */   public void setCarPlace(String carPlace) { this.carPlace = carPlace; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof CarParam)) return false; CarParam other = (CarParam)o; if (!other.canEqual(this)) return false; Object this$carBrand = getCarBrand(); Object other$carBrand = other.getCarBrand(); if (this$carBrand == null ? other$carBrand != null : !this$carBrand.equals(other$carBrand)) return false; Object this$carColor = getCarColor(); Object other$carColor = other.getCarColor(); if (this$carColor == null ? other$carColor != null : !this$carColor.equals(other$carColor)) return false; Object this$carFrameId = getCarFrameId(); Object other$carFrameId = other.getCarFrameId(); if (this$carFrameId == null ? other$carFrameId != null : !this$carFrameId.equals(other$carFrameId)) return false; Object this$carImages = getCarImages(); Object other$carImages = other.getCarImages(); if (this$carImages == null ? other$carImages != null : !this$carImages.equals(other$carImages)) return false; Object this$carModel = getCarModel(); Object other$carModel = other.getCarModel(); if (this$carModel == null ? other$carModel != null : !this$carModel.equals(other$carModel)) return false; Object this$carNo = getCarNo(); Object other$carNo = other.getCarNo(); if (this$carNo == null ? other$carNo != null : !this$carNo.equals(other$carNo)) return false; Object this$carOwner = getCarOwner(); Object other$carOwner = other.getCarOwner(); if (this$carOwner == null ? other$carOwner != null : !this$carOwner.equals(other$carOwner)) return false; Object this$carPhone = getCarPhone(); Object other$carPhone = other.getCarPhone(); if (this$carPhone == null ? other$carPhone != null : !this$carPhone.equals(other$carPhone)) return false; Object this$carYear = getCarYear(); Object other$carYear = other.getCarYear(); if (this$carYear == null ? other$carYear != null : !this$carYear.equals(other$carYear)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$carPlace = getCarPlace(); Object other$carPlace = other.getCarPlace(); return this$carPlace == null ? other$carPlace == null : this$carPlace.equals(other$carPlace); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof CarParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $carBrand = getCarBrand(); result = result * 59 + ($carBrand == null ? 0 : $carBrand.hashCode()); Object $carColor = getCarColor(); result = result * 59 + ($carColor == null ? 0 : $carColor.hashCode()); Object $carFrameId = getCarFrameId(); result = result * 59 + ($carFrameId == null ? 0 : $carFrameId.hashCode()); Object $carImages = getCarImages(); result = result * 59 + ($carImages == null ? 0 : $carImages.hashCode()); Object $carModel = getCarModel(); result = result * 59 + ($carModel == null ? 0 : $carModel.hashCode()); Object $carNo = getCarNo(); result = result * 59 + ($carNo == null ? 0 : $carNo.hashCode()); Object $carOwner = getCarOwner(); result = result * 59 + ($carOwner == null ? 0 : $carOwner.hashCode()); Object $carPhone = getCarPhone(); result = result * 59 + ($carPhone == null ? 0 : $carPhone.hashCode()); Object $carYear = getCarYear(); result = result * 59 + ($carYear == null ? 0 : $carYear.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $carPlace = getCarPlace(); result = result * 59 + ($carPlace == null ? 0 : $carPlace.hashCode()); return result; } 
/* 10 */   public String toString() { return "CarParam(carBrand=" + getCarBrand() + ", carColor=" + getCarColor() + ", carFrameId=" + getCarFrameId() + ", carImages=" + getCarImages() + ", carModel=" + getCarModel() + ", carNo=" + getCarNo() + ", carOwner=" + getCarOwner() + ", carPhone=" + getCarPhone() + ", carYear=" + getCarYear() + ", userSid=" + getUserSid() + ", carPlace=" + getCarPlace() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.car.CarParam
 * JD-Core Version:    0.6.2
 */