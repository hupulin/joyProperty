/*    */ package com.jinyi.ihome.module.apartment;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class ApartmentDetailTo extends ApartmentInfoTo
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentAddress;
/*    */   private String postCode;
/*    */   private int roomQty;
/*    */   private String groupName;
/*    */   private BigDecimal unitPrice;
/*    */ 
/*    */   public String getApartmentAddress()
/*    */   {
/* 18 */     return this.apartmentAddress;
/*    */   }
/* 20 */   public String getPostCode() { return this.postCode; } 
/*    */   public int getRoomQty() {
/* 22 */     return this.roomQty;
/*    */   }
/* 24 */   public String getGroupName() { return this.groupName; } 
/*    */   public BigDecimal getUnitPrice() {
/* 26 */     return this.unitPrice;
/*    */   }
/*    */ 
/*    */   public void setApartmentAddress(String apartmentAddress)
/*    */   {
/* 11 */     this.apartmentAddress = apartmentAddress; } 
/* 11 */   public void setPostCode(String postCode) { this.postCode = postCode; } 
/* 11 */   public void setRoomQty(int roomQty) { this.roomQty = roomQty; } 
/* 11 */   public void setGroupName(String groupName) { this.groupName = groupName; } 
/* 11 */   public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; } 
/* 11 */   public String toString() { return "ApartmentDetailTo(apartmentAddress=" + getApartmentAddress() + ", postCode=" + getPostCode() + ", roomQty=" + getRoomQty() + ", groupName=" + getGroupName() + ", unitPrice=" + getUnitPrice() + ")"; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ApartmentDetailTo)) return false; ApartmentDetailTo other = (ApartmentDetailTo)o; if (!other.canEqual(this)) return false; Object this$apartmentAddress = getApartmentAddress(); Object other$apartmentAddress = other.getApartmentAddress(); if (this$apartmentAddress == null ? other$apartmentAddress != null : !this$apartmentAddress.equals(other$apartmentAddress)) return false; Object this$postCode = getPostCode(); Object other$postCode = other.getPostCode(); if (this$postCode == null ? other$postCode != null : !this$postCode.equals(other$postCode)) return false; if (getRoomQty() != other.getRoomQty()) return false; Object this$groupName = getGroupName(); Object other$groupName = other.getGroupName(); if (this$groupName == null ? other$groupName != null : !this$groupName.equals(other$groupName)) return false; Object this$unitPrice = getUnitPrice(); Object other$unitPrice = other.getUnitPrice(); return this$unitPrice == null ? other$unitPrice == null : this$unitPrice.equals(other$unitPrice); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof ApartmentDetailTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentAddress = getApartmentAddress(); result = result * 59 + ($apartmentAddress == null ? 0 : $apartmentAddress.hashCode()); Object $postCode = getPostCode(); result = result * 59 + ($postCode == null ? 0 : $postCode.hashCode()); result = result * 59 + getRoomQty(); Object $groupName = getGroupName(); result = result * 59 + ($groupName == null ? 0 : $groupName.hashCode()); Object $unitPrice = getUnitPrice(); result = result * 59 + ($unitPrice == null ? 0 : $unitPrice.hashCode()); return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.apartment.ApartmentDetailTo
 * JD-Core Version:    0.6.2
 */