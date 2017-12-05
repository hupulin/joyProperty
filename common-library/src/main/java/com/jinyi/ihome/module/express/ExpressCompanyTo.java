/*    */ package com.jinyi.ihome.module.express;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ExpressCompanyTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String companySid;
/*    */   private String companyName;
/*    */   private Integer companyIndex;
/*    */   private String companyIcon;
/*    */ 
/*    */   public String getCompanySid()
/*    */   {
/* 15 */     return this.companySid;
/*    */   }
/* 17 */   public String getCompanyName() { return this.companyName; } 
/*    */   public Integer getCompanyIndex() {
/* 19 */     return this.companyIndex;
/*    */   }
/* 21 */   public String getCompanyIcon() { return this.companyIcon; }
/*    */ 
/*    */ 
/*    */   public void setCompanySid(String companySid)
/*    */   {
/* 11 */     this.companySid = companySid; } 
/* 11 */   public void setCompanyName(String companyName) { this.companyName = companyName; } 
/* 11 */   public void setCompanyIndex(Integer companyIndex) { this.companyIndex = companyIndex; } 
/* 11 */   public void setCompanyIcon(String companyIcon) { this.companyIcon = companyIcon; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ExpressCompanyTo)) return false; ExpressCompanyTo other = (ExpressCompanyTo)o; if (!other.canEqual(this)) return false; Object this$companySid = getCompanySid(); Object other$companySid = other.getCompanySid(); if (this$companySid == null ? other$companySid != null : !this$companySid.equals(other$companySid)) return false; Object this$companyName = getCompanyName(); Object other$companyName = other.getCompanyName(); if (this$companyName == null ? other$companyName != null : !this$companyName.equals(other$companyName)) return false; Object this$companyIndex = getCompanyIndex(); Object other$companyIndex = other.getCompanyIndex(); if (this$companyIndex == null ? other$companyIndex != null : !this$companyIndex.equals(other$companyIndex)) return false; Object this$companyIcon = getCompanyIcon(); Object other$companyIcon = other.getCompanyIcon(); return this$companyIcon == null ? other$companyIcon == null : this$companyIcon.equals(other$companyIcon); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof ExpressCompanyTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $companySid = getCompanySid(); result = result * 59 + ($companySid == null ? 0 : $companySid.hashCode()); Object $companyName = getCompanyName(); result = result * 59 + ($companyName == null ? 0 : $companyName.hashCode()); Object $companyIndex = getCompanyIndex(); result = result * 59 + ($companyIndex == null ? 0 : $companyIndex.hashCode()); Object $companyIcon = getCompanyIcon(); result = result * 59 + ($companyIcon == null ? 0 : $companyIcon.hashCode()); return result; } 
/* 11 */   public String toString() { return "ExpressCompanyTo(companySid=" + getCompanySid() + ", companyName=" + getCompanyName() + ", companyIndex=" + getCompanyIndex() + ", companyIcon=" + getCompanyIcon() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.express.ExpressCompanyTo
 * JD-Core Version:    0.6.2
 */