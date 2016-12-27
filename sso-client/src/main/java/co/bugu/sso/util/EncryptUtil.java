package co.bugu.sso.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class EncryptUtil {

	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static final String DES = "DES";

	/**
	 * MD5 32位小写
	 * 
	 * @param text
	 * @return
	 */
	public static String md5(String text) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(text.getBytes("utf-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("System doesn't support your  EncodingException.");
		}
		byte[] bytes = msgDigest.digest();
		String md5Str = new String(encodeHex(bytes));
		return md5Str;
	}

//	/**
//	 * DES加密base64
//	 *
//	 * @param data
//	 * @param key
//	 * @return
//	 * @throws Exception
//	 */
//	public static String encrypt(String data, String key) throws Exception {
//		byte[] bt = encrypt(data.getBytes(), key.getBytes());
//		String strs = Base64.encodeBase64URLSafeString(bt);
//		return strs;
//	}

//	/**
//	 * base64后DES解密
//	 *
//	 * @param data
//	 * @param key
//	 * @return
//	 * @throws IOException
//	 * @throws Exception
//	 */
//	public static String decrypt(String data, String key) throws IOException, Exception {
//		if (data == null) {
//			return null;
//		}
//		byte[] buf = Base64.decodeBase64(data);
//		byte[] bt = decrypt(buf, key.getBytes());
//		return new String(bt);
//	}

	/**
	 * DES加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}

	/**
	 * DES解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}

	/**
	 * 32位小写
	 * 
	 * @param data
	 * @return
	 */
	public static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}
		return out;
	}

    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }


	/**
	 * 获取Sign的值
	 *
	 * @param param
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSign(Map<String, String> param){
		return md5(createLinkString(paraFilter(param)));
	}

	/**
	 * 按字母升序排列
	 *
	 * @param params
	 * @return
	 */
	private static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	/**
	 * 除去签名参数
	 *
	 * @param sArray 签名参数组
	 * @return 去签名参数后的新签名参数组
	 */
	private static Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if(key.equalsIgnoreCase("sign"))
			{
				continue;
			}
			if (value == null ) {
				value="";
			}
			result.put(key, value);
		}
		return result;
	}


	public static void main(String[] args) {
		try {
			String data = "2";
			System.out.println(md5(data));
			String key = "^&%$#####$%$%$%$%$##@@#+/";
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
