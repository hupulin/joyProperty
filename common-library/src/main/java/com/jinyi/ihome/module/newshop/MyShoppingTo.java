package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2016/7/25.
 */
public class MyShoppingTo {

    /**
     * images : xz_test1.png;xz_test2.png
     * money : 1355.0
     * status : 0
     * payStatus : 0
     * phoneNumber : 15168234205
     */

    private String images;
    private double money;
    private int status;
    private int payStatus;
    private String phoneNumber;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "MyShoppingTo{" +
                "images='" + images + '\'' +
                ", money=" + money +
                ", status=" + status +
                ", payStatus=" + payStatus +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
