/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*    */
/*    */

/*    */
/*    */ public class StoreOrderMainTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String orderSid;
/*    */   private String apartmentSid;
/*    */   private Date createdOn;
/*    */   private float orderDiscountMoney;
/*    */   private String orderInfo;
/*    */   private float orderMoney;
/*    */   private String orderNo;
/*    */   private String orderRemark;
/*    */   private int orderStatus;
/*    */   private String ownerSid;
/*    */   private String address;
/*    */   private String phone;
/*    */   private List<StoreOrderDetailTo> details;
/*    */   private Date modifiedOn;
/*    */ 
/*    */   public String getOrderSid()
/*    */   {
/* 21 */     return this.orderSid;
/*    */   }
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 26 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public Date getCreatedOn()
/*    */   {
/* 31 */     return this.createdOn;
/*    */   }
/*    */ 
/*    */   public float getOrderDiscountMoney()
/*    */   {
/* 36 */     return this.orderDiscountMoney;
/*    */   }
/*    */ 
/*    */   public String getOrderInfo()
/*    */   {
/* 41 */     return this.orderInfo;
/*    */   }
/*    */ 
/*    */   public float getOrderMoney()
/*    */   {
/* 46 */     return this.orderMoney;
/*    */   }
/*    */ 
/*    */   public String getOrderNo()
/*    */   {
/* 51 */     return this.orderNo;
/*    */   }
/*    */ 
/*    */   public String getOrderRemark()
/*    */   {
/* 56 */     return this.orderRemark;
/*    */   }
/*    */   public int getOrderStatus() {
/* 59 */     return this.orderStatus;
/*    */   }
/* 61 */   public String getOwnerSid() { return this.ownerSid; } 
/*    */   public String getAddress() {
/* 63 */     return this.address;
/*    */   }
/* 65 */   public String getPhone() { return this.phone; } 
/*    */   public List<StoreOrderDetailTo> getDetails() {
/* 67 */     return this.details;
/*    */   }
/* 69 */   public Date getModifiedOn() { return this.modifiedOn; }
/*    */ 
/*    */ 
/*    */   public void setOrderSid(String orderSid)
/*    */   {
/* 14 */     this.orderSid = orderSid; } 
/* 14 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 14 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 14 */   public void setOrderDiscountMoney(float orderDiscountMoney) { this.orderDiscountMoney = orderDiscountMoney; } 
/* 14 */   public void setOrderInfo(String orderInfo) { this.orderInfo = orderInfo; } 
/* 14 */   public void setOrderMoney(float orderMoney) { this.orderMoney = orderMoney; } 
/* 14 */   public void setOrderNo(String orderNo) { this.orderNo = orderNo; } 
/* 14 */   public void setOrderRemark(String orderRemark) { this.orderRemark = orderRemark; } 
/* 14 */   public void setOrderStatus(int orderStatus) { this.orderStatus = orderStatus; } 
/* 14 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 14 */   public void setAddress(String address) { this.address = address; } 
/* 14 */   public void setPhone(String phone) { this.phone = phone; } 
/* 14 */   public void setDetails(List<StoreOrderDetailTo> details) { this.details = details; } 
/* 14 */   public void setModifiedOn(Date modifiedOn) { this.modifiedOn = modifiedOn; } 
/* 14 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreOrderMainTo)) return false; StoreOrderMainTo other = (StoreOrderMainTo)o; if (!other.canEqual(this)) return false; Object this$orderSid = getOrderSid(); Object other$orderSid = other.getOrderSid(); if (this$orderSid == null ? other$orderSid != null : !this$orderSid.equals(other$orderSid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; if (Float.compare(getOrderDiscountMoney(), other.getOrderDiscountMoney()) != 0) return false; Object this$orderInfo = getOrderInfo(); Object other$orderInfo = other.getOrderInfo(); if (this$orderInfo == null ? other$orderInfo != null : !this$orderInfo.equals(other$orderInfo)) return false; if (Float.compare(getOrderMoney(), other.getOrderMoney()) != 0) return false; Object this$orderNo = getOrderNo(); Object other$orderNo = other.getOrderNo(); if (this$orderNo == null ? other$orderNo != null : !this$orderNo.equals(other$orderNo)) return false; Object this$orderRemark = getOrderRemark(); Object other$orderRemark = other.getOrderRemark(); if (this$orderRemark == null ? other$orderRemark != null : !this$orderRemark.equals(other$orderRemark)) return false; if (getOrderStatus() != other.getOrderStatus()) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$address = getAddress(); Object other$address = other.getAddress(); if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false; Object this$phone = getPhone(); Object other$phone = other.getPhone(); if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false; Object this$details = getDetails(); Object other$details = other.getDetails(); if (this$details == null ? other$details != null : !this$details.equals(other$details)) return false; Object this$modifiedOn = getModifiedOn(); Object other$modifiedOn = other.getModifiedOn(); return this$modifiedOn == null ? other$modifiedOn == null : this$modifiedOn.equals(other$modifiedOn); }
/* 14 */   protected boolean canEqual(Object other) { return other instanceof StoreOrderMainTo; }
/* 14 */   public int hashCode() { int PRIME = 59; int result = 1; Object $orderSid = getOrderSid(); result = result * 59 + ($orderSid == null ? 0 : $orderSid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); result = result * 59 + Float.floatToIntBits(getOrderDiscountMoney()); Object $orderInfo = getOrderInfo(); result = result * 59 + ($orderInfo == null ? 0 : $orderInfo.hashCode()); result = result * 59 + Float.floatToIntBits(getOrderMoney()); Object $orderNo = getOrderNo(); result = result * 59 + ($orderNo == null ? 0 : $orderNo.hashCode()); Object $orderRemark = getOrderRemark(); result = result * 59 + ($orderRemark == null ? 0 : $orderRemark.hashCode()); result = result * 59 + getOrderStatus(); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $address = getAddress(); result = result * 59 + ($address == null ? 0 : $address.hashCode()); Object $phone = getPhone(); result = result * 59 + ($phone == null ? 0 : $phone.hashCode()); Object $details = getDetails(); result = result * 59 + ($details == null ? 0 : $details.hashCode()); Object $modifiedOn = getModifiedOn(); result = result * 59 + ($modifiedOn == null ? 0 : $modifiedOn.hashCode()); return result; } 
/* 14 */   public String toString() { return "StoreOrderMainTo(orderSid=" + getOrderSid() + ", apartmentSid=" + getApartmentSid() + ", createdOn=" + getCreatedOn() + ", orderDiscountMoney=" + getOrderDiscountMoney() + ", orderInfo=" + getOrderInfo() + ", orderMoney=" + getOrderMoney() + ", orderNo=" + getOrderNo() + ", orderRemark=" + getOrderRemark() + ", orderStatus=" + getOrderStatus() + ", ownerSid=" + getOwnerSid() + ", address=" + getAddress() + ", phone=" + getPhone() + ", details=" + getDetails() + ", modifiedOn=" + getModifiedOn() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreOrderMainTo
 * JD-Core Version:    0.6.2
 */