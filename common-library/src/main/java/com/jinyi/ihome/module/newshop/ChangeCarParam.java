package com.jinyi.ihome.module.newshop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xz on 2017/1/6.
 */
public class ChangeCarParam implements Serializable {


    /**
     * cartId :
     * cartGoodsList : [{"goodsId":"","purchaseQuantity":""}]
     */

    private String cartId;
    /**
     * goodsId :
     * purchaseQuantity :
     */
    private String userId;

    private List<ChangeCartGoods> cartGoodsList;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<ChangeCartGoods> getCartGoodsList() {
        return cartGoodsList;
    }

    public void setCartGoodsList(List<ChangeCartGoods> cartGoodsList) {
        this.cartGoodsList = cartGoodsList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class ChangeCartGoods {
        private String goodsId;
        private String purchaseQuantity;
        private String goodsType;
        private String specificationsId;
        private String newSpecificationsId="";
        private String newSpecificationsName="";

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getPurchaseQuantity() {
            return purchaseQuantity;
        }

        public void setPurchaseQuantity(String purchaseQuantity) {
            this.purchaseQuantity = purchaseQuantity;
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

        public String getNewSpecificationsId() {
            return newSpecificationsId;
        }

        public void setNewSpecificationsId(String newSpecificationsId) {
            this.newSpecificationsId = newSpecificationsId;
        }

        public String getNewSpecificationsName() {
            return newSpecificationsName;
        }

        public void setNewSpecificationsName(String newSpecificationsName) {
            this.newSpecificationsName = newSpecificationsName;
        }

        @Override
        public String toString() {
            return "ChangeCartGoods{" +
                    "goodsId='" + goodsId + '\'' +
                    ", purchaseQuantity='" + purchaseQuantity + '\'' +
                    ", goodsType='" + goodsType + '\'' +
                    ", specificationsId='" + specificationsId + '\'' +
                    ", newSpecificationsId='" + newSpecificationsId + '\'' +
                    ", newSpecificationsName='" + newSpecificationsName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ChangeCarParam{" +
                "cartId='" + cartId + '\'' +
                ", userId='" + userId + '\'' +
                ", cartGoodsList=" + cartGoodsList +
                '}';
    }
}
