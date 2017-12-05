/*    */ package com.jinyi.ihome.module.bill;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
/*    */ 
/*    */ public class WapPayLogTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String logSid;
/*    */   private Timestamp logDateTime;
/*    */   private String logUserSid;
/*    */   private String logBillNo;
/*    */   private String logContent;
/*    */ 
/*    */   public String getLogSid()
/*    */   {
/* 17 */     return this.logSid;
/*    */   }
/* 19 */   public Timestamp getLogDateTime() { return this.logDateTime; } 
/*    */   public String getLogUserSid() {
/* 21 */     return this.logUserSid;
/*    */   }
/* 23 */   public String getLogBillNo() { return this.logBillNo; } 
/*    */   public String getLogContent() {
/* 25 */     return this.logContent;
/*    */   }
/*    */ 
/*    */   public void setLogSid(String logSid)
/*    */   {
/* 12 */     this.logSid = logSid; } 
/* 12 */   public void setLogDateTime(Timestamp logDateTime) { this.logDateTime = logDateTime; } 
/* 12 */   public void setLogUserSid(String logUserSid) { this.logUserSid = logUserSid; } 
/* 12 */   public void setLogBillNo(String logBillNo) { this.logBillNo = logBillNo; } 
/* 12 */   public void setLogContent(String logContent) { this.logContent = logContent; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof WapPayLogTo)) return false; WapPayLogTo other = (WapPayLogTo)o; if (!other.canEqual(this)) return false; Object this$logSid = getLogSid(); Object other$logSid = other.getLogSid(); if (this$logSid == null ? other$logSid != null : !this$logSid.equals(other$logSid)) return false; Object this$logDateTime = getLogDateTime(); Object other$logDateTime = other.getLogDateTime(); if (this$logDateTime == null ? other$logDateTime != null : !this$logDateTime.equals(other$logDateTime)) return false; Object this$logUserSid = getLogUserSid(); Object other$logUserSid = other.getLogUserSid(); if (this$logUserSid == null ? other$logUserSid != null : !this$logUserSid.equals(other$logUserSid)) return false; Object this$logBillNo = getLogBillNo(); Object other$logBillNo = other.getLogBillNo(); if (this$logBillNo == null ? other$logBillNo != null : !this$logBillNo.equals(other$logBillNo)) return false; Object this$logContent = getLogContent(); Object other$logContent = other.getLogContent(); return this$logContent == null ? other$logContent == null : this$logContent.equals(other$logContent); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof WapPayLogTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $logSid = getLogSid(); result = result * 59 + ($logSid == null ? 0 : $logSid.hashCode()); Object $logDateTime = getLogDateTime(); result = result * 59 + ($logDateTime == null ? 0 : $logDateTime.hashCode()); Object $logUserSid = getLogUserSid(); result = result * 59 + ($logUserSid == null ? 0 : $logUserSid.hashCode()); Object $logBillNo = getLogBillNo(); result = result * 59 + ($logBillNo == null ? 0 : $logBillNo.hashCode()); Object $logContent = getLogContent(); result = result * 59 + ($logContent == null ? 0 : $logContent.hashCode()); return result; } 
/* 12 */   public String toString() { return "WapPayLogTo(logSid=" + getLogSid() + ", logDateTime=" + getLogDateTime() + ", logUserSid=" + getLogUserSid() + ", logBillNo=" + getLogBillNo() + ", logContent=" + getLogContent() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.bill.WapPayLogTo
 * JD-Core Version:    0.6.2
 */