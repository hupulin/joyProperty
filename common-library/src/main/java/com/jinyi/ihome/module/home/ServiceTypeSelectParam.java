package com.jinyi.ihome.module.home;

import java.io.Serializable;

/**
 * Created by usb on 2017/3/13.
 */
public class ServiceTypeSelectParam implements Serializable {
    /***
     * 小区Id
     */
    private String apartmentSid;
    /***
     * 服务分类
     */
    private String serviceCategory;
    /***
     * 服务类型Id
    */
    private String typeSid;
 //   服务更新时间
    private  String modifiedOn;
    /***
     * 响应用户
     */
     private String responseUser;;
    /***
     * 业主id
     */
    private String ownerSid;

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public String getResponseUser() {
        return responseUser;
    }

    public void setResponseUser(String responseUser) {
        this.responseUser = responseUser;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getTypeSid() {
        return typeSid;
    }

    public void setTypeSid(String typeSid) {
        this.typeSid = typeSid;
    }

    @Override
    public String toString() {
        return "ServiceTypeSelectParam{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", serviceCategory='" + serviceCategory + '\'' +
                ", typeSid='" + typeSid + '\'' +
                ", modifiedOn='" + modifiedOn + '\'' +
                ", responseUser='" + responseUser + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                '}';
    }
}
