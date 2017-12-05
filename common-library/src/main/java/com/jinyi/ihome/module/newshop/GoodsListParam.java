package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/1/5.
 */
public class GoodsListParam implements Serializable{

    /**
     * priceSort : asc
     * currentPage :
     * pageSize :
     */

    private String priceSort;
    private String currentPage;
    private String pageSize;
    private String categoryId;
    private String communityId;
    private String merchantId;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "GoodsListParam{" +
                "priceSort='" + priceSort + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", communityId='" + communityId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                '}';
    }
}
