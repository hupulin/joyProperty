/*    */ package com.jinyi.ihome.module.house;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RequirementConfigTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private List<HouseRegionTo> regionList;
/*    */   private List<String> areaList;
/*    */   private List<String> oldYearLists;
/*    */   private List<String> rentList;
/*    */   private List<String> sellList;
/*    */ 
/*    */   public void setRegionList(List<HouseRegionTo> regionList)
/*    */   {
/* 12 */     this.regionList = regionList; } 
/* 12 */   public void setAreaList(List<String> areaList) { this.areaList = areaList; } 
/* 12 */   public void setOldYearLists(List<String> oldYearLists) { this.oldYearLists = oldYearLists; } 
/* 12 */   public void setRentList(List<String> rentList) { this.rentList = rentList; } 
/* 12 */   public void setSellList(List<String> sellList) { this.sellList = sellList; }
/*    */ 
/*    */ 
/*    */   public List<HouseRegionTo> getRegionList()
/*    */   {
/* 17 */     return this.regionList;
/*    */   }
/*    */   public List<String> getAreaList() {
/* 20 */     return this.areaList;
/*    */   }
/* 22 */   public List<String> getOldYearLists() { return this.oldYearLists; } 
/*    */   public List<String> getRentList() {
/* 24 */     return this.rentList;
/*    */   }
/* 26 */   public List<String> getSellList() { return this.sellList; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.house.RequirementConfigTo
 * JD-Core Version:    0.6.2
 */