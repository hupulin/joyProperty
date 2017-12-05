/*    */ package com.jinyi.ihome.module.bill;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PayPartnerTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String paySid;
/*    */   private String payPartner;
/*    */   private String payIcon;
/*    */   private String payName;
/*    */   private String payDesc;
/*    */   private String payUrl;
/*    */ 
/*    */   public String getPaySid()
/*    */   {
/* 16 */     return this.paySid;
/*    */   }
/*    */ 
/*    */   public String getPayPartner()
/*    */   {
/* 23 */     return this.payPartner;
/*    */   }
/* 25 */   public String getPayIcon() { return this.payIcon; } 
/*    */   public String getPayName() {
/* 27 */     return this.payName;
/*    */   }
/* 29 */   public String getPayDesc() { return this.payDesc; } 
/*    */   public String getPayUrl() {
/* 31 */     return this.payUrl;
/*    */   }
/*    */ 
/*    */   public void setPaySid(String paySid)
/*    */   {
/* 11 */     this.paySid = paySid; } 
/* 11 */   public void setPayPartner(String payPartner) { this.payPartner = payPartner; } 
/* 11 */   public void setPayIcon(String payIcon) { this.payIcon = payIcon; } 
/* 11 */   public void setPayName(String payName) { this.payName = payName; } 
/* 11 */   public void setPayDesc(String payDesc) { this.payDesc = payDesc; } 
/* 11 */   public void setPayUrl(String payUrl) { this.payUrl = payUrl; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof PayPartnerTo)) return false; PayPartnerTo other = (PayPartnerTo)o; if (!other.canEqual(this)) return false; Object this$paySid = getPaySid(); Object other$paySid = other.getPaySid(); if (this$paySid == null ? other$paySid != null : !this$paySid.equals(other$paySid)) return false; Object this$payPartner = getPayPartner(); Object other$payPartner = other.getPayPartner(); if (this$payPartner == null ? other$payPartner != null : !this$payPartner.equals(other$payPartner)) return false; Object this$payIcon = getPayIcon(); Object other$payIcon = other.getPayIcon(); if (this$payIcon == null ? other$payIcon != null : !this$payIcon.equals(other$payIcon)) return false; Object this$payName = getPayName(); Object other$payName = other.getPayName(); if (this$payName == null ? other$payName != null : !this$payName.equals(other$payName)) return false; Object this$payDesc = getPayDesc(); Object other$payDesc = other.getPayDesc(); if (this$payDesc == null ? other$payDesc != null : !this$payDesc.equals(other$payDesc)) return false; Object this$payUrl = getPayUrl(); Object other$payUrl = other.getPayUrl(); return this$payUrl == null ? other$payUrl == null : this$payUrl.equals(other$payUrl); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof PayPartnerTo; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $paySid = getPaySid(); result = result * 59 + ($paySid == null ? 0 : $paySid.hashCode()); Object $payPartner = getPayPartner(); result = result * 59 + ($payPartner == null ? 0 : $payPartner.hashCode()); Object $payIcon = getPayIcon(); result = result * 59 + ($payIcon == null ? 0 : $payIcon.hashCode()); Object $payName = getPayName(); result = result * 59 + ($payName == null ? 0 : $payName.hashCode()); Object $payDesc = getPayDesc(); result = result * 59 + ($payDesc == null ? 0 : $payDesc.hashCode()); Object $payUrl = getPayUrl(); result = result * 59 + ($payUrl == null ? 0 : $payUrl.hashCode()); return result; } 
/* 11 */   public String toString() { return "PayPartnerTo(paySid=" + getPaySid() + ", payPartner=" + getPayPartner() + ", payIcon=" + getPayIcon() + ", payName=" + getPayName() + ", payDesc=" + getPayDesc() + ", payUrl=" + getPayUrl() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.bill.PayPartnerTo
 * JD-Core Version:    0.6.2
 */