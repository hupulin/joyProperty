/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceReportFlowParam
/*    */   implements Serializable
/*    */ {
/*    */   private String usid;
/*    */   private String category;
/*    */ 
/*    */   public String getUsid()
/*    */   {
/* 13 */     return this.usid;
/*    */   }
/*    */ 
/*    */   public String getCategory() {
/* 17 */     return this.category;
/*    */   }
/*    */ 
/*    */   public void setUsid(String usid)
/*    */   {
/* 10 */     this.usid = usid; } 
/* 10 */   public void setCategory(String category) { this.category = category; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceReportFlowParam)) return false; ServiceReportFlowParam other = (ServiceReportFlowParam)o; if (!other.canEqual(this)) return false; Object this$usid = getUsid(); Object other$usid = other.getUsid(); if (this$usid == null ? other$usid != null : !this$usid.equals(other$usid)) return false; Object this$category = getCategory(); Object other$category = other.getCategory(); return this$category == null ? other$category == null : this$category.equals(other$category); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ServiceReportFlowParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $usid = getUsid(); result = result * 59 + ($usid == null ? 0 : $usid.hashCode()); Object $category = getCategory(); result = result * 59 + ($category == null ? 0 : $category.hashCode()); return result; } 
/* 10 */   public String toString() { return "ServiceReportFlowParam(usid=" + getUsid() + ", category=" + getCategory() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceReportFlowParam
 * JD-Core Version:    0.6.2
 */