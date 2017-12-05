/*    */ package com.jinyi.ihome.module.home;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ServiceReportFlowTo
/*    */   implements Serializable
/*    */ {
    /*    */   private static final long serialVersionUID = 1L;
    /*    */   private String apartmentSid;
    /*    */   private String apartmentName;
    /*    */   private Map<String, Integer> value;//待评价
    /*    */private Map<String, Float> valueGrade;
    /***
     * praise 好评
     * medium 中评
     * bad 差评
     */
    private Map<String, Float> valueSatisfied;       // 满意汇总
    private Map<String, Float> valueSolve;           // 解决速度汇总
    private Map<String, Float> valueService;         // 服务汇总

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


    public Map<String, Float> getValueGrade() {
        return valueGrade;
    }

    public void setValueGrade(Map<String, Float> valueGrade) {
        this.valueGrade = valueGrade;
    }

    /*    */   public String getApartmentSid()
/*    */   {
/* 17 */     return this.apartmentSid;
/*    */   }
    /* 19 */   public String getApartmentName() { return this.apartmentName; }
    /*    */
/*    */
/*    */   public Map<String, Integer> getValue()
/*    */   {
/* 26 */     return this.value;
/*    */   }
    /*    */
/*    */   public void setApartmentSid(String apartmentSid)
/*    */   {
/* 11 */     this.apartmentSid = apartmentSid; }
    /* 11 */   public void setApartmentName(String apartmentName) { this.apartmentName = apartmentName; }
    /* 11 */   public void setValue(Map<String, Integer> value) { this.value = value; }
    /* 11 */   public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof ServiceReportFlowTo)) return false; ServiceReportFlowTo other = (ServiceReportFlowTo)o; if (!other.canEqual(this)) return false; Object this$apartmentSid = getApartmentSid(); Object other$apartmentSid = other.getApartmentSid(); if (this$apartmentSid == null ? other$apartmentSid != null : !this$apartmentSid.equals(other$apartmentSid)) return false; Object this$apartmentName = getApartmentName(); Object other$apartmentName = other.getApartmentName(); if (this$apartmentName == null ? other$apartmentName != null : !this$apartmentName.equals(other$apartmentName)) return false; Object this$value = getValue(); Object other$value = other.getValue(); return this$value == null ? other$value == null : this$value.equals(other$value); }
    /* 11 */   protected boolean canEqual(Object other) { return other instanceof ServiceReportFlowTo; }
    /* 11 */   public int hashCode() { int PRIME = 59; int result = 1; Object $apartmentSid = getApartmentSid(); result = result * 59 + ($apartmentSid == null ? 0 : $apartmentSid.hashCode()); Object $apartmentName = getApartmentName(); result = result * 59 + ($apartmentName == null ? 0 : $apartmentName.hashCode()); Object $value = getValue(); result = result * 59 + ($value == null ? 0 : $value.hashCode()); return result; }
/* 11 */

    public String getmPinyin() {
        return mPinyin;
    }

    public void setmPinyin(String mPinyin) {
        this.mPinyin = mPinyin;
    }

    @Override
    public String toString() {
        return "ServiceReportFlowTo{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", value=" + value +
                ", valueGrade=" + valueGrade +
                ", valueSatisfied=" + valueSatisfied +
                ", valueSolve=" + valueSolve +
                ", valueService=" + valueService +
                '}';
    }

/*    */
/*    */ }

/* Location:           C:\Users\xzz\Desktop\新建文件夹\ihome-to-1.0.jar
 * Qualified Name:     com.jinyi.ihome.module.home.ServiceReportFlowTo
 * JD-Core Version:    0.6.2
 */