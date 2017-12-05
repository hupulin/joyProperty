/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import com.jinyi.ihome.module.owner.UserBasicTo;
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
import java.util.Date;

/*    */
/*    */ public class   ServiceHistTo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String histSid;
/*    */   private String detailUserName;

    @Override
    public String toString() {
        return "ServiceHistTo{" +
                "histSid='" + histSid + '\'' +
                ", detailUserName='" + detailUserName + '\'' +
                ", serviceSid='" + serviceSid + '\'' +
                ", createByName='" + createByName + '\'' +
                ", processImages='" + processImages + '\'' +
                ", workSid='" + workSid + '\'' +
                ", histType=" + histType +
                ", replyImages='" + replyImages + '\'' +
                ", replyDesc='" + replyDesc + '\'' +
                ", photoTime=" + photoTime +
                ", evaluationTags='" + evaluationTags + '\'' +
                ", evaluationMsg='" + evaluationMsg + '\'' +
                ", expectFinishTime='" + expectFinishTime + '\'' +
                ", assignTo='" + assignTo + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", createUser=" + createUser +
                '}';
    }

    public String getDetailUserName() {
        return detailUserName;
    }

    public void setDetailUserName(String detailUserName) {
        this.detailUserName = detailUserName;
    }

    /*    */   private String serviceSid;
 //三个日常工单的参数
    private String createByName;
    /*    */   private String processImages;

    public String getWorkSid() {
        return workSid;
    }

    public void setWorkSid(String workSid) {
        this.workSid = workSid;
    }

    /*    */   private String workSid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getProcessImages() {
        return processImages;
    }

    public void setProcessImages(String processImages) {
        this.processImages = processImages;
    }

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

    /*    */   private int histType;
/*    */   private String replyImages;
/*    */   private String replyDesc;
    private Date photoTime;
    private String evaluationTags;
    private String evaluationMsg;
    public String getExpectFinishTime() {
        return expectFinishTime;
    }

    public void setExpectFinishTime(String expectFinishTime) {
        this.expectFinishTime = expectFinishTime;
    }

    /*    */   private String expectFinishTime;
/*    */   private String assignTo;
/*    */   private String createdBy;
/*    */
/*    */   private Timestamp createdOn;
/*    */   private UserBasicTo createUser;

    public ServiceHistTo() {
    }

    /*    */
/*    */   public String getHistSid()
/*    */   {
/* 19 */     return this.histSid;
/*    */   }
/* 21 */   public String getServiceSid() { return this.serviceSid; }
/*    */
/*    */
/*    */   public int getHistType()
/*    */   {
/* 34 */     return this.histType;
/*    */   }
/* 36 */   public String getReplyImages() { return this.replyImages; }
/*    */   public String getReplyDesc() {
/* 38 */     return this.replyDesc;
/*    */   }
/* 40 */   public String getAssignTo() { return this.assignTo; }
/*    */   public String getCreatedBy() {
/* 42 */     return this.createdBy;
/*    */   }
/* 44 */   public Timestamp getCreatedOn() { return this.createdOn; }
/*    */   public UserBasicTo getCreateUser() {
/* 46 */     return this.createUser;
/*    */   }
/*    */
/*    */   public void setHistSid(String histSid)
/*    */   {
/* 15 */     this.histSid = histSid; }
/* 15 */   public void setServiceSid(String serviceSid) { this.serviceSid = serviceSid; }
/* 15 */   public void setHistType(int histType) { this.histType = histType; }
/* 15 */   public void setReplyImages(String replyImages) { this.replyImages = replyImages; }
/* 15 */   public void setReplyDesc(String replyDesc) { this.replyDesc = replyDesc; }
/* 15 */   public void setAssignTo(String assignTo) { this.assignTo = assignTo; }
/* 15 */   public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
/* 15 */   public void setCreatedOn(Timestamp createdOn) { this.createdOn = createdOn; }
/* 15 */   public void setCreateUser(UserBasicTo createUser) { this.createUser = createUser; }
/* 15 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceHistTo)) return false; ServiceHistTo other = (ServiceHistTo)o; if (!other.canEqual(this)) return false; Object this$histSid = getHistSid(); Object other$histSid = other.getHistSid(); if (this$histSid == null ? other$histSid != null : !this$histSid.equals(other$histSid)) return false; Object this$serviceSid = getServiceSid(); Object other$serviceSid = other.getServiceSid(); if (this$serviceSid == null ? other$serviceSid != null : !this$serviceSid.equals(other$serviceSid)) return false; if (getHistType() != other.getHistType()) return false; Object this$replyImages = getReplyImages(); Object other$replyImages = other.getReplyImages(); if (this$replyImages == null ? other$replyImages != null : !this$replyImages.equals(other$replyImages)) return false; Object this$replyDesc = getReplyDesc(); Object other$replyDesc = other.getReplyDesc(); if (this$replyDesc == null ? other$replyDesc != null : !this$replyDesc.equals(other$replyDesc)) return false; Object this$assignTo = getAssignTo(); Object other$assignTo = other.getAssignTo(); if (this$assignTo == null ? other$assignTo != null : !this$assignTo.equals(other$assignTo)) return false; Object this$createdBy = getCreatedBy(); Object other$createdBy = other.getCreatedBy(); if (this$createdBy == null ? other$createdBy != null : !this$createdBy.equals(other$createdBy)) return false; Object this$createdOn = getCreatedOn(); Object other$createdOn = other.getCreatedOn(); if (this$createdOn == null ? other$createdOn != null : !this$createdOn.equals(other$createdOn)) return false; Object this$createUser = getCreateUser(); Object other$createUser = other.getCreateUser(); return this$createUser == null ? other$createUser == null : this$createUser.equals(other$createUser); }
/* 15 */   protected boolean canEqual(Object other) { return other instanceof ServiceHistTo; }
/* 15 */   public int hashCode() { int PRIME = 59; int result = 1; Object $histSid = getHistSid(); result = result * 59 + ($histSid == null ? 0 : $histSid.hashCode()); Object $serviceSid = getServiceSid(); result = result * 59 + ($serviceSid == null ? 0 : $serviceSid.hashCode()); result = result * 59 + getHistType(); Object $replyImages = getReplyImages(); result = result * 59 + ($replyImages == null ? 0 : $replyImages.hashCode()); Object $replyDesc = getReplyDesc(); result = result * 59 + ($replyDesc == null ? 0 : $replyDesc.hashCode()); Object $assignTo = getAssignTo(); result = result * 59 + ($assignTo == null ? 0 : $assignTo.hashCode()); Object $createdBy = getCreatedBy(); result = result * 59 + ($createdBy == null ? 0 : $createdBy.hashCode()); Object $createdOn = getCreatedOn(); result = result * 59 + ($createdOn == null ? 0 : $createdOn.hashCode()); Object $createUser = getCreateUser(); result = result * 59 + ($createUser == null ? 0 : $createUser.hashCode()); return result; }
/*    */
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceHistTo
 * JD-Core Version:    0.6.2
 */