/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
import java.util.Date;

/*    */
/*    */ public class ServiceHandleParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String serviceSid;
/*    */   private String groupUserSid;
/*    */   private String processDesc;
/*    */   private String processImage;
          private String typeSid;
          private String typeName;

    @Override
    public String toString() {
        return "ServiceHandleParam{" +
                "serviceSid='" + serviceSid + '\'' +
                ", groupUserSid='" + groupUserSid + '\'' +
                ", processDesc='" + processDesc + '\'' +
                ", processImage='" + processImage + '\'' +
                ", typeSid='" + typeSid + '\'' +
                ", typeName='" + typeName + '\'' +
                ", photoTime=" + photoTime +
                ", evaluationTags='" + evaluationTags + '\'' +
                ", evaluationMsg='" + evaluationMsg + '\'' +
                '}';
    }

    public String getTypeSid() {
        return typeSid;
    }

    public void setTypeSid(String typeSid) {
        this.typeSid = typeSid;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /*    */   private Date photoTime;

    /**评价标签：标签1,标签2,标签3*/
    private String evaluationTags;

    public Date getPhotoTime() {
        return photoTime;
    }

    public void setPhotoTime(Date photoTime) {
        this.photoTime = photoTime;
    }

    public String getEvaluationTags() {
        return evaluationTags;
    }

    public void setEvaluationTags(String evaluationTags) {
        this.evaluationTags = evaluationTags;
    }

    public String getEvaluationMsg() {
        return evaluationMsg;
    }

    public void setEvaluationMsg(String evaluationMsg) {
        this.evaluationMsg = evaluationMsg;
    }

    /**评价信息*/

    private String evaluationMsg;
/*    */   public String getServiceSid()
/*    */   {
/* 19 */     return this.serviceSid;
/*    */   }
/*    */ 
/*    */   public String getGroupUserSid()
/*    */   {
/* 24 */     return this.groupUserSid;
/*    */   }
/*    */   public String getProcessDesc() {
/* 27 */     return this.processDesc;
/*    */   }
/* 29 */   public String getProcessImage() { return this.processImage; }
/*    */
/*    */
/*    */   public void setServiceSid(String serviceSid)
/*    */   {
/* 10 */     this.serviceSid = serviceSid; } 
/* 10 */   public void setGroupUserSid(String groupUserSid) { this.groupUserSid = groupUserSid; } 
/* 10 */   public void setProcessDesc(String processDesc) { this.processDesc = processDesc; } 
/* 10 */   public void setProcessImage(String processImage) { this.processImage = processImage; } 
/* 10 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceHandleParam)) return false; ServiceHandleParam other = (ServiceHandleParam)o; if (!other.canEqual(this)) return false; Object this$serviceSid = getServiceSid(); Object other$serviceSid = other.getServiceSid(); if (this$serviceSid == null ? other$serviceSid != null : !this$serviceSid.equals(other$serviceSid)) return false; Object this$groupUserSid = getGroupUserSid(); Object other$groupUserSid = other.getGroupUserSid(); if (this$groupUserSid == null ? other$groupUserSid != null : !this$groupUserSid.equals(other$groupUserSid)) return false; Object this$processDesc = getProcessDesc(); Object other$processDesc = other.getProcessDesc(); if (this$processDesc == null ? other$processDesc != null : !this$processDesc.equals(other$processDesc)) return false; Object this$processImage = getProcessImage(); Object other$processImage = other.getProcessImage(); return this$processImage == null ? other$processImage == null : this$processImage.equals(other$processImage); } 
/* 10 */   protected boolean canEqual(Object other) { return other instanceof ServiceHandleParam; } 
/* 10 */   public int hashCode() { int PRIME = 59; int result = 1; Object $serviceSid = getServiceSid(); result = result * 59 + ($serviceSid == null ? 0 : $serviceSid.hashCode()); Object $groupUserSid = getGroupUserSid(); result = result * 59 + ($groupUserSid == null ? 0 : $groupUserSid.hashCode()); Object $processDesc = getProcessDesc(); result = result * 59 + ($processDesc == null ? 0 : $processDesc.hashCode()); Object $processImage = getProcessImage(); result = result * 59 + ($processImage == null ? 0 : $processImage.hashCode()); return result; } 
/*    */
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceHandleParam
 * JD-Core Version:    0.6.2
 */