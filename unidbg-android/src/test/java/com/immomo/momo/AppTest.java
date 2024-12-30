package com.immomo.momo;

import com.immomo.momo.enc.MomoApi;

import java.util.HashMap;
import java.util.Map;

public class AppTest {

    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("remoteid", "994491371");
        MomoApi momo = new MomoApi();
        momo.addUrl("https://api.immomo.com/v3/user/profile/info");
        momo.addParams(params);
        String build = momo.build();
        System.out.println(build);
    }
}
