/*    */ package com.jinyi.ihome.module.system;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GroupMenuParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String userSid;
/*    */   private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*    */
/*    */   public void setUserSid(String userSid)
/*    */   {
/* 11 */     this.userSid = userSid;
/*    */   }
/*    */ 
/*    */   public String getUserSid()
/*    */   {
/* 17 */     return this.userSid;
/*    */   }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.system.GroupMenuParam
 * JD-Core Version:    0.6.2
 */