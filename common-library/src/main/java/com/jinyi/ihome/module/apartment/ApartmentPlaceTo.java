/*    */ package com.jinyi.ihome.module.apartment;
/*    */ 
/*    */ import com.jinyi.ihome.module.basic.LocationTo;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ApartmentPlaceTo
/*    */   implements Serializable
/*    */ {
/*    */   private String name;
/*    */   private String address;
/*    */   private String uid;
/*    */   private String province;
/*    */   private String city;
/*    */   private LocationTo location;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 17 */     return this.name;
/*    */   }
/*    */ 
/*    */   public String getAddress() {
/* 21 */     return this.address;
/*    */   }
/*    */ 
/*    */   public String getUid() {
/* 25 */     return this.uid;
/*    */   }
/*    */ 
/*    */   public String getProvince() {
/* 29 */     return this.province;
/*    */   }
/*    */ 
/*    */   public String getCity() {
/* 33 */     return this.city;
/*    */   }
/*    */ 
/*    */   public LocationTo getLocation() {
/* 37 */     return this.location;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 12 */     this.name = name; } 
/* 12 */   public void setAddress(String address) { this.address = address; } 
/* 12 */   public void setUid(String uid) { this.uid = uid; } 
/* 12 */   public void setProvince(String province) { this.province = province; } 
/* 12 */   public void setCity(String city) { this.city = city; } 
/* 12 */   public void setLocation(LocationTo location) { this.location = location; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ApartmentPlaceTo)) return false; ApartmentPlaceTo other = (ApartmentPlaceTo)o; if (!other.canEqual(this)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$address = getAddress(); Object other$address = other.getAddress(); if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false; Object this$uid = getUid(); Object other$uid = other.getUid(); if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false; Object this$province = getProvince(); Object other$province = other.getProvince(); if (this$province == null ? other$province != null : !this$province.equals(other$province)) return false; Object this$city = getCity(); Object other$city = other.getCity(); if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false; Object this$location = getLocation(); Object other$location = other.getLocation(); return this$location == null ? other$location == null : this$location.equals(other$location); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof ApartmentPlaceTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $address = getAddress(); result = result * 59 + ($address == null ? 0 : $address.hashCode()); Object $uid = getUid(); result = result * 59 + ($uid == null ? 0 : $uid.hashCode()); Object $province = getProvince(); result = result * 59 + ($province == null ? 0 : $province.hashCode()); Object $city = getCity(); result = result * 59 + ($city == null ? 0 : $city.hashCode()); Object $location = getLocation(); result = result * 59 + ($location == null ? 0 : $location.hashCode()); return result; } 
/* 12 */   public String toString() { return "ApartmentPlaceTo(name=" + getName() + ", address=" + getAddress() + ", uid=" + getUid() + ", province=" + getProvince() + ", city=" + getCity() + ", location=" + getLocation() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.apartment.ApartmentPlaceTo
 * JD-Core Version:    0.6.2
 */