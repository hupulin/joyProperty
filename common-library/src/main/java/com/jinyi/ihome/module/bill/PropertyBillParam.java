/*    */ package com.jinyi.ihome.module.bill;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PropertyBillParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int pageIndex;
/*    */   private String userSid;
/*    */ 
/*    */   public int getPageIndex()
/*    */   {
/* 16 */     return this.pageIndex;
/*    */   }
/* 18 */   public String getUserSid() { return this.userSid; }
/*    */ 
/*    */ 
/*    */   public void setPageIndex(int pageIndex)
/*    */   {
/* 11 */     this.pageIndex = pageIndex; } 
/* 11 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 11 */   public String toString() { return "PropertyBillParam(pageIndex=" + getPageIndex() + ", userSid=" + getUserSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.bill.PropertyBillParam
 * JD-Core Version:    0.6.2
 */