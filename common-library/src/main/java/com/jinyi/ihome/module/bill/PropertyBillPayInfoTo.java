/*    */ package com.jinyi.ihome.module.bill;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PropertyBillPayInfoTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   private String money;
/*    */   private String roomNo;
/*    */   private String apartmentName;
/*    */   private List<PayPartnerTo> payPartners;
/*    */ 
/*    */   public String getName()
/*    */   {
/* 16 */     return this.name; } 
/* 17 */   public String getMoney() { return this.money; } 
/* 18 */   public String getRoomNo() { return this.roomNo; } 
/* 19 */   public String getApartmentName() { return this.apartmentName; }
/*    */ 
/*    */ 
/*    */   public List<PayPartnerTo> getPayPartners()
/*    */   {
/* 24 */     return this.payPartners;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 11 */     this.name = name; } 
/* 11 */   public void setMoney(String money) { this.money = money; } 
/* 11 */   public void setRoomNo(String roomNo) { this.roomNo = roomNo; } 
/* 11 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; } 
/* 11 */   public void setPayPartners(List<PayPartnerTo> payPartners) { this.payPartners = payPartners; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof PropertyBillPayInfoTo)) return false; PropertyBillPayInfoTo other = (PropertyBillPayInfoTo)o; if (!other.canEqual(this)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$money = getMoney(); Object other$money = other.getMoney(); if (this$money == null ? other$money != null : !this$money.equals(other$money)) return false; Object this$roomNo = getRoomNo(); Object other$roomNo = other.getRoomNo(); if (this$roomNo == null ? other$roomNo != null : !this$roomNo.equals(other$roomNo)) return false; Object this$apartmentName = getApartmentName(); Object other$apartmentName = other.getApartmentName(); if (this$apartmentName == null ? other$apartmentName != null : !this$apartmentName.equals(other$apartmentName)) return false; Object this$payPartners = getPayPartners(); Object other$payPartners = other.getPayPartners(); return this$payPartners == null ? other$payPartners == null : this$payPartners.equals(other$payPartners); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof PropertyBillPayInfoTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $money = getMoney(); result = result * 59 + ($money == null ? 0 : $money.hashCode()); Object $roomNo = getRoomNo(); result = result * 59 + ($roomNo == null ? 0 : $roomNo.hashCode()); Object $apartmentName = getApartmentName(); result = result * 59 + ($apartmentName == null ? 0 : $apartmentName.hashCode()); Object $payPartners = getPayPartners(); result = result * 59 + ($payPartners == null ? 0 : $payPartners.hashCode()); return result; } 
/* 11 */   public String toString() { return "PropertyBillPayInfoTo(name=" + getName() + ", money=" + getMoney() + ", roomNo=" + getRoomNo() + ", apartmentName=" + getApartmentName() + ", payPartners=" + getPayPartners() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.bill.PropertyBillPayInfoTo
 * JD-Core Version:    0.6.2
 */