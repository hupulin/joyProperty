/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ServiceTypeTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String categorySid;
/*    */   private String typeSid;
/*    */   private String typeName;
/*    */   private String typeDescription;
/*    */   private Float typePrice;
/*    */   private String typeQtyTitle;
/*    */   private int typeQtyDefault;
/*    */   private String typeQtyUnit;
/*    */   private String typeRemark;

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

    private String typeImage;
/*    */   private List<ServiceTypeTo> childList;
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 13 */     this.apartmentSid = apartmentSid; } 
/* 13 */   public void setCategorySid(String categorySid) { this.categorySid = categorySid; } 
/* 13 */   public void setTypeSid(String typeSid) { this.typeSid = typeSid; } 
/* 13 */   public void setTypeName(String typeName) { this.typeName = typeName; } 
/* 13 */   public void setTypeDescription(String typeDescription) { this.typeDescription = typeDescription; } 
/* 13 */   public void setTypePrice(Float typePrice) { this.typePrice = typePrice; } 
/* 13 */   public void setTypeQtyTitle(String typeQtyTitle) { this.typeQtyTitle = typeQtyTitle; } 
/* 13 */   public void setTypeQtyDefault(int typeQtyDefault) { this.typeQtyDefault = typeQtyDefault; } 
/* 13 */   public void setTypeQtyUnit(String typeQtyUnit) { this.typeQtyUnit = typeQtyUnit; } 
/* 13 */   public void setTypeRemark(String typeRemark) { this.typeRemark = typeRemark; } 
/* 13 */   public void setChildList(List<ServiceTypeTo> childList) { this.childList = childList; }
/*    */ 
/*    */ 
/*    */   public String getApartmentSid()
/*    */   {
/* 18 */     return this.apartmentSid;
/*    */   }
/* 20 */   public String getCategorySid() { return this.categorySid; }
/*    */   public String getTypeSid() {
/* 22 */     return this.typeSid;
/*    */   }
/* 24 */   public String getTypeName() { return this.typeName; }
/*    */ 
/*    */   public String getTypeDescription() {
/* 27 */     return this.typeDescription;
/*    */   }
/*    */ 
/*    */   public Float getTypePrice()
/*    */   {
/* 32 */     return this.typePrice;
/*    */   }
/*    */ 
/*    */   public String getTypeQtyTitle()
/*    */   {
/* 37 */     return this.typeQtyTitle;
/*    */   }
/*    */ 
/*    */   public int getTypeQtyDefault()
/*    */   {
/* 42 */     return this.typeQtyDefault;
/*    */   }
/*    */ 
/*    */   public String getTypeQtyUnit()
/*    */   {
/* 47 */     return this.typeQtyUnit;
/*    */   }
/*    */ 
/*    */   public String getTypeRemark()
/*    */   {
/* 52 */     return this.typeRemark;
/*    */   }
/*    */ 
/*    */   public List<ServiceTypeTo> getChildList()
/*    */   {
/* 57 */     return this.childList;
/*    */   }

    @Override
    public String toString() {
        return "ServiceTypeTo{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", categorySid='" + categorySid + '\'' +
                ", typeSid='" + typeSid + '\'' +
                ", typeName='" + typeName + '\'' +
                ", typeDescription='" + typeDescription + '\'' +
                ", typePrice=" + typePrice +
                ", typeQtyTitle='" + typeQtyTitle + '\'' +
                ", typeQtyDefault=" + typeQtyDefault +
                ", typeQtyUnit='" + typeQtyUnit + '\'' +
                ", typeRemark='" + typeRemark + '\'' +
                ", typeImage='" + typeImage + '\'' +
                ", childList=" + childList +
                '}';
    }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceTypeTo
 * JD-Core Version:    0.6.2
 */