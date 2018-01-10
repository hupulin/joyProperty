package com.Util.signencode;


import android.app.Activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by shixiongwei on 2017/12/26.
 */

public class SXHttpUtils {


    /*
         * Function  :   发送Post请求到服务器
         * Param     :   params请求体内容，encode编码格式
         */
    public static void requestPostData(final Activity context, final String strUrlPath, final Map<String, String> params, final String encode, final LoadListener listerner) {

        //获得请求体
        new Thread(new Runnable() {
            @Override
            public void run() {
                String requestData = getRequestData(params, encode).toString();

                try {
                    URL url = new URL(strUrlPath);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(3000);//设置连接超时时间
//            httpURLConnection.setDoInput(true);//打开输入流，以便于从服务器获取数据
                    httpURLConnection.setRequestMethod("POST");//设置POST提交
//            httpURLConnection.setUseCaches(false);//设置 不能使用缓存
//            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    byte[] data = requestData.getBytes("utf-8");
                    //设置请求体长度
//            httpURLConnection.setRequestProperty("Content-Length",String.valueOf(data.length)+"");
                    //至少要设置的两个请求头
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpURLConnection.setRequestProperty("Content-Length", data.length + "");

                    httpURLConnection.setDoOutput(true);//打卡输出流，便于提交数据
                    //获取输出流，向服务器写入数据
                    OutputStream outputStream = httpURLConnection.getOutputStream();//这里抛异常
                    outputStream.write(requestData.getBytes("UTF-8"));

                    int response = httpURLConnection.getResponseCode();//获取服务器的响应码
                    if (response == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        final String result = dealResponseResult(inputStream);
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listerner.onLoadSuccess(result);
                            }
                        });

                    } else
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listerner.onLoadError();
                            }
                        });

                } catch (Exception e) {
//            e.printStackTrace();
                    System.out.println(e + "errrrrrrrrrrrr");
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listerner.onLoadError();
                        }
                    });

                }
            }
        }).start();


    }

    /*
     * Function  :   封装请求体信息
     * Param     :   params请求体内容，encode编码格式
     */
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    /*
     * Function  :   封装请求体信息  Json字符串格式
     * Param     :   params请求体内容，encode编码格式
     */
    public static StringBuffer getJsonStringData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        stringBuffer.append("{");

        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer
                        .append("\"")
                        .append(entry.getKey())
                        .append("\"")
                        .append(":")
                        .append("\"")
                        .append(entry.getValue())
                        .append("\"")
                        .append(",");

            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个","
            stringBuffer.append("}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }


    /*
     * Function  :   处理服务器的响应结果（将输入流转化成字符串）
     * Param     :   inputStream服务器的响应输入流
     */
    public static String dealResponseResult(InputStream inputStream) {
        String resultData = null;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        resultData = new String(byteArrayOutputStream.toByteArray());
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

    private LoadListener listerner;

    public interface LoadListener {
        void onLoadSuccess(String rusult);

        void onLoadError();
    }

}

