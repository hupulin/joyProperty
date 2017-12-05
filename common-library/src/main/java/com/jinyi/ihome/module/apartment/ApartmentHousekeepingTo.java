/*    */ package com.jinyi.ihome.module.apartment;
/*    */ 
/*    */ public class ApartmentHousekeepingTo extends ApartmentInfoTo
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String housekeepingRemark;
/*    */ 
/*    */   public String getHousekeepingRemark()
/*    */   {
/* 18 */     return this.housekeepingRemark;
/*    */   }
/*    */ 
/*    */   public void setHousekeepingRemark(String housekeepingRemark)
/*    */   {
/* 11 */     this.housekeepingRemark = housekeepingRemark; } 
/* 11 */   public String toString() { return "ApartmentHousekeepingTo(housekeepingRemark=" + getHousekeepingRemark() + ")"; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ApartmentHousekeepingTo)) return false; ApartmentHousekeepingTo other = (ApartmentHousekeepingTo)o; if (!other.canEqual(this)) return false; Object this$housekeepingRemark = getHousekeepingRemark(); Object other$housekeepingRemark = other.getHousekeepingRemark(); return this$housekeepingRemark == null ? other$housekeepingRemark == null : this$housekeepingRemark.equals(other$housekeepingRemark); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof ApartmentHousekeepingTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $housekeepingRemark = getHousekeepingRemark(); result = result * 59 + ($housekeepingRemark == null ? 0 : $housekeepingRemark.hashCode()); return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.apartment.ApartmentHousekeepingTo
 * JD-Core Version:    0.6.2
 */