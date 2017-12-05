/*    */ package com.jinyi.ihome.module.owner;
/*    */ 
/*    */ import com.jinyi.ihome.module.apartment.ApartmentPlaceTo;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class RegisterParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ApartmentPlaceTo placeTo;
/*    */   private String apartmentSid;
/*    */   private String nickname;
/*    */   private String no;
/*    */   private String pwd;
/*    */   private int type;
/*    */   private String mobileNo;
/*    */ 
/*    */   public void setPlaceTo(ApartmentPlaceTo placeTo)
/*    */   {
/* 19 */     this.placeTo = placeTo; } 
/* 20 */   public ApartmentPlaceTo getPlaceTo() { return this.placeTo; }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid) {
/* 23 */     this.apartmentSid = apartmentSid; } 
/* 24 */   public String getApartmentSid() { return this.apartmentSid; }
/*    */ 
/*    */ 
/*    */   public void setNickname(String nickname)
/*    */   {
/* 33 */     this.nickname = nickname; } 
/* 34 */   public String getNickname() { return this.nickname; }
/*    */ 
/*    */ 
/*    */   public String getNo()
/*    */   {
/* 40 */     return this.no; } 
/* 41 */   public void setNo(String no) { this.no = no; }
/*    */ 
/*    */ 
/*    */   public void setPwd(String pwd)
/*    */   {
/* 47 */     this.pwd = pwd; } 
/* 48 */   public String getPwd() { return this.pwd; }
/*    */ 
/*    */ 
/*    */   public void setType(int type)
/*    */   {
/* 54 */     this.type = type; } 
/* 55 */   public int getType() { return this.type; }
/*    */ 
/*    */ 
/*    */   public String getMobileNo()
/*    */   {
/* 61 */     return this.mobileNo; } 
/* 62 */   public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.RegisterParam
 * JD-Core Version:    0.6.2
 */