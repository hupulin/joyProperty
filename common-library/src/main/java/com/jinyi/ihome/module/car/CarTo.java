/*    */ package com.jinyi.ihome.module.car;
/*    */ 
/*    */ import com.jinyi.ihome.module.visitor.VisitorCardTo;

import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CarTo
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
/*    */   private int violationQty;
/*    */   private List<CarPlaceTo> carPlaces;
/*    */   private List<Map<String, String>> contacts;
/*    */   private String carPlace;
    //小区ID
    public   String getCarApartmentId() {
        return carApartmentId;
    }public  void   setCarApartmentId(String carApartmentId) {
        this.carApartmentId = carApartmentId;
    }public  String getCarApartmentName() {
        return carApartmentName;
    }public  void   setCarApartmentName(String carApartmentName) {
        this.carApartmentName = carApartmentName;
    }private String carApartmentId;
    //小区名称
    private String carApartmentName;
    public int getIsYeZhu() {
        return isYeZhu;
    }

    public void setIsYeZhu(int isYeZhu) {
        this.isYeZhu = isYeZhu;
    }

    @Override
    public String toString() {
        return "CarTo{" +
                "carBrand='" + carBrand + '\'' +
                ", carColor='" + carColor + '\'' +
                ", carFrameId='" + carFrameId + '\'' +
                ", carImages='" + carImages + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carNo='" + carNo + '\'' +
                ", carOwner='" + carOwner + '\'' +
                ", carPhone='" + carPhone + '\'' +
                ", carYear='" + carYear + '\'' +
                ", violationQty=" + violationQty +
                ", carPlaces=" + carPlaces +
                ", contacts=" + contacts +
                ", carPlace='" + carPlace + '\'' +
                ", isYeZhu=" + isYeZhu +
                ", carRecords=" + carRecords +
                '}';
    }

    /**
     * 是否是业主（0：否；1：是）

     */
    private int isYeZhu;
    private  List<VisitorCardTo>  carRecords;
    public List<VisitorCardTo> getCarRecords() {
        return carRecords;
    }

    public void setCarRecords(List<VisitorCardTo> carRecords) {
        this.carRecords = carRecords;
    }


/*    */ 
/*    */   public String getCarBrand()
/*    */   {
/* 19 */     return this.carBrand;
/*    */   }
/*    */   public String getCarColor() {
/* 22 */     return this.carColor;
/*    */   }
/*    */   public String getCarFrameId() {
/* 25 */     return this.carFrameId;
/*    */   }
/*    */   public String getCarImages() {
/* 28 */     return this.carImages;
/*    */   }
/*    */   public String getCarModel() {
/* 31 */     return this.carModel;
/*    */   }
/*    */   public String getCarNo() {
/* 34 */     return this.carNo;
/*    */   }
/*    */   public String getCarOwner() {
/* 37 */     return this.carOwner;
/*    */   }
/*    */   public String getCarPhone() {
/* 40 */     return this.carPhone;
/*    */   }
/*    */   public String getCarYear() {
/* 43 */     return this.carYear;
/*    */   }
/*    */   public int getViolationQty() {
/* 46 */     return this.violationQty;
/*    */   }
/*    */   public List<CarPlaceTo> getCarPlaces() {
/* 49 */     return this.carPlaces;
/*    */   }
/*    */   public List<Map<String, String>> getContacts() {
/* 52 */     return this.contacts;
/*    */   }
/*    */ 
/*    */   public String getCarPlace()
/*    */   {
/* 57 */     return this.carPlace;
/*    */   }

    /*    */
