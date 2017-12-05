/*    */ package com.jinyi.ihome.module.apartment;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ApartmentSearchParam
/*    */   implements Serializable
/*    */ {
/*    */   public String keyword;
/*    */   public double lng;
/*    */   public double lat;
/*    */ 
/*    */   public String getKeyword()
/*    */   {
/* 13 */     return this.keyword;
/*    */   }
/* 15 */   public double getLng() { return this.lng; } 
/*    */   public double getLat() {
/* 17 */     return this.lat;
/*    */   }
/*    */ 
/*    */   public void setKeyword(String keyword)
/*    */   {
/* 10 */     this.keyword = keyword; } 
/* 10 */   public void setLng(double lng) { this.lng = lng; } 
/* 10 */   public void setLat(double lat) { this.lat = lat; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ApartmentSearchParam)) return false; ApartmentSearchParam other = (ApartmentSearchParam)o; if (!other.canEqual(this)) return false; Object this$keyword = getKeyword(); Object other$keyword = other.getKeyword(); if (this$keyword == null ? other$keyword != null : !this$keyword.equals(other$keyword)) return false; if (Double.compare(getLng(), other.getLng()) != 0) return false; return Double.compare(getLat(), other.getLat()) == 0; } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ApartmentSearchParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $keyword = getKeyword(); result = result * 59 + ($keyword == null ? 0 : $keyword.hashCode()); long $lng = Double.doubleToLongBits(getLng()); result = result * 59 + (int)($lng >>> 32 ^ $lng); long $lat = Double.doubleToLongBits(getLat()); result = result * 59 + (int)($lat >>> 32 ^ $lat); return result; } 
/* 10 */   public String toString() { return "ApartmentSearchParam(keyword=" + getKeyword() + ", lng=" + getLng() + ", lat=" + getLat() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.apartment.ApartmentSearchParam
 * JD-Core Version:    0.6.2
 */