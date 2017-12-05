package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/5/11.
 */
public class MakeOlderNewParam {
    private String cartId;
    private String goodsIds;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    @Override
    public String toString() {
        return "MakeOlderNewParam{" +
                "cartId='" + cartId + '\'' +
                ", goodsIds='" + goodsIds + '\'' +
                '}';
    }
}
