/*    */ package com.jinyi.ihome.infrastructure;
/*    */
/*    */

import com.jinyi.ihome.module.newshop.ActivityTo;
import com.jinyi.ihome.module.newshop.CarGoodsInfo;
import com.jinyi.ihome.module.newshop.CartSettleGoodsTo;
import com.jinyi.ihome.module.newshop.CouponCalculateTo;
import com.jinyi.ihome.module.newshop.CustomerServiceTo;
import com.jinyi.ihome.module.newshop.GoodsDetail;
import com.jinyi.ihome.module.newshop.GoodsDetailPicTo;
import com.jinyi.ihome.module.newshop.ImmediatelySubmitResultTo;
import com.jinyi.ihome.module.newshop.MyShopCarNormalTo;
import com.jinyi.ihome.module.newshop.MyShopCarTo;
import com.jinyi.ihome.module.newshop.OlderSidTo;
import com.jinyi.ihome.module.newshop.UserAddressTo;

import java.io.Serializable;
import java.util.List;

/*    */
/*    */ public class MessageToBulk<T>
/*    */   implements Serializable
/*    */ {

    private T receiverAddress;

    public T getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(T receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    private int total;
    private int code;
    private String message;
    private String payOrderId;

    private T goodsApiVoList;

    public T getReceiverAddressList() {
        return receiverAddressList;
    }

    public void setReceiverAddressList(T receiverAddressList) {
        this.receiverAddressList = receiverAddressList;
    }

    private T receiverAddressList;
    private T goodsCategoryList;
    private T cartCouponList;
    private T communityGroupGoodsCategoryList;
    private T communityGroupGoodsList;
    private T communityCategoryGoodsVoList;
    private T myNoUseCouponList;
    private T myUsedCouponList;
    private T myInvalideCouponList;
    private T cartSettleGoodsVoList;
    private T myAllOrderList;

    public T getReceiverAddressVo() {
        return ReceiverAddressVo;
    }

    public void setReceiverAddressVo(T receiverAddressVo) {
        ReceiverAddressVo = receiverAddressVo;
    }

    private T orderDetailsVo;
    private T ReceiverAddressVo;
    private GoodsDetail goodsApiVo;
    private T  goodsAllEvaluateList;
    private T payinto;
    private T  orderGoodsList;
    private T merchantOrderList;
    private T myWaitDeliverOrderList;
    private T myWaitPayOrderList;
    private T myWaitReceiveOrderList;
    private T myWaitEvaluateOrderList;
    private T immediateBuyGoodVo;
    private T myFinishOrderList;
    private T immediateBuyGoodsCouponVoList;
    private CustomerServiceTo customerServiceVo;
    private int isOpen;
    private String isSeaBuy="no";
    private List<GoodsDetailPicTo> GoodsPicApiVoList;
    private ImmediatelySubmitResultTo merchantOrder;
    private List<ActivityTo>activityList;
    private List<MyShopCarTo> cartActivityVoList;
    private String cartId;
    private CartSettleGoodsTo cartActivityGoodsSettle;
    private OlderSidTo activityGoodsOrder;
    private List<CouponCalculateTo>merchantGoodsSuitlist;
    private List<CouponCalculateTo>merchantGoodsNoSuitlist;
    private List<CouponCalculateTo>activityGoodsSuitList;
    private List<CouponCalculateTo>activityGoodsNoSuitList;
    private UserAddressTo receivingInformationVo;

    private double paymentAmount;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code ;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getGoodsApiVoList() {
        return goodsApiVoList;
    }

    public T getGoodsCategoryList() {
        return goodsCategoryList;
    }

    public T getCommunityCategoryGoodsVoList() {
        return communityCategoryGoodsVoList;
    }

    public T getGoodsAllEvaluateList() {
        return goodsAllEvaluateList;
    }

    public void setGoodsAllEvaluateList(T goodsAllEvaluateList) {
        this.goodsAllEvaluateList = goodsAllEvaluateList;
    }

    public void setCommunityCategoryGoodsVoList(T communityCategoryGoodsVoList) {
        this.communityCategoryGoodsVoList = communityCategoryGoodsVoList;
    }

    public void setGoodsCategoryList(T goodsCategoryList) {
        this.goodsCategoryList = goodsCategoryList;
    }



    public void setGoodsApiVoList(T goodsApiVoList) {
        this.goodsApiVoList = goodsApiVoList;
    }
/*    */

    public T getCartCouponList() {
        return cartCouponList;
    }

    public void setCartCouponList(T cartCouponList) {
        this.cartCouponList = cartCouponList;
    }

    public T getCommunityGroupGoodsCategoryList() {
        return communityGroupGoodsCategoryList;
    }

    public void setCommunityGroupGoodsCategoryList(T communityGroupGoodsCategoryList) {
        this.communityGroupGoodsCategoryList = communityGroupGoodsCategoryList;
    }

    public T getCommunityGroupGoodsList() {
        return communityGroupGoodsList;
    }

    public void setCommunityGroupGoodsList(T communityGroupGoodsList) {
        this.communityGroupGoodsList = communityGroupGoodsList;
    }

    public List<MyShopCarNormalTo> getCartNormalGoodsVoList() {
        return cartNormalGoodsVoList;
    }

    public void setCartNormalGoodsVoList(List<MyShopCarNormalTo> cartNormalGoodsVoList) {
        this.cartNormalGoodsVoList = cartNormalGoodsVoList;
    }

    public T getCartSettleGoodsVoList() {
        return cartSettleGoodsVoList;
    }

    public void setCartSettleGoodsVoList(T cartSettleGoodsVoList) {
        this.cartSettleGoodsVoList = cartSettleGoodsVoList;
    }

    public T getMyNoUseCouponList() {
        return myNoUseCouponList;
    }

    public void setMyNoUseCouponList(T myNoUseCouponList) {
        this.myNoUseCouponList = myNoUseCouponList;
    }

    public T getMyUsedCouponList() {
        return myUsedCouponList;
    }

    public void setMyUsedCouponList(T myUsedCouponList) {
        this.myUsedCouponList = myUsedCouponList;
    }

    public T getMyInvalideCouponList() {
        return myInvalideCouponList;
    }

    public void setMyInvalideCouponList(T myInvalideCouponList) {
        this.myInvalideCouponList = myInvalideCouponList;
    }

    public T getMyAllOrderList() {
        return myAllOrderList;
    }

    public void setMyAllOrderList(T myAllOrderList) {
        this.myAllOrderList = myAllOrderList;
    }

    public T getOrderDetailsVo() {
        return orderDetailsVo;
    }

    public void setOrderDetailsVo(T orderDetailsVo) {
        this.orderDetailsVo = orderDetailsVo;
    }



    public T getPayinto() {
        return payinto;
    }

    public void setPayinto(T payinto) {
        this.payinto = payinto;
    }

    public GoodsDetail getGoodsApiVo() {
        return goodsApiVo;
    }

    public void setGoodsApiVo(GoodsDetail goodsApiVo) {
        this.goodsApiVo = goodsApiVo;
    }

    public T getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(T orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public T getMerchantOrderList() {
        return merchantOrderList;
    }

    public void setMerchantOrderList(T merchantOrderList) {
        this.merchantOrderList = merchantOrderList;
    }

    public T getMyWaitDeliverOrderList() {
        return myWaitDeliverOrderList;
    }

    public void setMyWaitDeliverOrderList(T myWaitDeliverOrderList) {
        this.myWaitDeliverOrderList = myWaitDeliverOrderList;
    }

    public T getMyWaitPayOrderList() {
        return myWaitPayOrderList;
    }

    public void setMyWaitPayOrderList(T myWaitPayOrderList) {
        this.myWaitPayOrderList = myWaitPayOrderList;
    }

    public T getMyWaitReceiveOrderList() {
        return myWaitReceiveOrderList;
    }

    public void setMyWaitReceiveOrderList(T myWaitReceiveOrderList) {
        this.myWaitReceiveOrderList = myWaitReceiveOrderList;
    }

    public T getMyWaitEvaluateOrderList() {
        return myWaitEvaluateOrderList;
    }


    public List<MyShopCarNormalTo>cartNormalGoodsVoList;

    public void setMyWaitEvaluateOrderList(T myWaitEvaluateOrderList) {
        this.myWaitEvaluateOrderList = myWaitEvaluateOrderList;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public T getImmediateBuyGoodVo() {
        return immediateBuyGoodVo;
    }

    public void setImmediateBuyGoodVo(T immediateBuyGoodVo) {
        this.immediateBuyGoodVo = immediateBuyGoodVo;
    }

    public ImmediatelySubmitResultTo getMerchantOrder() {
        return merchantOrder;
    }

    public void setMerchantOrder(ImmediatelySubmitResultTo merchantOrder) {
        this.merchantOrder = merchantOrder;
    }

    public List<GoodsDetailPicTo> getGoodsPicApiVoList() {
        return GoodsPicApiVoList;
    }

    public void setGoodsPicApiVoList(List<GoodsDetailPicTo> goodsPicApiVoList) {
        GoodsPicApiVoList = goodsPicApiVoList;
    }

    public T getMyFinishOrderList() {
        return myFinishOrderList;
    }

    public void setMyFinishOrderList(T myFinishOrderList) {
        this.myFinishOrderList = myFinishOrderList;
    }

    public T getImmediateBuyGoodsCouponVoList() {
        return immediateBuyGoodsCouponVoList;
    }

    public void setImmediateBuyGoodsCouponVoList(T immediateBuyGoodsCouponVoList) {
        this.immediateBuyGoodsCouponVoList = immediateBuyGoodsCouponVoList;
    }

    public CustomerServiceTo getCustomerServiceVo() {
        return customerServiceVo;
    }

    public void setCustomerServiceVo(CustomerServiceTo customerServiceVo) {
        this.customerServiceVo = customerServiceVo;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public String getIsSeaBuy() {
        return isSeaBuy;
    }

    public void setIsSeaBuy(String isSeaBuy) {
        this.isSeaBuy = isSeaBuy;
    }

    public List<ActivityTo> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityTo> activityList) {
        this.activityList = activityList;
    }

    public List<MyShopCarTo> getCartActivityVoList() {
        return cartActivityVoList;
    }

    public void setCartActivityVoList(List<MyShopCarTo> cartActivityVoList) {
        this.cartActivityVoList = cartActivityVoList;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public CartSettleGoodsTo getCartActivityGoodsSettle() {
        return cartActivityGoodsSettle;
    }

    public void setCartActivityGoodsSettle(CartSettleGoodsTo cartActivityGoodsSettle) {
        this.cartActivityGoodsSettle = cartActivityGoodsSettle;
    }

    public OlderSidTo getActivityGoodsOrder() {
        return activityGoodsOrder;
    }

    public void setActivityGoodsOrder(OlderSidTo activityGoodsOrder) {
        this.activityGoodsOrder = activityGoodsOrder;
    }

    public List<CouponCalculateTo> getMerchantGoodsSuitlist() {
        return merchantGoodsSuitlist;
    }

    public void setMerchantGoodsSuitlist(List<CouponCalculateTo> merchantGoodsSuitlist) {
        this.merchantGoodsSuitlist = merchantGoodsSuitlist;
    }

    public List<CouponCalculateTo> getMerchantGoodsNoSuitlist() {
        return merchantGoodsNoSuitlist;
    }

    public void setMerchantGoodsNoSuitlist(List<CouponCalculateTo> merchantGoodsNoSuitlist) {
        this.merchantGoodsNoSuitlist = merchantGoodsNoSuitlist;
    }

    public List<CouponCalculateTo> getActivityGoodsSuitList() {
        return activityGoodsSuitList;
    }

    public void setActivityGoodsSuitList(List<CouponCalculateTo> activityGoodsSuitList) {
        this.activityGoodsSuitList = activityGoodsSuitList;
    }

    public List<CouponCalculateTo> getActivityGoodsNoSuitList() {
        return activityGoodsNoSuitList;
    }

    public void setActivityGoodsNoSuitList(List<CouponCalculateTo> activityGoodsNoSuitList) {
        this.activityGoodsNoSuitList = activityGoodsNoSuitList;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public UserAddressTo getReceivingInformationVo() {
        return receivingInformationVo;
    }

    @Override
    public String toString() {
        return "MessageToBulk{" +
                "total=" + total +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", goodsApiVoList=" + goodsApiVoList +
                ", goodsCategoryList=" + goodsCategoryList +
                ", defaultAddress=" + receiverAddress +
                '}';
    }

    /*    */ }

