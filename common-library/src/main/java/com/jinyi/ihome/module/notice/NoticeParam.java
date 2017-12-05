/*    */ package com.jinyi.ihome.module.notice;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class NoticeParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String noticeTitle;
/*    */   private String noticeImage;
/*    */   private String noticeContent;
/*    */   private String noticePublisher;
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 16 */     return this.apartmentSid;
/*    */   }
/*    */   public String getNoticeTitle() {
/* 19 */     return this.noticeTitle;
/*    */   }
/*    */   public String getNoticeImage() {
/* 22 */     return this.noticeImage;
/*    */   }
/*    */   public String getNoticeContent() {
/* 25 */     return this.noticeContent;
/*    */   }
/*    */   public String getNoticePublisher() {
/* 28 */     return this.noticePublisher;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 10 */     this.apartmentSid = apartmentSid; } 
/* 10 */   public void setNoticeTitle(String noticeTitle) { this.noticeTitle = noticeTitle; } 
/* 10 */   public void setNoticeImage(String noticeImage) { this.noticeImage = noticeImage; } 
/* 10 */   public void setNoticeContent(String noticeContent) { this.noticeContent = noticeContent; } 
/* 10 */   public void setNoticePublisher(String noticePublisher) { this.noticePublisher = noticePublisher; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof NoticeParam)) return false; NoticeParam other = (NoticeParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$noticeTitle = getNoticeTitle(); Object other$noticeTitle = other.getNoticeTitle(); if (this$noticeTitle == null ? other$noticeTitle != null : !this$noticeTitle.equals(other$noticeTitle)) return false; Object this$noticeImage = getNoticeImage(); Object other$noticeImage = other.getNoticeImage(); if (this$noticeImage == null ? other$noticeImage != null : !this$noticeImage.equals(other$noticeImage)) return false; Object this$noticeContent = getNoticeContent(); Object other$noticeContent = other.getNoticeContent(); if (this$noticeContent == null ? other$noticeContent != null : !this$noticeContent.equals(other$noticeContent)) return false; Object this$noticePublisher = getNoticePublisher(); Object other$noticePublisher = other.getNoticePublisher(); return this$noticePublisher == null ? other$noticePublisher == null : this$noticePublisher.equals(other$noticePublisher); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof NoticeParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $noticeTitle = getNoticeTitle(); result = result * 59 + ($noticeTitle == null ? 0 : $noticeTitle.hashCode()); Object $noticeImage = getNoticeImage(); result = result * 59 + ($noticeImage == null ? 0 : $noticeImage.hashCode()); Object $noticeContent = getNoticeContent(); result = result * 59 + ($noticeContent == null ? 0 : $noticeContent.hashCode()); Object $noticePublisher = getNoticePublisher(); result = result * 59 + ($noticePublisher == null ? 0 : $noticePublisher.hashCode()); return result; } 
/* 10 */   public String toString() { return "NoticeParam(apartmentSid=" + getApartmentSid() + ", noticeTitle=" + getNoticeTitle() + ", noticeImage=" + getNoticeImage() + ", noticeContent=" + getNoticeContent() + ", noticePublisher=" + getNoticePublisher() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.notice.NoticeParam
 * JD-Core Version:    0.6.2
 */