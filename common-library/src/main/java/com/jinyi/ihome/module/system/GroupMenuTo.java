/*    */ package com.jinyi.ihome.module.system;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GroupMenuTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String sid;
/*    */   private String code;
/*    */   private String name;
/*    */   private String icon;
/*    */ 
/*    */   public void setSid(String sid)
/*    */   {
/* 11 */     this.sid = sid; } 
/* 11 */   public void setCode(String code) { this.code = code; } 
/* 11 */   public void setName(String name) { this.name = name; } 
/* 11 */   public void setIcon(String icon) { this.icon = icon; }
/*    */ 
/*    */ 
/*    */   public String getSid()
/*    */   {
/* 16 */     return this.sid;
/*    */   }
/* 18 */   public String getCode() { return this.code; } 
/*    */   public String getName() {
/* 20 */     return this.name;
/*    */   }
/* 22 */   public String getIcon() { return this.icon; }
/*    */

    @Override
    public String toString() {
        return "GroupMenuTo{" +
                "sid='" + sid + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.system.GroupMenuTo
 * JD-Core Version:    0.6.2
 */