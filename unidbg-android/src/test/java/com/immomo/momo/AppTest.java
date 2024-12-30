package com.immomo.momo;

import com.immomo.momo.enc.MomoApi;
import org.json.JSONObject;

public class AppTest {

    public static void main(String[] args) throws Exception {
        String url = "https://api.immomo.com/v3/user/profile/info";
        JSONObject params = new JSONObject();
        JSONObject body = new MomoApi(url)
                .withParams(params)
                .doRequest();
        System.out.println(body);
    }
}
