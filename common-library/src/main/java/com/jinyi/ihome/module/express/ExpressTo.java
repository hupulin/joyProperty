/*    */ package com.jinyi.ihome.module.express;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ExpressTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String expressSid;
/*    */   private String apartmentSid;
/*    */   private String apartmentName;
/*    */   private String ownerSid;
/*    */   private String expressOwnerNo;
/*    */   private String expressCompany;
/*    */   private String expressIcon;
/*    */   private String expressNo;
/*    */   private String expressPhone;
/*    */   private String expressRemark;
/*    */   private Integer expressStatus;
/*    */   private Timestamp createdOn;
/*    */   private Timestamp modifiedOn;
/*    */   private String submitExpress;
/*    */   private List<Map<String, String>> flow;
/*    */ 
/*    */   public String getExpressSid()
/*    */   {
/* 17 */     return this.expressSid;
/*    */   }
/* 19 */   public String getApartmentSid() { return this.apartmentSid; } 
/*    */   public String getApartmentName() {
/* 21 */     return this.apartmentName;
/*    */   }
/* 23 */   public String getOwnerSid() { return this.ownerSid; } 
/*    */   public String getExpressOwnerNo() {
/* 25 */     return this.expressOwnerNo;
/*    */   }
/* 27 */   public String getExpressCompany() { return this.expressCompany; } 
/*    */   public String getExpressIcon() {
/* 29 */     return this.expressIcon;
/*    */   }
/* 31 */   public String getExpressNo() { return this.expressNo; } 
/*    */   public String getExpressPhone() {
/* 33 */     return this.expressPhone;
/*    */   }
/* 35 */   public String getExpressRemark() { return this.expressRemark; }
/*    */ 
/*    */ 
/*    */   public Integer getExpressStatus()
/*    */   {
/* 44 */     return this.expressStatus;
/*    */   }
/* 46 */   public Timestamp getCreatedOn() { return this.createdOn; } 
/*    */   public Timestamp getModifiedOn() {
/* 48 */     return this.modifiedOn;
/*    */   }
/* 50 */   public String getSubmitExpress() { return this.submitExpress; } 
/*    */   public List<Map<String, String>> getFlow() {
/* 52 */     return this.flow;
/*    */   }
/*    */ 
/*    */   public void setExpressSid(String expressSid)
/*    */   {
/* 13 */     this.expressSid = expressSid; } 
/* 13 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 13 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; } 
/* 13 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 13 */   public void setExpressOwnerNo(String expressOwnerNo) { this.expressOwnerNo = expressOwnerNo; } 
/* 13 */   public void setExpressCompany(String expressCompany) { this.expressCompany = expressCompany; } 
/* 13 */   public void setExpressIcon(String expressIcon) { this.expressIcon = expressIcon; } 
/* 13 */   public void setExpressNo(String expressNo) { this.expressNo = expressNo; } 
/* 13 */   public void setExpressPhone(String expressPhone) { this.expressPhone = expressPhone; } 
/* 13 */   public void setExpressRemark(String expressRemark) { this.expressRemark = expressRemark; } 
/* 13 */   public void setExpressStatus(Integer expressStatus) { this.expressStatus = expressStatus; } 
/* 13 */   public void setCreatedOn(Timestamp createdOn) { this.createdOn = createdOn; } 
/* 13 */   public void setModifiedOn(Timestamp modifiedOn) { this.modifiedOn = modifiedOn; } 
/* 13 */   public void setSubmitExpress(String submitExpress) { this.submitExpress = submitExpress; } 
/* 13 */   public void setFlow(List<Map<String, String>> flow) { this.flow = flow; } 
/* 13 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ExpressTo)) return false; ExpressTo other = (ExpressTo)o; if (!other.canEqual(this)) return false; Object this$expressSid = getExpressSid(); Object other$expressSid = other.getExpressSid(); if (this$expressSid == null ? other$expressSid != null : !this$expressSid.equals(other$expressSid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$apartmentName = getApartmentName(); Object other$apartmentName = other.getApartmentName(); if (this$apartmentName == null ? other$apartmentName != null : !this$apartmentName.equals(other$apartmentName)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$expressOwnerNo = getExpressOwnerNo(); Object other$expressOwnerNo = other.getExpressOwnerNo(); if (this$expressOwnerNo == null ? other$expressOwnerNo != null : !this$expressOwnerNo.equals(other$expressOwnerNo)) return false; Object this$expressCompany = getExpressCompany(); Object other$expressCompany = other.getExpressCompany(); if (this$expressCompany == null ? other$expressCompany != null : !this$expressCompany.equals(other$expressCompany)) return false; Object this$expressIcon = getExpressIcon(); Object other$expressIcon = other.getExpressIcon(); if (this$expressIcon == null ? other$expressIcon != null : !this$expressIcon.equals(other$expressIcon)) return false; Object this$expressNo = getExpressNo(); Object other$expressNo = other.getExpressNo(); if (this$expressNo == null ? other$expressNo != null : !this$expressNo.equals(other$expressNo)) return false; Object this$expressPhone = getExpressPhone(); Object other$expressPhone = other.getExpressPhone(); if (this$expressPhone == null ? other$expressPhone != null : !this$expressPhone.equals(other$expressPhone)) return false; Object this$expressRemark = getExpressRemark(); Object other$expressRemark = other.getExpressRemark(); if (this$expressRemark == null ? other$expressRemark != null : !this$expressRemark.equals(other$expressRemark)) return false; Object this$expressStatus = getExpressStatus(); Object other$expressStatus = other.getExpressStatus(); if (this$expressStatus == null ? other$expressStatus != null : !this$expressStatus.equals(other$expressStatus)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$modifiedOn = getModifiedOn(); Object other$modifiedOn = other.getModifiedOn(); if (this$modifiedOn == null ? other$modifiedOn != null : !this$modifiedOn.equals(other$modifiedOn)) return false; Object this$submitExpress = getSubmitExpress(); Object other$submitExpress = other.getSubmitExpress(); if (this$submitExpress == null ? other$submitExpress != null : !this$submitExpress.equals(other$submitExpress)) return false; Object this$flow = getFlow(); Object other$flow = other.getFlow(); return this$flow == null ? other$flow == null : this$flow.equals(other$flow); } 
/* 13 */   protected boolean canEqual(Object other) { return other instanceof ExpressTo; } 
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $expressSid = getExpressSid(); result = result * 59 + ($expressSid == null ? 0 : $expressSid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $apartmentName = getApartmentName(); result = result * 59 + ($apartmentName == null ? 0 : $apartmentName.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $expressOwnerNo = getExpressOwnerNo(); result = result * 59 + ($expressOwnerNo == null ? 0 : $expressOwnerNo.hashCode()); Object $expressCompany = getExpressCompany(); result = result * 59 + ($expressCompany == null ? 0 : $expressCompany.hashCode()); Object $expressIcon = getExpressIcon(); result = result * 59 + ($expressIcon == null ? 0 : $expressIcon.hashCode()); Object $expressNo = getExpressNo(); result = result * 59 + ($expressNo == null ? 0 : $expressNo.hashCode()); Object $expressPhone = getExpressPhone(); result = result * 59 + ($expressPhone == null ? 0 : $expressPhone.hashCode()); Object $expressRemark = getExpressRemark(); result = result * 59 + ($expressRemark == null ? 0 : $expressRemark.hashCode()); Object $expressStatus = getExpressStatus(); result = result * 59 + ($expressStatus == null ? 0 : $expressStatus.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $modifiedOn = getModifiedOn(); result = result * 59 + ($modifiedOn == null ? 0 : $modifiedOn.hashCode()); Object $submitExpress = getSubmitExpress(); result = result * 59 + ($submitExpress == null ? 0 : $submitExpress.hashCode()); Object $flow = getFlow(); result = result * 59 + ($flow == null ? 0 : $flow.hashCode()); return result; } 
/* 13 */   public String toString() { return "ExpressTo(expressSid=" + getExpressSid() + ", apartmentSid=" + getApartmentSid() + ", apartmentName=" + getApartmentName() + ", ownerSid=" + getOwnerSid() + ", expressOwnerNo=" + getExpressOwnerNo() + ", expressCompany=" + getExpressCompany() + ", expressIcon=" + getExpressIcon() + ", expressNo=" + getExpressNo() + ", expressPhone=" + getExpressPhone() + ", expressRemark=" + getExpressRemark() + ", expressStatus=" + getExpressStatus() + ", createdOn=" + getCreatedOn() + ", modifiedOn=" + getModifiedOn() + ", submitExpress=" + getSubmitExpress() + ", flow=" + getFlow() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.express.ExpressTo
 * JD-Core Version:    0.6.2
 */