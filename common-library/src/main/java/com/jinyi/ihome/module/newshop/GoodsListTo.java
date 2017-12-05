package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2016/7/25.
 */
public class GoodsListTo {

    /**
     * images : xz_test1.png;xz_test2.png;xz_test3.png;xz_test4.png
     * traffic : 12.0
     * expressCompany : 圆通快递
     */

    private String images;
    private double traffic;
    private String expressCompany;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public double getTraffic() {
        return traffic;
    }

    public void setTraffic(double traffic) {
        this.traffic = traffic;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }
}
