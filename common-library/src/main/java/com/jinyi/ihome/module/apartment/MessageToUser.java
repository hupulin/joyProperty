/*    */ package com.jinyi.ihome.module.apartment;
/*    */ 
/*    */ import com.jinyi.ihome.module.owner.UserInfoTo;

import java.io.Serializable;

/*    */
/*    */ public class MessageToUser
/*    */   implements Serializable
/*    */ {
/*    */   public static final String DATA_ABORT = "数据异常";
/*    */   public static final String PARAMETER_INVALID = "参数异常";
/*    */   private static final long serialVersionUID = -7124170122394232970L;
/*    */   private int success;
/*    */   private String message;
/*    */   private int total;
/*    */   private UserInfoTo data;
/*    */   private Object tag;
/*    */    private UserInfoTo userInfoTo;
/*    */   public MessageToUser()
/*    */   {
/* 47 */     this(0, "");
/*    */   }
/*    */
/*    */
/*    */
/*    */   public MessageToUser(int success, String desc) {
/* 56 */     this.success = success;
/* 57 */     this.message = desc;
/*    */   }
/*    */ 
/*    */   public MessageToUser success(String desc) {
/* 61 */     this.message = desc;
/* 62 */     this.success = 0;
/* 63 */     return this;
/*    */   }
/*    */ 
/*    */   public MessageToUser fail(String desc) {
/* 67 */     this.message = desc;
/* 68 */     this.success = 1;
/* 69 */     return this;
/*    */   }
/*    */ 
/*    */   public MessageToUser fail(int status, String desc) {
/* 73 */     this.message = desc;
/* 74 */     this.success = status;
/* 75 */     return this;
/*    */   }
/*    */ 
/*    */

    public UserInfoTo getData() {
        return data;
    }

    public void setData(UserInfoTo data) {
        this.data = data;
    }

    /*    */
/*    */   public String toString()
/*    */   {
/* 88 */     return String.format("成功：%s, 描述 ：%s, Data:%s", new Object[] { Integer.valueOf(this.success), this.message, this.data == null ? "null" : this.data.toString() });
/*    */   }
/*    */ 
/*    */   public void setSuccess(int success)
/*    */   {
/* 21 */     this.success = success; } 
/* 21 */   public int getSuccess() { return this.success; }
/*    */ 
/*    */ 
/*    */   public void setMessage(String message)
/*    */   {
/* 26 */     this.message = message; } 
/* 26 */   public String getMessage() { return this.message; }
/*    */ 
/*    */ 
/*    */   public void setTotal(int total)
/*    */   {
/* 32 */     this.total = total; } 
/* 33 */   public int getTotal() { return this.total; }
/*    */ 
/*    */ 
/*    */
/*    */

    public UserInfoTo getUserInfoTo() {
        return userInfoTo;
    }

    public void setUserInfoTo(UserInfoTo userInfoTo) {
        this.userInfoTo = userInfoTo;
    }

    /*    */   public void setTag(Object tag) {
/* 43 */     this.tag = tag; } 
/* 43 */   public Object getTag() { return this.tag; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.infrastructure.MessageTo
 * JD-Core Version:    0.6.2
 */