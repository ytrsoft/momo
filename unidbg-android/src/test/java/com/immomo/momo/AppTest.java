package com.immomo.momo;

import com.immomo.momo.enc.MomoApi;
import java.util.HashMap;
import java.util.Map;

public class AppTest {

    public static void main(String[] args) throws Exception {
        String url = "https://api.immomo.com/v3/user/profile/info";
        Map<String, String> params = new HashMap<>();
        params.put("remoteid", "994491371");
        MomoApi api = new MomoApi().url(url).args(params);
        String body = api.doRequest();
        System.out.println(body);
    }
}
