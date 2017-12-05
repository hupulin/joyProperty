/*    */ package com.jinyi.ihome.infrastructure;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class MessageTo<T>
/*    */   implements Serializable
/*    */ {
/*    */   public static final String DATA_ABORT = "数据异常";
/*    */   public static final String PARAMETER_INVALID = "参数异常";
/*    */   private static final long serialVersionUID = -7124170122394232970L;
/*    */   private int success;
/*    */   private String message;
/*    */   private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /*    */   private int    total;
/*    */   private     T      data;
/*    */   private     Object tag;
/*    */ 
/*    */   public MessageTo()
/*    */   {
/* 47 */     this(0, "");
/*    */   }
/*    */ 
/*    */   public MessageTo(T t) {
/* 51 */     this(0, "");
/* 52 */     this.data = t;
/*    */   }
/*    */ 
/*    */   public MessageTo(int success, String desc) {
/* 56 */     this.success = success;
/* 57 */     this.message = desc;
/*    */   }
/*    */ 
/*    */   public MessageTo success(String desc) {
/* 61 */     this.message = desc;
/* 62 */     this.success = 0;
/* 63 */     return this;
/*    */   }
/*    */ 
/*    */   public MessageTo fail(String desc) {
/* 67 */     this.message = desc;
/* 68 */     this.success = 1;
/* 69 */     return this;
/*    */   }
/*    */ 
/*    */   public MessageTo fail(int status, String desc) {
/* 73 */     this.message = desc;
/* 74 */     this.success = status;
/* 75 */     return this;
/*    */   }
/*    */ 
/*    */   public MessageTo setData(T data) {
/* 79 */     this.data = data;
/* 80 */     return this;
/*    */   }
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
/*    */   public T getData()
/*    */   {
/* 39 */     return this.data;
/*    */   }
/*    */ 
/*    */   public void setTag(Object tag) {
/* 43 */     this.tag = tag; } 
/* 43 */   public Object getTag() { return this.tag; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.infrastructure.MessageTo
 * JD-Core Version:    0.6.2
 */