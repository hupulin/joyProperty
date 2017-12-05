package com.jinyi.ihome.module.house;

/**
 * Created by xz on 2016/5/20.
 */
public class HouseValueTypeTo {
    /**
     * configSid : C2077CAE-9F88-4595-9875-59B3C98FA7D6
     * configType : 10
     * configName : 面积
     * configValue : 30平米以下
     * configIndex : 1
     */
    private static final long serialVersionUID = 1L;
    private String configSid;
    private int configType;
    private String configName;
    private String configValue;
    private int configIndex;
    private String configIcon;
    public String getConfigSid() {
        return configSid;
    }

    public void setConfigSid(String configSid) {
        this.configSid = configSid;
    }

    public int getConfigType() {
        return configType;
    }

    public void setConfigType(int configType) {
        this.configType = configType;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public int getConfigIndex() {
        return configIndex;
    }

    public void setConfigIndex(int configIndex) {
        this.configIndex = configIndex;
    }

    public String getConfigIcon() {
        return configIcon;
    }

    public void setConfigIcon(String configIcon) {
        this.configIcon = configIcon;
    }

    @Override
    public String toString() {
        return "HouseValueTypeTo{" +
                "configSid='" + configSid + '\'' +
                ", configType=" + configType +
                ", configName='" + configName + '\'' +
                ", configValue='" + configValue + '\'' +
                ", configIndex=" + configIndex +
                ", configIcon='" + configIcon + '\'' +
                '}';
    }
}
