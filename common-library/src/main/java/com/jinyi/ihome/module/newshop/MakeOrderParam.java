package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/9.
 */
public class MakeOrderParam {

    /**
     * cartId :
     * merchantList : [{"merchantId":"","merchantGoodslist":[{"goodsId":"","goodsNum":""}]}]
     */

    private String cartId;
    /**
     * merchantId :
     * merchantGoodslist : [{"goodsId":"","goodsNum":""}]
     */

    private List<MerchantList> merchantList;
    private List<ActivityGoodsListTo>cartActivityGoodsList;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<MerchantList> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<MerchantList> merchantList) {
        this.merchantList = merchantList;
    }

    public static class MerchantList {
        private String merchantId;
        /**
         * goodsId :
         * goodsNum :
         */

        private List<MerchantGoodslist> merchantGoodslist;

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public List<MerchantGoodslist> getMerchantGoodslist() {
            return merchantGoodslist;
        }

        public void setMerchantGoodslist(List<MerchantGoodslist> merchantGoodslist) {
            this.merchantGoodslist = merchantGoodslist;
        }

        public static class MerchantGoodslist {

            /**
             * goodsId :
             * goodsNum :
             * specificationsId :
             * goodsType :
             * retailPrice :
             */

            private String goodsId;
            private String goodsNum;
            private String specificationsId;
            private String goodsType;
            private String retailPrice;

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(String goodsNum) {
                this.goodsNum = goodsNum;
            }

            public String getSpecificationsId() {
                return specificationsId;
            }

            public void setSpecificationsId(String specificationsId) {
                this.specificationsId = specificationsId;
            }

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }

            public String getRetailPrice() {
                return retailPrice;
            }

            public void setRetailPrice(String retailPrice) {
                this.retailPrice = retailPrice;
            }

            @Override
            public String toString() {
                return "MerchantGoodslist{" +
                        "goodsId='" + goodsId + '\'' +
                        ", goodsNum='" + goodsNum + '\'' +
                        ", specificationsId='" + specificationsId + '\'' +
                        ", goodsType='" + goodsType + '\'' +
                        ", retailPrice='" + retailPrice + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "MerchantList{" +
                    "merchantId='" + merchantId + '\'' +
                    ", merchantGoodslist=" + merchantGoodslist +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MakeOrderParam{" +
                "cartId='" + cartId + '\'' +
                ", merchantList=" + merchantList +
                ", cartActivityGoodsList=" + cartActivityGoodsList +
                '}';
    }

    public static class  ActivityGoodsListTo{

        private String goodsId;
        private String goodsNum;
        private String specificationsId;
        private String goodsType;
        private String retailPrice;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getSpecificationsId() {
            return specificationsId;
        }

        public void setSpecificationsId(String specificationsId) {
            this.specificationsId = specificationsId;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(String retailPrice) {
            this.retailPrice = retailPrice;
        }

        @Override
        public String toString() {
            return "ActivityGoodsListTo{" +
                    "goodsId='" + goodsId + '\'' +
                    ", goodsNum='" + goodsNum + '\'' +
                    ", specificationsId='" + specificationsId + '\'' +
                    ", goodsType='" + goodsType + '\'' +
                    ", retailPrice='" + retailPrice + '\'' +
                    '}';
        }
    }

    public List<ActivityGoodsListTo> getCartActivityGoodsList() {
        return cartActivityGoodsList;
    }

    public void setCartActivityGoodsList(List<ActivityGoodsListTo> cartActivityGoodsList) {
        this.cartActivityGoodsList = cartActivityGoodsList;
    }
}
