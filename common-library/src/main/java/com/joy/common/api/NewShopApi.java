package com.joy.common.api;


import com.jinyi.ihome.infrastructure.MessageTo;
import com.jinyi.ihome.infrastructure.MessageToBulk;
import com.jinyi.ihome.module.newshop.ActivityCouponTo;
import com.jinyi.ihome.module.newshop.ActivityListParam;
import com.jinyi.ihome.module.newshop.ActivityTo;
import com.jinyi.ihome.module.newshop.AddAddressParam;
import com.jinyi.ihome.module.newshop.AddCarParam;
import com.jinyi.ihome.module.newshop.AddressTo;
import com.jinyi.ihome.module.newshop.BlankParam;
import com.jinyi.ihome.module.newshop.BulkUserInfoParam;
import com.jinyi.ihome.module.newshop.CancelOlderParam;
import com.jinyi.ihome.module.newshop.CartSettleGoodsTo;
import com.jinyi.ihome.module.newshop.CategoryChannelTo;
import com.jinyi.ihome.module.newshop.CategoryTo;
import com.jinyi.ihome.module.newshop.ChangeCarParam;
import com.jinyi.ihome.module.newshop.ChannelDetailParam;
import com.jinyi.ihome.module.newshop.ChannelParam;
import com.jinyi.ihome.module.newshop.CommentParam;
import com.jinyi.ihome.module.newshop.ConfirmOrderTo;
import com.jinyi.ihome.module.newshop.CouponCalculateParam;
import com.jinyi.ihome.module.newshop.CouponCalculateTo;
import com.jinyi.ihome.module.newshop.CouponParam;
import com.jinyi.ihome.module.newshop.CouponTo;
import com.jinyi.ihome.module.newshop.DefaultAddressParam;
import com.jinyi.ihome.module.newshop.DefaultAddressTo;
import com.jinyi.ihome.module.newshop.DeleteAddressParam;
import com.jinyi.ihome.module.newshop.DeleteGoodsParam;
import com.jinyi.ihome.module.newshop.EditAddressSaveParam;
import com.jinyi.ihome.module.newshop.ExpressName;
import com.jinyi.ihome.module.newshop.GetAddressListParam;
import com.jinyi.ihome.module.newshop.GoodsApiTo;
import com.jinyi.ihome.module.newshop.GoodsCommentListTo;
import com.jinyi.ihome.module.newshop.GoodsCommentParam;
import com.jinyi.ihome.module.newshop.GoodsCommentTo;
import com.jinyi.ihome.module.newshop.GoodsDetailParam;
import com.jinyi.ihome.module.newshop.GoodsListParam;
import com.jinyi.ihome.module.newshop.ImmediateBuyParam;
import com.jinyi.ihome.module.newshop.ImmediatelyCouponParam;
import com.jinyi.ihome.module.newshop.ImmediatelyGoodsTo;
import com.jinyi.ihome.module.newshop.ImmediatelySubmitParam;
import com.jinyi.ihome.module.newshop.ImmediatelySubmitResultTo;
import com.jinyi.ihome.module.newshop.MainInfoTo;
import com.jinyi.ihome.module.newshop.MakeOlderNewParam;
import com.jinyi.ihome.module.newshop.MakeOrderParam;
import com.jinyi.ihome.module.newshop.MyCouponParam;
import com.jinyi.ihome.module.newshop.MyOlderParam;
import com.jinyi.ihome.module.newshop.MyOlderTo;
import com.jinyi.ihome.module.newshop.MyShopCarNormalTo;
import com.jinyi.ihome.module.newshop.MyShopCarTo;
import com.jinyi.ihome.module.newshop.MyShoppingTo;
import com.jinyi.ihome.module.newshop.OlderDetailParam;
import com.jinyi.ihome.module.newshop.OlderDetailTo;
import com.jinyi.ihome.module.newshop.OlderListTo;
import com.jinyi.ihome.module.newshop.PayInfo;
import com.jinyi.ihome.module.newshop.PayParam;
import com.jinyi.ihome.module.newshop.PaySuccessParam;
import com.jinyi.ihome.module.newshop.PicTextTo;
import com.jinyi.ihome.module.newshop.SaveCouponParam;
import com.jinyi.ihome.module.newshop.ShopListDataTo;
import com.jinyi.ihome.module.newshop.ShopListDetailTo;
import com.jinyi.ihome.module.newshop.ShopListTo;
import com.jinyi.ihome.module.newshop.ShopTypeTo;
import com.jinyi.ihome.module.newshop.ShoppingCarTo;
import com.jinyi.ihome.module.newshop.ShoppingExpressTo;
import com.jinyi.ihome.module.newshop.SubmitParam;
import com.jinyi.ihome.module.newshop.UpdateUserInfoParam;
import com.jinyi.ihome.module.newshop.UserAddressParam;
import com.jinyi.ihome.module.newshop.UserAddressTo;
import com.jinyi.ihome.module.newshop.UserMessage;
import com.jinyi.ihome.module.newshop.AddressListParam;
import com.jinyi.ihome.module.shop.CarNumberParam;
import com.jinyi.ihome.module.shop.SearchParam;
import com.jinyi.ihome.module.shop.StoreItemCommentTo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Admin on 2014-11-14
 */
