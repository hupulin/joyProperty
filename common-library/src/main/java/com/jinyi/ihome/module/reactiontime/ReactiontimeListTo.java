package com.jinyi.ihome.module.reactiontime;

import com.jinyi.ihome.module.apartment.ApartmentBasicTo;

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
public class ReactiontimeListTo extends ApartmentBasicTo{

    /**
     * today : 0
     * history : 2
     * rate : 0
     */

    private List<ValueXYBean> valueXY;
    /**
     * today : 0
     * history : 7
     * rate : 0
     */

    private List<ValueCLBean> valueCL;

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
        private int rate;

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

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }
    }

    public static class ValueCLBean {
        private int today;
        private int history;
        private int rate;

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

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }
    }




}
