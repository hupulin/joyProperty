/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ServiceReportGradeTo
/*    */   implements Serializable
/*    */ {
    private static final long serialVersionUID = 1L;
    private String apartmentSid;
    private String apartmentName;
    private Map<String, Float> value;

    private Map<String, Float> valueSatisfied;       // 满意汇总
    private Map<String, Float> valueSolve;           // 解决速度汇总
    private Map<String, Float> valueService;         // 服务汇总.5

    private Map<String, Float> valueRSWXSatisfied;       // 满意入室维修
    private Map<String, Float> valueRSWXSolve;       // 解决速度入室维修
    private Map<String, Float> valueRSWXService;       // 服务入室维修

    private Map<String, Float> valueGGWXSatisfied;       // 满意公告维修
    private Map<String, Float> valueGGWXSolve;       // 解决速度公告维修
    private Map<String, Float> valueGGWXService;       // 服务公告维修

    private Map<String, Float> valueTSSatisfied;       // 满意投诉
    private Map<String, Float> valueTSSolve;       // 解决速度投诉
    private Map<String, Float> valueTSService;       // 服务投诉

    private Map<String, Float> valueJZSatisfied;       // 满意家政
    private Map<String, Float> valueJZSolve;       // 解决速度家政
    private Map<String, Float> valueJZService;       // 服务家政



    private Map<String ,Float> ValueRSWX;//入室维修
    private Map<String ,Float> valueGGWX;//公共维修
    private Map<String ,Float> valueTS;//投诉
    private Map<String ,Float> valueJZ;// 家政

    public Map<String, Float> getValueSatisfied() {
        return valueSatisfied;
    }
   private String mPinyin;
    public void setValueSatisfied(Map<String, Float> valueSatisfied) {
        this.valueSatisfied = valueSatisfied;
    }

    public Map<String, Float> getValueSolve() {
        return valueSolve;
    }

    public void setValueSolve(Map<String, Float> valueSolve) {
        this.valueSolve = valueSolve;
    }

    public Map<String, Float> getValueService() {
        return valueService;
    }

    public void setValueService(Map<String, Float> valueService) {
        this.valueService = valueService;
    }

    public Map<String, Float> getValueRSWXSatisfied() {
        return valueRSWXSatisfied;
    }

    public void setValueRSWXSatisfied(Map<String, Float> valueRSWXSatisfied) {
        this.valueRSWXSatisfied = valueRSWXSatisfied;
    }

    public Map<String, Float> getValueRSWXSolve() {
        return valueRSWXSolve;
    }

    public void setValueRSWXSolve(Map<String, Float> valueRSWXSolve) {
        this.valueRSWXSolve = valueRSWXSolve;
    }

    public Map<String, Float> getValueRSWXService() {
        return valueRSWXService;
    }

    public void setValueRSWXService(Map<String, Float> valueRSWXService) {
        this.valueRSWXService = valueRSWXService;
    }

    public Map<String, Float> getValueGGWXSatisfied() {
        return valueGGWXSatisfied;
    }

    public void setValueGGWXSatisfied(Map<String, Float> valueGGWXSatisfied) {
        this.valueGGWXSatisfied = valueGGWXSatisfied;
    }

    public Map<String, Float> getValueGGWXSolve() {
        return valueGGWXSolve;
    }

    public void setValueGGWXSolve(Map<String, Float> valueGGWXSolve) {
        this.valueGGWXSolve = valueGGWXSolve;
    }

    public Map<String, Float> getValueGGWXService() {
        return valueGGWXService;
    }

    public void setValueGGWXService(Map<String, Float> valueGGWXService) {
        this.valueGGWXService = valueGGWXService;
    }

    public Map<String, Float> getValueTSSatisfied() {
        return valueTSSatisfied;
    }

    public void setValueTSSatisfied(Map<String, Float> valueTSSatisfied) {
        this.valueTSSatisfied = valueTSSatisfied;
    }

    public Map<String, Float> getValueTSSolve() {
        return valueTSSolve;
    }

    public void setValueTSSolve(Map<String, Float> valueTSSolve) {
        this.valueTSSolve = valueTSSolve;
    }

    public Map<String, Float> getValueTSService() {
        return valueTSService;
    }

    public void setValueTSService(Map<String, Float> valueTSService) {
        this.valueTSService = valueTSService;
    }

    public Map<String, Float> getValueJZSatisfied() {
        return valueJZSatisfied;
    }

    public void setValueJZSatisfied(Map<String, Float> valueJZSatisfied) {
        this.valueJZSatisfied = valueJZSatisfied;
    }

    public Map<String, Float> getValueJZSolve() {
        return valueJZSolve;
    }

    public void setValueJZSolve(Map<String, Float> valueJZSolve) {
        this.valueJZSolve = valueJZSolve;
    }

    public Map<String, Float> getValueJZService() {
        return valueJZService;
    }

    public void setValueJZService(Map<String, Float> valueJZService) {
        this.valueJZService = valueJZService;
    }

    public Map<String, Float> getValueJZ() {
        return valueJZ;
    }

    public void setValueJZ(Map<String, Float> valueJZ) {
        this.valueJZ = valueJZ;
    }

    public Map<String, Float> getValueTS() {
        return valueTS;
    }

    public void setValueTS(Map<String, Float> valueTS) {
        this.valueTS = valueTS;
    }

    public Map<String, Float> getValueGGWX() {
        return valueGGWX;
    }

    public void setValueGGWX(Map<String, Float> valueGGWX) {
        this.valueGGWX = valueGGWX;
    }


    public Map<String, Float> getValueRSWX() {
        return ValueRSWX;
    }

    public void setValueRSWX(Map<String, Float> valueRSWX) {
        ValueRSWX = valueRSWX;
    }

    /*    */
/*    */   public String getApartmentSid()
/*    */   {
/* 15 */     return this.apartmentSid;
/*    */   }
    /* 17 */   public String getApartmentName() { return this.apartmentName; }
    /*    */
/*    */
/*    */   public Map<String, Float> getValue()
/*    */   {
/* 24 */     return this.value;
/*    */   }
    /*    */
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; }

    public String getmPinyin() {
        return mPinyin;
    }

    public void setmPinyin(String mPinyin) {
        this.mPinyin = mPinyin;
    }

    /* 11 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; }
    /* 11 */   public void setValue(Map<String, Float> value) { this.value = value; }
    /* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceReportGradeTo)) return false; ServiceReportGradeTo other = (ServiceReportGradeTo)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$apartmentName = getApartmentName(); Object other$apartmentName = other.getApartmentName(); if (this$apartmentName == null ? other$apartmentName != null : !this$apartmentName.equals(other$apartmentName)) return false; Object this$value = getValue(); Object other$value = other.getValue(); return this$value == null ? other$value == null : this$value.equals(other$value); }
    /* 11 */   protected boolean canEqual(Object other) { return other instanceof ServiceReportGradeTo; }
    /* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $apartmentName = getApartmentName(); result = result * 59 + ($apartmentName == null ? 0 : $apartmentName.hashCode()); Object $value = getValue(); result = result * 59 + ($value == null ? 0 : $value.hashCode()); return result; }
    /* 11 */   public String toString() { return "ServiceReportGradeTo(apartmentSid=" + getApartmentSid() + ", apartmentName=" + getApartmentName() + ", value=" + getValue() + ")"; }
/*    */
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceReportGradeTo
 * JD-Core Version:    0.6.2
 */