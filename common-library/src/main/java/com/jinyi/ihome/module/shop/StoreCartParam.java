/*    */ package com.jinyi.ihome.module.shop;
/*    */ 
/*    */

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*    */
/*    */

/*    */
/*    */ public class StoreCartParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String userSid;
/*    */   private List<Map<String, String>> itemList;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 19 */     return this.apartmentSid;
/*    */   }
/* 21 */   public String getUserSid() { return this.userSid; }
/*    */ 
/*    */ 
/*    */   public List<Map<String, String>> getItemList()
/*    */   {
/* 27 */     return this.itemList;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 14 */     this.apartmentSid = apartmentSid; } 
/* 14 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 14 */   public void setItemList(List<Map<String, String>> itemList) { this.itemList = itemList; } 
/* 14 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreCartParam)) return false; StoreCartParam other = (StoreCartParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$itemList = getItemList(); Object other$itemList = other.getItemList(); return this$itemList == null ? other$itemList == null : this$itemList.equals(other$itemList); }
/* 14 */   protected boolean canEqual(Object other) { return other instanceof StoreCartParam; }
/* 14 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $itemList = getItemList(); result = result * 59 + ($itemList == null ? 0 : $itemList.hashCode()); return result; } 
/* 14 */   public String toString() { return "StoreCartParam(apartmentSid=" + getApartmentSid() + ", userSid=" + getUserSid() + ", itemList=" + getItemList() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.shop.StoreCartParam
 * JD-Core Version:    0.6.2
 */