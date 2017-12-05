package com.jinyi.ihome.module.shop;

/**
 * Created by xz on 2017/1/14.
 */
public class SearchParam {

    /**
     * goodsName :
     * priceSort : asc
     * currentPage :
     * pageSize :
     * communityId :
     */

    private String goodsName;
    private String priceSort;
    private String currentPage;
    private String pageSize;
    private String communityId;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Override
    public String toString() {
        return "SearchParam{" +
                "goodsName='" + goodsName + '\'' +
                ", priceSort='" + priceSort + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", communityId='" + communityId + '\'' +
                '}';
    }
}
