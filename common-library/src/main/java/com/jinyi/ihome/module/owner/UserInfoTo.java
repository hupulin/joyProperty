/*     */
package com.jinyi.ihome.module.owner;
/*     */ 
/*     */

import com.jinyi.ihome.module.group.HomeGroupTo;
import com.jinyi.ihome.module.system.GroupMenuTo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Timestamp;
import java.util.List;

/*     */
/*     */ public class UserInfoTo
/*     */ implements Serializable
/*     */ {
    /*     */   private static final long serialVersionUID = 1L;
    /*     */   private String sid;
    /*     */   private BigDecimal accountBalance;
    /*     */   private String apartmentSid;
    /*     */   private String appPartner;
    /*     */   private String babyTag;
    /*     */   private BigDecimal buildingArea;
    /*     */   private String buildingType;
    /*     */   private String carTag;
    /*     */   private String chargeEndDate;
    /*     */   private String chargeOverdue;
    /*     */   private String chargeStartDate;
    /*     */   private Timestamp createdOn;
    /*     */   private String createdby;
    /*     */   private BigDecimal creditScore;
    /*     */   private String deviceToken;
    /*     */   private String familyName;
    /*     */   private String familyType;
    /*     */   private String homepageImage;
    /*     */   private String inputDate;
    /*     */   private BigDecimal lateFee;
    /*     */   private Timestamp modifiedOn;
    /*     */   private String modifiedby;
    /*     */   private String neighborNotification;
    /*     */   private String noticeNotification;
    /*     */   private String olderTag;
    /*     */   private String birthday;
    /*     */   private String image;
    /*     */   private String name;
    /*     */   private String no;
    /*     */   private String phone;
    /*     */   private int status;
    /*     */   private String tag;
    /*     */   private String type;
    /*     */   private String petTag;
    /*     */   private String purchaseNotification;
    /*     */   private String remark;
    /*     */   private BigDecimal unitPrice;
    /*     */   private String verificationTag;
    /*     */   private HomeGroupTo groupTo;
    /*     */   private String dashboardTag;
    /*     */   private String apartmentTag;
    /*     */   private String shortpy;
                private List<GroupMenuTo> groupMenuToList;
               private String mPinyin;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        UserInfoTo other = (UserInfoTo) obj;
        if (sid != other.getSid()) return false;
        if (name == null) {
            if (other.getName() != null) return false;
        } else if (!name.equals(other.getName())) return false;
        if (phone == null) {
            if (other.getPhone() != null) return false;
        } else if (!phone.equals(other.getPhone())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (sid!=null?sid.hashCode():1)*31+status;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getShortpy() {
        return shortpy;
    }

    public void setShortpy(String shortpy) {
        this.shortpy = shortpy;
    }

    /*     */ private String pinyin;

    /*     */
   /*     */
    public void setSid(String sid)
/*     */ {
/*  17 */
        this.sid = sid;
    }

    /*  18 */
    public String getSid() {
        return this.sid;
    }

    /*     */
/*     */
    public BigDecimal getAccountBalance() {
/*  21 */
        return this.accountBalance;
    }

    /*  22 */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    /*     */
/*     */
    public String getApartmentSid() {
/*  25 */
        return this.apartmentSid;
    }

    /*  26 */
    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    /*     */
/*     */
    public String getAppPartner() {
/*  29 */
        return this.appPartner;
    }

    /*  30 */
    public void setAppPartner(String appPartner) {
        this.appPartner = appPartner;
    }

    /*     */
/*     */
    public String getBabyTag()
/*     */ {
/*  34 */
        return this.babyTag;
    }

    /*  35 */
    public void setBabyTag(String babyTag) {
        this.babyTag = babyTag;
    }

    /*     */
/*     */
    public BigDecimal getBuildingArea()
/*     */ {
/*  39 */
        return this.buildingArea;
    }

    /*  40 */
    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    /*     */
/*     */
    public String getBuildingType()
/*     */ {
/*  44 */
        return this.buildingType;
    }

    /*  45 */
    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    /*     */
/*     */
    public String getCarTag()
/*     */ {
/*  49 */
        return this.carTag;
    }

    /*  50 */
    public void setCarTag(String carTag) {
        this.carTag = carTag;
    }

    /*     */
/*     */
    public String getChargeEndDate()
/*     */ {
/*  54 */
        return this.chargeEndDate;
    }

    /*  55 */
    public void setChargeEndDate(String chargeEndDate) {
        this.chargeEndDate = chargeEndDate;
    }

    /*     */
/*     */
    public String getChargeOverdue()
/*     */ {
/*  59 */
        return this.chargeOverdue;
    }

    /*  60 */
    public void setChargeOverdue(String chargeOverdue) {
        this.chargeOverdue = chargeOverdue;
    }

    /*     */
/*     */
    public String getChargeStartDate()
/*     */ {
/*  64 */
        return this.chargeStartDate;
    }

    /*  65 */
    public void setChargeStartDate(String chargeStartDate) {
        this.chargeStartDate = chargeStartDate;
    }

    /*     */
/*     */
    public Timestamp getCreatedOn()
/*     */ {
/*  69 */
        return this.createdOn;
    }

    /*  70 */
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    /*     */
/*     */
    public String getCreatedby()
/*     */ {
/*  74 */
        return this.createdby;
    }

    /*  75 */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    /*     */
/*     */
    public BigDecimal getCreditScore()
/*     */ {
/*  79 */
        return this.creditScore;
    }

    /*  80 */
    public void setCreditScore(BigDecimal creditScore) {
        this.creditScore = creditScore;
    }

    /*     */
/*     */
    public String getDeviceToken()
/*     */ {
/*  84 */
        return this.deviceToken;
    }

    /*  85 */
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    /*     */
/*     */
    public String getFamilyName()
/*     */ {
/*  89 */
        return this.familyName;
    }

    /*  90 */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /*     */
/*     */
    public String getFamilyType()
/*     */ {
/*  94 */
        return this.familyType;
    }

    /*  95 */
    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }

    /*     */
/*     */
    public String getHomepageImage()
/*     */ {
/*  99 */
        return this.homepageImage;
    }

    /* 100 */
    public void setHomepageImage(String homepageImage) {
        this.homepageImage = homepageImage;
    }

    /*     */
/*     */
    public String getInputDate()
/*     */ {
/* 104 */
        return this.inputDate;
    }

    /* 105 */
    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    /*     */
/*     */
    public BigDecimal getLateFee()
/*     */ {
/* 109 */
        return this.lateFee;
    }

    /* 110 */
    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    /*     */
/*     */
    public Timestamp getModifiedOn()
/*     */ {
/* 114 */
        return this.modifiedOn;
    }

    /* 115 */
    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    /*     */
/*     */
    public String getModifiedby()
/*     */ {
/* 119 */
        return this.modifiedby;
    }

    /* 120 */
    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    /*     */
/*     */
    public String getNeighborNotification()
/*     */ {
/* 124 */
        return this.neighborNotification;
    }

    /* 125 */
    public void setNeighborNotification(String neighborNotification) {
        this.neighborNotification = neighborNotification;
    }

    /*     */
/*     */
    public String getNoticeNotification()
/*     */ {
/* 129 */
        return this.noticeNotification;
    }

    /* 130 */
    public void setNoticeNotification(String noticeNotification) {
        this.noticeNotification = noticeNotification;
    }

    /*     */
/*     */
    public String getOlderTag()
/*     */ {
/* 134 */
        return this.olderTag;
    }

    /* 135 */
    public void setOlderTag(String olderTag) {
        this.olderTag = olderTag;
    }

    /*     */
/*     */
    public String getBirthday()
/*     */ {
/* 139 */
        return this.birthday;
    }

    /* 140 */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /*     */
/*     */
    public String getImage()
/*     */ {
/* 144 */
        return this.image;
    }

    /* 145 */
    public void setImage(String image) {
        this.image = image;
    }

    /*     */
/*     */
    public String getName()
/*     */ {
/* 149 */
        return this.name;
    }

    /* 150 */
    public void setName(String name) {
        this.name = name;
    }

    /*     */
/*     */
    public String getNo()
/*     */ {
/* 154 */
        return this.no;
    }

    /* 155 */
    public void setNo(String no) {
        this.no = no;
    }

    /*     */
/*     */
    public String getPhone()
/*     */ {
/* 159 */
        return this.phone;
    }

    /* 160 */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /*     */
/*     */
    public int getStatus() {
/* 163 */
        return this.status;
    }

    /* 164 */
    public void setStatus(int status) {
        this.status = status;
    }

    /*     */
/*     */
    public String getTag()
/*     */ {
/* 168 */
        return this.tag;
    }

    /* 169 */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /*     */
/*     */
    public String getType()
/*     */ {
/* 173 */
        return this.type;
    }

    /* 174 */
    public void setType(String type) {
        this.type = type;
    }

    /*     */
/*     */
    public String getPetTag()
/*     */ {
/* 178 */
        return this.petTag;
    }

    /* 179 */
    public void setPetTag(String petTag) {
        this.petTag = petTag;
    }

    /*     */
/*     */
    public String getPurchaseNotification()
/*     */ {
/* 183 */
        return this.purchaseNotification;
    }

    /* 184 */
    public void setPurchaseNotification(String purchaseNotification) {
        this.purchaseNotification = purchaseNotification;
    }

    /*     */
/*     */
    public String getRemark()
/*     */ {
/* 188 */
        return this.remark;
    }

    /* 189 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /*     */
/*     */
    public BigDecimal getUnitPrice()
/*     */ {
/* 193 */
        return this.unitPrice;
    }

    /* 194 */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /*     */
/*     */ 
/*     */
    public String getVerificationTag()
/*     */ {
/* 200 */
        return this.verificationTag;
    }

    /* 201 */
    public void setVerificationTag(String verificationTag) {
        this.verificationTag = verificationTag;
    }

    /*     */
/*     */
    public void setGroupTo(HomeGroupTo groupTo) {
/* 204 */
        this.groupTo = groupTo;
    }

    /* 205 */
    public HomeGroupTo getGroupTo() {
        return this.groupTo;
    }

    /*     */
/*     */
    public String getDashboardTag() {
/* 208 */
        return this.dashboardTag;
    }

    /* 209 */
    public void setDashboardTag(String dashboardTag) {
        this.dashboardTag = dashboardTag;
    }

    /*     */
/*     */
    public String getApartmentTag() {
/* 212 */
        return this.apartmentTag;
    }

    /* 213 */
    public void setApartmentTag(String apartmentTag) {
        this.apartmentTag = apartmentTag;
    }
/*     */
/*     */

    public List<GroupMenuTo> getGroupMenuToList() {
        return groupMenuToList;
    }

    public void setGroupMenuToList(List<GroupMenuTo> groupMenuToList) {
        this.groupMenuToList = groupMenuToList;
    }

    public String getmPinyin() {
        return mPinyin;
    }

    public void setmPinyin(String mPinyin) {
        this.mPinyin = mPinyin;
    }

    @Override
    public String toString() {
        return "UserInfoTo{" +
                "sid='" + sid + '\'' +
                ", accountBalance=" + accountBalance +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", appPartner='" + appPartner + '\'' +
                ", babyTag='" + babyTag + '\'' +
                ", buildingArea=" + buildingArea +
                ", buildingType='" + buildingType + '\'' +
                ", carTag='" + carTag + '\'' +
                ", chargeEndDate='" + chargeEndDate + '\'' +
                ", chargeOverdue='" + chargeOverdue + '\'' +
                ", chargeStartDate='" + chargeStartDate + '\'' +
                ", createdOn=" + createdOn +
                ", createdby='" + createdby + '\'' +
                ", creditScore=" + creditScore +
                ", deviceToken='" + deviceToken + '\'' +
                ", familyName='" + familyName + '\'' +
                ", familyType='" + familyType + '\'' +
                ", homepageImage='" + homepageImage + '\'' +
                ", inputDate='" + inputDate + '\'' +
                ", lateFee=" + lateFee +
                ", modifiedOn=" + modifiedOn +
                ", modifiedby='" + modifiedby + '\'' +
                ", neighborNotification='" + neighborNotification + '\'' +
                ", noticeNotification='" + noticeNotification + '\'' +
                ", olderTag='" + olderTag + '\'' +
                ", birthday='" + birthday + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", tag='" + tag + '\'' +
                ", type='" + type + '\'' +
                ", petTag='" + petTag + '\'' +
                ", purchaseNotification='" + purchaseNotification + '\'' +
                ", remark='" + remark + '\'' +
                ", unitPrice=" + unitPrice +
                ", verificationTag='" + verificationTag + '\'' +
                ", groupTo=" + groupTo +
                ", dashboardTag='" + dashboardTag + '\'' +
                ", apartmentTag='" + apartmentTag + '\'' +
                ", shortpy='" + shortpy + '\'' +
                ", groupMenuToList=" + groupMenuToList +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }

}

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.owner.UserInfoTo
 * JD-Core Version:    0.6.2
 */