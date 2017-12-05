/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class UserMessageTo
/*    */   implements Serializable
/*    */ {
/*    */   private String msgSid;
/*    */   private String apartmentSid;
/*    */   private String ownerSid;
/*    */   private String msgTopic;
/*    */   private String msgContent;
/*    */   private String msgSourceTag;
/*    */   private String msgSourceSid;
/*    */   private String msgStatus;
/*    */   private Date msgTime;
/*    */ 
/*    */   public String getMsgSid()
/*    */   {
/* 16 */     return this.msgSid;
/*    */   }
/* 18 */   public String getApartmentSid() { return this.apartmentSid; } 
/*    */   public String getOwnerSid() {
/* 20 */     return this.ownerSid;
/*    */   }
/* 22 */   public String getMsgTopic() { return this.msgTopic; } 
/*    */   public String getMsgContent() {
/* 24 */     return this.msgContent;
/*    */   }
/*    */ 
/*    */   public String getMsgSourceTag()
/*    */   {
/* 29 */     return this.msgSourceTag;
/*    */   }
/* 31 */   public String getMsgSourceSid() { return this.msgSourceSid; } 
/*    */   public String getMsgStatus() {
/* 33 */     return this.msgStatus;
/*    */   }
/* 35 */   public Date getMsgTime() { return this.msgTime; }
/*    */ 
/*    */ 
/*    */   public void setMsgSid(String msgSid)
/*    */   {
/* 13 */     this.msgSid = msgSid; } 
/* 13 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 13 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 13 */   public void setMsgTopic(String msgTopic) { this.msgTopic = msgTopic; } 
/* 13 */   public void setMsgContent(String msgContent) { this.msgContent = msgContent; } 
/* 13 */   public void setMsgSourceTag(String msgSourceTag) { this.msgSourceTag = msgSourceTag; } 
/* 13 */   public void setMsgSourceSid(String msgSourceSid) { this.msgSourceSid = msgSourceSid; } 
/* 13 */   public void setMsgStatus(String msgStatus) { this.msgStatus = msgStatus; } 
/* 13 */   public void setMsgTime(Date msgTime) { this.msgTime = msgTime; } 
/* 13 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof UserMessageTo)) return false; UserMessageTo other = (UserMessageTo)o; if (!other.canEqual(this)) return false; Object this$msgSid = getMsgSid(); Object other$msgSid = other.getMsgSid(); if (this$msgSid == null ? other$msgSid != null : !this$msgSid.equals(other$msgSid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$msgTopic = getMsgTopic(); Object other$msgTopic = other.getMsgTopic(); if (this$msgTopic == null ? other$msgTopic != null : !this$msgTopic.equals(other$msgTopic)) return false; Object this$msgContent = getMsgContent(); Object other$msgContent = other.getMsgContent(); if (this$msgContent == null ? other$msgContent != null : !this$msgContent.equals(other$msgContent)) return false; Object this$msgSourceTag = getMsgSourceTag(); Object other$msgSourceTag = other.getMsgSourceTag(); if (this$msgSourceTag == null ? other$msgSourceTag != null : !this$msgSourceTag.equals(other$msgSourceTag)) return false; Object this$msgSourceSid = getMsgSourceSid(); Object other$msgSourceSid = other.getMsgSourceSid(); if (this$msgSourceSid == null ? other$msgSourceSid != null : !this$msgSourceSid.equals(other$msgSourceSid)) return false; Object this$msgStatus = getMsgStatus(); Object other$msgStatus = other.getMsgStatus(); if (this$msgStatus == null ? other$msgStatus != null : !this$msgStatus.equals(other$msgStatus)) return false; Object this$msgTime = getMsgTime(); Object other$msgTime = other.getMsgTime(); return this$msgTime == null ? other$msgTime == null : this$msgTime.equals(other$msgTime); } 
/* 13 */   protected boolean canEqual(Object other) { return other instanceof UserMessageTo; } 
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $msgSid = getMsgSid(); result = result * 59 + ($msgSid == null ? 0 : $msgSid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $msgTopic = getMsgTopic(); result = result * 59 + ($msgTopic == null ? 0 : $msgTopic.hashCode()); Object $msgContent = getMsgContent(); result = result * 59 + ($msgContent == null ? 0 : $msgContent.hashCode()); Object $msgSourceTag = getMsgSourceTag(); result = result * 59 + ($msgSourceTag == null ? 0 : $msgSourceTag.hashCode()); Object $msgSourceSid = getMsgSourceSid(); result = result * 59 + ($msgSourceSid == null ? 0 : $msgSourceSid.hashCode()); Object $msgStatus = getMsgStatus(); result = result * 59 + ($msgStatus == null ? 0 : $msgStatus.hashCode()); Object $msgTime = getMsgTime(); result = result * 59 + ($msgTime == null ? 0 : $msgTime.hashCode()); return result; } 
/* 13 */   public String toString() { return "UserMessageTo(msgSid=" + getMsgSid() + ", apartmentSid=" + getApartmentSid() + ", ownerSid=" + getOwnerSid() + ", msgTopic=" + getMsgTopic() + ", msgContent=" + getMsgContent() + ", msgSourceTag=" + getMsgSourceTag() + ", msgSourceSid=" + getMsgSourceSid() + ", msgStatus=" + getMsgStatus() + ", msgTime=" + getMsgTime() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.UserMessageTo
 * JD-Core Version:    0.6.2
 */