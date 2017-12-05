/*    */ package com.jinyi.ihome.module.store;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class StoreTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String aroundSid;
/*    */   private String address;
/*    */   private String apartmentSid;
/*    */   private String businessAdd;
/*    */   private String businessIcon;
/*    */   private String businessImage;
/*    */   private String businessName;
/*    */   private String businessHours;
/*    */   private Integer callQty;
/*    */   private String categoryName;
/*    */   private String contacts;
/*    */   private String contentsDesc;
/*    */   private String contents;
/*    */   private Date createdOn;
/*    */   private String createdby;
/*    */   private Integer ctq;
/*    */   private Date modifiedOn;
/*    */   private String modifiedby;
/*    */   private String telphone;
/*    */   private int good;
/*    */   private int bad;
/*    */   private StoreCommentTo commentTo;
/*    */ 
/*    */   public String getAroundSid()
/*    */   {
/* 16 */     return this.aroundSid;
/*    */   }
/* 18 */   public String getAddress() { return this.address; } 
/*    */   public String getApartmentSid() {
/* 20 */     return this.apartmentSid;
/*    */   }
/* 22 */   public String getBusinessAdd() { return this.businessAdd; } 
/*    */   public String getBusinessIcon() {
/* 24 */     return this.businessIcon;
/*    */   }
/* 26 */   public String getBusinessImage() { return this.businessImage; } 
/*    */   public String getBusinessName() {
/* 28 */     return this.businessName;
/*    */   }
/* 30 */   public String getBusinessHours() { return this.businessHours; } 
/*    */   public Integer getCallQty() {
/* 32 */     return this.callQty;
/*    */   }
/* 34 */   public String getCategoryName() { return this.categoryName; } 
/*    */   public String getContacts() {
/* 36 */     return this.contacts;
/*    */   }
/* 38 */   public String getContentsDesc() { return this.contentsDesc; } 
/*    */   public String getContents() {
/* 40 */     return this.contents;
/*    */   }
/* 42 */   public Date getCreatedOn() { return this.createdOn; } 
/*    */   public String getCreatedby() {
/* 44 */     return this.createdby;
/*    */   }
/* 46 */   public Integer getCtq() { return this.ctq; } 
/*    */   public Date getModifiedOn() {
/* 48 */     return this.modifiedOn;
/*    */   }
/* 50 */   public String getModifiedby() { return this.modifiedby; } 
/*    */   public String getTelphone() {
/* 52 */     return this.telphone;
/*    */   }
/* 54 */   public int getGood() { return this.good; } 
/* 55 */   public int getBad() { return this.bad; } 
/*    */   public StoreCommentTo getCommentTo() {
/* 57 */     return this.commentTo;
/*    */   }
/*    */ 
/*    */   public void setAroundSid(String aroundSid)
/*    */   {
/* 12 */     this.aroundSid = aroundSid; } 
/* 12 */   public void setAddress(String address) { this.address = address; } 
/* 12 */   public void setApartmentSid(String apartmentSid) { this.apartmentSid = apartmentSid; } 
/* 12 */   public void setBusinessAdd(String businessAdd) { this.businessAdd = businessAdd; } 
/* 12 */   public void setBusinessIcon(String businessIcon) { this.businessIcon = businessIcon; } 
/* 12 */   public void setBusinessImage(String businessImage) { this.businessImage = businessImage; } 
/* 12 */   public void setBusinessName(String businessName) { this.businessName = businessName; } 
/* 12 */   public void setBusinessHours(String businessHours) { this.businessHours = businessHours; } 
/* 12 */   public void setCallQty(Integer callQty) { this.callQty = callQty; } 
/* 12 */   public void setCategoryName(String categoryName) { this.categoryName = categoryName; } 
/* 12 */   public void setContacts(String contacts) { this.contacts = contacts; } 
/* 12 */   public void setContentsDesc(String contentsDesc) { this.contentsDesc = contentsDesc; } 
/* 12 */   public void setContents(String contents) { this.contents = contents; } 
/* 12 */   public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; } 
/* 12 */   public void setCreatedby(String createdby) { this.createdby = createdby; } 
/* 12 */   public void setCtq(Integer ctq) { this.ctq = ctq; } 
/* 12 */   public void setModifiedOn(Date modifiedOn) { this.modifiedOn = modifiedOn; } 
/* 12 */   public void setModifiedby(String modifiedby) { this.modifiedby = modifiedby; } 
/* 12 */   public void setTelphone(String telphone) { this.telphone = telphone; } 
/* 12 */   public void setGood(int good) { this.good = good; } 
/* 12 */   public void setBad(int bad) { this.bad = bad; } 
/* 12 */   public void setCommentTo(StoreCommentTo commentTo) { this.commentTo = commentTo; } 
/* 12 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof StoreTo)) return false; StoreTo other = (StoreTo)o; if (!other.canEqual(this)) return false; Object this$aroundSid = getAroundSid(); Object other$aroundSid = other.getAroundSid(); if (this$aroundSid == null ? other$aroundSid != null : !this$aroundSid.equals(other$aroundSid)) return false; Object this$address = getAddress(); Object other$address = other.getAddress(); if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$businessAdd = getBusinessAdd(); Object other$businessAdd = other.getBusinessAdd(); if (this$businessAdd == null ? other$businessAdd != null : !this$businessAdd.equals(other$businessAdd)) return false; Object this$businessIcon = getBusinessIcon(); Object other$businessIcon = other.getBusinessIcon(); if (this$businessIcon == null ? other$businessIcon != null : !this$businessIcon.equals(other$businessIcon)) return false; Object this$businessImage = getBusinessImage(); Object other$businessImage = other.getBusinessImage(); if (this$businessImage == null ? other$businessImage != null : !this$businessImage.equals(other$businessImage)) return false; Object this$businessName = getBusinessName(); Object other$businessName = other.getBusinessName(); if (this$businessName == null ? other$businessName != null : !this$businessName.equals(other$businessName)) return false; Object this$businessHours = getBusinessHours(); Object other$businessHours = other.getBusinessHours(); if (this$businessHours == null ? other$businessHours != null : !this$businessHours.equals(other$businessHours)) return false; Object this$callQty = getCallQty(); Object other$callQty = other.getCallQty(); if (this$callQty == null ? other$callQty != null : !this$callQty.equals(other$callQty)) return false; Object this$categoryName = getCategoryName(); Object other$categoryName = other.getCategoryName(); if (this$categoryName == null ? other$categoryName != null : !this$categoryName.equals(other$categoryName)) return false; Object this$contacts = getContacts(); Object other$contacts = other.getContacts(); if (this$contacts == null ? other$contacts != null : !this$contacts.equals(other$contacts)) return false; Object this$contentsDesc = getContentsDesc(); Object other$contentsDesc = other.getContentsDesc(); if (this$contentsDesc == null ? other$contentsDesc != null : !this$contentsDesc.equals(other$contentsDesc)) return false; Object this$contents = getContents(); Object other$contents = other.getContents(); if (this$contents == null ? other$contents != null : !this$contents.equals(other$contents)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$createdby = getCreatedby(); Object other$createdby = other.getCreatedby(); if (this$createdby == null ? other$createdby != null : !this$createdby.equals(other$createdby)) return false; Object this$ctq = getCtq(); Object other$ctq = other.getCtq(); if (this$ctq == null ? other$ctq != null : !this$ctq.equals(other$ctq)) return false; Object this$modifiedOn = getModifiedOn(); Object other$modifiedOn = other.getModifiedOn(); if (this$modifiedOn == null ? other$modifiedOn != null : !this$modifiedOn.equals(other$modifiedOn)) return false; Object this$modifiedby = getModifiedby(); Object other$modifiedby = other.getModifiedby(); if (this$modifiedby == null ? other$modifiedby != null : !this$modifiedby.equals(other$modifiedby)) return false; Object this$telphone = getTelphone(); Object other$telphone = other.getTelphone(); if (this$telphone == null ? other$telphone != null : !this$telphone.equals(other$telphone)) return false; if (getGood() != other.getGood()) return false; if (getBad() != other.getBad()) return false; Object this$commentTo = getCommentTo(); Object other$commentTo = other.getCommentTo(); return this$commentTo == null ? other$commentTo == null : this$commentTo.equals(other$commentTo); } 
/* 12 */   protected boolean canEqual(Object other) { return other instanceof StoreTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $aroundSid = getAroundSid(); result = result * 59 + ($aroundSid == null ? 0 : $aroundSid.hashCode()); Object $address = getAddress(); result = result * 59 + ($address == null ? 0 : $address.hashCode()); Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $businessAdd = getBusinessAdd(); result = result * 59 + ($businessAdd == null ? 0 : $businessAdd.hashCode()); Object $businessIcon = getBusinessIcon(); result = result * 59 + ($businessIcon == null ? 0 : $businessIcon.hashCode()); Object $businessImage = getBusinessImage(); result = result * 59 + ($businessImage == null ? 0 : $businessImage.hashCode()); Object $businessName = getBusinessName(); result = result * 59 + ($businessName == null ? 0 : $businessName.hashCode()); Object $businessHours = getBusinessHours(); result = result * 59 + ($businessHours == null ? 0 : $businessHours.hashCode()); Object $callQty = getCallQty(); result = result * 59 + ($callQty == null ? 0 : $callQty.hashCode()); Object $categoryName = getCategoryName(); result = result * 59 + ($categoryName == null ? 0 : $categoryName.hashCode()); Object $contacts = getContacts(); result = result * 59 + ($contacts == null ? 0 : $contacts.hashCode()); Object $contentsDesc = getContentsDesc(); result = result * 59 + ($contentsDesc == null ? 0 : $contentsDesc.hashCode()); Object $contents = getContents(); result = result * 59 + ($contents == null ? 0 : $contents.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $createdby = getCreatedby(); result = result * 59 + ($createdby == null ? 0 : $createdby.hashCode()); Object $ctq = getCtq(); result = result * 59 + ($ctq == null ? 0 : $ctq.hashCode()); Object $modifiedOn = getModifiedOn(); result = result * 59 + ($modifiedOn == null ? 0 : $modifiedOn.hashCode()); Object $modifiedby = getModifiedby(); result = result * 59 + ($modifiedby == null ? 0 : $modifiedby.hashCode()); Object $telphone = getTelphone(); result = result * 59 + ($telphone == null ? 0 : $telphone.hashCode()); result = result * 59 + getGood(); result = result * 59 + getBad(); Object $commentTo = getCommentTo(); result = result * 59 + ($commentTo == null ? 0 : $commentTo.hashCode()); return result; } 
/* 12 */   public String toString() { return "StoreTo(aroundSid=" + getAroundSid() + ", address=" + getAddress() + ", apartmentSid=" + getApartmentSid() + ", businessAdd=" + getBusinessAdd() + ", businessIcon=" + getBusinessIcon() + ", businessImage=" + getBusinessImage() + ", businessName=" + getBusinessName() + ", businessHours=" + getBusinessHours() + ", callQty=" + getCallQty() + ", categoryName=" + getCategoryName() + ", contacts=" + getContacts() + ", contentsDesc=" + getContentsDesc() + ", contents=" + getContents() + ", createdOn=" + getCreatedOn() + ", createdby=" + getCreatedby() + ", ctq=" + getCtq() + ", modifiedOn=" + getModifiedOn() + ", modifiedby=" + getModifiedby() + ", telphone=" + getTelphone() + ", good=" + getGood() + ", bad=" + getBad() + ", commentTo=" + getCommentTo() + ")"; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.store.StoreTo
 * JD-Core Version:    0.6.2
 */