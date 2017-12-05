package com.joy.common.bean;

import com.jinyi.ihome.module.apartment.ApartmentPlaceTo;

import java.io.Serializable;

/**
 * Created by xzz on 2016/2/24.
 */
public class RegisterParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private ApartmentPlaceTo placeTo;
    private String apartmentSid;
    private String nickname;
    private String no;
    private String pwd;
    private int type;
    private String mobileNo;

    public RegisterParam() {
    }

    public void setPlaceTo(ApartmentPlaceTo placeTo) {
        this.placeTo = placeTo;
    }

    public ApartmentPlaceTo getPlaceTo() {
        return this.placeTo;
    }

    public void setApartmentSid(String apartmentSid) {
        this.apartmentSid = apartmentSid;
    }

    public String getApartmentSid() {
        return this.apartmentSid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    private String roomTag;
    private String name;

    /***
     * 身份证号码
     */

    private String idString;

    //业主姓名


    public String getRoomTag() {
        return roomTag;
    }

    public void setRoomTag(String roomTag) {
        this.roomTag = roomTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }



}
