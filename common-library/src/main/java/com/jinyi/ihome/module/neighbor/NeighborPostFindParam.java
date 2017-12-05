/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */

import java.io.Serializable;
import java.util.Date;

/*    */

/*    */
/*    */ public class NeighborPostFindParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String postSid;
/*    */   private String userSid;
/*    */   private Date lastTime;
/*    */   private int index;
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 13 */     this.apartmentSid = apartmentSid; } 
/* 13 */   public void setPostSid(String postSid) { this.postSid = postSid; } 
/* 13 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 13 */   public void setLastTime(Date lastTime) { this.lastTime = lastTime; } 
/* 13 */   public void setIndex(int index) { this.index = index; }
/*    */ 
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 22 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getPostSid()
/*    */   {
/* 28 */     return this.postSid;
/*    */   }
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 33 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public Date getLastTime()
/*    */   {
/* 39 */     return this.lastTime;
/*    */   }
/*    */ 
/*    */   public int getIndex()
/*    */   {
/* 44 */     return this.index;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborPostFindParam
 * JD-Core Version:    0.6.2
 */