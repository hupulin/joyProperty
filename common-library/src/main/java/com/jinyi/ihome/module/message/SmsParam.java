/*    */ package com.jinyi.ihome.module.message;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SmsParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String to;
/*    */   private String templateId;
/*    */   private String[] datas;
/*    */ 
/*    */   public void setTo(String to)
/*    */   {
/* 13 */     this.to = to; } 
/* 14 */   public String getTo() { return this.to; }
/*    */ 
/*    */   public void setTemplateId(String templateId) {
/* 17 */     this.templateId = templateId; } 
/* 18 */   public String getTemplateId() { return this.templateId; }
/*    */ 
/*    */   public String[] getDatas() {
/* 21 */     return this.datas; } 
/* 22 */   public void setDatas(String[] datas) { this.datas = datas; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.message.SmsParam
 * JD-Core Version:    0.6.2
 */