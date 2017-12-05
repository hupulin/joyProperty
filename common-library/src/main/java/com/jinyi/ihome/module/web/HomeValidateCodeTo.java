/*    */ package com.jinyi.ihome.module.web;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class HomeValidateCodeTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String validateCodeSid;
/*    */   private String validatePhone;
/*    */   private String validateCode;
/*    */   private Date createdOn;
/*    */   private Date modifiedOn;
/*    */ 
/*    */   public String getValidateCodeSid()
/*    */   {
/* 19 */     return this.validateCodeSid;
/*    */   }
/*    */ 
/*    */   public String getValidatePhone()
/*    */   {
/* 24 */     return this.validatePhone;
/*    */   }
/*    */ 
/*    */   public String getValidateCode()
/*    */   {
/* 29 */     return this.validateCode;
/*    */   }
/*    */ 
/*    */   public Date getCreatedOn()
/*    */   {
/* 34 */     return this.createdOn;
/*    */   }
/*    */ 
/*    */   public Date getModifiedOn()
/*    */   {
/* 39 */     return this.modifiedOn;
/*    */   }
/*    */ 
/*    */   public void setValidateCodeSid(String validateCodeSid)
/*    */   {
/* 11 */     this.validateCodeSid = validateCodeSid; } 
/* 11 */   public void setValidatePhone(String validatePhone) { this.validatePhone = validatePhone; } 
/* 11 */   public void setValidateCode(String validateCode) { this.validateCode = validateCode; } 
/* 11 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 11 */   public void setModifiedOn(Date modifiedOn) { this.modifiedOn = modifiedOn; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof HomeValidateCodeTo)) return false; HomeValidateCodeTo other = (HomeValidateCodeTo)o; if (!other.canEqual(this)) return false; Object this$validateCodeSid = getValidateCodeSid(); Object other$validateCodeSid = other.getValidateCodeSid(); if (this$validateCodeSid == null ? other$validateCodeSid != null : !this$validateCodeSid.equals(other$validateCodeSid)) return false; Object this$validatePhone = getValidatePhone(); Object other$validatePhone = other.getValidatePhone(); if (this$validatePhone == null ? other$validatePhone != null : !this$validatePhone.equals(other$validatePhone)) return false; Object this$validateCode = getValidateCode(); Object other$validateCode = other.getValidateCode(); if (this$validateCode == null ? other$validateCode != null : !this$validateCode.equals(other$validateCode)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$modifiedOn = getModifiedOn(); Object other$modifiedOn = other.getModifiedOn(); return this$modifiedOn == null ? other$modifiedOn == null : this$modifiedOn.equals(other$modifiedOn); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof HomeValidateCodeTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $validateCodeSid = getValidateCodeSid(); result = result * 59 + ($validateCodeSid == null ? 0 : $validateCodeSid.hashCode()); Object $validatePhone = getValidatePhone(); result = result * 59 + ($validatePhone == null ? 0 : $validatePhone.hashCode()); Object $validateCode = getValidateCode(); result = result * 59 + ($validateCode == null ? 0 : $validateCode.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $modifiedOn = getModifiedOn(); result = result * 59 + ($modifiedOn == null ? 0 : $modifiedOn.hashCode()); return result; } 
/* 11 */   public String toString() { return "HomeValidateCodeTo(validateCodeSid=" + getValidateCodeSid() + ", validatePhone=" + getValidatePhone() + ", validateCode=" + getValidateCode() + ", createdOn=" + getCreatedOn() + ", modifiedOn=" + getModifiedOn() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.web.HomeValidateCodeTo
 * JD-Core Version:    0.6.2
 */