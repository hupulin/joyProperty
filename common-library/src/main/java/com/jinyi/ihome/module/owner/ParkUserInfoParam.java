package com.jinyi.ihome.module.owner;

/**
 * Created by xz on 2017/3/18.
 */
public class ParkUserInfoParam {


        /***
         * 姓名
         */

        private String ownerName;
        /***
         * 電話號碼
         */

        private String ownerPhone;

    @Override
    public String toString() {
        return "ParkUserInfoParam{" +
                "ownerName='" + ownerName + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                '}';
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
}
