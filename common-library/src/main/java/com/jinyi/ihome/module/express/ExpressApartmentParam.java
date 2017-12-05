/*    */ package com.jinyi.ihome.module.express;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ExpressApartmentParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private int pageIndex;
/*    */   private int type;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 15 */     return this.apartmentSid;
/*    */   }
/* 17 */   public int getPageIndex() { return this.pageIndex; }
/*    */ 
/*    */ 
/*    */   public int getType()
/*    */   {
/* 23 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 10 */     this.apartmentSid = apartmentSid; } 
/* 10 */   public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex; } 
/* 10 */   public void setType(int type) { this.type = type; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ExpressApartmentParam)) return false; ExpressApartmentParam other = (ExpressApartmentParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; if (getPageIndex() != other.getPageIndex()) return false; return getType() == other.getType(); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ExpressApartmentParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); result = result * 59 + getPageIndex(); result = result * 59 + getType(); return result; } 
/* 10 */   public String toString() { return "ExpressApartmentParam(apartmentSid=" + getApartmentSid() + ", pageIndex=" + getPageIndex() + ", type=" + getType() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.express.ExpressApartmentParam
 * JD-Core Version:    0.6.2
 */