public interface NewShopApi {




    @GET("/type.json")
    void getType(Callback<MessageTo<List<ShopTypeTo>>> callback);
    @GET("/channel")
    void getChannel(Callback<MessageTo<List<ShopTypeTo>>> callback);
    @GET("/hot")
    void getChannelList(Callback<MessageTo<List<ShopListTo>>> callback);
    /***
     * 根据价格排序获取商品

     *  @return
     *  */
    @POST("/api/goods/getAllGoodsByPrice")
    void getMore(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);
    /***
     * 根据销量排序获取商品

     *  @return
     *  */
    @POST("/api/goods/getAllGoodsBySales")
    void getBySale(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);
    /***
     * 获取新商品

     *  @return
     *  */
    @POST("/api/goods/getAllGoodsByNewProducts")
    void getByNewGoods(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);

    /***
     * 获取分类列表

     *  @return
     *  */
    @POST("/api/goods/getGoodsCategoryList")
    void getCategory(@Body BlankParam param, Callback<MessageToBulk<List<CategoryTo>>> callback);

    /***
     * 获取小区是否开放

     *  @return
     *  */
    @POST("/api/goods/isOpenYueGou")
    void getBulkOpen(@Body BlankParam param, Callback<MessageToBulk> callback);

    /***
     * 根据价格排序获取商品-单个商品

     *  @return
     *  */
    @POST("/api/goods/getSingleChannelPageByPrice")
    void getMoreSingle(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);
    /***
     * 根据销量排序获取商品-单个商品

     *  @return
     *  */
    @POST("/api/goods/getSingleChannelPageBySales")
    void getBySaleSingle(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);
    /***
     * 获取新商品-单个商品

     *  @return
     *  */
    @POST("/api/goods/getSingleChannelPageByNewProducts")
    void getByNewGoodsSingle(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);


    /***
     * 根据价格排序获取商品-自营商店

     *  @return
     *  */
    @POST("/api/goods/getSelfGoodsByPrice")
    void getMoreSelf(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);
    /***
     * 根据销量排序获取商品-自营商店

     *  @return
     *  */
    @POST("/api/goods/getSelfGoodsBySales")
    void getBySaleSelf(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);
    /***
     * 获取新商品-自营商店

     *  @return
     *  */
    @POST("/api/goods/getSelfGoodsByNewProducts")
    void getByNewGoodsSelf(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);

    /***
     * 根据销量排序获取商品-商家店铺

     *  @return
     *  */
    @POST("/api/goods/getMerchantGoodsBySales")
    void getBySaleMerchant(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);
    /***
     * 按价格获取-商家店铺

     *  @return
     *  */
    @POST("/api/goods/getMerchantGoodsByPrice")
    void getMoreMerchant(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);

