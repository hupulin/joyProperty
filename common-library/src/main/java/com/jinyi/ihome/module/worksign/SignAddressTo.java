package com.jinyi.ihome.module.worksign;

/**
 * Created by xzz on 2018/1/15.
 */

public class SignAddressTo {


    /**
     * parkname : 幸福家园
     * eqaddress : 联合大厦704
     */

    private String parkname;
    private String eqaddress;

    public String getParkname() {
        return parkname;
    }

    public void setParkname(String parkname) {
        this.parkname = parkname;
    }

    public String getEqaddress() {
        return eqaddress;
    }

    public void setEqaddress(String eqaddress) {
        this.eqaddress = eqaddress;
    }

    @Override
    public String toString() {
        return "SignAddressTo{" +
                "parkname='" + parkname + '\'' +
                ", eqaddress='" + eqaddress + '\'' +
                '}';
    }
}
