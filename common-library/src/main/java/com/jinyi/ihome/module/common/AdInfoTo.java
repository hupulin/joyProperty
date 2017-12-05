/*    */ package com.jinyi.ihome.module.common;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class AdInfoTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String adSid;
/*    */   private String adTitle;
/*    */   private int adIndex;
/*    */   private String adImage;
/*    */   private String adUrl;
/*    */   private String adContent;
/*    */   private String adInnerUrl;
/*    */   public String getAdSid()
/*    */   {
/* 16 */     return this.adSid;
/*    */   }
/* 18 */   public String getAdTitle() { return this.adTitle; } 
/*    */   public int getAdIndex() {
/* 20 */     return this.adIndex;
/*    */   }
/* 22 */   public String getAdImage() { return this.adImage; } 
/*    */   public String getAdUrl() {
/* 24 */     return this.adUrl;
/*    */   }
/* 26 */   public String getAdContent() { return this.adContent; }
/*    */ 
/*    */ 
/*    */   public void setAdSid(String adSid)
/*    */   {
/* 12 */     this.adSid = adSid; } 
/* 12 */   public void setAdTitle(String adTitle) { this.adTitle = adTitle; } 
/* 12 */   public void setAdIndex(int adIndex) { this.adIndex = adIndex; } 
/* 12 */   public void setAdImage(String adImage) { this.adImage = adImage; } 
/* 12 */   public void setAdUrl(String adUrl) { this.adUrl = adUrl; } 
/* 12 */   public void setAdContent(String adContent) { this.adContent = adContent; } 
/* 12 */

    public String getAdInnerUrl() {
        return adInnerUrl;
    }

    public void setAdInnerUrl(String adInnerUrl) {
        this.adInnerUrl = adInnerUrl;
    }

    public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof AdInfoTo)) return false; AdInfoTo other = (AdInfoTo)o; if (!other.canEqual(this)) return false; Object this$adSid = getAdSid(); Object other$adSid = other.getAdSid(); if (this$adSid == null ? other$adSid != null : !this$adSid.equals(other$adSid)) return false; Object this$adTitle = getAdTitle(); Object other$adTitle = other.getAdTitle(); if (this$adTitle == null ? other$adTitle != null : !this$adTitle.equals(other$adTitle)) return false; if (getAdIndex() != other.getAdIndex()) return false; Object this$adImage = getAdImage(); Object other$adImage = other.getAdImage(); if (this$adImage == null ? other$adImage != null : !this$adImage.equals(other$adImage)) return false; Object this$adUrl = getAdUrl(); Object other$adUrl = other.getAdUrl(); if (this$adUrl == null ? other$adUrl != null : !this$adUrl.equals(other$adUrl)) return false; Object this$adContent = getAdContent(); Object other$adContent = other.getAdContent(); return this$adContent == null ? other$adContent == null : this$adContent.equals(other$adContent); }
/* 12 */   protected boolean canEqual(Object other) { return other instanceof AdInfoTo; } 
/* 12 */   public int hashCode() { int PRIME = 59; int result = 1; Object $adSid = getAdSid(); result = result * 59 + ($adSid == null ? 0 : $adSid.hashCode()); Object $adTitle = getAdTitle(); result = result * 59 + ($adTitle == null ? 0 : $adTitle.hashCode()); result = result * 59 + getAdIndex(); Object $adImage = getAdImage(); result = result * 59 + ($adImage == null ? 0 : $adImage.hashCode()); Object $adUrl = getAdUrl(); result = result * 59 + ($adUrl == null ? 0 : $adUrl.hashCode()); Object $adContent = getAdContent(); result = result * 59 + ($adContent == null ? 0 : $adContent.hashCode()); return result; } 
/*    */

    @Override
    public String toString() {
        return "AdInfoTo{" +
                "adSid='" + adSid + '\'' +
                ", adTitle='" + adTitle + '\'' +
                ", adIndex=" + adIndex +
                ", adImage='" + adImage + '\'' +
                ", adUrl='" + adUrl + '\'' +
                ", adContent='" + adContent + '\'' +
                ", adInnerUrl='" + adInnerUrl + '\'' +
                '}';
    }
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.common.AdInfoTo
 * JD-Core Version:    0.6.2
 */