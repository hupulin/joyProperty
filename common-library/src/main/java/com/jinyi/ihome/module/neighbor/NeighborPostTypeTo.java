/*    */ package com.jinyi.ihome.module.neighbor;
/*    */ 
/*    */ import java.io.Serializable;

/*    */
/*    */ public class NeighborPostTypeTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String typeSid;
/*    */   private String typeName;
/*    */   private Integer typeIndex;
/*    */private String typeImage;

    public NeighborPostTypeTo(String typeSid, String typeName, Integer typeIndex, String typeImage, String typeAutoFlag) {
        this.typeSid = typeSid;
        this.typeName = typeName;
        this.typeIndex = typeIndex;
        this.typeImage = typeImage;
        this.typeAutoFlag = typeAutoFlag;
    }
public NeighborPostTypeTo(){

}
    public NeighborPostTypeTo(String typeSid, String typeName, Integer typeIndex, String typeImage) {
        this.typeSid = typeSid;
        this.typeName = typeName;
        this.typeIndex = typeIndex;
        this.typeImage = typeImage;
    }

    private String typeAutoFlag;


    public String getTypeImage() {
        return typeImage;
    }

    public String getTypeAutoFlag() {
        return typeAutoFlag;
    }

    public void setTypeAutoFlag(String typeAutoFlag) {
        this.typeAutoFlag = typeAutoFlag;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }



    public NeighborPostTypeTo(String typeSid, String typeName, Integer typeIndex) {
        this.typeSid = typeSid;
        this.typeName = typeName;
        this.typeIndex = typeIndex;
    }

    /*    */   public boolean equals(Object o)
/*    */   {
/* 13 */     if (o == this) return true; if (!(o instanceof NeighborPostTypeTo)) return false; NeighborPostTypeTo other = (NeighborPostTypeTo)o; if (!other.canEqual(this)) return false; Object this$typeSid = getTypeSid(); Object other$typeSid = other.getTypeSid(); if (this$typeSid == null ? other$typeSid != null : !this$typeSid.equals(other$typeSid)) return false; Object this$typeName = getTypeName(); Object other$typeName = other.getTypeName(); if (this$typeName == null ? other$typeName != null : !this$typeName.equals(other$typeName)) return false; Object this$typeIndex = getTypeIndex(); Object other$typeIndex = other.getTypeIndex(); return this$typeIndex == null ? other$typeIndex == null : this$typeIndex.equals(other$typeIndex); }
/* 13 */   protected boolean canEqual(Object other) { return other instanceof NeighborPostTypeTo; }
/* 13 */   public int hashCode() { int PRIME = 59; int result = 1; Object $typeSid = getTypeSid(); result = result * 59 + ($typeSid == null ? 0 : $typeSid.hashCode()); Object $typeName = getTypeName(); result = result * 59 + ($typeName == null ? 0 : $typeName.hashCode()); Object $typeIndex = getTypeIndex(); result = result * 59 + ($typeIndex == null ? 0 : $typeIndex.hashCode()); return result; } 
/* 13 */   public String toString() { return "NeighborPostTypeTo(typeSid=" + getTypeSid() + ", typeName=" + getTypeName() + ", typeIndex=" + getTypeIndex() + ")"; }
/*    */ 
/*    */   public void setTypeSid(String typeSid)
/*    */   {
/* 17 */     this.typeSid = typeSid; } 
/* 18 */   public String getTypeSid() { return this.typeSid; }
/*    */ 
/*    */ 
/*    */   public void setTypeName(String typeName)
/*    */   {
/* 24 */     this.typeName = typeName; } 
/* 25 */   public String getTypeName() { return this.typeName; }
/*    */ 
/*    */ 
/*    */   public void setTypeIndex(Integer typeIndex)
/*    */   {
/* 31 */     this.typeIndex = typeIndex; } 
/* 32 */   public Integer getTypeIndex() { return this.typeIndex; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.neighbor.NeighborPostTypeTo
 * JD-Core Version:    0.6.2
 */