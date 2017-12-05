package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/6.
 */
public class DeleteGoodsParam {

    /**
     * cartId :
     * cartGoodsList : [{"goodsId":""}]
     */

    private String cartId;
    /**
     * goodsId :
     */
private String goodsId;
    private List<CartGoodsListBean> cartGoodsList;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartGoodsListBean> getCartGoodsList() {
        return cartGoodsList;
    }

    public void setCartGoodsList(List<CartGoodsListBean> cartGoodsList) {
        this.cartGoodsList = cartGoodsList;
    }

    public static class CartGoodsListBean {
        private String goodsId;
        private String goodsType;
        private String specificationsId;
        private String merchantId;
        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getSpecificationsId() {
            return specificationsId;
        }

        public void setSpecificationsId(String specificationsId) {
            this.specificationsId = specificationsId;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        @Override
        public String toString() {
            return "CartGoodsListBean{" +
                    "goodsId='" + goodsId + '\'' +
                    ", goodsType='" + goodsType + '\'' +
                    ", specificationsId='" + specificationsId + '\'' +
                    ", merchantId='" + merchantId + '\'' +
                    '}';
        }
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "DeleteGoodsParam{" +
                "cartId='" + cartId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", cartGoodsList=" + cartGoodsList +
                '}';
    }
}
