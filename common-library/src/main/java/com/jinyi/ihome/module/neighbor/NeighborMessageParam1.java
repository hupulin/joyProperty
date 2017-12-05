/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */

import java.io.Serializable;

/*    */

/*    */
/*    */ public class NeighborMessageParam1
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String apartmentSid;
/*    */   private String userSid;
/*    */
/*    */   private int pageIndex;
/*    */ private String typeName ;
/*    */   public String getApartmentSid()
/*    */   {
/* 18 */     return this.apartmentSid;
/*    */   }
/*    */ 
/*    */   public String getUserSid() {
/* 22 */     return this.userSid;
/*    */   }
/*    */ 
/*    */

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /*    */   public int getPageIndex() {
/* 30 */     return this.pageIndex;
/*    */   }
/*    */ 
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; } 
/* 11 */   public void setUserSid(String userSid) { this.userSid = userSid; } 
/* 11 */   public void setPageIndex(int pageIndex) { this.pageIndex = pageIndex; }
/* 11 */   protected boolean canEqual(Object other) { return other instanceof NeighborMessageParam1; }
/*    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NeighborMessageParam1 that = (NeighborMessageParam1) o;

        if (pageIndex != that.pageIndex) return false;
        if (apartmentSid != null ? !apartmentSid.equals(that.apartmentSid) : that.apartmentSid != null)
            return false;
        return !(userSid != null ? !userSid.equals(that.userSid) : that.userSid != null);

    }

    @Override
    public int hashCode() {
        int result = apartmentSid != null ? apartmentSid.hashCode() : 0;
        result = 31 * result + (userSid != null ? userSid.hashCode() : 0);
        result = 31 * result + pageIndex;
        return result;
    }

    @Override
    public String toString() {
        return "NeighborMessageParam1{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", userSid='" + userSid + '\'' +
                ", pageIndex=" + pageIndex +
                ", typeName='" + typeName + '\'' +
                '}';
    }
    /*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborMessageParam
 * JD-Core Version:    0.6.2
 */