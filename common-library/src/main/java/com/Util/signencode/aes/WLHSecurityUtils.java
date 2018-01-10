/**
 * 文 件 名:  WLHSecurityUtils.java
 * 版    权:  Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  江钰锋 00501
 * 修改时间:  2017/3/26
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

package com.Util.signencode.aes;

import android.support.annotation.NonNull;

import com.Util.signencode.encode.Base64Utils;
import com.Util.signencode.encode.SXAES;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 加解密工具
 *
 * @author 江钰锋 00501
 * @version [版本号, 2017/3/26]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class WLHSecurityUtils {

    private final static String charsetName="UTF-8";



    /***
     * 解密
     * @param data
     * @return
     */
    public static byte[] decrypt(byte[] data) {
        String content=new String(data);
        return AESUtils.decrypt(Base64Utils.decodeFromString(content),Base64Utils.decodeFromString(SXAES.SECURITY_KET));
    }

    /**
     * 加密
     * @param data
     * @return
     */
    public static String encrypt(byte[] data) {
        byte[] target=AESUtils.encrypt(data, Base64Utils.decode(SXAES.SECURITY_KET.getBytes()));
        return toURLEncoded(new String(Base64Utils.encode(target)));
    }

    /***
     * 加密
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        return encrypt(data.getBytes());
    }

    public static String toURLEncoded(@NonNull String paramString) {
        try
        {
            String str = new String(paramString.getBytes(), charsetName);
            str = URLEncoder.encode(str, charsetName);
            return str;
        }
        catch (Exception localException)
        {
//            LazyLogger.e("toURLEncoded error:"+paramString, localException);
        }
        return "";
    }

    public static String toURLDecoded(@NonNull String paramString) {
        try
        {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLDecoder.decode(str, "UTF-8");
            return str;
        }
        catch (Exception localException)
        {
//            LazyLogger.e("toURLDecoded error:"+paramString, localException);
        }

        return "";
    }

    public static void printHexString(byte[] b)
    {
        for (int i = 0; i < b.length; i++)
        {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1)
            {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
//        System.out.println("AES解密结果:");
//        printHexString(Base64Utils.decode(WLHAppConfing.SECURITY_KET.getBytes()));
//        System.out.println("AES解密结果:" + new String(decrypt("vAacFp1yaWZ1BygxvjUH1slvjJNPBqfl%2FclWRleG60Fti7lsRn9rKXchX8BksGaOjHGRIt8gfJyBjWJOTW7MtSKt68s1IJL05yV%2FVtNlxwyLKVl55ClKmbCv%2F7CAEMNPL2QQ1%2BQo5xu1ANOzf5RlAvAg4ktVrrjUOmYA7wboQaAQ9WPwftSmLGIhGrW1vP94UphcQK2XQbwXb9pLfocGzxWjf1zvRVBA%2FT0O0xTIln4%3D\n".getBytes())));
        System.out.println("AES加密结果:" + encrypt("{'totalCardCount':'0','userId':'63AFD0C85532B63252A31E27B505A5FE'}"));

//        System.out.println("AES解密结果:" + AESUtils.decrypt("666109B9FA0B1B53B00D1B4EAB9B2D7C688517D652A7BC00DB96AB54D85C2E1217721C04813D30B58A767DCE89B2EF78FD992ACC682C7CD999C302B8A630FA57",Base64Utils.decodeFromString(WLHAppConfing.SECURITY_KET)));
//        String context="{\n" +
//                "    \"ResultCode\": 0,\n" +
//                "    \"reason\": \"请求成功\"\n" +
//                "}";
//        System.out.println("AES加密结果:" + AESUtils.encrypt(context.getBytes(),Base64Utils.decodeFromString(WLHAppConfing.SECURITY_KET)));
    }
}
