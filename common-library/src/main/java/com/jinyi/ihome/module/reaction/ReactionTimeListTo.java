package com.jinyi.ihome.module.reaction;

import java.io.Serializable;
import java.util.List;

/**
 * Author:    fz
 * Version    V1.0
 * Date:      2016/5/26
 * Description:
 * Modification    History:
 * Date         	Author        		Version        	Description
 * ------------------------------------------------------------------
 * 2016/5/26         fz             1.0                1.0
 * Why & What is modified:
 */
public class ReactionTimeListTo implements Serializable{


    /**
     * success : 0
     * message :
     * total : 0
     * data : [{"apartmentSid":"009416cc-28fa-45a8-bcb7-13e469205048","apartmentName":"上尚庭","valueXY":[{"today":0,"history":9,"rate":0},{"today":0,"history":4,"rate":0},{"today":0,"history":3,"rate":0}],"valueCL":[{"today":0,"history":6,"rate":0},{"today":0,"history":3,"rate":0},{"today":0,"history":26,"rate":0}]}]
     * tag : null
     */

    private int success;
    private String message;
    private int total;
    private Object tag;
    /**
     * apartmentSid : 009416cc-28fa-45a8-bcb7-13e469205048
     * apartmentName : 上尚庭
     * valueXY : [{"today":0,"history":9,"rate":0},{"today":0,"history":4,"rate":0},{"today":0,"history":3,"rate":0}]
     * valueCL : [{"today":0,"history":6,"rate":0},{"today":0,"history":3,"rate":0},{"today":0,"history":26,"rate":0}]
     */

    private List<DataBean> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "apartmentSid='" + apartmentSid + '\'' +
                    ", apartmentName='" + apartmentName + '\'' +
                    ", valueXY=" + valueXY +
                    ", valueCL=" + valueCL +
                    '}';
        }

        private String apartmentSid;
        private String apartmentName;
        /**
         * today : 0
         * history : 9
         * rate : 0.0
         */

        private List<ValueXYBean> valueXY;
        /**
         * today : 0
         * history : 6
         * rate : 0.0
         */

        private List<ValueCLBean> valueCL;

        public String getApartmentSid() {
            return apartmentSid;
        }

        public void setApartmentSid(String apartmentSid) {
            this.apartmentSid = apartmentSid;
        }

        public String getApartmentName() {
            return apartmentName;
        }

        public void setApartmentName(String apartmentName) {
            this.apartmentName = apartmentName;
        }

        public List<ValueXYBean> getValueXY() {
            return valueXY;
        }

        public void setValueXY(List<ValueXYBean> valueXY) {
            this.valueXY = valueXY;
        }

        public List<ValueCLBean> getValueCL() {
            return valueCL;
        }

        public void setValueCL(List<ValueCLBean> valueCL) {
            this.valueCL = valueCL;
        }

        public static class ValueXYBean {
            private int today;
            private int history;
            private double rate;

            public int getToday() {
                return today;
            }

            public void setToday(int today) {
                this.today = today;
            }

            public int getHistory() {
                return history;
            }

            public void setHistory(int history) {
                this.history = history;
            }

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }
        }

        public static class ValueCLBean {
            private int today;
            private int history;
            private double rate;

            public int getToday() {
                return today;
            }

            public void setToday(int today) {
                this.today = today;
            }

            public int getHistory() {
                return history;
            }

            public void setHistory(int history) {
                this.history = history;
            }

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }
        }
    }
}
