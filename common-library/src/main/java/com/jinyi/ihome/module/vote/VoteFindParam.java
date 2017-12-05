/*    */ package com.jinyi.ihome.module.vote;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VoteFindParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private int index;
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setIndex(int index) { this.index = index; }
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 15 */     return this.apartmentSid;
/*    */   }
/* 17 */   public int getIndex() { return this.index; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.vote.VoteFindParam
 * JD-Core Version:    0.6.2
 */