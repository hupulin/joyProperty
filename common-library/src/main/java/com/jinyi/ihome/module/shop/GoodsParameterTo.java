package com.jinyi.ihome.module.shop;

/**
 * Created by xz on 2017/4/9.
 */
public class GoodsParameterTo {

    /**
     * parameterName : mock
     * parameterValue : mock
     * parameterId : mock
     */

    private String parameterName;
    private String parameterValue;
    private String parameterId;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    @Override
    public String toString() {
        return "GoodsParameterTo{" +
                "parameterName='" + parameterName + '\'' +
                ", parameterValue='" + parameterValue + '\'' +
                ", parameterId='" + parameterId + '\'' +
                '}';
    }
}
