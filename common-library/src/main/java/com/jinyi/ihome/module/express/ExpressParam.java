/*    */ package com.jinyi.ihome.module.express;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ExpressParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String ownerSid;
/*    */   private String roomNo;
/*    */   private String expressCompany;
/*    */   private String expressNo;
/*    */   private String userSid;
/*    */   private String expressPhone;
/*    */   private String expressRemark;
/*    */   private String userName;
/*    */   private String expressImages;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExpressImages() {
        return expressImages;
    }

    public void setExpressImages(String expressImages) {
        this.expressImages = expressImages;
    }

    /*     userName false string 姓名
        expressImages
        */
    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public ExpressParam() {

    }

    /*    */
/*    */   public String getApartmentSid()
/*    */   {
/* 16 */     return this.apartmentSid;
/*    */   }
/* 18 */   public String getOwnerSid() { return this.ownerSid; } 
/*    */   public String getRoomNo() {
/* 20 */     return this.roomNo;
/*    */   }
/* 22 */   public String getExpressCompany() { return this.expressCompany; } 
/*    */   public String getExpressNo() {
/* 24 */     return this.expressNo;
/*    */   }
/* 26 */   public String getExpressPhone() { return this.expressPhone; } 
/*    */   public String getExpressRemark() {
/* 28 */     return this.expressRemark;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setOwnerSid(String ownerSid) { this.ownerSid = ownerSid; } 
/* 11 */   public void setRoomNo(String roomNo) { this.roomNo = roomNo; } 
/* 11 */   public void setExpressCompany(String expressCompany) { this.expressCompany = expressCompany; } 
/* 11 */   public void setExpressNo(String expressNo) { this.expressNo = expressNo; } 
/* 11 */   public void setExpressPhone(String expressPhone) { this.expressPhone = expressPhone; } 
/* 11 */   public void setExpressRemark(String expressRemark) { this.expressRemark = expressRemark; } 
/* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ExpressParam)) return false; ExpressParam other = (ExpressParam)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$ownerSid = getOwnerSid(); Object other$ownerSid = other.getOwnerSid(); if (this$ownerSid == null ? other$ownerSid != null : !this$ownerSid.equals(other$ownerSid)) return false; Object this$roomNo = getRoomNo(); Object other$roomNo = other.getRoomNo(); if (this$roomNo == null ? other$roomNo != null : !this$roomNo.equals(other$roomNo)) return false; Object this$expressCompany = getExpressCompany(); Object other$expressCompany = other.getExpressCompany(); if (this$expressCompany == null ? other$expressCompany != null : !this$expressCompany.equals(other$expressCompany)) return false; Object this$expressNo = getExpressNo(); Object other$expressNo = other.getExpressNo(); if (this$expressNo == null ? other$expressNo != null : !this$expressNo.equals(other$expressNo)) return false; Object this$expressPhone = getExpressPhone(); Object other$expressPhone = other.getExpressPhone(); if (this$expressPhone == null ? other$expressPhone != null : !this$expressPhone.equals(other$expressPhone)) return false; Object this$expressRemark = getExpressRemark(); Object other$expressRemark = other.getExpressRemark(); return this$expressRemark == null ? other$expressRemark == null : this$expressRemark.equals(other$expressRemark); } 
/* 11 */   protected boolean canEqual(Object other) { return other instanceof ExpressParam; } 
/* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $ownerSid = getOwnerSid(); result = result * 59 + ($ownerSid == null ? 0 : $ownerSid.hashCode()); Object $roomNo = getRoomNo(); result = result * 59 + ($roomNo == null ? 0 : $roomNo.hashCode()); Object $expressCompany = getExpressCompany(); result = result * 59 + ($expressCompany == null ? 0 : $expressCompany.hashCode()); Object $expressNo = getExpressNo(); result = result * 59 + ($expressNo == null ? 0 : $expressNo.hashCode()); Object $expressPhone = getExpressPhone(); result = result * 59 + ($expressPhone == null ? 0 : $expressPhone.hashCode()); Object $expressRemark = getExpressRemark(); result = result * 59 + ($expressRemark == null ? 0 : $expressRemark.hashCode()); return result; }

    @Override
    public String toString() {
        return "ExpressParam{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", expressCompany='" + expressCompany + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", userSid='" + userSid + '\'' +
                ", expressPhone='" + expressPhone + '\'' +
                ", expressRemark='" + expressRemark + '\'' +
                ", userName='" + userName + '\'' +
                ", expressImages='" + expressImages + '\'' +
                '}';
    }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.express.ExpressParam
 * JD-Core Version:    0.6.2
 */