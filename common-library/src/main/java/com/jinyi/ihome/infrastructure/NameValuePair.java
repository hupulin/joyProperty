/*    */ package com.jinyi.ihome.infrastructure;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class NameValuePair<T, E>
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private T name;
/*    */   private E value;
/*    */ 
/*    */   public NameValuePair()
/*    */   {
/*    */   }
/*    */ 
/*    */   public NameValuePair(T name, E value)
/*    */   {
/* 23 */     this.name = name;
/* 24 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public T getName()
/*    */   {
/* 15 */     return this.name; } 
/* 16 */   public E getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   public void setName(T name)
/*    */   {
/* 10 */     this.name = name; } 
/* 10 */   public void setValue(E value) { this.value = value; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NameValuePair)) return false; NameValuePair other = (NameValuePair)o; if (!other.canEqual(this)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$value = getValue(); Object other$value = other.getValue(); return this$value == null ? other$value == null : this$value.equals(other$value); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof NameValuePair; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $value = getValue(); result = result * 59 + ($value == null ? 0 : $value.hashCode()); return result; } 
/* 10 */   public String toString() { return "NameValuePair(name=" + getName() + ", value=" + getValue() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.infrastructure.NameValuePair
 * JD-Core Version:    0.6.2
 */