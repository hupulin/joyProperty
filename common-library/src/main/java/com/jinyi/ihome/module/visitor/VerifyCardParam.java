/*    */ package com.jinyi.ihome.module.visitor;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class VerifyCardParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String userSid;
/*    */   private String cardData;
/*    */   private String cardNo;
/*    */

/*    */   public String getUserSid()
/*    */   {
/* 14 */     return this.userSid;
/*    */   }
/*    */ 
/*    */   public String getCardData() {
/* 18 */     return this.cardData;
/*    */   }
/*    */ 
/*    */   public String getCardNo()
/*    */   {
/* 23 */     return this.cardNo;
/*    */   }
/*    */ 
/*    */   public void setUserSid(String userSid)
/*    */   {
/* 10 */     this.userSid = userSid; } 
/* 10 */   public void setCardData(String cardData) { this.cardData = cardData; } 
/* 10 */   public void setCardNo(String cardNo) { this.cardNo = cardNo; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof VerifyCardParam)) return false; VerifyCardParam other = (VerifyCardParam)o; if (!other.canEqual(this)) return false; Object this$userSid = getUserSid(); Object other$userSid = other.getUserSid(); if (this$userSid == null ? other$userSid != null : !this$userSid.equals(other$userSid)) return false; Object this$cardData = getCardData(); Object other$cardData = other.getCardData(); if (this$cardData == null ? other$cardData != null : !this$cardData.equals(other$cardData)) return false; Object this$cardNo = getCardNo(); Object other$cardNo = other.getCardNo(); return this$cardNo == null ? other$cardNo == null : this$cardNo.equals(other$cardNo); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof VerifyCardParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $userSid = getUserSid(); result = result * 59 + ($userSid == null ? 0 : $userSid.hashCode()); Object $cardData = getCardData(); result = result * 59 + ($cardData == null ? 0 : $cardData.hashCode()); Object $cardNo = getCardNo(); result = result * 59 + ($cardNo == null ? 0 : $cardNo.hashCode()); return result; } 
/* 10 */   public String toString() { return "VerifyCardParam(userSid=" + getUserSid() + ", cardData=" + getCardData() + ", cardNo=" + getCardNo() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.visitor.VerifyCardParam
 * JD-Core Version:    0.6.2
 */