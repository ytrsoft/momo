package com.immomo.momo;

import com.immomo.momo.enc.Api;
import com.immomo.momo.enc.MomoApi;
import org.json.JSONObject;

public class AppTest {
    public static void main(String[] args) throws Exception {
        JSONObject params = new JSONObject();
        params.put("remoteid", "109040377");
        MomoApi api = new MomoApi(Api.PROFILE);
        JSONObject response = api.withParams(params).doRequest();
        String name = response.getJSONObject("data").getJSONObject("profile").getString("name");
        System.out.println(name);
    }
}
