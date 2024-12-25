package com.immomo.momo;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 签名算法还原测试
 * 由余麻子提供YushengjunSecurity工具类独家支持
 */
public class MomoAlgorithmRestorer {
    public static void main(String[] args) throws Exception {
            String str3 = YushengjunSecurity.getInstance().generateSalt();
        String jSONObject2 = genBody().toString();
        byte[] bytes = jSONObject2.getBytes();
        byte[] bytes2 = str3.getBytes();
        byte[] bArr2 = new byte[YushengjunSecurity.getInstance().computeOutputLength(bytes.length, 1)];
        int aesEncode = YushengjunSecurity.getInstance().aesEncode(bytes, bytes.length, bytes2, bytes2.length, bArr2);
        byte[] bArr3 = new byte[aesEncode];
        System.arraycopy(bArr2, 0, bArr3, 0, aesEncode);
        String mzip = Base64.a(bArr3);
        System.out.println("mzip:" + mzip);
        Map<String, String> header = genHeader();
        String x_sign = a(bArr3, header, str3);
        System.out.println("x-sign:" + x_sign);
    }

    private static String a(byte[] bArr, Map<String, String> map, String str) {
        byte[] bytes = str.getBytes();
        boolean a = HttpRequestHelper.a(map);

        return "";
    }

    private static JSONObject genBody() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("_ab_test_", "location-spxwuo_blank;active-wklfmo_blank;nearbyfeedlive-reymea_A");
        map.put("_iid", "246a3889eca5376ffcbf15660557a927");
        map.put("remoteid", "994491371");
        map.put("_net_", "wifi");
        map.put("_uid_", "a3931e93ff9cb0bc16e38cf3a14aa599");
        JSONObject jSONObject = new JSONObject();
        for (String str2 : map.keySet()) {
            jSONObject.put(str2, map.get(str2));
        }
        System.out.println(jSONObject);
        return jSONObject;
    }

    private static Map<String, String> genHeader() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("X-ACT", "br");
        map.put("X-Span-Id", "0");
        map.put("cookie", "SESSIONID=9BD91EB9-DDF5-AF88-F242-1E2ED951FD36");
        map.put("User-Agent", "MomoChat/9.14.10 Android/12475 (V2241A; Android 12; Gapps 0; zh_CN; 1; vivo)");
        map.put("Accept-Language", "zh-CN");
        map.put("X-Trace-Id", "A31E1A41-C881-4FA1-881C-C522E0F8A94F");
        return map;
    }
}
