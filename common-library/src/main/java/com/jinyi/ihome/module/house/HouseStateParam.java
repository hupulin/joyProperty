/*    */ package com.jinyi.ihome.module.house;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class HouseStateParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String sid;
/*    */   private int state;
/*    */   private String userSid;
/*    */ 
/*    */   public void setSid(String sid)
/*    */   {
/* 11 */     this.sid = sid; } 
/* 11 */   public void setState(int state) { this.state = state; } 
/* 11 */   public void setUserSid(String userSid) { this.userSid = userSid; }
/*    */ 
/*    */   public String getSid()
/*    */   {
/* 15 */     return this.sid;
/*    */   }
/* 17 */   public int getState() { return this.state; } 
/*    */   public String getUserSid() {
/* 19 */     return this.userSid;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.house.HouseStateParam
 * JD-Core Version:    0.6.2
 */