/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class UserMessageParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String ownerSid;
/*    */   private String msgTopic;
/*    */   private String msgContent;
/*    */   private String msgSourceTag;
/*    */   private String msgSourceSid;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 15 */     return this.apartmentSid;
/*    */   }
/* 17 */   public String getOwnerSid() { return this.ownerSid; } 
/*    */   public String getMsgTopic() {
/* 19 */     return this.msgTopic;
/*    */   }
/* 21 */   public String getMsgContent() { return this.msgContent; }
/*    */ 
/*    */ 
/*    */   public String getMsgSourceTag()
/*    */   {
/* 26 */     return this.msgSourceTag;
/*    */   }
/* 28 */   public String getMsgSourceSid() { return this.msgSourceSid; }
/*    */ 
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 11 */   public void setMsgTopic(String msgTopic) { this.msgTopic = msgTopic; } 
/* 11 */   public void setMsgContent(String msgContent) { this.msgContent = msgContent; } 
/* 11 */   public void setMsgSourceTag(String msgSourceTag) { this.msgSourceTag = msgSourceTag; } 
/* 11 */   public void setMsgSourceSid(String msgSourceSid) { this.msgSourceSid = msgSourceSid; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof UserMessageParam)) return false; UserMessageParam other = (UserMessageParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$msgTopic = getMsgTopic(); Object other$msgTopic = other.getMsgTopic(); if (this$msgTopic == null ? other$msgTopic != null : !this$msgTopic.equals(other$msgTopic)) return false; Object this$msgContent = getMsgContent(); Object other$msgContent = other.getMsgContent(); if (this$msgContent == null ? other$msgContent != null : !this$msgContent.equals(other$msgContent)) return false; Object this$msgSourceTag = getMsgSourceTag(); Object other$msgSourceTag = other.getMsgSourceTag(); if (this$msgSourceTag == null ? other$msgSourceTag != null : !this$msgSourceTag.equals(other$msgSourceTag)) return false; Object this$msgSourceSid = getMsgSourceSid(); Object other$msgSourceSid = other.getMsgSourceSid(); return this$msgSourceSid == null ? other$msgSourceSid == null : this$msgSourceSid.equals(other$msgSourceSid); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof UserMessageParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $msgTopic = getMsgTopic(); result = result * 59 + ($msgTopic == null ? 0 : $msgTopic.hashCode()); Object $msgContent = getMsgContent(); result = result * 59 + ($msgContent == null ? 0 : $msgContent.hashCode()); Object $msgSourceTag = getMsgSourceTag(); result = result * 59 + ($msgSourceTag == null ? 0 : $msgSourceTag.hashCode()); Object $msgSourceSid = getMsgSourceSid(); result = result * 59 + ($msgSourceSid == null ? 0 : $msgSourceSid.hashCode()); return result; } 
/* 11 */   public String toString() { return "UserMessageParam(apartmentSid=" + getApartmentSid() + ", ownerSid=" + getOwnerSid() + ", msgTopic=" + getMsgTopic() + ", msgContent=" + getMsgContent() + ", msgSourceTag=" + getMsgSourceTag() + ", msgSourceSid=" + getMsgSourceSid() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.UserMessageParam
 * JD-Core Version:    0.6.2
 */