package com.jinyi.ihome.module.newshop;

/**
 * Created by usb on 2017/4/12.
 */

public class ActivityCouponTo {
    private String couponId ;
    private String activityId ;//活动id

    @Override
    public String toString() {
        return "ActivityCouponTo{" +
                "couponId='" + couponId + '\'' +
                ", activityId=" + activityId +
                '}';
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