    /***
     * 获取新商品-商家店铺

     *  @return
     *  */
    @POST("/api/goods/getMerchantGoodsByNewProducts")
    void getByNewGoodsMerchant(@Body GoodsListParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);

    /***
     * 加入购物车

     *  @return
     *  */
    @POST("/api/cart/addCart")
    void addCar(@Body AddCarParam param, Callback<MessageToBulk> callback);

    /***
     * 悦购用户登录

     *  @return
     *  */
    @POST("/api/user/yueGouLogin")
    void shopLogin(@Body BulkUserInfoParam param, Callback<UserMessage> callback);

    /***
     * 我的购物车

     *  @return
     *  */
    @POST("/api/cart/myCart")
    void getMyShopCar(@Body AddCarParam param, Callback<MessageToBulk<List<MyShopCarNormalTo>>> callback);

    /***
     * 删除购物车宝贝

     *  @return
     *  */
    @POST("/api/cart/deleteCartGoods")
    void deleteGoods(@Body DeleteGoodsParam param, Callback<MessageToBulk> callback);
    /***
     * 编辑购物车

     *  @return
     *  */
    @POST("/api/cart/editCartSave")
    void changeShopCar(@Body ChangeCarParam param, Callback<MessageToBulk> callback);

    /***
     * 获取优惠券

     *  @return
     *  */
    @POST("/api/cart/cartCoupon")
    void getCoupon(@Body CouponParam param, Callback<MessageToBulk<List<CouponTo>>> callback);

    /***
     * 获取立即购买优惠券

     *  @return
     *  */
    @POST("/api/cart/getImmediateBuyGoodsCouponList")
    void getImmediatelyCoupon(@Body ImmediatelyCouponParam param, Callback<MessageToBulk<List<CouponTo>>> callback);
    /***
     * 领取优惠券

     *  @return
     *  */



    @POST("/api/cart/cartCouponSave")
    void confirmCoupon(@Body SaveCouponParam param, Callback<MessageToBulk> callback);

    /***
     * 获取物业团购频道

     *  @return
     *  */
    @POST("/api/goods/getCommunityGroupGoodsCategoryList")
    void getBulkChannel(@Body ChannelParam param, Callback<MessageToBulk<List<CategoryChannelTo>>> callback);

    /***
     * 获取自定义团购频道

     *  @return
     *  */
    @POST("/api/goods/getCommunityLayOutGroupGoodsCategoryList")
    void getOtherBulkChannel(@Body ChannelParam param, Callback<MessageToBulk<List<CategoryChannelTo>>> callback);
    /***
     * 获取物业团购频道详情

     *  @return
     *  */
    @POST("/api/goods/getCommunityGroupGoodsByCategoryId")
    void getBulkChannelDetail(@Body ChannelDetailParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);

    /***
     * 获取其它团购频道详情

     *  @return
     *  */
    @POST("/api/goods/getCommunityLayoutGroupGoodsByCategoryId")
    void getOtherBulkChannelDetail(@Body ChannelDetailParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);

    /***
     * 首页频道商品

     *  @return
     *  */
    @POST("/api/goods/getCommunityCategoryGoods")
    void getChannelGoodsList(@Body ChannelParam param, Callback<MessageToBulk<List<CategoryChannelTo>>> callback);

    /***
     * 生成订单

     *  @return
     *  */
    @POST("/api/cart/cartSettle")
    void makeOrder(@Body MakeOrderParam param, Callback<MessageToBulk<List<CartSettleGoodsTo>>> callback);

    /***
     * 提交订单

     *  @return
     *  */
    @POST("/api/order/submitOrder")
    void submitOrder(@Body SubmitParam param, Callback<MessageToBulk<List<OlderListTo>>> callback);

    /***
     * 立即购买提交订单

     *  @return
     *  */
    @POST("/api/order/immediateBuyGoodsSubmitOrder")
    void immediatelySubmitOrder(@Body ImmediatelySubmitParam param, Callback<MessageToBulk<ImmediatelySubmitResultTo>> callback);

