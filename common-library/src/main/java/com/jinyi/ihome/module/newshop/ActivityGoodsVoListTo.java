package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/4/9.
 */
public class ActivityGoodsVoListTo {


    /**
     * activityGoodsId : mock
     * activityGoodsCode : mock
     * activityGoodsName : mock
     * originalPriceName : mock
     * currentPriceName : mock
     * minSalesNum : mock
     * distributionMode : mock
     * distributionCost : mock
     * goodsDescribe : mock
     * goodsType : mock
     * goodsDetailStr : mock
     * groundingTime : mock
     * activityGoodsPicVO : [{"id":"mock","activityGoodsId":"mock","url":"mock"}]
     * activityGoodsSpecificationVO : [{"id":"mock","activityGoodsId":"mock","specificationName":"mock","originalPrice":"mock","currentPrice":"mock","stockNum":"mock"}]
     * activityGoodsLableVO : [{"id":"mock","activityGoodsId":"mock","lableName":"mock"}]
     * stockSum : mock
     * minOriginalPrice : mock
     * minCurrentPrice : mock
     */

    private String activityGoodsId;
    private String activityGoodsCode;
    private String activityGoodsName;
    private String originalPriceName;
    private String currentPriceName;
    private int minSalesNum;
    private String distributionMode;
    private String distributionCost;
    private String goodsDescribe;
    private String goodsType;
    private String goodsDetailStr;
    private String groundingTime;
    private String stockSum;
    private double minOriginalPrice;
    private double minCurrentPrice;
    private List<ActivityGoodsPicVOBean> activityGoodsPicVO;
    private List<ActivityGoodsSpecificationVOBean> activityGoodsSpecificationVO;
    private List<ActivityGoodsLableVOBean> activityGoodsLableVO;

    public String getActivityGoodsId() {
        return activityGoodsId;
    }

    public void setActivityGoodsId(String activityGoodsId) {
        this.activityGoodsId = activityGoodsId;
    }

    public String getActivityGoodsCode() {
        return activityGoodsCode;
    }

    public void setActivityGoodsCode(String activityGoodsCode) {
        this.activityGoodsCode = activityGoodsCode;
    }

    public String getActivityGoodsName() {
        return activityGoodsName;
    }

    public void setActivityGoodsName(String activityGoodsName) {
        this.activityGoodsName = activityGoodsName;
    }

    public String getOriginalPriceName() {
        return originalPriceName;
    }

    public void setOriginalPriceName(String originalPriceName) {
        this.originalPriceName = originalPriceName;
    }

    public String getCurrentPriceName() {
        return currentPriceName;
    }

    public void setCurrentPriceName(String currentPriceName) {
        this.currentPriceName = currentPriceName;
    }

    public int getMinSalesNum() {
        return minSalesNum;
    }

    public void setMinSalesNum(int minSalesNum) {
        this.minSalesNum = minSalesNum;
    }

    public void setMinOriginalPrice(double minOriginalPrice) {
        this.minOriginalPrice = minOriginalPrice;
    }

    public void setMinCurrentPrice(double minCurrentPrice) {
        this.minCurrentPrice = minCurrentPrice;
    }

    public String getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(String distributionMode) {
        this.distributionMode = distributionMode;
    }

    public String getDistributionCost() {
        return distributionCost;
    }

    public void setDistributionCost(String distributionCost) {
        this.distributionCost = distributionCost;
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsDetailStr() {
        return goodsDetailStr;
    }

    public void setGoodsDetailStr(String goodsDetailStr) {
        this.goodsDetailStr = goodsDetailStr;
    }

    public String getGroundingTime() {
        return groundingTime;
    }

    public void setGroundingTime(String groundingTime) {
        this.groundingTime = groundingTime;
    }

    public String getStockSum() {
        return stockSum;
    }

    public void setStockSum(String stockSum) {
        this.stockSum = stockSum;
    }



    public List<ActivityGoodsPicVOBean> getActivityGoodsPicVO() {
        return activityGoodsPicVO;
    }

    public void setActivityGoodsPicVO(List<ActivityGoodsPicVOBean> activityGoodsPicVO) {
        this.activityGoodsPicVO = activityGoodsPicVO;
    }

    public List<ActivityGoodsSpecificationVOBean> getActivityGoodsSpecificationVO() {
        return activityGoodsSpecificationVO;
    }

    public void setActivityGoodsSpecificationVO(List<ActivityGoodsSpecificationVOBean> activityGoodsSpecificationVO) {
        this.activityGoodsSpecificationVO = activityGoodsSpecificationVO;
    }

    public List<ActivityGoodsLableVOBean> getActivityGoodsLableVO() {
        return activityGoodsLableVO;
    }

    public void setActivityGoodsLableVO(List<ActivityGoodsLableVOBean> activityGoodsLableVO) {
        this.activityGoodsLableVO = activityGoodsLableVO;
    }

    public static class ActivityGoodsPicVOBean {
        /**
         * id : mock
         * activityGoodsId : mock
         * url : mock
         */

        private String id;
        private String activityGoodsId;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getActivityGoodsId() {
            return activityGoodsId;
        }

        public void setActivityGoodsId(String activityGoodsId) {
            this.activityGoodsId = activityGoodsId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ActivityGoodsSpecificationVOBean {
        /**
         * id : mock
         * activityGoodsId : mock
         * specificationName : mock
         * originalPrice : mock
         * currentPrice : mock
         * stockNum : mock
         */

        private String id;
        private String activityGoodsId;
        private String specificationName;
        private String originalPrice;
        private String currentPrice;
        private String stockNum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getActivityGoodsId() {
            return activityGoodsId;
        }

        public void setActivityGoodsId(String activityGoodsId) {
            this.activityGoodsId = activityGoodsId;
        }

        public String getSpecificationName() {
            return specificationName;
        }

        public void setSpecificationName(String specificationName) {
            this.specificationName = specificationName;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = currentPrice;
        }

        public String getStockNum() {
            return stockNum;
        }

        public void setStockNum(String stockNum) {
            this.stockNum = stockNum;
        }
    }

    public static class ActivityGoodsLableVOBean {
        /**
         * id : mock
         * activityGoodsId : mock
         * lableName : mock
         */

        private String id;
        private String activityGoodsId;
        private String lableName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getActivityGoodsId() {
            return activityGoodsId;
        }

        public void setActivityGoodsId(String activityGoodsId) {
            this.activityGoodsId = activityGoodsId;
        }

        public String getLableName() {
            return lableName;
        }

        public void setLableName(String lableName) {
            this.lableName = lableName;
        }

        @Override
        public String toString() {
            return "ActivityGoodsLableVOBean{" +
                    "id='" + id + '\'' +
                    ", activityGoodsId='" + activityGoodsId + '\'' +
                    ", lableName='" + lableName + '\'' +
                    '}';
        }
    }
}
