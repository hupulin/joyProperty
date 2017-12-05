package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2017/1/6.
 */
public class MyShopCarTo {

    /**
     * merchantId : 14234766318044160
     * merchantName : 南都物业
     * storesName : 南都物业
     * cartId : 15566648547804160
     * cartMerchantGoodsVolist : [{"goodsId":15403711943214080,"goodsName":"火龙果","retailPrice":22,"goodsLabel":null,"categoryId":14214114562343936,"categoryName":"3333","merchantId":"14119730665998336","merchantName":"南都物业","storesName":"南都物业","salesType":"商户商品","picUrl":"web_4b5ddfd9-604b-41f0-8b10-f65dbffef390","discountPercentage":"1","currentPrice":"22.00","isGroup":"非团购","num":null,"minSalesNum":null,"maxSalesNum":null,"goodsNum":1,"isInvalid":"正常","distributionMode":"物业配送","distributionCost":"10"}]
     */

    private String merchantId;
    private String merchantName;
    private String storesName;
    private String cartId;
    private boolean haveSelect;
    private List<CarGoodsInfo>cartMerchantGoodsVolist;
    private List<CarGoodsInfo> cartActivityGoogsVoList;


    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getStoresName() {
        return storesName;
    }

    public void setStoresName(String storesName) {
        this.storesName = storesName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CarGoodsInfo> getCartMerchantGoodsVolist() {
        return cartMerchantGoodsVolist;
    }

    public void setCartMerchantGoodsVolist(List<CarGoodsInfo> cartMerchantGoodsVolist) {
        this.cartMerchantGoodsVolist = cartMerchantGoodsVolist;
    }

    public boolean isHaveSelect() {
        return haveSelect;
    }

    public void setHaveSelect(boolean haveSelect) {
        this.haveSelect = haveSelect;
    }

    public List<CarGoodsInfo> getCartActivityGoogsVoList() {
        return cartActivityGoogsVoList;
    }

    public void setCartActivityGoogsVoList(List<CarGoodsInfo> cartActivityGoogsVoList) {
        this.cartActivityGoogsVoList = cartActivityGoogsVoList;
    }

    @Override
    public String toString() {
        return "MyShopCarTo{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", storesName='" + storesName + '\'' +
                ", cartId='" + cartId + '\'' +
                ", cartMerchantGoodsVolist=" + cartMerchantGoodsVolist +
                '}';
    }
}
