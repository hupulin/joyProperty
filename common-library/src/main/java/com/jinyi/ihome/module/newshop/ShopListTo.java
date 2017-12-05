package com.jinyi.ihome.module.newshop;

import java.util.List;

/**
 * Created by xz on 2016/7/14.
 */
public class ShopListTo {


    /**
     * channelName : 熊壮美食
     * content : [{"price":63,"primePrice":88.88,"image":"http://192.168.1.149:8080/ihome/typeImage/2.png","name":"外面的世界很精彩，外面的世界很无赖","goodSid":"h001"},{"price":63,"primePrice":88.88,"image":"http://192.168.1.149:8080/ihome/typeImage/2.png","name":"外面的世界很精彩，外面的世界很无赖","goodSid":"h002"},{"price":63,"primePrice":88.88,"image":"http://192.168.1.149:8080/ihome/typeImage/2.png","name":"外面的世界很精彩，外面的世界很无赖","goodSid":"h003"},{"price":63,"primePrice":88.88,"image":"http://192.168.1.149:8080/ihome/typeImage/2.png","name":"外面的世界很精彩，外面的世界很无赖","goodSid":"h004"},{"price":63,"primePrice":88.88,"image":"http://192.168.1.149:8080/ihome/typeImage/2.png","name":"外面的世界很精彩，外面的世界很无赖","goodSid":"h005"}]
     */

    private String channelName;
    /**
     * price : 63.0
     * primePrice : 88.88
     * image : http://192.168.1.149:8080/ihome/typeImage/2.png
     * name : 外面的世界很精彩，外面的世界很无赖
     * goodSid : h001
     */

    private List<ShopListDetailTo> content;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<ShopListDetailTo> getContent() {
        return content;
    }

    public void setContent(List<ShopListDetailTo> content) {
        this.content = content;
    }

    public static class ContentBean {
        private double price;
        private double primePrice;
        private String image;
        private String name;
        private String goodSid;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPrimePrice() {
            return primePrice;
        }

        public void setPrimePrice(double primePrice) {
            this.primePrice = primePrice;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGoodSid() {
            return goodSid;
        }

        public void setGoodSid(String goodSid) {
            this.goodSid = goodSid;
        }
    }
}
