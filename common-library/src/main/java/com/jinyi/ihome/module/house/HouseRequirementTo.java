/*    */ package com.jinyi.ihome.module.house;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class HouseRequirementTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String requirementSid;
/*    */   private Date createdOn;
/*    */   private String createdby;
/*    */   private String houseAge;
/*    */   private String houseArea;
/*    */   private String houseDecoration;
/*    */   private String houseFloor;
/*    */   private String houseOrientation;
/*    */   private String housePrice;
/*    */   private String houseRegion;
/*    */   private String houseType;
/*    */   private String requirementDesc;
/*    */   private String requirementPhone;
/*    */   private int requirementStatus;
/*    */   private int requirementType;
/*    */ 
/*    */   public String getTitle()
/*    */   {
/* 52 */     String title = this.requirementType == 0 ? "求购：" : "求租：";
/* 53 */     if ((this.houseRegion != null) && (this.houseRegion.length() > 0)) {
/* 54 */       title = title + this.houseRegion;
/*    */     }
/*    */ 
/* 57 */     String houseTypeStr = "";
/*    */ 
/* 59 */     String[] arr = this.houseType.split("-");
/* 60 */     if (arr.length > 0) {
/* 61 */       houseTypeStr = houseTypeStr + arr[0] + "室";
/*    */     }
/* 63 */     if (arr.length > 1) {
/* 64 */       houseTypeStr = houseTypeStr + arr[1] + "厅";
/*    */     }
/* 66 */     if (arr.length > 2) {
/* 67 */       houseTypeStr = houseTypeStr + arr[2] + "卫";
/*    */     }
/*    */ 
/* 70 */     if ((title == null) || (title.isEmpty()))
/* 71 */       title = houseTypeStr;
/*    */     else {
/* 73 */       title = title + " " + houseTypeStr;
/*    */     }
/*    */ 
/* 76 */     return title;
/*    */   }
/*    */ 
/*    */   public void setRequirementSid(String requirementSid)
/*    */   {
/* 12 */     this.requirementSid = requirementSid; } 
/* 12 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 12 */   public void setCreatedby(String createdby) { this.createdby = createdby; } 
/* 12 */   public void setHouseAge(String houseAge) { this.houseAge = houseAge; } 
/* 12 */   public void setHouseArea(String houseArea) { this.houseArea = houseArea; } 
/* 12 */   public void setHouseDecoration(String houseDecoration) { this.houseDecoration = houseDecoration; } 
/* 12 */   public void setHouseFloor(String houseFloor) { this.houseFloor = houseFloor; } 
/* 12 */   public void setHouseOrientation(String houseOrientation) { this.houseOrientation = houseOrientation; } 
/* 12 */   public void setHousePrice(String housePrice) { this.housePrice = housePrice; } 
/* 12 */   public void setHouseRegion(String houseRegion) { this.houseRegion = houseRegion; } 
/* 12 */   public void setHouseType(String houseType) { this.houseType = houseType; } 
/* 12 */   public void setRequirementDesc(String requirementDesc) { this.requirementDesc = requirementDesc; } 
/* 12 */   public void setRequirementPhone(String requirementPhone) { this.requirementPhone = requirementPhone; } 
/* 12 */   public void setRequirementStatus(int requirementStatus) { this.requirementStatus = requirementStatus; } 
/* 12 */   public void setRequirementType(int requirementType) { this.requirementType = requirementType; }
/*    */ 
/*    */   public String getRequirementSid()
/*    */   {
/* 16 */     return this.requirementSid;
/*    */   }
/* 18 */   public Date getCreatedOn() { return this.createdOn; } 
/*    */   public String getCreatedby() {
/* 20 */     return this.createdby;
/*    */   }
/* 22 */   public String getHouseAge() { return this.houseAge; } 
/*    */   public String getHouseArea() {
/* 24 */     return this.houseArea;
/*    */   }
/* 26 */   public String getHouseDecoration() { return this.houseDecoration; } 
/*    */   public String getHouseFloor() {
/* 28 */     return this.houseFloor;
/*    */   }
/* 30 */   public String getHouseOrientation() { return this.houseOrientation; } 
/*    */   public String getHousePrice() {
/* 32 */     return this.housePrice;
/*    */   }
/* 34 */   public String getHouseRegion() { return this.houseRegion; } 
/*    */   public String getHouseType() {
/* 36 */     return this.houseType;
/*    */   }
/* 38 */   public String getRequirementDesc() { return this.requirementDesc; } 
/*    */   public String getRequirementPhone() {
/* 40 */     return this.requirementPhone;
/*    */   }
/* 42 */   public int getRequirementStatus() { return this.requirementStatus; } 
/*    */   public int getRequirementType() {
/* 44 */     return this.requirementType;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.house.HouseRequirementTo
 * JD-Core Version:    0.6.2
 */