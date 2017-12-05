/*    */ package com.jinyi.ihome.module.vote;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VoteReleaseParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String voteSid;
/*    */ 
/*    */   public void setVoteSid(String voteSid)
/*    */   {
/* 11 */     this.voteSid = voteSid;
/*    */   }
/*    */ 
/*    */   public String getVoteSid() {
/* 15 */     return this.voteSid;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.vote.VoteReleaseParam
 * JD-Core Version:    0.6.2
 */