package com.jinyi.ihome.module.newshop;

/**
 * Created by xz on 2017/1/10.
 */
public class PayOlderTo {

    /**
     * paySid : null
     * payPartner : ALIPAY_WALLET
     * payIcon : app_alipay.png
     * payName : 支付宝钱包支付
     * payDesc : 推荐有支付宝钱包的用户使用
     * payUrl : partner="2088121649089415"&seller_id="ydwl@naradares.com"&out_trade_no="15867325358605312"&subject="悦购订单支付"&body="悦购订单支付"&total_fee="65.00"&notify_url="http://139.129.166.169:9000/ihome/api/v1/payment/alipay_app_group_notify"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&show_url="m.alipay.com"&sign="uMZCJw9UC0nU6tZFZPDN8cpJf4clvLSsMmd4%2FjnJFyFoXmLPlUmTtLrosqAUWWcBmDaPPuatglt3yW69MpSWKg9VEJMu5Pe8OPyXOkzKQykdfEPkvmkV8SVBkKGnkRx3RYnXaOShqMedR0l%2BKHS9GIHrv7d1kU4hptOYznkhYhw%3D"&sign_type="RSA"
     */

    private Object paySid;
    private String payPartner;
    private String payIcon;
    private String payName;
    private String payDesc;
    private String payUrl;

    public Object getPaySid() {
        return paySid;
    }

    public void setPaySid(Object paySid) {
        this.paySid = paySid;
    }

    public String getPayPartner() {
        return payPartner;
    }

    public void setPayPartner(String payPartner) {
        this.payPartner = payPartner;
    }

    public String getPayIcon() {
        return payIcon;
    }

    public void setPayIcon(String payIcon) {
        this.payIcon = payIcon;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    @Override
    public String toString() {
        return "PayOlderTo{" +
                "paySid=" + paySid +
                ", payPartner='" + payPartner + '\'' +
                ", payIcon='" + payIcon + '\'' +
                ", payName='" + payName + '\'' +
                ", payDesc='" + payDesc + '\'' +
                ", payUrl='" + payUrl + '\'' +
                '}';
    }
}
