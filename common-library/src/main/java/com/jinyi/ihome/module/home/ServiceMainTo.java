/*     */ package com.jinyi.ihome.module.home;
/*     */ 
/*     */ import com.jinyi.ihome.module.owner.UserBasicTo;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ServiceMainTo
/*     */   implements Serializable
/*     */ {
    /*     */   private static final long serialVersionUID = 1L;
    /*     */
    /*     */   private String serviceSid;
    /*     */   private String apartmentSid;
    /*     */   private String apartmentName;

    /*     */   private String serviceCategory;
    /*   类型id  */
    /*     */   private String typeSid;
    /*     */   private String typeName;
    /*     */   private String serviceNo;
    /*     */   private String serviceImages;
    /*     */   private String serviceDesc;
    /*     */   private String serviceStatus;
    /*     */   private String replyDesc;
    /*     */   private UserBasicTo replyUser;
    /*     */   private String replyImages;
    /*     */   private Date replyTime;
    /*     */   private Integer evaluationItem1;
    /*     */   private Integer evaluationItem2;
    /*     */   private Integer evaluationItem3;
    /*     */   private String evaluationDesc;
    /*     */   private Date createdOn;
    /*     */   private UserBasicTo createUser;
    /*     */   private String serviceUser;
    /*     */   private String ownerPhone;
    /*     */   private String waitingTime;
    /*     */   private Date modifiedOn;
    /*     */   private String serviceOwnerNo;
    /*     */   private String responseDesc;
    /*     */   private Date responseTime;
    /*     */   private UserBasicTo responseUser;
    /*     */   private String remark;
    /*     */   private List<ServiceMessageTo> msgList;
    /*     */   private String serviceAddress;
    /*     */   private String serviceContact;
    /*     */   private String servicePhone;
    /*     */   private float servicePrice;
    /*     */   private String serviceBookingTime;
    /*     */   private int serviceQty;
    /*     */   private String serviceQtyDesc;
    /*     */   private Integer processCycle;
    /*     */   private Integer responseCycle;
                private String carNo;
                private String postionName;

    //违停位置

    private String illegallyParkedPostion;

    //违停通知单

    private String illegallyParkedNotion;

    private ServiceMainExtTo serviceMainExtTo;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ServiceMainExtTo getServiceMainExtTo() {
        return serviceMainExtTo;
    }

    public void setServiceMainExtTo(ServiceMainExtTo serviceMainExtTo) {
        this.serviceMainExtTo = serviceMainExtTo;
    }

    public Integer getResponseCycle() {
        return responseCycle;
    }

    public void setResponseCycle(Integer responseCycle) {
        this.responseCycle = responseCycle;
    }

    public Integer getProcessCycle() {
        return processCycle;
    }

    public void setProcessCycle(Integer processCycle) {
        this.processCycle = processCycle;
    }


    public String getStatusS() {
        return this.serviceStatus;
    }


    /*     */
/*     */   public String getStatusStr()
/*     */   {
/* 231 */     String status = "";
/* 232 */     if ("1".equals(this.serviceStatus))
/* 233 */       status = "等待物业响应";
/* 234 */     else if ("2".equals(this.serviceStatus))
///* 235 */
/* 235 */       status = "已指派";
/* 236 */     else if ("4".equals(this.serviceStatus))
/* 237 */       status = "处理完成，等待您的评价";
/* 238 */     else if ("6".equals(this.serviceStatus))
/* 239 */       status = "已结束";
/* 240 */     else if ("9".equals(this.serviceStatus)) {
/* 241 */       status = "已结束";
/*     */     }
        else if ("20".equals(this.serviceStatus)) {
            status = "正在帮您解决中";
///* 235 */       status = "已指派";
        }
/*     */ else if ("22".equals(this.serviceStatus)) {
            status = "正在帮您解决中";
        }else if ("21".equals(this.serviceStatus)) {
            status = "正在帮您解决中";
        }
/* 244 */     return status;
/*     */   }
    /*     */




    /*     */   public String getEvaluationItem3Str() {
/* 248 */     if (this.evaluationItem3 == null) {
/* 249 */       return "待评价";
/*     */     }
/* 251 */     if (this.evaluationItem3.intValue() ==5.0f) {
/* 252 */       return "惊喜";
/*     */     }
/* 254 */     if (this.evaluationItem3.intValue() == 4.0f) {
/* 255 */       return "满意";
/*     */     }
/* 257 */     if (this.evaluationItem3.intValue() ==3.0f) {
/* 258 */       return "一般";
/*     */     }
/* 257 */     if (this.evaluationItem3.intValue() ==2.0f) {
/* 258 */       return "差评";
/*     */     }
/* 257 */     if (this.evaluationItem3.intValue() ==1.0f) {
/* 258 */       return "不满";
/*     */     }

/* 260 */     return "";
/*     */   }




    /*     */   public String getEvaluationItem1Str() {
/* 248 */     if (this.evaluationItem1 == null) {
/* 249 */       return "待评价";
/*     */     }
/* 251 */     if (this.evaluationItem1.intValue() ==5.0f) {
/* 252 */       return "惊喜";
/*     */     }
/* 254 */     if (this.evaluationItem1.intValue() == 4.0f) {
/* 255 */       return "满意";
/*     */     }
/* 257 */     if (this.evaluationItem1.intValue() ==3.0f) {
/* 258 */       return "一般";
/*     */     }
/* 257 */     if (this.evaluationItem1.intValue() ==2.0f) {
/* 258 */       return "差评";
/*     */     }
/* 257 */     if (this.evaluationItem1.intValue() ==1.0f) {
/* 258 */       return "不满";
/*     */     }

/* 260 */     return "";
/*     */   }
    /*     */   public String getEvaluationItem2Str() {
/* 248 */     if (this.evaluationItem2 == null) {
/* 249 */       return "待评价";
/*     */     }
/* 251 */     if (this.evaluationItem2.intValue() ==5.0f) {
/* 252 */       return "惊喜";
/*     */     }
/* 254 */     if (this.evaluationItem2.intValue() == 4.0f) {
/* 255 */       return "满意";
/*     */     }
/* 257 */     if (this.evaluationItem2.intValue() ==3.0f) {
/* 258 */       return "一般";
/*     */     }
/* 257 */     if (this.evaluationItem2.intValue() ==2.0f) {
/* 258 */       return "差评";
/*     */     }
/* 257 */     if (this.evaluationItem2.intValue() ==1.0f) {
/* 258 */       return "不满";
/*     */     }

/* 260 */     return "";
/*     */   }

    /*     */
/*     */   public void setServiceSid(String serviceSid)
/*     */   {
/*  19 */     this.serviceSid = serviceSid; }
    /*  20 */   public String getServiceSid() { return this.serviceSid; }
    /*     */
/*     */ 
/*     */   public void setApartmentSid(String apartmentSid)
/*     */   {
/*  27 */     this.apartmentSid = apartmentSid; }
    /*  28 */   public String getApartmentSid() { return this.apartmentSid; }
    /*     */
/*     */   public void setApartmentName(String apartmentName) {
/*  31 */     this.apartmentName = apartmentName; }
    /*  32 */   public String getApartmentName() { return this.apartmentName; }
    /*     */
/*     */   public void setServiceCategory(String serviceCategory) {
/*  35 */     this.serviceCategory = serviceCategory; }
    /*  36 */   public String getServiceCategory() { return this.serviceCategory; }
    /*     */
/*     */ 
/*     */   public void setTypeSid(String typeSid)
/*     */   {
/*  51 */     this.typeSid = typeSid; }
    /*  52 */   public String getTypeSid() { return this.typeSid; }
    /*     */
/*     */ 
/*     */   public void setTypeName(String typeName)
/*     */   {
/*  58 */     this.typeName = typeName; }
    /*  59 */   public String getTypeName() { return this.typeName; }
    /*     */
/*     */
/*     */   public void setServiceNo(String serviceNo)
/*     */   {
/*  65 */     this.serviceNo = serviceNo; }
    /*  66 */   public String getServiceNo() { return this.serviceNo; }
    /*     */
/*     */ 
/*     */   public void setServiceImages(String serviceImages)
/*     */   {
/*  72 */     this.serviceImages = serviceImages; }
    /*  73 */   public String getServiceImages() { return this.serviceImages; }
    /*     */
/*     */ 
/*     */   public void setServiceDesc(String serviceDesc)
/*     */   {
/*  79 */     this.serviceDesc = serviceDesc; }
    /*  80 */   public String getServiceDesc() { return this.serviceDesc; }
    /*     */
/*     */ 
/*     */   public void setServiceStatus(String serviceStatus)
/*     */   {
/*  86 */     this.serviceStatus = serviceStatus; }
    /*  87 */   public String getServiceStatus() { return this.serviceStatus; }
    /*     */
/*     */ 
/*     */   public void setReplyDesc(String replyDesc)
/*     */   {
/* 101 */     this.replyDesc = replyDesc; }
    /* 102 */   public String getReplyDesc() { return this.replyDesc; }
    /*     */
/*     */ 
/*     */   public void setReplyUser(UserBasicTo replyUser)
/*     */   {
/* 108 */     this.replyUser = replyUser; }
    /* 109 */   public UserBasicTo getReplyUser() { return this.replyUser; }
    /*     */
/*     */ 
/*     */   public void setReplyImages(String replyImages)
/*     */   {
/* 115 */     this.replyImages = replyImages; }
    /* 116 */   public String getReplyImages() { return this.replyImages; }
    /*     */
/*     */ 
/*     */   public void setReplyTime(Date replyTime)
/*     */   {
/* 122 */     this.replyTime = replyTime; }
    /* 123 */   public Date getReplyTime() { return this.replyTime; }
    /*     */
/*     */ 
/*     */   public void setEvaluationItem1(Integer evaluationItem1)
/*     */   {
/* 129 */     this.evaluationItem1 = evaluationItem1; }
    /* 130 */   public Integer getEvaluationItem1() { return this.evaluationItem1; }
    /*     */
/*     */ 
/*     */   public void setEvaluationItem2(Integer evaluationItem2)
/*     */   {
/* 136 */     this.evaluationItem2 = evaluationItem2; }
    /* 137 */   public Integer getEvaluationItem2() { return this.evaluationItem2; }
    /*     */
/*     */ 
/*     */   public void setEvaluationItem3(Integer evaluationItem3)
/*     */   {
/* 143 */     this.evaluationItem3 = evaluationItem3; }
    /* 144 */   public Integer getEvaluationItem3() { return this.evaluationItem3; }
    /*     */
/*     */ 
/*     */   public void setEvaluationDesc(String evaluationDesc)
/*     */   {
/* 150 */     this.evaluationDesc = evaluationDesc; }
    /* 151 */   public String getEvaluationDesc() { return this.evaluationDesc; }
    /*     */
/*     */ 
/*     */   public void setCreatedOn(Date createdOn)
/*     */   {
/* 157 */     this.createdOn = createdOn; }
    /* 158 */   public Date getCreatedOn() { return this.createdOn; }
    /*     */
/*     */ 
/*     */   public void setCreateUser(UserBasicTo createUser)
/*     */   {
/* 165 */     this.createUser = createUser; }
    /* 166 */   public UserBasicTo getCreateUser() { return this.createUser; }
    /*     */
/*     */ 
/*     */   public void setServiceUser(String serviceUser)
/*     */   {
/* 175 */     this.serviceUser = serviceUser; }
    /* 175 */   public String getServiceUser() { return this.serviceUser; }
    /*     */
/*     */ 
/*     */   public void setOwnerPhone(String ownerPhone)
/*     */   {
/* 181 */     this.ownerPhone = ownerPhone; }
    /* 181 */   public String getOwnerPhone() { return this.ownerPhone; }
    /*     */
/*     */ 
/*     */   public String getWaitingTime()
/*     */   {
/* 187 */     return this.waitingTime; }
    /* 188 */   public void setWaitingTime(String waitingTime) { this.waitingTime = waitingTime; }
    /*     */
/*     */   public void setModifiedOn(Date modifiedOn) {
/* 191 */     this.modifiedOn = modifiedOn; }
    /* 192 */   public Date getModifiedOn() { return this.modifiedOn; }
    /*     */
/*     */ 
/*     */   public void setServiceOwnerNo(String serviceOwnerNo)
/*     */   {
/* 198 */     this.serviceOwnerNo = serviceOwnerNo; }
    /* 199 */   public String getServiceOwnerNo() { return this.serviceOwnerNo; }
    /*     */
/*     */ 
/*     */   public String getResponseDesc()
/*     */   {
/* 207 */     return this.responseDesc; }
    /* 207 */   public void setResponseDesc(String responseDesc) { this.responseDesc = responseDesc; }
    /*     */
/*     */   public Date getResponseTime() {
/* 210 */     return this.responseTime; }
    /* 210 */   public void setResponseTime(Date responseTime) { this.responseTime = responseTime; }
    /*     */
/*     */   public UserBasicTo getResponseUser() {
/* 213 */     return this.responseUser; }
    /* 213 */   public void setResponseUser(UserBasicTo responseUser) { this.responseUser = responseUser; }
    /*     */
/*     */   public void setRemark(String remark) {
/* 216 */     this.remark = remark; }
    /* 217 */   public String getRemark() { return this.remark; }
    /*     */
/*     */ 
/*     */   public void setMsgList(List<ServiceMessageTo> msgList)
/*     */   {
/* 226 */     this.msgList = msgList; }
    /* 227 */   public List<ServiceMessageTo> getMsgList() { return this.msgList; }
    /*     */
/*     */ 
/*     */   public void setServiceAddress(String serviceAddress)
/*     */   {
/* 263 */     this.serviceAddress = serviceAddress; }
    /* 264 */   public String getServiceAddress() { return this.serviceAddress; }
    /*     */
/*     */ 
/*     */   public void setServiceContact(String serviceContact)
/*     */   {
/* 270 */     this.serviceContact = serviceContact; }
    /* 271 */   public String getServiceContact() { return this.serviceContact; }
    /*     */
/*     */ 
/*     */   public void setServicePhone(String servicePhone)
/*     */   {
/* 277 */     this.servicePhone = servicePhone; }
    /* 278 */   public String getServicePhone() { return this.servicePhone; }
    /*     */
/*     */ 
/*     */   public void setServicePrice(float servicePrice)
/*     */   {
/* 284 */     this.servicePrice = servicePrice; }
    /* 285 */   public float getServicePrice() { return this.servicePrice; }
    /*     */
/*     */ 
/*     */   public void setServiceBookingTime(String serviceBookingTime)
/*     */   {
/* 291 */     this.serviceBookingTime = serviceBookingTime; }
    /* 292 */   public String getServiceBookingTime() { return this.serviceBookingTime; }
    /*     */
/*     */ 
/*     */   public void setServiceQty(int serviceQty)
/*     */   {
/* 298 */     this.serviceQty = serviceQty; }
    /* 299 */   public int getServiceQty() { return this.serviceQty; }
    /*     */
/*     */ 
/*     */   public void setServiceQtyDesc(String serviceQtyDesc)
/*     */   {
/* 305 */     this.serviceQtyDesc = serviceQtyDesc; }
    /* 306 */   public String getServiceQtyDesc() { return this.serviceQtyDesc; }
/*     */

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getIllegallyParkedPostion() {
        return illegallyParkedPostion;
    }

    public void setIllegallyParkedPostion(String illegallyParkedPostion) {
        this.illegallyParkedPostion = illegallyParkedPostion;
    }

    public String getIllegallyParkedNotion() {
        return illegallyParkedNotion;
    }

    public void setIllegallyParkedNotion(String illegallyParkedNotion) {
        this.illegallyParkedNotion = illegallyParkedNotion;
    }

    public String getPostionName() {
        return postionName;
    }

    public void setPostionName(String postionName) {
        this.postionName = postionName;
    }

    @Override
    public String toString() {
        return "ServiceMainTo{" +
                "serviceSid='" + serviceSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", serviceCategory='" + serviceCategory + '\'' +
                ", typeSid='" + typeSid + '\'' +
                ", typeName='" + typeName + '\'' +
                ", serviceNo='" + serviceNo + '\'' +
                ", serviceImages='" + serviceImages + '\'' +
                ", serviceDesc='" + serviceDesc + '\'' +
                ", serviceStatus='" + serviceStatus + '\'' +
                ", replyDesc='" + replyDesc + '\'' +
                ", replyUser=" + replyUser +
                ", replyImages='" + replyImages + '\'' +
                ", replyTime=" + replyTime +
                ", evaluationItem1=" + evaluationItem1 +
                ", evaluationItem2=" + evaluationItem2 +
                ", evaluationItem3=" + evaluationItem3 +
                ", evaluationDesc='" + evaluationDesc + '\'' +
                ", createdOn=" + createdOn +
                ", createUser=" + createUser +
                ", serviceUser='" + serviceUser + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", waitingTime='" + waitingTime + '\'' +
                ", modifiedOn=" + modifiedOn +
                ", serviceOwnerNo='" + serviceOwnerNo + '\'' +
                ", responseDesc='" + responseDesc + '\'' +
                ", responseTime=" + responseTime +
                ", responseUser=" + responseUser +
                ", remark='" + remark + '\'' +
                ", msgList=" + msgList +
                ", serviceAddress='" + serviceAddress + '\'' +
                ", serviceContact='" + serviceContact + '\'' +
                ", servicePhone='" + servicePhone + '\'' +
                ", servicePrice=" + servicePrice +
                ", serviceBookingTime='" + serviceBookingTime + '\'' +
                ", serviceQty=" + serviceQty +
                ", serviceQtyDesc='" + serviceQtyDesc + '\'' +
                ", processCycle=" + processCycle +
                ", responseCycle=" + responseCycle +
                ", carNo='" + carNo + '\'' +
                ", illegallyParkedPostion='" + illegallyParkedPostion + '\'' +
                ", illegallyParkedNotion='" + illegallyParkedNotion + '\'' +
                ", serviceMainExtTo=" + serviceMainExtTo +
                '}';
    }
/*     */ }