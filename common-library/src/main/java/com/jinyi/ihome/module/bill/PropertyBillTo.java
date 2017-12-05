/*    */ package com.jinyi.ihome.module.bill;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class PropertyBillTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String billSid;
/*    */   private String apartmentSid;
/*    */   private String billName;
/*    */   private String billDesc;
/*    */   private String roomNo;
/*    */   private int billStatus;
/*    */   private double billMoney;
/*    */   private String billCreatedTime;
/*    */   private Integer payType;
/*    */   private String payBill;
/*    */   private String payResult;
/*    */   private Date payDateTime;
/*    */   private String payOwnerSid;
/*    */   private String billDateSpan;
           private String billItemNO;
           private String billMainSid;
private String userSid;

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public String getBillDateSpan() {
        return billDateSpan;
    }

    public void setBillDateSpan(String billDateSpan) {
        this.billDateSpan = billDateSpan;
    }

    public String getBillItemNO() {
        return billItemNO;
    }

    public void setBillItemNO(String billItemNO) {
        this.billItemNO = billItemNO;
    }



    public String getBillCreatedTime() {
        return billCreatedTime;
    }

    public void setBillCreatedTime(String billCreatedTime) {
        this.billCreatedTime = billCreatedTime;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillMainSid() {
        return billMainSid;
    }

    public void setBillMainSid(String billMainSid) {
        this.billMainSid = billMainSid;
    }

    public double getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(double billMoney) {
        this.billMoney = billMoney;
    }

    /*    */   public String getBillSid()
/*    */   {
/* 18 */     return this.billSid;
/*    */   }
/* 20 */   public String getApartmentSid() { return this.apartmentSid; }
/*    */
/*    */   public String getBillName()
/*    */   {
/* 24 */     return this.billName;
/*    */   }
/* 26 */   public String getBillDesc() { return this.billDesc; }
/*    */   public String getRoomNo() {
/* 28 */     return this.roomNo;
/*    */   }
/*    */
/*    */
/*    */
/*    */
/*    */   public Integer getPayType()
/*    */   {
/* 46 */     return this.payType;
/*    */   }
/* 48 */   public String getPayBill() { return this.payBill; }
/*    */   public String getPayResult() {
/* 50 */     return this.payResult;
/*    */   }
/* 52 */   public Date getPayDateTime() { return this.payDateTime; }
/*    */   public String getPayOwnerSid() {
/* 54 */     return this.payOwnerSid;
/*    */   }
/*    */
/*    */   public void setBillSid(String billSid)
/*    */   {
/* 13 */     this.billSid = billSid; }
/* 13 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; }
/* 13 */   public void setBillName(String billName) { this.billName = billName; }
/* 13 */   public void setBillDesc(String billDesc) { this.billDesc = billDesc; }
/* 13 */   public void setRoomNo(String roomNo) { this.roomNo = roomNo; }
/* 13 */   public void setPayType(Integer payType) { this.payType = payType; }
/* 13 */   public void setPayBill(String payBill) { this.payBill = payBill; }
/* 13 */   public void setPayResult(String payResult) { this.payResult = payResult; }
/* 13 */   public void setPayDateTime(Date payDateTime) { this.payDateTime = payDateTime; }
/* 13 */   public void setPayOwnerSid(String payOwnerSid) { this.payOwnerSid = payOwnerSid; }
/* 13 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof PropertyBillTo)) return false; PropertyBillTo other = (PropertyBillTo)o; if (!other.canEqual(this)) return false; Object this$billSid = getBillSid(); Object other$billSid = other.getBillSid(); if (this$billSid == null ? other$billSid != null : !this$billSid.equals(other$billSid)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$billName = getBillName(); Object other$billName = other.getBillName(); if (this$billName == null ? other$billName != null : !this$billName.equals(other$billName)) return false; Object this$billDesc = getBillDesc(); Object other$billDesc = other.getBillDesc(); if (this$billDesc == null ? other$billDesc != null : !this$billDesc.equals(other$billDesc)) return false; Object this$roomNo = getRoomNo(); Object other$roomNo = other.getRoomNo(); if (this$roomNo == null ? other$roomNo != null : !this$roomNo.equals(other$roomNo)) return false; Object this$billStatus = getBillStatus(); Object other$billStatus = other.getBillStatus(); if (this$billStatus == null ? other$billStatus != null : !this$billStatus.equals(other$billStatus)) return false; Object this$billMoney = getBillMoney(); Object other$billMoney = other.getBillMoney(); if (this$billMoney == null ? other$billMoney != null : !this$billMoney.equals(other$billMoney)) return false; Object this$billCreatedTime = getBillCreatedTime(); Object other$billCreatedTime = other.getBillCreatedTime(); if (this$billCreatedTime == null ? other$billCreatedTime != null : !this$billCreatedTime.equals(other$billCreatedTime)) return false; Object this$payType = getPayType(); Object other$payType = other.getPayType(); if (this$payType == null ? other$payType != null : !this$payType.equals(other$payType)) return false; Object this$payBill = getPayBill(); Object other$payBill = other.getPayBill(); if (this$payBill == null ? other$payBill != null : !this$payBill.equals(other$payBill)) return false; Object this$payResult = getPayResult(); Object other$payResult = other.getPayResult(); if (this$payResult == null ? other$payResult != null : !this$payResult.equals(other$payResult)) return false; Object this$payDateTime = getPayDateTime(); Object other$payDateTime = other.getPayDateTime(); if (this$payDateTime == null ? other$payDateTime != null : !this$payDateTime.equals(other$payDateTime)) return false; Object this$payOwnerSid = getPayOwnerSid(); Object other$payOwnerSid = other.getPayOwnerSid(); return this$payOwnerSid == null ? other$payOwnerSid == null : this$payOwnerSid.equals(other$payOwnerSid); }
/* 13 */   protected boolean canEqual(Object other) { return other instanceof PropertyBillTo; }
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $billSid = getBillSid(); result = result * 59 + ($billSid == null ? 0 : $billSid.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $billName = getBillName(); result = result * 59 + ($billName == null ? 0 : $billName.hashCode()); Object $billDesc = getBillDesc(); result = result * 59 + ($billDesc == null ? 0 : $billDesc.hashCode()); Object $roomNo = getRoomNo(); result = result * 59 + ($roomNo == null ? 0 : $roomNo.hashCode()); Object $billStatus = getBillStatus(); result = result * 59 + ($billStatus == null ? 0 : $billStatus.hashCode()); Object $billMoney = getBillMoney(); result = result * 59 + ($billMoney == null ? 0 : $billMoney.hashCode()); Object $billCreatedTime = getBillCreatedTime(); result = result * 59 + ($billCreatedTime == null ? 0 : $billCreatedTime.hashCode()); Object $payType = getPayType(); result = result * 59 + ($payType == null ? 0 : $payType.hashCode()); Object $payBill = getPayBill(); result = result * 59 + ($payBill == null ? 0 : $payBill.hashCode()); Object $payResult = getPayResult(); result = result * 59 + ($payResult == null ? 0 : $payResult.hashCode()); Object $payDateTime = getPayDateTime(); result = result * 59 + ($payDateTime == null ? 0 : $payDateTime.hashCode()); Object $payOwnerSid = getPayOwnerSid(); result = result * 59 + ($payOwnerSid == null ? 0 : $payOwnerSid.hashCode()); return result; }
/*    */

    @Override
    public String toString() {
        return "PropertyBillTo{" +
                "billSid='" + billSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", billName='" + billName + '\'' +
                ", billDesc='" + billDesc + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", billStatus=" + billStatus +
                ", billMoney=" + billMoney +
                ", billCreatedTime=" + billCreatedTime +
                ", payType=" + payType +
                ", payBill='" + payBill + '\'' +
                ", payResult='" + payResult + '\'' +
                ", payDateTime=" + payDateTime +
                ", payOwnerSid='" + payOwnerSid + '\'' +
                ", billDateSpan='" + billDateSpan + '\'' +
                ", billItemNO='" + billItemNO + '\'' +
                ", billMainSid='" + billMainSid + '\'' +
                ", userSid='" + userSid + '\'' +
                '}';
    }

  /*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.bill.PropertyBillTo
 * JD-Core Version:    0.6.2
 */