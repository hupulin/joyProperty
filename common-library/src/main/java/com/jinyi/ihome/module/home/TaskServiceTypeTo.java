package com.jinyi.ihome.module.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by usb on 2017/6/22.
 */

public class TaskServiceTypeTo  implements Serializable{
    /*    */   private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "TaskServiceTypeTo{" +
                "typeApartmentSid='" + typeApartmentSid + '\'' +
                ", apartmentSid='" + apartmentSid + '\'' +
                ", categorySid='" + categorySid + '\'' +
                ", typeSid='" + typeSid + '\'' +
                ", typeName='" + typeName + '\'' +
                ", parentSid='" + parentSid + '\'' +
                ", workTypeInfoList=" + workTypeInfoList +
                '}';
    }

    private String typeApartmentSid;
        private String apartmentSid;
        /*    */   private String categorySid;
        private String typeSid;
        /*    */   private String typeName;
    private String parentSid;
    /*    */   private List<TaskServiceTypeTo> workTypeInfoList;

    public String getTypeApartmentSid() {
        return typeApartmentSid;
    }

    public void setTypeApartmentSid(String typeApartmentSid) {
        this.typeApartmentSid = typeApartmentSid;
    }

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getCategorySid() {
        return categorySid;
    }

    public void setCategorySid(String categorySid) {
        this.categorySid = categorySid;
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

    public String getParentSid() {
        return parentSid;
    }

    public void setParentSid(String parentSid) {
        this.parentSid = parentSid;
    }

    public List<TaskServiceTypeTo> getWorkTypeInfoList() {
        return workTypeInfoList;
    }

    public void setWorkTypeInfoList(List<TaskServiceTypeTo> workTypeInfoList) {
        this.workTypeInfoList = workTypeInfoList;
    }
}
