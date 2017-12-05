/*    */ package com.jinyi.ihome.module.express;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CompanyConfirmParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String expressSid;
/*    */   private String companyName;
/*    */   private String expressNo;
/*    */   private long time;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 15 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getExpressSid()
/*    */   {
/* 20 */     return this.expressSid;
/*    */   }
/*    */ 
/*    */   public String getCompanyName()
/*    */   {
/* 25 */     return this.companyName;
/*    */   }
/*    */ 
/*    */   public String getExpressNo()
/*    */   {
/* 30 */     return this.expressNo;
/*    */   }
/*    */ 
/*    */   public long getTime()
/*    */   {
/* 35 */     return this.time;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 10 */     this.apartmentSid = apartmentSid; } 
/* 10 */   public void setExpressSid(String expressSid) { this.expressSid = expressSid; } 
/* 10 */   public void setCompanyName(String companyName) { this.companyName = companyName; } 
/* 10 */   public void setExpressNo(String expressNo) { this.expressNo = expressNo; } 
/* 10 */   public void setTime(long time) { this.time = time; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof CompanyConfirmParam)) return false; CompanyConfirmParam other = (CompanyConfirmParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$expressSid = getExpressSid(); Object other$expressSid = other.getExpressSid(); if (this$expressSid == null ? other$expressSid != null : !this$expressSid.equals(other$expressSid)) return false; Object this$companyName = getCompanyName(); Object other$companyName = other.getCompanyName(); if (this$companyName == null ? other$companyName != null : !this$companyName.equals(other$companyName)) return false; Object this$expressNo = getExpressNo(); Object other$expressNo = other.getExpressNo(); if (this$expressNo == null ? other$expressNo != null : !this$expressNo.equals(other$expressNo)) return false; return getTime() == other.getTime(); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof CompanyConfirmParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $expressSid = getExpressSid(); result = result * 59 + ($expressSid == null ? 0 : $expressSid.hashCode()); Object $companyName = getCompanyName(); result = result * 59 + ($companyName == null ? 0 : $companyName.hashCode()); Object $expressNo = getExpressNo(); result = result * 59 + ($expressNo == null ? 0 : $expressNo.hashCode()); long $time = getTime(); result = result * 59 + (int)($time >>> 32 ^ $time); return result; } 
/* 10 */   public String toString() { return "CompanyConfirmParam(apartmentSid=" + getApartmentSid() + ", expressSid=" + getExpressSid() + ", companyName=" + getCompanyName() + ", expressNo=" + getExpressNo() + ", time=" + getTime() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.express.CompanyConfirmParam
 * JD-Core Version:    0.6.2
 */