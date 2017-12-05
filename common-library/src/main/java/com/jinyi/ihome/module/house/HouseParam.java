/*     */ package com.jinyi.ihome.module.house;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class HouseParam
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String propertySid;
/*     */   private String apartmentSid;
/*     */   private String apartmentName;
/*     */   private int publishType;
/*     */   private String rentType;
/*     */   private String houseYear;
/*     */   private String houseType;
/*     */   private String houseOrientation;
/*     */   private Float houseArea;
/*     */   private String houseFloor;
/*     */   private String houseNo;
/*     */   private String houseDecoration;
/*     */   private String houseImages;
/*     */   private String houseDesc;
/*     */   private Integer housePrice;
/*     */   private String housePhone;
/*     */   private String publishOwnerSid;

    public String getConfigHouse() {
        return configHouse;
    }

    public void setConfigHouse(String configHouse) {
        this.configHouse = configHouse;
    }

    /*     */   private String configHouse;
/*     */   public void setPropertySid(String propertySid)
/*     */   {
/*  13 */     this.propertySid = propertySid; } 
/*  13 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/*  13 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; } 
/*  13 */   public void setPublishType(int publishType) { this.publishType = publishType; } 
/*  13 */   public void setRentType(String rentType) { this.rentType = rentType; } 
/*  13 */   public void setHouseYear(String houseYear) { this.houseYear = houseYear; } 
/*  13 */   public void setHouseType(String houseType) { this.houseType = houseType; } 
/*  13 */   public void setHouseOrientation(String houseOrientation) { this.houseOrientation = houseOrientation; } 
/*  13 */   public void setHouseArea(Float houseArea) { this.houseArea = houseArea; } 
/*  13 */   public void setHouseFloor(String houseFloor) { this.houseFloor = houseFloor; } 
/*  13 */   public void setHouseNo(String houseNo) { this.houseNo = houseNo; } 
/*  13 */   public void setHouseDecoration(String houseDecoration) { this.houseDecoration = houseDecoration; } 
/*  13 */   public void setHouseImages(String houseImages) { this.houseImages = houseImages; } 
/*  13 */   public void setHouseDesc(String houseDesc) { this.houseDesc = houseDesc; } 
/*  13 */   public void setHousePrice(Integer housePrice) { this.housePrice = housePrice; } 
/*  13 */   public void setHousePhone(String housePhone) { this.housePhone = housePhone; } 
/*  13 */   public void setPublishOwnerSid(String publishOwnerSid) { this.publishOwnerSid = publishOwnerSid; }
/*     */ 
/*     */ 
/*     */   public String getPropertySid()
/*     */   {
/*  23 */     return this.propertySid;
/*     */   }
/*     */ 
/*     */   public String getApartmentSid()
/*     */   {
/*  28 */     return this.apartmentSid;
/*     */   }
/*     */ 
/*     */   public String getApartmentName()
/*     */   {
/*  33 */     return this.apartmentName;
/*     */   }
/*     */ 
/*     */   public int getPublishType()
/*     */   {
/*  39 */     return this.publishType;
/*     */   }
/*     */ 
/*     */   public String getRentType()
/*     */   {
/*  44 */     return this.rentType;
/*     */   }
/*     */ 
/*     */   public String getHouseYear()
/*     */   {
/*  49 */     return this.houseYear;
/*     */   }
/*     */ 
/*     */   public String getHouseType()
/*     */   {
/*  54 */     return this.houseType;
/*     */   }
/*     */ 
/*     */   public String getHouseOrientation()
/*     */   {
/*  59 */     return this.houseOrientation;
/*     */   }
/*     */ 
/*     */   public Float getHouseArea()
/*     */   {
/*  64 */     return this.houseArea;
/*     */   }
/*     */ 
/*     */   public String getHouseFloor()
/*     */   {
/*  70 */     return this.houseFloor;
/*     */   }
/*     */ 
/*     */   public String getHouseNo()
/*     */   {
/*  76 */     return this.houseNo;
/*     */   }
/*     */ 
/*     */   public String getHouseDecoration()
/*     */   {
/*  81 */     return this.houseDecoration;
/*     */   }
/*     */ 
/*     */   public String getHouseImages()
/*     */   {
/*  86 */     return this.houseImages;
/*     */   }
/*     */ 
/*     */   public String getHouseDesc()
/*     */   {
/*  91 */     return this.houseDesc;
/*     */   }
/*     */ 
/*     */   public Integer getHousePrice()
/*     */   {
/*  96 */     return this.housePrice;
/*     */   }
/*     */ 
/*     */   public String getHousePhone()
/*     */   {
/* 101 */     return this.housePhone;
/*     */   }
/*     */ 
/*     */   public String getPublishOwnerSid()
/*     */   {
/* 106 */     return this.publishOwnerSid;
/*     */   }
/*     */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.house.HouseParam
 * JD-Core Version:    0.6.2
 */