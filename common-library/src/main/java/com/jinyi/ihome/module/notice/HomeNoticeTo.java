/*    */ package com.jinyi.ihome.module.notice;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class HomeNoticeTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String noticeSid;
/*    */   private String apartmentSid;
/*    */   private String noticeTitle;
/*    */   private String noticeImage;
/*    */   private String noticeAbstract;
/*    */   private String noticeContent;
/*    */   private int browseQty;
/*    */   private int commentQty;
/*    */   private Date noticeDate;
/*    */   private List<HomeNoticeCommentTo> commentList;
/*    */ 
/*    */   public void setNoticeSid(String noticeSid)
/*    */   {
/* 18 */     this.noticeSid = noticeSid; } 
/* 19 */   public String getNoticeSid() { return this.noticeSid; }
/*    */ 
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 25 */     this.apartmentSid = apartmentSid; } 
/* 26 */   public String getApartmentSid() { return this.apartmentSid; }
/*    */ 
/*    */ 
/*    */   public void setNoticeTitle(String noticeTitle)
/*    */   {
/* 32 */     this.noticeTitle = noticeTitle; } 
/* 33 */   public String getNoticeTitle() { return this.noticeTitle; }
/*    */ 
/*    */ 
/*    */   public void setNoticeImage(String noticeImage)
/*    */   {
/* 39 */     this.noticeImage = noticeImage; } 
/* 40 */   public String getNoticeImage() { return this.noticeImage; }
/*    */ 
/*    */ 
/*    */   public void setNoticeAbstract(String noticeAbstract)
/*    */   {
/* 46 */     this.noticeAbstract = noticeAbstract; } 
/* 47 */   public String getNoticeAbstract() { return this.noticeAbstract; }
/*    */ 
/*    */ 
/*    */   public void setNoticeContent(String noticeContent)
/*    */   {
/* 53 */     this.noticeContent = noticeContent; } 
/* 54 */   public String getNoticeContent() { return this.noticeContent; }
/*    */ 
/*    */ 
/*    */   public void setBrowseQty(int browseQty)
/*    */   {
/* 60 */     this.browseQty = browseQty; } 
/* 61 */   public int getBrowseQty() { return this.browseQty; }
/*    */ 
/*    */ 
/*    */   public void setCommentQty(int commentQty)
/*    */   {
/* 67 */     this.commentQty = commentQty; } 
/* 68 */   public int getCommentQty() { return this.commentQty; }
/*    */ 
/*    */ 
/*    */   public void setNoticeDate(Date noticeDate)
/*    */   {
/* 74 */     this.noticeDate = noticeDate; } 
/* 75 */   public Date getNoticeDate() { return this.noticeDate; }
/*    */ 
/*    */ 
/*    */   public void setCommentList(List<HomeNoticeCommentTo> commentList)
/*    */   {
/* 81 */     this.commentList = commentList; } 
/* 82 */   public List<HomeNoticeCommentTo> getCommentList() { return this.commentList; }

    @Override
    public String toString() {
        return "HomeNoticeTo{" +
                "noticeSid='" + noticeSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeImage='" + noticeImage + '\'' +
                ", noticeAbstract='" + noticeAbstract + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", browseQty=" + browseQty +
                ", commentQty=" + commentQty +
                ", noticeDate=" + noticeDate +
                ", commentList=" + commentList +
                '}';
    }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.notice.HomeNoticeTo
 * JD-Core Version:    0.6.2
 */