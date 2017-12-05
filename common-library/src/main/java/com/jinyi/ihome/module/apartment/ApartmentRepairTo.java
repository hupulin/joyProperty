/*    */ package com.jinyi.ihome.module.apartment;
/*    */ 
/*    */ public class ApartmentRepairTo extends ApartmentInfoTo
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String repairRemark;
/*    */ 
/*    */   public String getRepairRemark()
/*    */   {
/* 16 */     return this.repairRemark;
/*    */   }
/*    */ 
/*    */   public void setRepairRemark(String repairRemark)
/*    */   {
/*  9 */     this.repairRemark = repairRemark; } 
/*  9 */   public String toString() { return "ApartmentRepairTo(repairRemark=" + getRepairRemark() + ")"; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ApartmentRepairTo)) return false; ApartmentRepairTo other = (ApartmentRepairTo)o; if (!other.canEqual(this)) return false; Object this$repairRemark = getRepairRemark(); Object other$repairRemark = other.getRepairRemark(); return this$repairRemark == null ? other$repairRemark == null : this$repairRemark.equals(other$repairRemark); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ApartmentRepairTo; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $repairRemark = getRepairRemark(); result = result * 59 + ($repairRemark == null ? 0 : $repairRemark.hashCode()); return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.apartment.ApartmentRepairTo
 * JD-Core Version:    0.6.2
 */