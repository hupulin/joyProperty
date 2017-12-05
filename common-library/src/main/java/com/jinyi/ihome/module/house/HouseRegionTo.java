/*    */ package com.jinyi.ihome.module.house;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HouseRegionTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String regionSid;
/*    */   private String parentRegion;
/*    */   private int regionIndex;
/*    */   private String regionName;
/*    */   private List<HouseRegionTo> childList;
/*    */ 
/*    */   public void setRegionSid(String regionSid)
/*    */   {
/* 12 */     this.regionSid = regionSid; } 
/* 12 */   public void setParentRegion(String parentRegion) { this.parentRegion = parentRegion; } 
/* 12 */   public void setRegionIndex(int regionIndex) { this.regionIndex = regionIndex; } 
/* 12 */   public void setRegionName(String regionName) { this.regionName = regionName; } 
/* 12 */   public void setChildList(List<HouseRegionTo> childList) { this.childList = childList; }
/*    */ 
/*    */   public String getRegionSid()
/*    */   {
/* 16 */     return this.regionSid;
/*    */   }
/* 18 */   public String getParentRegion() { return this.parentRegion; } 
/*    */   public int getRegionIndex() {
/* 20 */     return this.regionIndex;
/*    */   }
/* 22 */   public String getRegionName() { return this.regionName; } 
/*    */   public List<HouseRegionTo> getChildList() {
/* 24 */     return this.childList;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.house.HouseRegionTo
 * JD-Core Version:    0.6.2
 */