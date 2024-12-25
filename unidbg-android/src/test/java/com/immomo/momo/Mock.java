package com.immomo.momo;

import java.util.HashMap;
import java.util.Map;

public class Mock {

    public static String genSalt() {
        return "6ueA4OxIkFLAjNV8Dqkxa4sKqxbSNHu66ueA4OxIkFLAjNV8";
    }

    public static Map<String, String> genRequestParams() {
        Map<String, String> map = new HashMap<>();
        map.put("_ab_test_", "location-spxwuo_blank;active-wklfmo_blank;nearbyfeedlive-reymea_A");
        map.put("_iid", "246a3889eca5376ffcbf15660557a927");
        map.put("remoteid", "994491371");
        map.put("_net_", "wifi");
        map.put("_uid_", "a3931e93ff9cb0bc16e38cf3a14aa599");
        return map;
    }

    public static Map<String, String> genHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("X-ACT", "br");
        map.put("X-Span-Id", "0");
        map.put("cookie", "SESSIONID=9BD91EB9-DDF5-AF88-F242-1E2ED951FD36");
        map.put("User-Agent", "MomoChat/9.14.10 Android/12475 (V2241A; Android 12; Gapps 0; zh_CN; 1; vivo)");
        map.put("Accept-Language", "zh-CN");
        map.put("X-Trace-Id", "D179E388-650F-4AEE-8EFA-A5599F8A77D9");
        return map;
    }

}
