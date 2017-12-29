package com.joy.common.api;



import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.common.helper.UserInfoHelper;
import com.joy.library.utils.MD5;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * Created by warrior on 14/10/15
 */
public class  ApiClient {

    static RestAdapter adapter;
    static Context mContext;

    /**
     * 192.168.17.108:9000
     * 192.168.17.109:9000
     * 旧 121.43.158.111:9000
     * * http://tyuanqu.joyhomenet.com:89/ihome

     * 域名测试http://thome.joyhomenet.com:89/ihome
     *  *域名正式：http://s1.joyhomenet.com:9040/ihome

     * 新服务器地址http://139.129.166.169:9000/ihome
     *http://s0.joyhomenet.com:9040/park
     */
    static String URL=ApiUrlUtil.JOYHOME_BASE_URL ;
//    static String URL="http://thome.joyhomenet.com:89/ihome" ;

    static {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                long time = System.currentTimeMillis();
                String timeStr = String.valueOf(time);
                String sign =  MD5.getMD5(MD5.getMD5(timeStr) + "AIzaSyDgDTWUqhXyDx_NayRsGNHBcYTjiVyB1Mw");
                request.addHeader("token", sign );
                request.addHeader("time" ,timeStr);
                request.addHeader("pushBy","1");
                request.addHeader("app-version", "android-"+getVersion());
                request.addHeader("userId", (mContext==null|| UserInfoHelper.getInstance(mContext)==null)?null:UserInfoHelper.getInstance(mContext).getSid());
            }

        };

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setConverter(new JacksonConverter())
                .setRequestInterceptor(requestInterceptor);
        adapter = builder.build();
    }
         public static  <T> T create(Class<T> service){
        return  adapter.create(service);
    }

         public static class JacksonConverter implements Converter {
        private static final String MIME_TYPE = "application/json; charset=UTF-8";

        private final ObjectMapper objectMapper;

        public JacksonConverter() {
            this(new ObjectMapper());
        }

        public JacksonConverter(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA));
        }

        @Override
        public Object fromBody(TypedInput body, Type type) throws ConversionException {
            try {
                if(type == Void.class) {
                    return null;
                }

                JavaType javaType = objectMapper.getTypeFactory().constructType(type);

                return objectMapper.readValue(body.in(), javaType);
            } catch (JsonParseException e) {
                throw new ConversionException(e);
            } catch (JsonMappingException e) {
                throw new ConversionException(e);
            } catch (IOException e) {
                throw new ConversionException(e);
            }
        }

        @Override
        public TypedOutput toBody(Object object) {
            try {
                String json = objectMapper.writeValueAsString(object);
                return new TypedByteArray(MIME_TYPE, json.getBytes("UTF-8"));
            } catch (JsonProcessingException e) {
                throw new AssertionError(e);
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }

    }

    /**
     * 通过MainApp 文件传递context内容
     * @param context
     */
    public  void setmContext(Context context) {
        mContext = context;
    }
    /**
     *  获取版本号
     * */
    public static String getVersion() {
        try {
            
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //http://tyuanqu.joyhomenet.com:89/ihome
//http://s0.joyhomenet.com:9040/park
    //"http://139.129.166.169:19087/park"
public static void setParkUrl() {
    RequestInterceptor requestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            long time = System.currentTimeMillis();
            String timeStr = String.valueOf(time);
            String sign =  MD5.getMD5(MD5.getMD5(timeStr) + "AIzaSyDgDTWUqhXyDx_NayRsGNHBcYTjiVyB1Mw");
            request.addHeader("token", sign );
            request.addHeader("time" ,timeStr);
            // request.addHeader("check-code" ,"AIzaSyDgDTWUqhXyDx_NayRsGNHBcYTjiVyB1Mw");
            request.addHeader("app-version", "android-"+getVersion());
            request.addHeader("pushBy","1");
            request.addHeader("userId", (mContext==null|| UserInfoHelper.getInstance(mContext)==null)?null:UserInfoHelper.getInstance(mContext).getSid());
        }

    };

    RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint("http://s0.joyhomenet.com:9040/park")
            .setConverter(new JacksonConverter())
            .setRequestInterceptor(requestInterceptor);
    adapter = builder.build();


}
    public static void setHomeUrl() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                long time = System.currentTimeMillis();
                String timeStr = String.valueOf(time);
                String sign =  MD5.getMD5(MD5.getMD5(timeStr) + "AIzaSyDgDTWUqhXyDx_NayRsGNHBcYTjiVyB1Mw");
                request.addHeader("token", sign );
                request.addHeader("time" ,timeStr);
                // request.addHeader("check-code" ,"AIzaSyDgDTWUqhXyDx_NayRsGNHBcYTjiVyB1Mw");
                request.addHeader("app-version", "android-"+getVersion());
                request.addHeader("userId", (mContext==null|| UserInfoHelper.getInstance(mContext)==null)?null:UserInfoHelper.getInstance(mContext).getSid());
                request.addHeader("pushBy","1");
            }

        };

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setConverter(new JacksonConverter())
                .setRequestInterceptor(requestInterceptor);
        adapter = builder.build();


    }
}