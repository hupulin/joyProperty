package com.jinyi.ihome.module.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by usb on 2017/6/15.
 */

public class ServiceMyWorkTo  implements Serializable{
    /*    */   private static final long serialVersionUID = 1L;
    private String workSid ; // 任务主键ID
    private String apartmentSid ; // 小区ID
    private String apartmentName ; // 小区名称
    private String callerUserId ; // 呼叫人ID
    private String callerUserName ; // 呼叫人姓名
    private String callerTime ; // 呼叫时间
    private String responseTime ; // 响应时间
    private String typeName ; // 工单任务名称
    private String remark ; // 工单任务内容
    private String typeDesc ; // 工单任务备注
    private String finishTime ; // 处理时间
    private String workNo ; // 服务编号
    private String distanceTimes ; // 任务开始时间差
    private String workStatus  ; //服务状态
    private String workStatusDesc;//服务状态描述
    private List<ServiceHistTo> histList;

    @Override
    public String toString() {
        return "ServiceMyWorkTo{" +
                "workSid='" + workSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", callerUserId='" + callerUserId + '\'' +
                ", callerUserName='" + callerUserName + '\'' +
                ", callerTime='" + callerTime + '\'' +
                ", responseTime='" + responseTime + '\'' +
                ", typeName='" + typeName + '\'' +
                ", remark='" + remark + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", workNo='" + workNo + '\'' +
                ", distanceTimes='" + distanceTimes + '\'' +
                ", workStatus='" + workStatus + '\'' +
                ", workStatusDesc='" + workStatusDesc + '\'' +
                ", histList=" + histList +
                '}';
    }

    public List<ServiceHistTo> getHistList() {
        return histList;
    }

    public void setHistList(List<ServiceHistTo> histList) {
        this.histList = histList;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkStatusDesc() {
        return workStatusDesc;
    }

    public void setWorkStatusDesc(String workStatusDesc) {
        this.workStatusDesc = workStatusDesc;
    }

    public String getWorkSid() {
        return workSid;
    }

    public void setWorkSid(String workSid) {
        this.workSid = workSid;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getCallerUserId() {
        return callerUserId;
    }

    public void setCallerUserId(String callerUserId) {
        this.callerUserId = callerUserId;
    }

    public String getCallerUserName() {
        return callerUserName;
    }

    public void setCallerUserName(String callerUserName) {
        this.callerUserName = callerUserName;
    }

    public String getCallerTime() {
        return callerTime;
    }

    public void setCallerTime(String callerTime) {
        this.callerTime = callerTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getDistanceTimes() {
        return distanceTimes;
    }

    public void setDistanceTimes(String distanceTimes) {
        this.distanceTimes = distanceTimes;
    }
}
