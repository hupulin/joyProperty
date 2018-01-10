package com.jinyi.ihome.module.worksign;

/**
 * Created by xzz on 2018/1/8.
 */

public class CheckDeviceTo {

    /**
     * status : 1
     * checkmsg :
     */

    private int status;
    private String checkmsg;
    private String ParkName;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCheckmsg() {
        return checkmsg;
    }

    public void setCheckmsg(String checkmsg) {
        this.checkmsg = checkmsg;
    }

    public String getParkName() {
        return ParkName;
    }

    public void setParkName(String parkName) {
        ParkName = parkName;
    }
}