    /***
     * 购物券未使用

     *  @return
     *  */
    @POST("/api/order/myNoUseCouponList")
    void getCouponUnUse(@Body MyCouponParam param, Callback<MessageToBulk<List<CouponTo>>> callback);
    /***
     * 购物券已使用

     *  @return
     *  */
    @POST("/api/order/myUsedCouponList")
    void getCouponUse(@Body MyCouponParam param, Callback<MessageToBulk<List<CouponTo>>> callback);
    /***
     * 购物券已过期

     *  @return
     *  */
    @POST("/api/order/myInvalideCouponList")
    void getCouponOut(@Body MyCouponParam param, Callback<MessageToBulk<List<CouponTo>>> callback);
    /***
     * 全部订单

     *  @return
     *  */
    @POST("/api/order/myAllOrderList")
    void getAllOlder(@Body MyOlderParam param, Callback<MessageToBulk<List<MyOlderTo>>> callback);

    /***
     * 获取订单详情

     *  @return
     *  */
    @POST("/api/order/getOrderDetail")
    void getOrderDetail(@Body OlderDetailParam param, Callback<MessageToBulk<OlderDetailTo>> callback);

    /***
     * 取消订单

     *  @return
     *  */
    @POST("/api/order/getOrderDetail")
    void getCancelOlder(@Body OlderDetailParam param, Callback<MessageToBulk<OlderDetailTo>> callback);
    /***
     * 商品详情

     *  @return
     *  */
    @POST("/api/goods/getGoodsDetailByGoodsId")
    void getGoodsDetail(@Body GoodsDetailParam param, Callback<GoodsApiTo> callback);

    /***
     * 获取全部评论

     *  @return
     *  */
    @POST("/api/order/getGoodsAllEvaluateList")
    void getAllComment(@Body CommentParam param, Callback<MessageToBulk<List<GoodsCommentTo>>> callback);

    /***
     * 获取好的评论

     *  @return
     *  */
    @POST("/api/order/getGoodsBestEvaluateList")
    void getGoodComment(@Body CommentParam param, Callback<MessageToBulk<List<GoodsCommentTo>>> callback);

    /***
     * 获取差的评论

     *  @return
     *  */
    @POST("/api/order/getGoodsBadEvaluateList")
    void getBadComment(@Body CommentParam param, Callback<MessageToBulk<List<GoodsCommentTo>>> callback);

    /***
     * 获取中评论

     *  @return
     *  */
    @POST("/api/order/getGoodsSecondaryEvaluateList")
    void getMiddleComment(@Body CommentParam param, Callback<MessageToBulk<List<GoodsCommentTo>>> callback);
    /***
     * 支付订单

     *  @return
     *  */
    @POST("/api/order/enterPayOrderPage")
    void payOlder(@Body PayParam param, Callback<MessageToBulk<PayInfo>> callback);

    /***
     * 获取评价页面

     *  @return
     *  */
    @POST("/api/order/publishGoodsComment")
    void postComment(@Body GoodsCommentParam param, Callback<MessageToBulk> callback);

    /***
     * 评价商品

     *  @return
     *  */
    @POST("/api/order/goCommentPage")
    void getCommentList(@Body OlderDetailParam param, Callback<MessageToBulk<List<GoodsCommentListTo>>> callback);

    /***
     * 评价商品

     *  @return
     *  */
    @POST("/api/order/payOrderSuccess")
    void paySuccess(@Body PaySuccessParam param, Callback<MessageToBulk> callback);

    /***
     * 删除订单

     *  @return
     *  */
    @POST("/api/order/cancelOrder")
    void deleteOlder(@Body CancelOlderParam param, Callback<MessageToBulk> callback);



    /***
     * 待发货

     *  @return
     *  */
    @POST("/api/order/myWaitDeliverOrderList")
    void getWaiteGoodsList(@Body MyOlderParam param, Callback<MessageToBulk<List<MyOlderTo>>> callback);

