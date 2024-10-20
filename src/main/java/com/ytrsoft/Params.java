package com.ytrsoft;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import com.ytrsoft.momo.RequestEncoder;

public final class Params {

    public static final String SESSION_ID = "8FC6B726-2F66-CB71-7D4D-B1BF6507E67A";

    private Params () {
        throw new UnsupportedOperationException();
    }

    private static String toRaw(Map<String, Object> params) {
        RequestEncoder encoder = new RequestEncoder();
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (result.length() > 0) {
                result.append("&");
            }
            String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8);
            String value = URLEncoder.encode(String.valueOf(entry.getValue()), StandardCharsets.UTF_8);
            result.append(key).append("=").append(value);
        }
        return encoder.getZippedJson(result.toString());
    }

    public static String nearbyPeoples() {
        Map<String, Object> params = new HashMap<>();
        params.put("acc", "550");
        params.put("gapps", "0");
        params.put("mmuid", "");
        params.put("realauth", "0");
        params.put("screen", "900x1600");
        params.put("device_type", "android");
        params.put("osversion_int", "32");
        params.put("ddian_active_time", "1729410954");
        params.put("online_time", "0");
        params.put("constellation", "0");
        params.put("model", "NCO-AL00");
        params.put("net", "1");
        params.put("androidId", "fd2e4ba255725cac");
        params.put("lat", "39.633542");
        params.put("age_max", "30");
        params.put("_uid_", "fd52d6e1a5dcb727827afa527bf2d4e6");
        params.put("phone_type", "GSM");
        params.put("refreshmode", "user");
        params.put("lng", "115.109334");
        params.put("count", "20");
        params.put("age_min", "18");
        params.put("index", "0");
        params.put("_iid", "095c54419ab5322ae60e78e565105c7c");
        params.put("version", "12055");
        params.put("apksign", "4f3a531caff3e37c278659cc78bfaecc");
        params.put("_net_", "wifi");
        params.put("router_mac", "38:75:34:d6:73:ea");
        params.put("network_class", "wifi");
        params.put("vip_filter", "0");
        params.put("loctime", "1729410376786000");
        params.put("buildnumber", "V417IR/309");
        params.put("_ab_test_", "active-wklfmo_blank;nearbyfeedlive-reymea_A");
        params.put("save", "YES");
        params.put("locater", "202");
        params.put("imsi", "unknown");
        params.put("moment_sex", "");
        params.put("emu", "029f181d6e7ba188885c78462623c37a");
        params.put("loctype", "1");
        params.put("mac", "02:00:00:00:00:00");
        params.put("manufacturer", "HUAWEI");
        params.put("rom", "12");
        params.put("uid", "fd52d6e1a5dcb727827afa527bf2d4e6");
        params.put("total", "0");
        params.put("native_ua", "Mozilla/5.0 (Linux; Android 12; NCO-AL00 Build/V417IR; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/95.0.4638.74 Safari/537.36");
        params.put("market_source", "1");
        params.put("oaid", "");
        params.put("social", "0");
        params.put("phone_netWork", "0");
        params.put("dpp", "0b6b052c9d7fc7bdfd708c269d16a52a");
        params.put("sex", "F");
        params.put("lua_version", "3.0");
        params.put("firstRefresh", "0");
        params.put("_uidType", "androidid");
        return toRaw(params);
    }

    public static String search(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("myprofile_source", "self");
        params.put("signcount", "0");
        params.put("_ab_test_", "active-wklfmo_blank;nearbyfeedlive-reymea_A");
        params.put("_iid", "095c54419ab5322ae60e78e565105c7c");
        params.put("source_info", "{\"type\":\"-1\",\"extra\":\"com.immomo.momo.fullsearch.activity.FullSearchActivity\",\"stack\":\"[{\\\"name\\\":\\\"SessionListInnerFragment\\\"},{\\\"name\\\":\\\"FullSearchActivity\\\"},{\\\"data\\\":\\\"{\\\\\\\"userid\\\\\\\":\\\\\\\"1086398051\\\\\\\"}\\\",\\\"name\\\":\\\"PersonalProfileActivityK\\\"}]\"}");
        params.put("profile_source", "profile");
        params.put("newProfileExp", "B");
        params.put("_net_", "wifi");
        params.put("_uid_", "fd52d6e1a5dcb727827afa527bf2d4e6");
        params.put("remoteid", id);
    
        return toRaw(params);
    }

}
