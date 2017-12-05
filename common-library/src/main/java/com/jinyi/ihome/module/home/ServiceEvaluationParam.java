/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ServiceEvaluationParam
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String serviceSid;
/*    */   private Integer evaluationItem1;
/*    */   private Integer evaluationItem2;
/*    */   private Integer evaluationItem3;
/*    */   private String evaluationDesc;
/*    */   private String ownerSid;

    public String getTypeSid() {

        return typeSid;
    }

    public void setTypeSid(String typeSid) {
        this.typeSid = typeSid;
    }

    /*    */   private String typeSid;


    @Override
    public String toString() {
        return "ServiceEvaluationParam{" +
                "serviceSid='" + serviceSid + '\'' +
                ", evaluationItem1=" + evaluationItem1 +
                ", evaluationItem2=" + evaluationItem2 +
                ", evaluationItem3=" + evaluationItem3 +
                ", evaluationDesc='" + evaluationDesc + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", badEvaluationDescribe='" + badEvaluationDescribe + '\'' +
                ", badEvaluationRemark='" + badEvaluationRemark + '\'' +
                '}';
    }

    public String getBadEvaluationDescribe() {
        return badEvaluationDescribe;
    }

    public void setBadEvaluationDescribe(String badEvaluationDescribe) {
        this.badEvaluationDescribe = badEvaluationDescribe;
    }

    public String getBadEvaluationRemark() {
        return badEvaluationRemark;
    }

    public void setBadEvaluationRemark(String badEvaluationRemark) {
        this.badEvaluationRemark = badEvaluationRemark;
    }

    private String  badEvaluationDescribe;// 差评标签描述
    private String badEvaluationRemark;//   备注
/*    */ 
/*    */   public void setServiceSid(String serviceSid)
/*    */   {
/* 16 */     this.serviceSid = serviceSid; } 
/* 17 */   public String getServiceSid() { return this.serviceSid; }
/*    */ 
/*    */ 
/*    */   public void setEvaluationItem1(Integer evaluationItem1)
/*    */   {
/* 23 */     this.evaluationItem1 = evaluationItem1; } 
/* 24 */   public Integer getEvaluationItem1() { return this.evaluationItem1; }
/*    */ 
/*    */ 
/*    */   public void setEvaluationItem2(Integer evaluationItem2)
/*    */   {
/* 30 */     this.evaluationItem2 = evaluationItem2; } 
/* 31 */   public Integer getEvaluationItem2() { return this.evaluationItem2; }
/*    */ 
/*    */ 
/*    */   public void setEvaluationItem3(Integer evaluationItem3)
/*    */   {
/* 37 */     this.evaluationItem3 = evaluationItem3; } 
/* 38 */   public Integer getEvaluationItem3() { return this.evaluationItem3; }
/*    */ 
/*    */ 
/*    */   public void setEvaluationDesc(String evaluationDesc)
/*    */   {
/* 44 */     this.evaluationDesc = evaluationDesc; } 
/* 45 */   public String getEvaluationDesc() { return this.evaluationDesc; }
/*    */ 
/*    */ 
/*    */   public void setOwnerSid(String ownerSid)
/*    */   {
/* 51 */     this.ownerSid = ownerSid; } 
/* 52 */   public String getOwnerSid() { return this.ownerSid; }
/*    */ 
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceEvaluationParam
 * JD-Core Version:    0.6.2
 */