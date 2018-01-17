package com.jinyi.ihome.module.worksign;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xzz on 2018/1/2.
 */

public class SignRecordTo  {


    /**
     * signlist : [{"id":1,"openid":"NACITYRES","pj_title":"项目名称","type_title":"类型名称","address":"巡更地点","user_name":"用户名","branch_name":"工程部","user_account":"18368032222","work_cont":"工作内容","work_img":[],"remark":"备注","status":1,"createtime":"2017-12-01 11:05:50"}]
     * totalsign : 1
     */

    private int totalsign;
    private List<SignListTo> signlist;

    public int getTotalsign() {
        return totalsign;
    }

    public void setTotalsign(int totalsign) {
        this.totalsign = totalsign;
    }

    public List<SignListTo> getSignlist() {
        return signlist;
    }

    public void setSignlist(List<SignListTo> signlist) {
        this.signlist = signlist;
    }

    public static class SignListTo implements Serializable{
        /**
         * id : 1
         * openid : NACITYRES
         * pj_title : 项目名称
         * type_title : 类型名称
         * address : 巡更地点
         * user_name : 用户名
         * branch_name : 工程部
         * user_account : 18368032222
         * work_cont : 工作内容
         * work_img : []
         * remark : 备注
         * status : 1
         * createtime : 2017-12-01 11:05:50
         */

        private int id;
        private String openid;
        private String pj_title;
        private String type_title;
        private String address;
        private String user_name;
        private String branch_name;
        private String user_account;
        private String work_cont;
        private String remark;
        private int status;
        private String createtime;
        private String work_img;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getPj_title() {
            return pj_title;
        }

        public void setPj_title(String pj_title) {
            this.pj_title = pj_title;
        }

        public String getType_title() {
            return type_title;
        }

        public void setType_title(String type_title) {
            this.type_title = type_title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getBranch_name() {
            return branch_name;
        }

        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }

        public String getUser_account() {
            return user_account;
        }

        public void setUser_account(String user_account) {
            this.user_account = user_account;
        }

        public String getWork_cont() {
            return work_cont;
        }

        public void setWork_cont(String work_cont) {
            this.work_cont = work_cont;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getWork_img() {
            return work_img;
        }

        public void setWork_img(String work_img) {
            this.work_img = work_img;
        }

        @Override
        public String toString() {
            return "SignlistBean{" +
                    "id=" + id +
                    ", openid='" + openid + '\'' +
                    ", pj_title='" + pj_title + '\'' +
                    ", type_title='" + type_title + '\'' +
                    ", address='" + address + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", branch_name='" + branch_name + '\'' +
                    ", user_account='" + user_account + '\'' +
                    ", work_cont='" + work_cont + '\'' +
                    ", remark='" + remark + '\'' +
                    ", status=" + status +
                    ", createtime='" + createtime + '\'' +
                    ", work_img=" + work_img +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SignRecordTo{" +
                "totalsign=" + totalsign +
                ", signlist=" + signlist +
                '}';
    }
}
