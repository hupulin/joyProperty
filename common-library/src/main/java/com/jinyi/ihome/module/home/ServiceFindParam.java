/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceFindParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int pageIndex;
/*    */   private String usid;
/*    */   private String apartmentSid;
/*    */   private String category;
/*    */   private String flowState;
/*    */ 
/*    */   public int getPageIndex()
/*    */   {
/* 14 */     return this.pageIndex; } 
/* 15 */   public String getUsid() { return this.usid; } 
/* 16 */   public String getApartmentSid() { return this.apartmentSid; }
/*    */ 
/*    */   public String getCategory()
/*    */   {
/* 20 */     return this.category; } 
/* 21 */   public String getFlowState() { return this.flowState; }
/*    */ 
/*    */ 
/*    */   public void setPageIndex(int pageIndex)
/*    */   {
/* 10 */     this.pageIndex = pageIndex; } 
/* 10 */   public void setUsid(String usid) { this.usid = usid; } 
/* 10 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 10 */   public void setCategory(String category) { this.category = category; } 
/* 10 */   public void setFlowState(String flowState) { this.flowState = flowState; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceFindParam)) return false; ServiceFindParam other = (ServiceFindParam)o; if (!other.canEqual(this)) return false; if (getPageIndex() != other.getPageIndex()) return false; Object this$usid = getUsid(); Object other$usid = other.getUsid(); if (this$usid == null ? other$usid != null : !this$usid.equals(other$usid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$category = getCategory(); Object other$category = other.getCategory(); if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false; Object this$flowState = getFlowState(); Object other$flowState = other.getFlowState(); return this$flowState == null ? other$flowState == null : this$flowState.equals(other$flowState); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ServiceFindParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; result = result * 59 + getPageIndex(); Object $usid = getUsid(); result = result * 59 + ($usid == null ? 0 : $usid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $category = getCategory(); result = result * 59 + ($category == null ? 0 : $category.hashCode()); Object $flowState = getFlowState(); result = result * 59 + ($flowState == null ? 0 : $flowState.hashCode()); return result; } 
/* 10 */   public String toString() { return "ServiceFindParam(pageIndex=" + getPageIndex() + ", usid=" + getUsid() + ", apartmentSid=" + getApartmentSid() + ", category=" + getCategory() + ", flowState=" + getFlowState() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceFindParam
 * JD-Core Version:    0.6.2
 */