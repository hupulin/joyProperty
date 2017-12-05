package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/8.
 */
public class CategoryChannelTo {

    /**
     * categoryId : 14214114562343936
     * categoryName : 3333
     */

    private String categoryId;
    private String categoryName;
    private String homePagePic;
    private List<CarGoodsInfo>goodsApiVolist;
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CarGoodsInfo> getGoodsApiVolist() {
        return goodsApiVolist;
    }

    public void setGoodsApiVolist(List<CarGoodsInfo> goodsApiVolist) {
        this.goodsApiVolist = goodsApiVolist;
    }

    public String getHomePagePic() {
        return homePagePic;
    }

    public void setHomePagePic(String homePagePic) {
        this.homePagePic = homePagePic;
    }

    @Override
    public String toString() {
        return "CategoryChannelTo{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", goodsApiVolist=" + goodsApiVolist +
                '}';
    }
}
