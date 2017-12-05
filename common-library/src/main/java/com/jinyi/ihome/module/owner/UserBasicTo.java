/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserBasicTo
/*    */   implements Serializable
/*    */ {
/*    */   private String sid;
/*    */   private String name;
/*    */   private String icon;
/*    */   private String phone;
/*    */ 
/*    */   @JsonIgnore
/*    */   private String type;
/*    */ 
/*    */   public String getSid()
/*    */   {
/* 15 */     return this.sid;
/*    */   }
/* 17 */   public String getName() { return this.name; } 
/*    */   public String getIcon() {
/* 19 */     return this.icon;
/*    */   }
/* 21 */   public String getPhone() { return this.phone; }
/*    */ 
/*    */   public String getType() {
/* 24 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setSid(String sid)
/*    */   {
/* 12 */     this.sid = sid; } 
/* 12 */   public void setName(String name) { this.name = name; } 
/* 12 */   public void setIcon(String icon) { this.icon = icon; } 
/* 12 */   public void setPhone(String phone) { this.phone = phone; } 
/* 12 */   public void setType(String type) { this.type = type; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof UserBasicTo)) return false; UserBasicTo other = (UserBasicTo)o; if (!other.canEqual(this)) return false; Object this$sid = getSid(); Object other$sid = other.getSid(); if (this$sid == null ? other$sid != null : !this$sid.equals(other$sid)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$icon = getIcon(); Object other$icon = other.getIcon(); if (this$icon == null ? other$icon != null : !this$icon.equals(other$icon)) return false; Object this$phone = getPhone(); Object other$phone = other.getPhone(); if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false; Object this$type = getType(); Object other$type = other.getType(); return this$type == null ? other$type == null : this$type.equals(other$type); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof UserBasicTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $sid = getSid(); result = result * 59 + ($sid == null ? 0 : $sid.hashCode()); Object $name = getName(); result = result * 59 + ($name == null ? 0 : $name.hashCode()); Object $icon = getIcon(); result = result * 59 + ($icon == null ? 0 : $icon.hashCode()); Object $phone = getPhone(); result = result * 59 + ($phone == null ? 0 : $phone.hashCode()); Object $type = getType(); result = result * 59 + ($type == null ? 0 : $type.hashCode()); return result; } 
/* 12 */   public String toString() { return "UserBasicTo(sid=" + getSid() + ", name=" + getName() + ", icon=" + getIcon() + ", phone=" + getPhone() + ", type=" + getType() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.UserBasicTo
 * JD-Core Version:    0.6.2
 */