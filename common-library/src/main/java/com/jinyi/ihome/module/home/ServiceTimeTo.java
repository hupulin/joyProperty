/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ServiceTimeTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String day;
/*    */   private String date;
/*    */   private List<String> time;
/*    */ 
/*    */   public String getDay()
/*    */   {
/* 20 */     return this.day;
/*    */   }
/*    */ 
/*    */   public String getDate()
/*    */   {
/* 25 */     return this.date;
/*    */   }
/*    */ 
/*    */   public List<String> getTime() {
/* 29 */     return this.time;
/*    */   }
/*    */ 
/*    */   public void setDay(String day)
/*    */   {
/* 13 */     this.day = day; } 
/* 13 */   public void setDate(String date) { this.date = date; } 
/* 13 */   public void setTime(List<String> time) { this.time = time; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceTimeTo
 * JD-Core Version:    0.6.2
 */