/*    */ package com.jinyi.ihome.module.visitor;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VerifyCardResultTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private boolean result;
/*    */   private String apartmentName;
/*    */   private boolean apartmentPass;
/*    */   private String visitorName;
/*    */   private String ownerPhone;
/*    */   private String ownerNo;
/*    */   private String carNo;
/*    */   private String visitTime;
/*    */   private String leaveTime;
/*    */   private boolean datePass;
/*    */   private boolean visitTimePass;
/*    */   private boolean leaveTimePass;
/*    */   private String indate;

/*    */   public boolean isResult()
/*    */   {
/* 18 */     return this.result;
/*    */   }
/* 20 */   public String getApartmentName() { return this.apartmentName; } 
/*    */   public boolean isApartmentPass() {
/* 22 */     return this.apartmentPass;
/*    */   }
/* 24 */   public String getVisitorName() { return this.visitorName; } 
/*    */   public String getOwnerPhone() {
/* 26 */     return this.ownerPhone;
/*    */   }
/* 28 */   public String getOwnerNo() { return this.ownerNo; } 
/*    */   public String getCarNo() {
/* 30 */     return this.carNo;
/*    */   }
/*    */ 
/*    */   public String getVisitTime()
/*    */   {
/* 35 */     return this.visitTime;
/*    */   }
/*    */ 
/*    */   public String getLeaveTime() {
/* 39 */     return this.leaveTime;
/*    */   }
/* 41 */   public boolean isDatePass() { return this.datePass; } 
/*    */   public boolean isVisitTimePass() {
/* 43 */     return this.visitTimePass;
/*    */   }
/* 45 */   public boolean isLeaveTimePass() { return this.leaveTimePass; } 
/*    */   public String getIndate() {
/* 47 */     return this.indate;
/*    */   }
/*    */ 
/*    */   public void setResult(boolean result)
/*    */   {
/* 11 */     this.result = result; } 
/* 11 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; } 
/* 11 */   public void setApartmentPass(boolean apartmentPass) { this.apartmentPass = apartmentPass; } 
/* 11 */   public void setVisitorName(String visitorName) { this.visitorName = visitorName; } 
/* 11 */   public void setOwnerPhone(String ownerPhone) { this.ownerPhone = ownerPhone; } 
/* 11 */   public void setOwnerNo(String ownerNo) { this.ownerNo = ownerNo; } 
/* 11 */   public void setCarNo(String carNo) { this.carNo = carNo; } 
/* 11 */   public void setVisitTime(String visitTime) { this.visitTime = visitTime; } 
/* 11 */   public void setLeaveTime(String leaveTime) { this.leaveTime = leaveTime; } 
/* 11 */   public void setDatePass(boolean datePass) { this.datePass = datePass; } 
/* 11 */   public void setVisitTimePass(boolean visitTimePass) { this.visitTimePass = visitTimePass; } 
/* 11 */   public void setLeaveTimePass(boolean leaveTimePass) { this.leaveTimePass = leaveTimePass; } 
/* 11 */   public void setIndate(String indate) { this.indate = indate; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof VerifyCardResultTo)) return false; VerifyCardResultTo other = (VerifyCardResultTo)o; if (!other.canEqual(this)) return false; if (isResult() != other.isResult()) return false; Object this$apartmentName = getApartmentName(); Object other$apartmentName = other.getApartmentName(); if (this$apartmentName == null ? other$apartmentName != null : !this$apartmentName.equals(other$apartmentName)) return false; if (isApartmentPass() != other.isApartmentPass()) return false; Object this$visitorName = getVisitorName(); Object other$visitorName = other.getVisitorName(); if (this$visitorName == null ? other$visitorName != null : !this$visitorName.equals(other$visitorName)) return false; Object this$ownerPhone = getOwnerPhone(); Object other$ownerPhone = other.getOwnerPhone(); if (this$ownerPhone == null ? other$ownerPhone != null : !this$ownerPhone.equals(other$ownerPhone)) return false; Object this$ownerNo = getOwnerNo(); Object other$ownerNo = other.getOwnerNo(); if (this$ownerNo == null ? other$ownerNo != null : !this$ownerNo.equals(other$ownerNo)) return false; Object this$carNo = getCarNo(); Object other$carNo = other.getCarNo(); if (this$carNo == null ? other$carNo != null : !this$carNo.equals(other$carNo)) return false; Object this$visitTime = getVisitTime(); Object other$visitTime = other.getVisitTime(); if (this$visitTime == null ? other$visitTime != null : !this$visitTime.equals(other$visitTime)) return false; Object this$leaveTime = getLeaveTime(); Object other$leaveTime = other.getLeaveTime(); if (this$leaveTime == null ? other$leaveTime != null : !this$leaveTime.equals(other$leaveTime)) return false; if (isDatePass() != other.isDatePass()) return false; if (isVisitTimePass() != other.isVisitTimePass()) return false; if (isLeaveTimePass() != other.isLeaveTimePass()) return false; Object this$indate = getIndate(); Object other$indate = other.getIndate(); return this$indate == null ? other$indate == null : this$indate.equals(other$indate); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof VerifyCardResultTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; result = result * 59 + (isResult() ? 79 : 97); Object $apartmentName = getApartmentName(); result = result * 59 + ($apartmentName == null ? 0 : $apartmentName.hashCode()); result = result * 59 + (isApartmentPass() ? 79 : 97); Object $visitorName = getVisitorName(); result = result * 59 + ($visitorName == null ? 0 : $visitorName.hashCode()); Object $ownerPhone = getOwnerPhone(); result = result * 59 + ($ownerPhone == null ? 0 : $ownerPhone.hashCode()); Object $ownerNo = getOwnerNo(); result = result * 59 + ($ownerNo == null ? 0 : $ownerNo.hashCode()); Object $carNo = getCarNo(); result = result * 59 + ($carNo == null ? 0 : $carNo.hashCode()); Object $visitTime = getVisitTime(); result = result * 59 + ($visitTime == null ? 0 : $visitTime.hashCode()); Object $leaveTime = getLeaveTime(); result = result * 59 + ($leaveTime == null ? 0 : $leaveTime.hashCode()); result = result * 59 + (isDatePass() ? 79 : 97); result = result * 59 + (isVisitTimePass() ? 79 : 97); result = result * 59 + (isLeaveTimePass() ? 79 : 97); Object $indate = getIndate(); result = result * 59 + ($indate == null ? 0 : $indate.hashCode()); return result; } 
/* 11 */   public String toString() { return "VerifyCardResultTo(result=" + isResult() + ", apartmentName=" + getApartmentName() + ", apartmentPass=" + isApartmentPass() + ", visitorName=" + getVisitorName() + ", ownerPhone=" + getOwnerPhone() + ", ownerNo=" + getOwnerNo() + ", carNo=" + getCarNo() + ", visitTime=" + getVisitTime() + ", leaveTime=" + getLeaveTime() + ", datePass=" + isDatePass() + ", visitTimePass=" + isVisitTimePass() + ", leaveTimePass=" + isLeaveTimePass() + ", indate=" + getIndate() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.visitor.VerifyCardResultTo
 * JD-Core Version:    0.6.2
 */