    /***
     * 获取首页广告图

     *  @return
     *  */

    @GET("/api/layoutApp/selectLayoutAppVoList")
    void getMainPageInfo(@Query("layoutType") String layoutType, @Query("communityId") String communityId, Callback<MessageTo<MainInfoTo>> callback);

    /***
     * 获取花样菜场和啄木鸟
     *
     * @return
     */

    @GET("/api/layoutApp/selectLayoutAppVoList")
    void getMarketMainPageInfo(@Query("layoutType") String layoutType, @Query("communityId") String communityId, @Query("version") String version, Callback<MessageTo<MainInfoTo>> callback);

    /***
     * 获取图文详情

     *  @return
     *  */

    @GET("/api/layoutApp/selectOne")
    void getPicText(@Query("id") String id, Callback<MessageTo<PicTextTo>> callback);

    /***
     * 待付款

     *  @return
     *  */
    @POST("/api/order/myWaitPayOrderList")
    void getWaitePay(@Body MyOlderParam param, Callback<MessageToBulk<List<MyOlderTo>>> callback);

    /***
     * 待收货

     *  @return
     *  */
    @POST("/api/order/myWaitReceiveOrderList")
    void getWaiteReicive(@Body MyOlderParam param, Callback<MessageToBulk<List<MyOlderTo>>> callback);

    /***
     * 待评价

     *  @return
     *  */
    @POST("/api/order/myWaitEvaluateOrderList")
    void getWaiteComment(@Body MyOlderParam param, Callback<MessageToBulk<List<MyOlderTo>>> callback);
    /***
     * 完成订单

     *  @return
     *  */
    @POST("/api/order/myFinishOrderList")
    void getFinishOlder(@Body MyOlderParam param, Callback<MessageToBulk<List<MyOlderTo>>> callback);

    /***
     * 确认收货

     *  @return
     *  */
    @POST("/api/order/confirmReceiveOrderGoods")
    void confirmReceive(@Body OlderDetailParam param, Callback<MessageToBulk> callback);

    /***
     * 立即购买

     *  @return
     *  */
    @POST("/api/cart/immediateBuy")
    void immediatelyBuy(@Body ImmediateBuyParam param, Callback<MessageToBulk<ImmediatelyGoodsTo>> callback);

    /***
     * 获取购物车商品件数

     *  @return
     *  */
    @POST("/api/cart/getMyCartGoodsTotal")
    void getCarNumber(@Body CarNumberParam param, Callback<MessageToBulk> callback);
    /**
     * 获取收货地址列表
     */
    @POST("/api/order/getReceiveAddressList")
    void getAddress(@Body GetAddressListParam param,Callback<MessageToBulk<List<AddressTo>>> callback);
    /**
     * 编辑收货地址列表
     */
    @POST("/api/order/editReceiveAddressSave")
    void editReceiveAddress(@Body AddressListParam param,Callback<MessageToBulk<AddressTo>> callback);
    /**
     * 编辑收货地址列表保存
     */
    @POST("/api/order/editReceiveAddressSave")
    void editAddressSave(@Body EditAddressSaveParam param,Callback<MessageToBulk> callback);
    /**
     * 增加收货地址
     */
    @POST("/api/order/addReceiveAddress")
    void addAddress(@Body AddAddressParam param, Callback<MessageToBulk> Callback);
    /**
     * 删除收货地址
     */
    @POST("/api/order/deleteReceiveAddress")
    void deleteReceiveAddress(@Body DeleteAddressParam param, Callback<MessageToBulk> Callback);
    /**
     * 设置默认地址
     */
    @POST("/api/order/designDefaultAddress")
    void designDefaultAddress(@Body DefaultAddressParam param, Callback<MessageToBulk> Callback);
    /**
     * 获取默认地址
     *
     */
    @POST("/api/order/getMyDefaultReceiveAddress")
    void getMyDefaultReceiveAddress(@Body AddressListParam param, Callback<MessageToBulk<DefaultAddressTo>> Callback);

