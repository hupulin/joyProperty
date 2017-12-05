package com.jinyi.ihome.module.newshop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2017/4/6.
 */
public class ActivityTo {


    /**
     * activityVo : {"activityId":"mock","activityName":"mock","activityUrl":"mock","isWarmup":"mock","warmupStartTime":"mock","activityStartTime":"mock","activityEndTime":"mock","activityGoodsVoList":[{"activityGoodsId":"mock","activityGoodsCode":"mock","activityGoodsName":"mock","originalPriceName":"mock","currentPriceName":"mock","minSalesNum":"mock","distributionMode":"mock","distributionCost":"mock","goodsDescribe":"mock","goodsType":"mock","groundingTime":"mock","activityGoodsPicVO":[{"id":"mock","activityGoodsId":"mock","url":"mock"}],"activityGoodsSpecificationVO":[{"id":"mock","activityGoodsId":"mock","specificationName":"mock","originalPrice":"mock","currentPrice":"mock","stockNum":"mock"}],"activityGoodsLableVO":[{"id":"mock","activityGoodsId":"mock","lableName":"mock"}],"stockSum":"mock","minOriginalPrice":"mock","minCurrentPrice":"mock"}]}
     */

    private ActivityVoTo activityVo;

    public ActivityVoTo getActivityVo() {
        return activityVo;
    }

    public void setActivityVo(ActivityVoTo activityVo) {
        this.activityVo = activityVo;
    }

    public static class ActivityVoTo implements Serializable{
        /**
         * activityId : mock
         * activityName : mock
         * activityUrl : mock
         * isWarmup : mock
         * warmupStartTime : mock
         * activityStartTime : mock
         * activityEndTime : mock
         * activityGoodsVoList : [{"activityGoodsId":"mock","activityGoodsCode":"mock","activityGoodsName":"mock","originalPriceName":"mock","currentPriceName":"mock","minSalesNum":"mock","distributionMode":"mock","distributionCost":"mock","goodsDescribe":"mock","goodsType":"mock","groundingTime":"mock","activityGoodsPicVO":[{"id":"mock","activityGoodsId":"mock","url":"mock"}],"activityGoodsSpecificationVO":[{"id":"mock","activityGoodsId":"mock","specificationName":"mock","originalPrice":"mock","currentPrice":"mock","stockNum":"mock"}],"activityGoodsLableVO":[{"id":"mock","activityGoodsId":"mock","lableName":"mock"}],"stockSum":"mock","minOriginalPrice":"mock","minCurrentPrice":"mock"}]
         */

        private String activityId;
        private String activityName;
        private String activityUrl;
        private String isWarmup;
        private String warmupStartTime;
        private String activityStartTime;
        private String activityEndTime;
        private List<ActivityGoodsVoListTo> activityGoodsVoList;

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivityUrl() {
            return activityUrl;
        }

        public void setActivityUrl(String activityUrl) {
            this.activityUrl = activityUrl;
        }

        public String getIsWarmup() {
            return isWarmup;
        }

        public void setIsWarmup(String isWarmup) {
            this.isWarmup = isWarmup;
        }

        public String getWarmupStartTime() {
            return warmupStartTime;
        }

        public void setWarmupStartTime(String warmupStartTime) {
            this.warmupStartTime = warmupStartTime;
        }

        public String getActivityStartTime() {
            return activityStartTime;
        }

        public void setActivityStartTime(String activityStartTime) {
            this.activityStartTime = activityStartTime;
        }

        public String getActivityEndTime() {
            return activityEndTime;
        }

        public void setActivityEndTime(String activityEndTime) {
            this.activityEndTime = activityEndTime;
        }

        public List<ActivityGoodsVoListTo> getActivityGoodsVoList() {
            return activityGoodsVoList;
        }

        public void setActivityGoodsVoList(List<ActivityGoodsVoListTo> activityGoodsVoList) {
            this.activityGoodsVoList = activityGoodsVoList;
        }

        @Override
        public String toString() {
            return "ActivityVoTo{" +
                    "activityId='" + activityId + '\'' +
                    ", activityName='" + activityName + '\'' +
                    ", activityUrl='" + activityUrl + '\'' +
                    ", isWarmup='" + isWarmup + '\'' +
                    ", warmupStartTime='" + warmupStartTime + '\'' +
                    ", activityStartTime='" + activityStartTime + '\'' +
                    ", activityEndTime='" + activityEndTime + '\'' +
                    ", activityGoodsVoList=" + activityGoodsVoList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ActivityTo{" +
                "activityVo=" + activityVo +
                '}';
    }
}
