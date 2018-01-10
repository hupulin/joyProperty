package com.jinyi.ihome.module.worksign;

import java.util.List;

/**
 * Created by xzz on 2018/1/2.
 */

public class MyPrintTo  {

    /**
     * timeslist : [{"block":"2017.12.01 11:00","bktotal":1,"bkey":"2017.12.01 11:00"},{"block":"2017.12.01 14:00","bktotal":4,"bkey":"2017.12.01 14:00"}]
     * totalsign : 5
     * typelist : {"类型名称":1,"机房":4}
     */

    private int totalsign;
    private List<TypelistBean> typelist;
    private List<TimeslistBean> timeslist;

    public int getTotalsign() {
        return totalsign;
    }

    public void setTotalsign(int totalsign) {
        this.totalsign = totalsign;
    }


    public List<TypelistBean> getTypelist() {
        return typelist;
    }

    public void setTypelist(List<TypelistBean> typelist) {
        this.typelist = typelist;
    }

    public List<TimeslistBean> getTimeslist() {
        return timeslist;
    }

    public void setTimeslist(List<TimeslistBean> timeslist) {
        this.timeslist = timeslist;
    }

    public static class TypelistBean {
        /**
         * 类型名称 : 1
         * 机房 : 4
         */

        private String tpname;
        private int tpnum;

        public String getTpname() {
            return tpname;
        }

        public void setTpname(String tpname) {
            this.tpname = tpname;
        }

        public int getTpnum() {
            return tpnum;
        }

        public void setTpnum(int tpnum) {
            this.tpnum = tpnum;
        }

        @Override
        public String toString() {
            return "TypelistBean{" +
                    "tpname='" + tpname + '\'' +
                    ", tpnum=" + tpnum +
                    '}';
        }
    }

    public static class TimeslistBean {
        /**
         * block : 2017.12.01 11:00
         * bktotal : 1
         * bkey : 2017.12.01 11:00
         */

        private String block;
        private int bktotal;
        private String bkey;
        private String workContent;
        private String workPlace;
        private String workTime;

        public String getBlock() {
            return block;
        }

        public void setBlock(String block) {
            this.block = block;
        }

        public int getBktotal() {
            return bktotal;
        }

        public void setBktotal(int bktotal) {
            this.bktotal = bktotal;
        }

        public String getBkey() {
            return bkey;
        }

        public void setBkey(String bkey) {
            this.bkey = bkey;
        }

        public String getWorkContent() {
            return workContent;
        }

        public void setWorkContent(String workContent) {
            this.workContent = workContent;
        }

        public String getWorkPlace() {
            return workPlace;
        }

        public void setWorkPlace(String workPlace) {
            this.workPlace = workPlace;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        @Override
        public String toString() {
            return "TimeslistBean{" +
                    "block='" + block + '\'' +
                    ", bktotal=" + bktotal +
                    ", bkey='" + bkey + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyPrintTo{" +
                "totalsign=" + totalsign +
                ", typelist=" + typelist +
                ", timeslist=" + timeslist +
                '}';
    }
}
