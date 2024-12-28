package com.immomo.momo;


import com.immomo.momo.fire.ApiSecurity;

import java.util.HashMap;
import java.util.Map;

public class AesEncoder {
    public static void main(String[] args) throws Exception {
        String key = "49R09OMs+KgPpe667Kg0eunfFqV/HS5g";
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("remoteid", "994491371");
        reqParams.put("_net_", "wifi");
        reqParams.put("_uid_", "a3931e93ff9cb0bc16e38cf3a14aa599");
        reqParams.put("_iid", "246a3889eca5376ffcbf15660557a927");
        reqParams.put("_ab_test_", "location-spxwuo_blank;microcosm-jtgdzn_A;active-wklfmo_blank;nearbyfeedlive-reymea_A");
        ApiSecurity security = new ApiSecurity();
        byte[] encrypt = security.getEncrypt(reqParams, key);
        String mzip = security.getBase64(encrypt);
        System.out.println("mzip = > " + mzip);
        Map<String, String> header = new HashMap<>();
        header.put("Accept-Language", "zh-CN");
        header.put("cookie", "SESSIONID=9BD91EB9-DDF5-AF88-F242-1E2ED951FD36");
        header.put("User-Agent", "MomoChat/9.14.10 Android/12475 (V2241A; Android 12; Gapps 0; zh_CN; 1; vivo)");
        header.put("X-ACT", "br");
        header.put("X-Span-Id", "0");
        header.put("X-Trace-Id", "71DC92D2-45D8-4EB1-9C8E-53E6B196CD4A");
        String sign = security.getSign(encrypt, header, key);
        System.out.println("sign = > " + sign);
    }
}
