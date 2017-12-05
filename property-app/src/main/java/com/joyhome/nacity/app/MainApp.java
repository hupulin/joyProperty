package com.joyhome.nacity.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import com.joy.common.api.ApiClient;
import com.joy.library.application.BaseApplication;
import com.joy.library.utils.ConfigUtil;
import com.joy.library.utils.FileUtil;
import com.joy.property.R;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;



public class MainApp extends BaseApplication {

    public static Context mContext;
    public static MainApp mInstance = null;
    public static DisplayImageOptions optionsHeadImage;
    public static DisplayImageOptions mShopOptions;
    public static DisplayImageOptions mStoreBusinessOptions;
    public static DisplayImageOptions mHouseOptions;
    //private RefWatcher refWatcher;


    @Override
    public void onCreate() {
        mInstance = this;
        mContext = this.getApplicationContext();

        ConfigUtil.mPref = this.mPref;
        new ApiClient().setmContext(mContext);
        super.onCreate();

      //refWatcher = LeakCanary.install(this);
        initImageLoader(getApplicationContext());
        optionsHeadImage = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.guest_head_image)
                .showImageForEmptyUri(R.drawable.guest_head_image)
                .showImageOnFail(R.drawable.guest_head_image)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .build();

        mShopOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.around_shop_ic)
                .showImageForEmptyUri(R.drawable.around_shop_ic)
                .showImageOnFail(R.drawable.around_shop_ic)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .build();
        mStoreBusinessOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.store_business_ic)
                .showImageForEmptyUri(R.drawable.store_business_ic)
                .showImageOnFail(R.drawable.store_business_ic)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .build();
        mHouseOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.neighborhood_shop_ic)
                .showImageForEmptyUri(R.drawable.neighborhood_shop_ic)
                .showImageOnFail(R.drawable.neighborhood_shop_ic)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .build();
    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPoolSize(3);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.memoryCache(new WeakMemoryCache());
        config.memoryCacheSize(2 * 1024 * 1024);
        config.memoryCacheSizePercentage(13); // default
        config.diskCacheFileCount(100);
        config.diskCacheFileNameGenerator(new HashCodeFileNameGenerator());
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        MainApp application = (MainApp) context.getApplicationContext();
//        return application.refWatcher;
//    }


    public static String getCacheImagePath() {
        return FileUtil.getSdCardPath();
    }

    public static class KeyValue {
        public final static String KEY_REFRESH_TIME = "time";
        public final static String KEY_LOGIN_LAT = "lat";
        public final static String KEY_LOGIN_LNG = "lng";
        public final static String KEY_DATE_NEIGHBORPOSTDATE = "neighborPostDate";
        public final static String KEY_DATE_NOTICEDATE = "noticeDate";
        public final static String KEY_DATE_SHOP = "shop";
        public final static String KEY_DATE_MESSAGEDATE = "messageDate";
        public final static String KEY_SHORTCUT_LOGIN = "SHORTCUT ";
        public final static String KEY_AUTO_PHONE = "telephone";
        public final static String KEY_HOME_DATA = "data";
        public final static String KEY_LOADING_INFO = "loading";
        public final static String KEY_EXPRESS_AGREEMENT = "agree";
        public final static String KEY_REGISTER_NICKNAME = "NickName";
        public final static String KEY_CHILDLIST = "childList";
        public final static String KEY_POST_CONTENT = "content";
        public final static String KEY_HOUSE_PARAM = "houseParam";
        public final static String KEY_SERVICE_TYPE = "service_type";
        public final static String KEY_REGISTER_PARAM = "register_param";
        public final static String KEY_GUEST_TIP = "guest_tip";
        public final static String KEY_HOUSE_KEEP = "house_keep_tip";
        public final static String KEY_HOUSE_REPAIR = "house_repair_tip";
        public final static String KEY_PUBLIC_REPAIR = "public_repair_tip";
        public final static String KEY_SERVICE_TO = "ServiceTypeTo";
        public final static String KEY_PRAISE_OR_ADVICE = "praise_advice";
        public final static String KEY_SERVICE_REQUEST_PARAM = "ServiceRequestParam";
        public final static String KEY_WHEEL_RIGHT_POSITION = "right_position";
        public final static String KEY_WHEEL_LEFT_POSITION = "left_position";
        public final static String KEY_REPAIR_TIME = "repair_time";
        public final static String KEY_SERVICE_TIME = "house_keep_time";
        public final static String KEY_HOUSE_REGION = "house_region";
        public final static String KEY_SERVICE_SID = "category_sid";
        public final static String KEY_PRAISE_CONTENT = "praise_content";

    }


    public static class DefaultValue {
        public static final String IMAGE_URI = "http://7xk6y7.com2.z0.glb.qiniucdn.com/";
        public static final String CHAT_URL = "http://1http://s1.joyhomenet.com:9040/web/chat/";
        public static final String NOTICE_URL = "http://1http://s1.joyhomenet.com:9040/web/notice/";
        //public static final String GUIDE_URL = "http://www.ajweishequ.com:9000/web/guide/";
        public static final String COMMENT_BULK_URL = "http://lite.m.dianping.com/bJby01RKKP";
        public static final String PROTOCOL_URL = "http://1http://s1.joyhomenet.com:9040/static/html/";
        public static final String EXPRESS_PROTOCOL_URL = "http:/1http://s1.joyhomenet.com:9040/static/html/express_protocol.html";

        public static final String ABOUT_URL = "http://1http://s1.joyhomenet.com:9040/static/html/about.html?version=";

        public static final String Load_File_PATH = Environment.getExternalStorageDirectory() + "/" + "com.joy.home";

        //114.215.84.188   ajweishequ.com 192.168.17.109:9000
        public static final String PAY_BILL_PATH = "http://1http://s1.joyhomenet.com:9040/ihome/";
        // public static final String SHARE_PATH = "http://192.168.17.109:9009/web/topic/";
        public static final String VOTE_URL = "http://s1.joyhomenet.com:9040/web/vote/";
        public static final String VOTE_URL_NEIGHBOR="http://s1.joyhomenet.com:9040/web/neighborhood/vote/";
        public static final String VOTE_URL_NEIGHBOR_PARK="http://s0.joyhomenet.com:9040/web/neighborhood/vote/";

        public static final String alipayStr = "alipays://platformapi/startapp?appId=%s&sourceId=joy-ihome&returnUrl=%s";
        public static final String HOUSE_PROTOCOL="http://1http://s1.joyhomenet.com:9040/web/agreement/";

    }


    public static String getImagePath(String path) {
        if (path == null)
            return "";
        return DefaultValue.IMAGE_URI + path;

    }
    public static String getPicassoImagePath(String path) {
        if (TextUtils.isEmpty(path))
            return "picasso";
        return DefaultValue.IMAGE_URI + path;

    }
}
