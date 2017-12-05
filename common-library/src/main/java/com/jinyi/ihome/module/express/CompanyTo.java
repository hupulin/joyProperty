package com.jinyi.ihome.module.express;

import java.io.Serializable;

/**
 * Created by usb on 2017/6/13.
 */

public class CompanyTo implements Serializable {
    /**
     * companySid : 01
     * companyName : 韵达快递
     * companyIndex : 1
     * companyIcon : yunda
     */

    private String companySid;
    private String companyName;
    private int companyIndex;
    private String companyIcon;

    public String getCompanySid() {
        return companySid;
    }

    public void setCompanySid(String companySid) {
        this.companySid = companySid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyIndex() {
        return companyIndex;
    }

    public void setCompanyIndex(int companyIndex) {
        this.companyIndex = companyIndex;
    }

    public String getCompanyIcon() {
        return companyIcon;
    }

    public void setCompanyIcon(String companyIcon) {
        this.companyIcon = companyIcon;
    }
}
