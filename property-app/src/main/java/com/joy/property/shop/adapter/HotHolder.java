package com.joy.property.shop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joy.property.R;

/**
 * Created by xz on 2016/7/12.
 **/
public class HotHolder {
    private View view;
    private TextView channelName;
    private ImageView channelmage;

    private ImageView more;
    private ImageView hotImageOne;
    private ImageView hotImageTow;
    private ImageView hotImageThree;
    private ImageView hotImageFour;
    private ImageView hotImage;
    private TextView goodsName;
    private TextView goodsNameOne;
    private TextView goodsNameTow;
    private TextView goodsNameThree;
    private TextView goodsNameFour;
    private TextView hotPrice;
    private TextView hotPriceOne;
    private TextView hotPriceTwo;
    private TextView hotPriceThree;
    private TextView hotPriceFour;
    private TextView hotPriceRight;
    private TextView hotPriceOneRight;
    private TextView hotPriceTwoRight;
    private TextView hotPriceThreeRight;
    private TextView hotPriceFourRight;
    private TextView primePrice;
    private TextView labelOne;
    private TextView labelTwo;
    private TextView labelThree;
    private TextView primePriceOne;
    private TextView primePriceTwo;
    private TextView primePriceThree;
    private TextView primePriceFour;
    private RelativeLayout shopLayout;
    private RelativeLayout channelLayout;
    private RelativeLayout sellingLayout;
    private RelativeLayout sellingLayoutOne;
    private RelativeLayout sellingLayoutTwo;
    private RelativeLayout sellingLayoutThree;
    private RelativeLayout sellingLayoutFour;
    private LinearLayout goodsListLayout;
    private TextView channelLabel1;
    private TextView channelLabel2;
    private TextView channelLabel3;

    public HotHolder(View view){
    this.view=view;
}
  public ImageView getChannelmage(){
      if(channelmage==null)
          channelmage= (ImageView) view.findViewById(R.id.channel_image);
      return channelmage;
  }
    public TextView getChannelName(){
      if(channelName==null)

          channelName= (TextView) view.findViewById(R.id.channel_name);
      return channelName;
  }
    public ImageView getMore(){
        if(more==null)
            more= (ImageView) view.findViewById(R.id.more);
        return more;
    }
    public ImageView getHotImage(){
        if(hotImage==null)
            hotImage= (ImageView) view.findViewById(R.id.hot_image);
        return hotImage;
    }
    public ImageView getHotImageOne(){
        if(hotImageOne==null)
    hotImageOne= (ImageView) view.findViewById(R.id.hot_image_two);
    return hotImageOne;
}
    public ImageView getHotImageTow(){
        if(hotImageTow==null)
            hotImageTow= (ImageView) view.findViewById(R.id.hot_image_three);
        return hotImageTow;
    }
    public ImageView getHotImageThree(){
        if(hotImageThree==null)
            hotImageThree= (ImageView) view.findViewById(R.id.hot_image_three);
        return hotImageThree;
    }

    public TextView getGoodsName(){
        if(goodsName==null)
            goodsName= (TextView) view.findViewById(R.id.goods_name);
        return goodsName;
    }
    public TextView getGoodsNameOne(){
        if(goodsNameOne==null)
            goodsNameOne= (TextView) view.findViewById(R.id.goods_name_two);
        return goodsNameOne;
    }
    public TextView getGoodsNameTow(){
        if(goodsNameTow==null)
            goodsNameTow= (TextView) view.findViewById(R.id.goods_name_three);
        return goodsNameTow;
    }
    public TextView getGoodsNameThree(){
        if(goodsNameThree==null)
            goodsNameThree= (TextView) view.findViewById(R.id.goods_name_three);
        return goodsNameThree;
    }

    public TextView getHotPrice(){
        if(hotPrice==null)
            hotPrice= (TextView) view.findViewById(R.id.hot_price);
        return hotPrice;
    }
    public TextView getHotPriceOne(){
        if(hotPriceOne==null)
            hotPriceOne= (TextView) view.findViewById(R.id.hot_price_two);
        return hotPriceOne;
    }
    public TextView getHotPriceTwo(){
        if(hotPriceTwo==null)
            hotPriceTwo= (TextView) view.findViewById(R.id.hot_price_three);
        return hotPriceTwo;
    }
    public TextView getHotPriceThree(){
        if(hotPriceThree==null)
            hotPriceThree= (TextView) view.findViewById(R.id.hot_price_three);
        return hotPriceThree;
    }

