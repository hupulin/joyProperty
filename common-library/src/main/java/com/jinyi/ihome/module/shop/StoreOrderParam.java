/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */

import java.io.Serializable;
import java.util.List;

/*    */

/*    */
/*    */ public class StoreOrderParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String userSid;
/*    */   private String apartmentSid;
/*    */   private String orderSid;
/*    */   private String orderStatus;
/*    */   private String info;
/*    */   private String remark;
/*    */   private String address;
/*    */   private String phone;
/*    */   private List<StoreOrderItemParam> orderItems;
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 17 */     return this.userSid;
/*    */   }
/* 19 */   public String getApartmentSid() { return this.apartmentSid; }
/*    */ 
/*    */ 
/*    */   public String getOrderSid()
/*    */   {
/* 24 */     return this.orderSid;
/*    */   }
/*    */ 
/*    */   public String getOrderStatus()
/*    */   {
/* 29 */     return this.orderStatus;
/*    */   }
/*    */ 
/*    */   public String getInfo()
/*    */   {
/* 34 */     return this.info;
/*    */   }
/*    */ 
/*    */   public String getRemark()
/*    */   {
/* 39 */     return this.remark;
/*    */   }
/* 41 */   public String getAddress() { return this.address; } 
/*    */   public String getPhone() {
/* 43 */     return this.phone;
/*    */   }
/*    */ 
/*    */   public List<StoreOrderItemParam> getOrderItems()
/*    */   {
/* 50 */     return this.orderItems;
/*    */   }
/*    */ 
/*    */   public void setUserSid(String userSid)
/*    */   {
/* 12 */     this.userSid = userSid; } 
/* 12 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 12 */   public void setOrderSid(String orderSid) { this.orderSid = orderSid; } 
/* 12 */   public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; } 
/* 12 */   public void setInfo(String info) { this.info = info; } 
/* 12 */   public void setRemark(String remark) { this.remark = remark; } 
/* 12 */   public void setAddress(String address) { this.address = address; } 
/* 12 */   public void setPhone(String phone) { this.phone = phone; } 
/* 12 */   public void setOrderItems(List<StoreOrderItemParam> orderItems) { this.orderItems = orderItems; }
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreOrderParam)) return false; StoreOrderParam other = (StoreOrderParam)o; if (!other.canEqual(this)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$orderSid = getOrderSid(); Object other$orderSid = other.getOrderSid(); if (this$orderSid == null ? other$orderSid != null : !this$orderSid.equals(other$orderSid)) return false; Object this$orderStatus = getOrderStatus(); Object other$orderStatus = other.getOrderStatus(); if (this$orderStatus == null ? other$orderStatus != null : !this$orderStatus.equals(other$orderStatus)) return false; Object this$info = getInfo(); Object other$info = other.getInfo(); if (this$info == null ? other$info != null : !this$info.equals(other$info)) return false; Object this$remark = getRemark(); Object other$remark = other.getRemark(); if (this$remark == null ? other$remark != null : !this$remark.equals(other$remark)) return false; Object this$address = getAddress(); Object other$address = other.getAddress(); if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false; Object this$phone = getPhone(); Object other$phone = other.getPhone(); if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false; Object this$orderItems = getOrderItems(); Object other$orderItems = other.getOrderItems(); return this$orderItems == null ? other$orderItems == null : this$orderItems.equals(other$orderItems); }
/* 12 */   protected boolean canEqual(Object other) { return other instanceof StoreOrderParam; }
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $orderSid = getOrderSid(); result = result * 59 + ($orderSid == null ? 0 : $orderSid.hashCode()); Object $orderStatus = getOrderStatus(); result = result * 59 + ($orderStatus == null ? 0 : $orderStatus.hashCode()); Object $info = getInfo(); result = result * 59 + ($info == null ? 0 : $info.hashCode()); Object $remark = getRemark(); result = result * 59 + ($remark == null ? 0 : $remark.hashCode()); Object $address = getAddress(); result = result * 59 + ($address == null ? 0 : $address.hashCode()); Object $phone = getPhone(); result = result * 59 + ($phone == null ? 0 : $phone.hashCode()); Object $orderItems = getOrderItems(); result = result * 59 + ($orderItems == null ? 0 : $orderItems.hashCode()); return result; } 
/* 12 */   public String toString() { return "StoreOrderParam(userSid=" + getUserSid() + ", apartmentSid=" + getApartmentSid() + ", orderSid=" + getOrderSid() + ", orderStatus=" + getOrderStatus() + ", info=" + getInfo() + ", remark=" + getRemark() + ", address=" + getAddress() + ", phone=" + getPhone() + ", orderItems=" + getOrderItems() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreOrderParam
 * JD-Core Version:    0.6.2
 */