/*    */   public void setCarBrand(String carBrand)
/*    */   {
/* 12 */     this.carBrand = carBrand; } 
/* 12 */   public void setCarColor(String carColor) { this.carColor = carColor; } 
/* 12 */   public void setCarFrameId(String carFrameId) { this.carFrameId = carFrameId; } 
/* 12 */   public void setCarImages(String carImages) { this.carImages = carImages; } 
/* 12 */   public void setCarModel(String carModel) { this.carModel = carModel; } 
/* 12 */   public void setCarNo(String carNo) { this.carNo = carNo; } 
/* 12 */   public void setCarOwner(String carOwner) { this.carOwner = carOwner; } 
/* 12 */   public void setCarPhone(String carPhone) { this.carPhone = carPhone; } 
/* 12 */   public void setCarYear(String carYear) { this.carYear = carYear; } 
/* 12 */   public void setViolationQty(int violationQty) { this.violationQty = violationQty; } 
/* 12 */   public void setCarPlaces(List<CarPlaceTo> carPlaces) { this.carPlaces = carPlaces; } 
/* 12 */   public void setContacts(List<Map<String, String>> contacts) { this.contacts = contacts; } 
/* 12 */   public void setCarPlace(String carPlace) { this.carPlace = carPlace; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof CarTo)) return false; CarTo other = (CarTo)o; if (!other.canEqual(this)) return false; Object this$carBrand = getCarBrand(); Object other$carBrand = other.getCarBrand(); if (this$carBrand == null ? other$carBrand != null : !this$carBrand.equals(other$carBrand)) return false; Object this$carColor = getCarColor(); Object other$carColor = other.getCarColor(); if (this$carColor == null ? other$carColor != null : !this$carColor.equals(other$carColor)) return false; Object this$carFrameId = getCarFrameId(); Object other$carFrameId = other.getCarFrameId(); if (this$carFrameId == null ? other$carFrameId != null : !this$carFrameId.equals(other$carFrameId)) return false; Object this$carImages = getCarImages(); Object other$carImages = other.getCarImages(); if (this$carImages == null ? other$carImages != null : !this$carImages.equals(other$carImages)) return false; Object this$carModel = getCarModel(); Object other$carModel = other.getCarModel(); if (this$carModel == null ? other$carModel != null : !this$carModel.equals(other$carModel)) return false; Object this$carNo = getCarNo(); Object other$carNo = other.getCarNo(); if (this$carNo == null ? other$carNo != null : !this$carNo.equals(other$carNo)) return false; Object this$carOwner = getCarOwner(); Object other$carOwner = other.getCarOwner(); if (this$carOwner == null ? other$carOwner != null : !this$carOwner.equals(other$carOwner)) return false; Object this$carPhone = getCarPhone(); Object other$carPhone = other.getCarPhone(); if (this$carPhone == null ? other$carPhone != null : !this$carPhone.equals(other$carPhone)) return false; Object this$carYear = getCarYear(); Object other$carYear = other.getCarYear(); if (this$carYear == null ? other$carYear != null : !this$carYear.equals(other$carYear)) return false; if (getViolationQty() != other.getViolationQty()) return false; Object this$carPlaces = getCarPlaces(); Object other$carPlaces = other.getCarPlaces(); if (this$carPlaces == null ? other$carPlaces != null : !this$carPlaces.equals(other$carPlaces)) return false; Object this$contacts = getContacts(); Object other$contacts = other.getContacts(); if (this$contacts == null ? other$contacts != null : !this$contacts.equals(other$contacts)) return false; Object this$carPlace = getCarPlace(); Object other$carPlace = other.getCarPlace(); return this$carPlace == null ? other$carPlace == null : this$carPlace.equals(other$carPlace); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof CarTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $carBrand = getCarBrand(); result = result * 59 + ($carBrand == null ? 0 : $carBrand.hashCode()); Object $carColor = getCarColor(); result = result * 59 + ($carColor == null ? 0 : $carColor.hashCode()); Object $carFrameId = getCarFrameId(); result = result * 59 + ($carFrameId == null ? 0 : $carFrameId.hashCode()); Object $carImages = getCarImages(); result = result * 59 + ($carImages == null ? 0 : $carImages.hashCode()); Object $carModel = getCarModel(); result = result * 59 + ($carModel == null ? 0 : $carModel.hashCode()); Object $carNo = getCarNo(); result = result * 59 + ($carNo == null ? 0 : $carNo.hashCode()); Object $carOwner = getCarOwner(); result = result * 59 + ($carOwner == null ? 0 : $carOwner.hashCode()); Object $carPhone = getCarPhone(); result = result * 59 + ($carPhone == null ? 0 : $carPhone.hashCode()); Object $carYear = getCarYear(); result = result * 59 + ($carYear == null ? 0 : $carYear.hashCode()); result = result * 59 + getViolationQty(); Object $carPlaces = getCarPlaces(); result = result * 59 + ($carPlaces == null ? 0 : $carPlaces.hashCode()); Object $contacts = getContacts(); result = result * 59 + ($contacts == null ? 0 : $contacts.hashCode()); Object $carPlace = getCarPlace(); result = result * 59 + ($carPlace == null ? 0 : $carPlace.hashCode()); return result; } 
/*    */
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.car.CarTo
 * JD-Core Version:    0.6.2
 */