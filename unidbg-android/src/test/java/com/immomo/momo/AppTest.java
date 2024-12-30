package com.immomo.momo;

import com.immomo.momo.enc.MomoApi;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AppTest {

    public static void main(String[] args) throws Exception {
        String url = "https://api.immomo.com/v3/user/profile/info";
        Map<String, String> params = new HashMap<>();
        params.put("remoteid", "147217653");
        MomoApi api = new MomoApi().url(url).args(params);
        JSONObject profile = new JSONObject(api.doRequest())
                .getJSONObject("data")
                .getJSONObject("profile");
        System.out.println(profile.get("name"));
    }
}
