/*    */ package com.jinyi.ihome.module.car;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CarViolationHistoryParam
/*    */   implements Serializable
/*    */ {
/*    */   private String carNo;
/*    */ 
/*    */   public String getCarNo()
/*    */   {
/* 13 */     return this.carNo;
/*    */   }
/*    */ 
/*    */   public void setCarNo(String carNo)
/*    */   {
/* 11 */     this.carNo = carNo; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof CarViolationHistoryParam)) return false; CarViolationHistoryParam other = (CarViolationHistoryParam)o; if (!other.canEqual(this)) return false; Object this$carNo = getCarNo(); Object other$carNo = other.getCarNo(); return this$carNo == null ? other$carNo == null : this$carNo.equals(other$carNo); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof CarViolationHistoryParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $carNo = getCarNo(); result = result * 59 + ($carNo == null ? 0 : $carNo.hashCode()); return result; } 
/* 11 */   public String toString() { return "CarViolationHistoryParam(carNo=" + getCarNo() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.car.CarViolationHistoryParam
 * JD-Core Version:    0.6.2
 */