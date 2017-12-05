package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/1/19.
 */
public class ChangeAddressTo implements Serializable{

    private String name;
    private String phone;
    private String idCard;
    private String address;
    private boolean ischeck;
    @Override
    public String toString() {
        return "ChangeAddressTo{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                ", ischeck=" + ischeck +
                '}';
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }



    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
