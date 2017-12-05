package com.jinyi.ihome.module.home;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xz on 2017/3/14.
 */
public class ServiceCategoryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /***
     * 小区Id
     */
    private String apartmentSid;
    private String groupUserSid;

    public String getGroupUserSid() {
        return groupUserSid;
    }

    public void setGroupUserSid(String groupUserSid) {
        this.groupUserSid = groupUserSid;
    }

    /***
     * 家政服务 9098ED29-072D-4653-A37D-3C2F6DF80861
     * 入室维修 BCCF6994-9449-4E6D-9F5B-09CE08AD9353
     * 公共维修 C733AA3D-32FA-4F5B-B299-061044661DC0
     * 投诉 7D2B996C-12EC-4CD4-8499-B453E96AF11F
     * 表扬 3198DD68-1346-4856-BD50-90E9373559A0
     * 建议 9B773735-1E0E-4677-A3B5-19A50B58D15D
     * 巡检 51979B62-10E6-44C7-88B9-4B239B1CE02F
     * 服务分类
     */
    private String serviceCategory;

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String state) {
        this.serviceStatus = state;
    }

    private String serviceStatus;

    /***
     * 服务类型ID
     */
    private String typeSid;

    /***
     * 服务更新时间
     */
    private String modifiedOn;

    /***
     * 响应用户SID     （维修工）
     */
    private String responseUser;

    /***
     * 用户id
     */
    private String ownerSid;

    //区别我的巡检  我的执行人 '1'  我的关注  '2'

    private String type;

    //分页
    private  int pageIndex;

    public String getApartmentSid() {
        return apartmentSid;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
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

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getResponseUser() {
        return responseUser;
    }

    public void setResponseUser(String responseUser) {
        this.responseUser = responseUser;
    }

    public String getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(String ownerSid) {
        this.ownerSid = ownerSid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return "ServiceCategoryParam{" +
                "apartmentSid='" + apartmentSid + '\'' +
                ", groupUserSid='" + groupUserSid + '\'' +
                ", serviceCategory='" + serviceCategory + '\'' +
                ", serviceStatus='" + serviceStatus + '\'' +
                ", typeSid='" + typeSid + '\'' +
                ", modifiedOn='" + modifiedOn + '\'' +
                ", responseUser='" + responseUser + '\'' +
                ", ownerSid='" + ownerSid + '\'' +
                ", type='" + type + '\'' +
                ", pageIndex=" + pageIndex +
                '}';
    }
}
