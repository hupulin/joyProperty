/*    */ package com.jinyi.ihome.module.apartment;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class HomeMenuTo
/*    */   implements Serializable
/*    */ {
/*    */   private Map<String, String> modelShop;
/*    */   private Map<String, String> modelExpress;
/*    */ 
/*    */   public void setModelShop(Map<String, String> modelShop)
/*    */   {
/* 12 */     this.modelShop = modelShop; } 
/* 12 */   public void setModelExpress(Map<String, String> modelExpress) { this.modelExpress = modelExpress; }
/*    */ 
/*    */ 
/*    */   public Map<String, String> getModelShop()
/*    */   {
/* 21 */     return this.modelShop;
/*    */   }
/*    */ 
/*    */   public Map<String, String> getModelExpress()
/*    */   {
/* 26 */     return this.modelExpress;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.apartment.HomeMenuTo
 * JD-Core Version:    0.6.2
 */