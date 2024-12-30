package com.immomo.momo;

import com.immomo.momo.enc.MomoApi;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AppTest {

    public static void main(String[] args) throws Exception {
        String url = "https://api.immomo.com/v3/user/profile/info";
        Map<String, String> params = new HashMap<>();
        params.put("remoteid", "1085971146");
        MomoApi api = new MomoApi(url).withParams(params);
        String body = api.doRequest();
        String name = new JSONObject(body)
                .getJSONObject("data")
                .getJSONObject("profile")
                .getString("name");
        System.out.println(name);
    }
}
