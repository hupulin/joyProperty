package com.jinyi.ihome.module.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2017/3/14.
 */
public class ServiceInspectionPositionTo implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 服务巡检位置ID
     */
    private String positionSid;

    /**
     * 小区SID
     */
    private String apartmentSid;

    /**
     * 区域名称
     */
    private String positionName;

    /**
     * 排序号
     */
    private int positionIndex;

    /**
     * 上级区域
     */
    private String parentPosition;

    //

    private List<ServiceInspectionPositionTo> childList;

    public String getPositionSid() {
        return positionSid;
    }

    public void setPositionSid(String positionSid) {
        this.positionSid = positionSid;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }

    public String getParentPosition() {
        return parentPosition;
    }

    public void setParentPosition(String parentPosition) {
        this.parentPosition = parentPosition;
    }

    public List<ServiceInspectionPositionTo> getChildList() {
        return childList;
    }

    public void setChildList(List<ServiceInspectionPositionTo> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "ServiceInspectionPositionTo{" +
                "positionSid='" + positionSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", positionName='" + positionName + '\'' +
                ", positionIndex=" + positionIndex +
                ", parentPosition='" + parentPosition + '\'' +
                ", childList=" + childList +
                '}';
    }
}
