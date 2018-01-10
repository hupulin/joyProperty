package com.jinyi.ihome.module.worksign;

/**
 * Created by xzz on 2017/12/29.
 */

public class SignMainPageTo {

    /**
     * username : test
     * branchname : 工程部
     * signnum : 0
     * signitem : {"logid":1,"createtime":"2017年12月01日11:00","address":"巡更地点","work_cont":"工作内容"}
     * eqaddress :
     */

    private String username;
    private String branchname;
    private int signnum;
    private SignitemBean signitem;
    private String eqaddress;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public int getSignnum() {
        return signnum;
    }

    public void setSignnum(int signnum) {
        this.signnum = signnum;
    }

    public SignitemBean getSignitem() {
        return signitem;
    }

    public void setSignitem(SignitemBean signitem) {
        this.signitem = signitem;
    }

    public String getEqaddress() {
        return eqaddress;
    }

    public void setEqaddress(String eqaddress) {
        this.eqaddress = eqaddress;
    }



    public static class SignitemBean {
        /**
         * logid : 1
         * createtime : 2017年12月01日11:00
         * address : 巡更地点
         * work_cont : 工作内容
         */

        private int logid;
        private String createtime;
        private String address;
        private String work_cont;

        public int getLogid() {
            return logid;
        }

        public void setLogid(int logid) {
            this.logid = logid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getWork_cont() {
            return work_cont;
        }

        public void setWork_cont(String work_cont) {
            this.work_cont = work_cont;
        }

        @Override
        public String toString() {
            return "SignitemBean{" +
                    "logid=" + logid +
                    ", createtime='" + createtime + '\'' +
                    ", address='" + address + '\'' +
                    ", work_cont='" + work_cont + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SignMainPageTo{" +
                "username='" + username + '\'' +
                ", branchname='" + branchname + '\'' +
                ", signnum=" + signnum +
                ", signitem=" + signitem +
                ", eqaddress='" + eqaddress + '\'' +
                '}';
    }
}