    /***
     * 搜素按照新商品

     *  @return
     *  */
    @POST("/api/goods/getHomePageGoodsSearchByNewProducts")
    void searchByNewGoods(@Body SearchParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);

    /***
     * 搜素按照价格

     *  @return
     *  */
    @POST("/api/goods/getHomePageGoodsSearchByPrice")
    void searchByPrice(@Body SearchParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);

    /***
     * 搜素按照销量

     *  @return
     *  */
    @POST("/api/goods/getHomePageGoodsSearchBySales")
    void searchBySalas(@Body SearchParam param, Callback<MessageToBulk<List<ShopListDetailTo>>> callback);

    /***
     * 获取客服信息

     *  @return
     *  */
    @POST("/api/user/getCustomerServiceInfo")
    void getCustomerService(Callback<MessageToBulk> callback);

    /***
     * 悦服务更新用户

     *  @return
     *  */
    @POST("/api/user/appEditUser")
    void updateInfo(@Body UpdateUserInfoParam param, Callback<MessageToBulk> callback);

    @GET("/typeChannel")
    void getTypeChannel(Callback<MessageTo<List<ShopTypeTo>>> callback);
    @GET("/{sid}")
    void getTypeList(@Path("sid") String sid, Callback<MessageTo<List<ShopListDetailTo>>> callback);
    @GET("/comment")
    void getComment(Callback<MessageTo<List<GoodsCommentTo>>> callback);
    @GET("/shoppingCar")
    void getShoppigCar(Callback<MessageTo<List<ShoppingCarTo>>> callback);
    @GET("/coupon")
    void getCoupon(Callback<MessageTo<List<CouponTo>>> callback);
    @GET("/confirmOrder")
    void getOrder(Callback<MessageTo<ConfirmOrderTo>> callback);

    @GET("/myShopping")
    void getMyShopping(Callback<MessageTo<List<MyShoppingTo>>> callback);
    @GET("/expressName")
    void getExpressName(Callback<MessageTo<List<ExpressName>>> callback);
    @GET("express{type}")
    void getExpress(@Path("type") String type, Callback<MessageTo<ShoppingExpressTo>> callback);
    @GET("/olderDetail{type}")
    void getOlderDetail(@Path("type") String type, Callback<MessageTo<OlderDetailTo>> callback);

    /**
     *获取活动
     * @param param
     * @param callback
     */
    @POST("/api/activity/getActivity")
    void getActivityList(@Body ActivityListParam param , Callback<MessageToBulk<List<ActivityTo>> > callback);



    /***
     * 优惠券对应的普通商品
     * **/
    @GET("/api/couponRangeApp/selectCouponGoodsVoList")
    void getCouponGoodsList(@Query("couponId") String couponId,@Query("communityId") String communityId,@Query("sortType") String sortType,@Query("order") String order,@Query("nextPage") int nextPage, @Query("limit") int limit, Callback<MessageTo<ShopListDataTo>> callback);
    /***
     * 优惠券对应的活动
     * **/
    @GET("/api/couponRangeApp/selectNowActivity")
    void getCouponActivity(@Query("couponId") String couponId, Callback<MessageTo<ActivityCouponTo>> callback);


    /***
     * 获取优惠券计算
     * **/
    @POST("/api/cart/couponCalculation")
    void getCouponCalculate(@Body CouponCalculateParam param, Callback<MessageToBulk<List<CouponCalculateTo>>> callback);

    /***
     * 新的购物车结算
     * **/

    @POST("/api/cart/cartSettleNew")
    void newMakeOlder(@Body MakeOlderNewParam param, Callback<MessageToBulk<List<CartSettleGoodsTo>>> callback);


    /***
     * 获取用户房号地址
     * **/

    @POST("/api/order/getReceiveAddressByOldUserId")
    void getUserAddress(@Body UserAddressParam param, Callback<MessageToBulk<UserAddressTo>> callback);
}
