/*    */ package com.jinyi.ihome.module.house;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class HouseRequirementAddParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String houseArea;
/*    */   private String houseDecoration;
/*    */   private String houseFloor;
/*    */   private String houseOrientation;
/*    */   private String housePrice;
/*    */   private String houseRegion;
/*    */   private String houseType;
/*    */   private String requirementDesc;
/*    */   private String requirementPhone;
/*    */   private String houseAge;
/*    */   private int requirementStatus;
/*    */   private int requirementType;
/*    */   private String ownerSid;
/*    */ 
/*    */   public void setHouseArea(String houseArea)
/*    */   {
/* 11 */     this.houseArea = houseArea; } 
/* 11 */   public void setHouseDecoration(String houseDecoration) { this.houseDecoration = houseDecoration; } 
/* 11 */   public void setHouseFloor(String houseFloor) { this.houseFloor = houseFloor; } 
/* 11 */   public void setHouseOrientation(String houseOrientation) { this.houseOrientation = houseOrientation; } 
/* 11 */   public void setHousePrice(String housePrice) { this.housePrice = housePrice; } 
/* 11 */   public void setHouseRegion(String houseRegion) { this.houseRegion = houseRegion; } 
/* 11 */   public void setHouseType(String houseType) { this.houseType = houseType; } 
/* 11 */   public void setRequirementDesc(String requirementDesc) { this.requirementDesc = requirementDesc; } 
/* 11 */   public void setRequirementPhone(String requirementPhone) { this.requirementPhone = requirementPhone; } 
/* 11 */   public void setHouseAge(String houseAge) { this.houseAge = houseAge; } 
/* 11 */   public void setRequirementStatus(int requirementStatus) { this.requirementStatus = requirementStatus; } 
/* 11 */   public void setRequirementType(int requirementType) { this.requirementType = requirementType; } 
/* 11 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; }
/*    */ 
/*    */ 
/*    */   public String getHouseArea()
/*    */   {
/* 16 */     return this.houseArea;
/*    */   }
/* 18 */   public String getHouseDecoration() { return this.houseDecoration; } 
/*    */   public String getHouseFloor() {
/* 20 */     return this.houseFloor;
/*    */   }
/* 22 */   public String getHouseOrientation() { return this.houseOrientation; } 
/*    */   public String getHousePrice() {
/* 24 */     return this.housePrice;
/*    */   }
/* 26 */   public String getHouseRegion() { return this.houseRegion; } 
/*    */   public String getHouseType() {
/* 28 */     return this.houseType;
/*    */   }
/* 30 */   public String getRequirementDesc() { return this.requirementDesc; } 
/*    */   public String getRequirementPhone() {
/* 32 */     return this.requirementPhone;
/*    */   }
/* 34 */   public String getHouseAge() { return this.houseAge; } 
/*    */   public int getRequirementStatus() {
/* 36 */     return this.requirementStatus;
/*    */   }
/* 38 */   public int getRequirementType() { return this.requirementType; } 
/*    */   public String getOwnerSid() {
/* 40 */     return this.ownerSid;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.house.HouseRequirementAddParam
 * JD-Core Version:    0.6.2
 */