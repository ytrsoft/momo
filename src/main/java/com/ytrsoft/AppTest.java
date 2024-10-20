package com.ytrsoft;

import cn.hutool.json.JSONUtil;

public class AppTest {
    public static void main(String[] args) {
        String mZip = Params.nearbyPeoples();
        String jsonStr = Http.post(Api.NEARBY_PEOPLES, mZip);
        String unescapedJson = jsonStr.replaceAll("\\\\", "");
        String formattedJson = JSONUtil.formatJsonStr(unescapedJson);
        System.out.println(formattedJson);
    }
}
