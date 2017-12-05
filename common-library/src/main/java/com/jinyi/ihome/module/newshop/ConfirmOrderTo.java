package com.jinyi.ihome.module.newshop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2016/7/23.
 */
public class ConfirmOrderTo implements Serializable{



    private int activityName;
    /**
     * name : 熊壮
     * receiverNumber : 15168234205
     * receiver : 湖北省咸宁市咸安区温泉区湖北科技学院核技术与化学生物学院熊壮
     */

    private List<OrderAddressTo> address;


    private List<CouponTo> couponList;

    public int getActivityName() {
        return activityName;
    }

    public void setActivityName(int activityName) {
        this.activityName = activityName;
    }

    public List<OrderAddressTo> getAddress() {
        return address;
    }

    public void setAddress(List<OrderAddressTo> address) {
        this.address = address;
    }

    public List<CouponTo> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponTo> couponList) {
        this.couponList = couponList;
    }





    @Override
    public String toString() {
        return "ConfirmOrderTo{" +
                "activityName=" + activityName +
                ", address=" + address +
                ", couponList=" + couponList +
                '}';
    }
}