    public TextView getHotPriceRight(){
        if(hotPriceRight==null)
            hotPriceRight= (TextView) view.findViewById(R.id.hot_price_one_right);
        return hotPriceRight;
    }
    public TextView getHotPriceOneRight(){
        if(hotPriceOneRight==null)
            hotPriceOneRight= (TextView) view.findViewById(R.id.hot_price_two_right);
        return hotPriceOneRight;
    }
    public TextView getHotPriceTwoRight(){
        if(hotPriceTwoRight==null)
            hotPriceTwoRight= (TextView) view.findViewById(R.id.hot_price_three_right);
        return hotPriceTwoRight;
    }
    public TextView getHotPriceThreeRight(){
        if(hotPriceThreeRight==null)
            hotPriceThreeRight= (TextView) view.findViewById(R.id.hot_price_three_right);
        return hotPriceThreeRight;
    }

    public TextView getPrimePrice(){
        if(primePrice==null)
            primePrice= (TextView) view.findViewById(R.id.prime_price);
        return primePrice;
    }
    public TextView getPrimePriceOne(){
        if(primePriceOne==null)
            primePriceOne= (TextView) view.findViewById(R.id.prime_price_two);
        return primePriceOne;
    }
    public TextView getPrimePriceTwo(){
        if(primePriceTwo==null)
            primePriceTwo= (TextView) view.findViewById(R.id.prime_price_three);
        return primePriceTwo;
    }
    public TextView getLabelOne(){
        if(labelOne==null)
            labelOne = (TextView) view.findViewById(R.id.label_01);
        return labelOne;
    }public TextView getLabelTwo(){
        if(labelTwo==null)
            labelTwo = (TextView) view.findViewById(R.id.label_02);
        return labelTwo;
    }public TextView getLabelThree(){
        if(labelThree==null)
            labelThree = (TextView) view.findViewById(R.id.label_03);
        return labelThree;
    }
    public TextView getPrimePriceThree(){
        if(primePriceThree==null)
            primePriceThree= (TextView) view.findViewById(R.id.prime_price_three);
        return primePriceThree;
    }

    public RelativeLayout getSellingLayoutOne(){
        if(sellingLayoutOne==null)
            sellingLayoutOne= (RelativeLayout) view.findViewById(R.id.sellingLayout_two);
        return sellingLayoutOne;
    }
    public RelativeLayout getShopLayout(){
        if(shopLayout==null)
            shopLayout= (RelativeLayout) view.findViewById(R.id.rl_shop);
        return shopLayout;
    }
    public RelativeLayout getChannelLayout(){
        if(channelLayout==null)

            channelLayout= (RelativeLayout) view.findViewById(R.id.rl_channel);
        return channelLayout;
    }
    public RelativeLayout getSellingLayoutTwo(){
        if(sellingLayoutTwo==null)
            sellingLayoutTwo= (RelativeLayout) view.findViewById(R.id.sellingLayout_three);
        return sellingLayoutTwo;
    }
//    public RelativeLayout getSellingLayoutThree(){
//        if(sellingLayoutThree==null)
//            sellingLayoutThree= (RelativeLayout) view.findViewById(R.id.selling_right_three);
//        return sellingLayoutThree;
//    }
//    public RelativeLayout getSellingLayoutFour(){
//        if(sellingLayoutFour==null)
//            sellingLayoutFour= (RelativeLayout) view.findViewById(R.id.selling_right_four);
//        return sellingLayoutFour;
//    }
    public RelativeLayout getSellingLayout(){
        if(sellingLayout==null)
            sellingLayout= (RelativeLayout) view.findViewById(R.id.sellingLayout);
        return sellingLayout;
    }
    public LinearLayout getGoodsListLayout(){
        if (goodsListLayout==null)
            goodsListLayout= (LinearLayout) view.findViewById(R.id.goodsListLayout);
        return goodsListLayout;


    }

    public TextView getChannelLabel1(){
        if(channelLabel1==null)
            channelLabel1= (TextView) view.findViewById(R.id.channelLabel1);
        return channelLabel1;
    }
    public TextView getChannelLabel2(){
        if(channelLabel2==null)
            channelLabel2= (TextView) view.findViewById(R.id.channelLabel2);
        return channelLabel2;
    }
    public TextView getChannelLabel3(){
        if(channelLabel3==null)
            channelLabel3= (TextView) view.findViewById(R.id.channelLabel3);
        return channelLabel3;
    }
}
