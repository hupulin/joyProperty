/*    */ package com.jinyi.ihome.module.basic;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class LocationTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private double lat;
/*    */   private double lng;
/*    */ 
/*    */   public LocationTo()
/*    */   {
/*    */   }
/*    */ 
/*    */   public LocationTo(Double lat, Double lng)
/*    */   {
/* 34 */     if (lat != null) {
/* 35 */       this.lat = lat.doubleValue();
/*    */     }
/*    */ 
/* 38 */     if (lng != null)
/* 39 */       this.lng = lng.doubleValue();
/*    */   }
/*    */ 
/*    */   public double getLat()
/*    */   {
/* 20 */     return this.lat; } 
/* 21 */   public void setLat(double lat) { this.lat = lat; }
/*    */ 
/*    */ 
/*    */   public void setLng(double lng)
/*    */   {
/* 27 */     this.lng = lng; } 
/* 28 */   public double getLng() { return this.lng; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.basic.LocationTo
 * JD-Core Version:    0.6.2
 */