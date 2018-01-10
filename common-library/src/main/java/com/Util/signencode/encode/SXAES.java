package com.Util.signencode.encode;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by shixiongwei on 2017/12/27.
 */

public class SXAES {

    private static final String UTF_8 = "UTF-8";
    public static final String SECURITY_KET = "xqdJRyYZJRWc+MsNnmc5vA==";


    /**
     * 加密
     * @param cleartext 需要加密的内容
     * @param key  加密密码(目前写死的，传入的是 编码过的key)
     * @return
     */

    public static String encryptSX(String key, String cleartext)
            throws Exception {
//        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
//        SecretKeySpec keyString = new SecretKeySpec(key.getBytes(), "AES");
        byte[] str = SXBase64.decode(key);
        SecretKeySpec keySX = new SecretKeySpec(str, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
//        byte[] byteContent = cleartext.getBytes(UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, keySX);
//        byte[] encryptedData = cipher.doFinal(cleartext.getBytes("utf-8"));
//        return SXBase64.encode(encryptedData);

        byte[] result = cipher.doFinal(cleartext.getBytes(UTF_8));
        return SXBase64.encode(result);

    }

//    /**
//     * 解密
//     * @param cleartext 需要解密的内容
//     * @param key  加密密码 (目前传入的是 编码过的key)
//     * @return
//     */
//    public static String decryptSX(String key, String cleartext)
//            throws Exception {
////            resultkey = new String(key.getBytes("ASCII"), "ASCII");
//        byte[] str = SXBase64.decode(key);//解码
//        SecretKeySpec keySX = new SecretKeySpec(str, "AES");
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE,keySX);
//        byte[] decryptedData = cipher.doFinal(cleartext.getBytes(UTF_8));
//        String result = new String(decryptedData,UTF_8);
//        return result;
//    }

    private static  String byte2Base64(byte[] encode)
    {
        try
        {
            return Base64.encodeToString(encode, Base64.NO_WRAP);
        }
        catch (Exception ext)
        {
            ext.printStackTrace();
        }

        return null;
    }
}
