package com.immomo.momo;

import com.immomo.momo.enc.Api;
import com.immomo.momo.enc.MomoApi;
import org.json.JSONObject;

public class AppTest {

    public static void main(String[] args) throws Exception {
        JSONObject params = new JSONObject();
        params.put("remoteid", "858716741");
        MomoApi momoApi = new MomoApi(Api.PROFILE)
                .withParams(params);
        momoApi.doRequest();
    }
}
