package com.jinyi.ihome.module.worksign;

import java.io.Serializable;

/**
 * Created by xzz on 2018/1/10.
 */

public class SignRecordInfoTo implements Serializable{
    private int footprintType;
    private String userName;
    private String startDate;
    private String endDate;

    public int getFootprintType() {
        return footprintType;
    }

    public void setFootprintType(int footprintType) {
        this.footprintType = footprintType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SignRecordInfoTo{" +
                "footprintType=" + footprintType +
                ", userName='" + userName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
