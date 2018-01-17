package com.joy.property.worksign.adapter;

import java.util.List;

/**
 * Created by xzz on 2017/12/28.
 */
public class SignSubmitJsonTo {

    /**
     * AppVersion : 1.0.0
     * OsVersion : 1.0.0
     * TermType : Android
     * DeviceId : XXXXXXX
     * BrandCode : NACITYRES
     * UniqueStr : SSSSSSSSSSSSSSS
     * TradeType : GetHome
     * OpenId : NACITYRES
     */

    private String AppVersion="1.0.0";
    private String OsVersion="1.0.0";
    private String TermType="Android";
    private String DeviceId="1909DCFD-243D-2F68-233A-250C9C9B571E";
    private String BrandCode="NACITYRES";
    private String UniqueStr;
    private String TradeType;
    private String OpenId="NACITYRES";
    private String EqId="1512092252";
    private String EqMark="1512092252";
    private String EqEy="90";
    private String SignNote;
    private String ImgToken;
    private String JobStr;
    private int ReportType;
    private String StartDate;
    private String EndDate;
    private String BKey;
    private int SignId;
    private String SignTime;
    private String ParkName;
    private List<String>imageList;
    private boolean isAllUpload;
    private String address;



    public String getAppVersion() {
        return AppVersion;
    }

    public void setAppVersion(String AppVersion) {
        this.AppVersion = AppVersion;
    }

    public String getOsVersion() {
        return OsVersion;
    }

    public void setOsVersion(String OsVersion) {
        this.OsVersion = OsVersion;
    }

    public String getTermType() {
        return TermType;
    }

    public void setTermType(String TermType) {
        this.TermType = TermType;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }

    public String getBrandCode() {
        return BrandCode;
    }

    public void setBrandCode(String BrandCode) {
        this.BrandCode = BrandCode;
    }

    public String getUniqueStr() {
        return UniqueStr;
    }

    public void setUniqueStr(String UniqueStr) {
        this.UniqueStr = UniqueStr;
    }

    public String getTradeType() {
        return TradeType;
    }

    public void setTradeType(String TradeType) {
        this.TradeType = TradeType;
    }

    public String getOpenId() {
        return OpenId;
    }

    public void setOpenId(String OpenId) {
        this.OpenId = OpenId;
    }

    public String getEqId() {
        return EqId;
    }

    public void setEqId(String eqId) {
        EqId = eqId;
    }

    public String getEqMark() {
        return EqMark;
    }

    public void setEqMark(String eqMark) {
        EqMark = eqMark;
    }

    public String getEqEy() {
        return EqEy;
    }

    public void setEqEy(String eqEy) {
        EqEy = eqEy;
    }

    public String getSignNote() {
        return SignNote;
    }

    public void setSignNote(String signNote) {
        SignNote = signNote;
    }

    public String getImgToken() {
        return ImgToken;
    }

    public void setImgToken(String imgToken) {
        ImgToken = imgToken;
    }

    public String getJobStr() {
        return JobStr;
    }

    public void setJobStr(String jobStr) {
        JobStr = jobStr;
    }

    public int getReportType() {
        return ReportType;
    }

    public void setReportType(int reportType) {
        ReportType = reportType;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getBKey() {
        return BKey;
    }

    public void setBKey(String BKey) {
        this.BKey = BKey;
    }

    public int getSignId() {
        return SignId;
    }

    public void setSignId(int signId) {
        SignId = signId;
    }

    public String getSignTime() {
        return SignTime;
    }

    public void setSignTime(String signTime) {
        SignTime = signTime;
    }

    public String getParkName() {
        return ParkName;
    }

    public void setParkName(String parkName) {
        ParkName = parkName;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }


    public boolean isAllUpload() {
        return isAllUpload;
    }

    public void setAllUpload(boolean allUpload) {
        isAllUpload = allUpload;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SignSubmitJsonTo{" +
                "AppVersion='" + AppVersion + '\'' +
                ", OsVersion='" + OsVersion + '\'' +
                ", TermType='" + TermType + '\'' +
                ", DeviceId='" + DeviceId + '\'' +
                ", BrandCode='" + BrandCode + '\'' +
                ", UniqueStr='" + UniqueStr + '\'' +
                ", TradeType='" + TradeType + '\'' +
                ", OpenId='" + OpenId + '\'' +
                ", EqId='" + EqId + '\'' +
                ", EqMark='" + EqMark + '\'' +
                ", EqEy='" + EqEy + '\'' +
                ", SignNote='" + SignNote + '\'' +
                ", ImgToken='" + ImgToken + '\'' +
                ", JobStr='" + JobStr + '\'' +
                ", ReportType=" + ReportType +
                ", StartDate='" + StartDate + '\'' +
                ", EndDate='" + EndDate + '\'' +
                ", BKey='" + BKey + '\'' +
                ", SignId=" + SignId +
                ", SignTime='" + SignTime + '\'' +
                ", ParkName='" + ParkName + '\'' +
                ", imageList=" + imageList +
                '}';
    }
}
