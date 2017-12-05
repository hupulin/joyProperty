/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class NeighborCommentFindParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int index;
/*    */   private String postSid;
/*    */ 
/*    */   public void setIndex(int index)
/*    */   {
/* 11 */     this.index = index; } 
/* 11 */   public void setPostSid(String postSid) { this.postSid = postSid; }
/*    */ 
/*    */   public int getIndex()
/*    */   {
/* 15 */     return this.index; } 
/* 16 */   public String getPostSid() { return this.postSid; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborCommentFindParam
 * JD-Core Version:    0.6.2
 */