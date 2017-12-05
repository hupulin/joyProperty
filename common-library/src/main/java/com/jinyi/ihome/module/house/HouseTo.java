/*     */ package com.jinyi.ihome.module.house;
/*     */ 
/*     */ import com.jinyi.ihome.module.apartment.ApartmentInfoTo;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
import java.util.List;

/*     */
/*     */ public class HouseTo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String propertySid;
/*     */   private String apartmentSid;
/*     */   private String apartmentName;
/*     */   private String apartmentPhone;
/*     */   private String houseRegion;
/*     */   private int houseSticky;
/*     */   private int houseChosen;
/*     */   private ApartmentInfoTo apartmentInfoTo;
/*     */   private String houseCity;
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
/*     */   private String houseDate;
/*     */   private int status;
/*     */   private String publishOwnerSid;
/*     */   private String publishOwnerNo;
/*     */   private Date publishTime;
/*     */   private List<HouseValueTypeTo>configHouseType;
            private List<HouseValueTypeTo>configHouseJD;
/*     */   public String getTitle()
/*     */   {
/* 162 */     String title = "";
/* 163 */     if ((this.apartmentName != null) && (this.apartmentName.length() > 0)) {
/* 164 */       title = this.apartmentName;
/*     */     }
/*     */ 
/* 167 */     String houseTypeStr = "";
/*     */ 
/* 169 */     String[] arr = this.houseType.split("-");
/* 170 */     if (arr.length > 0) {
/* 171 */       houseTypeStr = houseTypeStr + arr[0] + "室";
/*     */     }
/* 173 */     if (arr.length > 1) {
/* 174 */       houseTypeStr = houseTypeStr + arr[1] + "厅";
/*     */     }
/* 176 */     if (arr.length > 2) {
/* 177 */       houseTypeStr = houseTypeStr + arr[2] + "卫";
/*     */     }
/*     */ 
/* 180 */     if ((title == null) || (title.isEmpty()))
/* 181 */       title = houseTypeStr;
/*     */     else {
/* 183 */       title = title + " " + houseTypeStr;
/*     */     }
/*     */ 
/* 186 */     return title;
/*     */   }
/*     */ 
/*     */   public String getDashboardTitle()
/*     */   {
/* 195 */     String dashboardTitle = "";
/* 196 */     if (("0".equals(Integer.valueOf(this.publishType))) || ("1".equals(Integer.valueOf(this.publishType)))) {
/* 197 */       String houseTypeStr = "";
/* 198 */       String[] arr = this.houseType.split("-");
/* 199 */       if (arr.length > 0) {
/* 200 */         houseTypeStr = houseTypeStr + arr[0] + "室";
/*     */       }
/* 202 */       if (arr.length > 1) {
/* 203 */         houseTypeStr = houseTypeStr + arr[1] + "厅";
/*     */       }
/* 205 */       if (arr.length > 2) {
/* 206 */         houseTypeStr = houseTypeStr + arr[2] + "卫";
/*     */       }
/*     */ 
/* 209 */       if ("0".equals(Integer.valueOf(this.publishType)))
/* 210 */         dashboardTitle = this.housePrice.toString() + "万 " + houseTypeStr;
/*     */       else
/* 212 */         dashboardTitle = this.housePrice.toString() + "元/月 " + houseTypeStr;
/*     */     }
/* 214 */     else if ("2".equals(Integer.valueOf(this.publishType))) {
/* 215 */       dashboardTitle = this.housePrice.toString() + "元/月 " + this.rentType;
/*     */     }
/*     */ 
/* 218 */     return dashboardTitle;
/*     */   }
/*     */ 
/*     */   public String getHouseUnitPrice()
/*     */   {
/* 227 */     String unitPrice = "";
/* 228 */     if (("0".equals(Integer.valueOf(this.publishType))) && 
/* 229 */       (this.housePrice != null) && (this.houseArea != null)) {
/* 230 */       unitPrice = String.valueOf(this.housePrice.intValue() / this.houseArea.floatValue() * 10000.0F) + " 元 / ㎡";
/*     */     }
/*     */ 
/* 233 */     return unitPrice;
/*     */   }
/*     */ 
/*     */   public void setPropertySid(String propertySid)
/*     */   {
/*  13 */     this.propertySid = propertySid; } 
/*  13 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/*  13 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; } 
/*  13 */   public void setApartmentPhone(String apartmentPhone) { this.apartmentPhone = apartmentPhone; } 
/*  13 */   public void setHouseRegion(String houseRegion) { this.houseRegion = houseRegion; } 
/*  13 */   public void setHouseSticky(int houseSticky) { this.houseSticky = houseSticky; } 
/*  13 */   public void setHouseChosen(int houseChosen) { this.houseChosen = houseChosen; } 
/*  13 */   public void setApartmentInfoTo(ApartmentInfoTo apartmentInfoTo) { this.apartmentInfoTo = apartmentInfoTo; } 
/*  13 */   public void setHouseCity(String houseCity) { this.houseCity = houseCity; } 
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
/*  13 */   public void setHouseDate(String houseDate) { this.houseDate = houseDate; } 
/*  13 */   public void setStatus(int status) { this.status = status; } 
/*  13 */   public void setPublishOwnerSid(String publishOwnerSid) { this.publishOwnerSid = publishOwnerSid; } 
/*  13 */   public void setPublishOwnerNo(String publishOwnerNo) { this.publishOwnerNo = publishOwnerNo; } 
/*  13 */   public void setPublishTime(Date publishTime) { this.publishTime = publishTime; }
/*     */ 
/*     */ 
/*     */   public String getPropertySid()
/*     */   {
/*  22 */     return this.propertySid;
/*     */   }
/*     */ 
/*     */   public String getApartmentSid()
/*     */   {
/*  27 */     return this.apartmentSid;
/*     */   }
/*     */ 
/*     */   public String getApartmentName()
/*     */   {
/*  32 */     return this.apartmentName;
/*     */   }
/*     */ 
/*     */   public String getApartmentPhone()
/*     */   {
/*  37 */     return this.apartmentPhone;
/*     */   }
/*     */ 
/*     */   public String getHouseRegion()
/*     */   {
/*  42 */     return this.houseRegion;
/*     */   }
/*     */ 
/*     */   public int getHouseSticky()
/*     */   {
/*  47 */     return this.houseSticky;
/*     */   }
/*     */ 
/*     */   public int getHouseChosen()
/*     */   {
/*  53 */     return this.houseChosen;
/*     */   }
/*     */ 
/*     */   public ApartmentInfoTo getApartmentInfoTo()
/*     */   {
/*  58 */     return this.apartmentInfoTo;
/*     */   }
/*     */ 
/*     */   public String getHouseCity()
/*     */   {
/*  63 */     return this.houseCity;
/*     */   }
/*     */ 
/*     */   public int getPublishType()
/*     */   {
/*  69 */     return this.publishType;
/*     */   }
/*     */ 
/*     */   public String getRentType()
/*     */   {
/*  74 */     return this.rentType;
/*     */   }
/*     */ 
/*     */   public String getHouseYear()
/*     */   {
/*  79 */     return this.houseYear;
/*     */   }
/*     */ 
/*     */   public String getHouseType()
/*     */   {
/*  84 */     return this.houseType;
/*     */   }
/*     */ 
/*     */   public String getHouseOrientation()
/*     */   {
/*  89 */     return this.houseOrientation;
/*     */   }
/*     */ 
/*     */   public Float getHouseArea()
/*     */   {
/*  94 */     return this.houseArea;
/*     */   }
/*     */ 
/*     */   public String getHouseFloor()
/*     */   {
/*  99 */     return this.houseFloor;
/*     */   }
/*     */ 
/*     */   public String getHouseNo()
/*     */   {
/* 104 */     return this.houseNo;
/*     */   }
/*     */ 
/*     */   public String getHouseDecoration()
/*     */   {
/* 109 */     return this.houseDecoration;
/*     */   }
/*     */ 
/*     */   public String getHouseImages()
/*     */   {
/* 114 */     return this.houseImages;
/*     */   }
/*     */ 
/*     */   public String getHouseDesc()
/*     */   {
/* 119 */     return this.houseDesc;
/*     */   }
/*     */ 
/*     */   public Integer getHousePrice()
/*     */   {
/* 124 */     return this.housePrice;
/*     */   }
/*     */ 
/*     */   public String getHousePhone()
/*     */   {
/* 129 */     return this.housePhone;
/*     */   }
/*     */ 
/*     */   public String getHouseDate()
/*     */   {
/* 134 */     return this.houseDate;
/*     */   }
/*     */ 
/*     */   public int getStatus()
/*     */   {
/* 139 */     return this.status;
/*     */   }
/*     */ 
/*     */   public String getPublishOwnerSid()
/*     */   {
/* 144 */     return this.publishOwnerSid;
/*     */   }
/*     */ 
/*     */   public String getPublishOwnerNo()
/*     */   {
/* 149 */     return this.publishOwnerNo;
/*     */   }
/*     */ 
/*     */   public Date getPublishTime()
/*     */   {
/* 154 */     return this.publishTime;
/*     */   }

    public List<HouseValueTypeTo> getConfigHouseType() {
        return configHouseType;
    }

    public void setConfigHouseType(List<HouseValueTypeTo> configHouseType) {
        this.configHouseType = configHouseType;
    }

    public List<HouseValueTypeTo> getConfigHouseJD() {
        return configHouseJD;
    }

    public void setConfigHouseJD(List<HouseValueTypeTo> configHouseJD) {
        this.configHouseJD = configHouseJD;

    }
/*     */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.house.HouseTo
 * JD-Core Version:    0.6.2
 */