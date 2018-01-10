package com.jinyi.ihome.module.worksign;

/**
 * Created by xzz on 2018/1/8.
 *
 **/

public class SignApartmentTo {

    /**
     * PId : 1
     * title : 幸福家园
     */

    private int PId;
    private String title;

    public int getPId() {
        return PId;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SignApartmentTo{" +
                "PId=" + PId +
                ", title='" + title + '\'' +
                '}';
    }
}
