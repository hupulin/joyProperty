package com.jinyi.ihome.module.newshop;

import java.io.Serializable;

/**
 * Created by xz on 2017/4/14.
 */
public class ActivityTimeTo implements Serializable{
    private String activityId;
    private String activityName;
    private String activityUrl;
    private String isWarmup;
    private String warmupStartTime;
    private String activityStartTime;
    private String activityEndTime;

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
}
