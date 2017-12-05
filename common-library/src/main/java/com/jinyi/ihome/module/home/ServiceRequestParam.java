/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceRequestParam
/*    */   implements Serializable
/*    */ {
/*    */   private String apartmentSid;
/*    */   private String serviceCategory;
/*    */   private String typeSid;
/*    */   private String typeName;
/*    */   private String serviceImages;
/*    */   private String serviceDesc;
/*    */   private String ownerSid;
           private String serviceEmergenctStatus;
           private String carNo;
    private String positionName;
    private String illegallyParkedPostion;
    private String illegallyParkedNotion;







    public String getServiceEmergenctStatus() {
        return serviceEmergenctStatus;
    }

    public void setServiceEmergenctStatus(String serviceEmergenctStatus) {
        this.serviceEmergenctStatus = serviceEmergenctStatus;
    }

    /*    */   private String serviceAddress;
/*    */   private String serviceContact;
/*    */   private String servicePhone;
/*    */   private float servicePrice;
/*    */   private String serviceBookingTime;
/*    */   private int serviceQty;
/*    */   private String roomNo;
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setServiceCategory(String serviceCategory) { this.serviceCategory = serviceCategory; } 
/* 11 */   public void setTypeSid(String typeSid) { this.typeSid = typeSid; } 
/* 11 */   public void setTypeName(String typeName) { this.typeName = typeName; } 
/* 11 */   public void setServiceImages(String serviceImages) { this.serviceImages = serviceImages; } 
/* 11 */   public void setServiceDesc(String serviceDesc) { this.serviceDesc = serviceDesc; } 
/* 11 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 11 */   public void setServiceAddress(String serviceAddress) { this.serviceAddress = serviceAddress; } 
/* 11 */   public void setServiceContact(String serviceContact) { this.serviceContact = serviceContact; } 
/* 11 */   public void setServicePhone(String servicePhone) { this.servicePhone = servicePhone; } 
/* 11 */   public void setServicePrice(float servicePrice) { this.servicePrice = servicePrice; } 
/* 11 */   public void setServiceBookingTime(String serviceBookingTime) { this.serviceBookingTime = serviceBookingTime; } 
/* 11 */   public void setServiceQty(int serviceQty) { this.serviceQty = serviceQty; }
/*    */ 
/*    */

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /*    */   public String getApartmentSid()
/*    */   {
/* 18 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getServiceCategory()
/*    */   {
/* 30 */     return this.serviceCategory;
/*    */   }
/*    */ 
/*    */   public String getTypeSid()
/*    */   {
/* 35 */     return this.typeSid;
/*    */   }
/*    */ 
/*    */   public String getTypeName()
/*    */   {
/* 40 */     return this.typeName;
/*    */   }
/*    */ 
/*    */   public String getServiceImages()
/*    */   {
/* 45 */     return this.serviceImages;
/*    */   }
/*    */ 
/*    */   public String getServiceDesc()
/*    */   {
/* 50 */     return this.serviceDesc;
/*    */   }
/*    */ 
/*    */   public String getOwnerSid()
/*    */   {
/* 55 */     return this.ownerSid;
/*    */   }
/*    */ 
/*    */   public String getServiceAddress()
/*    */   {
/* 60 */     return this.serviceAddress;
/*    */   }
/*    */ 
/*    */   public String getServiceContact()
/*    */   {
/* 65 */     return this.serviceContact;
/*    */   }
/*    */ 
/*    */   public String getServicePhone()
/*    */   {
/* 70 */     return this.servicePhone;
/*    */   }
/*    */ 
/*    */   public float getServicePrice()
/*    */   {
/* 75 */     return this.servicePrice;
/*    */   }
/*    */ 
/*    */   public String getServiceBookingTime()
/*    */   {
/* 81 */     return this.serviceBookingTime;
/*    */   }
/*    */ 
/*    */   public int getServiceQty()
/*    */   {
/* 86 */     return this.serviceQty;
/*    */   }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getIllegallyParkedPostion() {
        return illegallyParkedPostion;
    }

    public void setIllegallyParkedPostion(String illegallyParkedPostion) {
        this.illegallyParkedPostion = illegallyParkedPostion;
    }

    public String getIllegallyParkedNotion() {
        return illegallyParkedNotion;
    }

    public void setIllegallyParkedNotion(String illegallyParkedNotion) {
        this.illegallyParkedNotion = illegallyParkedNotion;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "ServiceRequestParam{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", serviceCategory='" + serviceCategory + '\'' +
                ", typeSid='" + typeSid + '\'' +
                ", typeName='" + typeName + '\'' +
                ", serviceImages='" + serviceImages + '\'' +
                ", serviceDesc='" + serviceDesc + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", serviceAddress='" + serviceAddress + '\'' +
                ", serviceContact='" + serviceContact + '\'' +
                ", servicePhone='" + servicePhone + '\'' +
                ", servicePrice=" + servicePrice +
                ", serviceBookingTime='" + serviceBookingTime + '\'' +
                ", serviceQty=" + serviceQty +
                ", roomNo='" + roomNo + '\'' +
                '}';
    }
    /*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceRequestParam
 * JD-Core Version:    0.6.2
 */