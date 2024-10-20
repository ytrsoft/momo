package com.ytrsoft;

import cn.hutool.json.JSONUtil;

public final class MomoService {
    private MomoService() {
        throw new UnsupportedOperationException();
    }

    public static String nearbyPeoples() {
        String mZip = Params.nearbyPeoples();
        String jsonStr = Http.post(Api.NEARBY_PEOPLES, mZip);
        String unescapedJson = jsonStr.replaceAll("\\\\", "");
        return JSONUtil.formatJsonStr(unescapedJson);
    }

    public static String search(String id) {
        String mZip = Params.search(id);
        String jsonStr = Http.post(Api.USER_PROFILE, mZip);
        String unescapedJson = jsonStr.replaceAll("\\\\", "");
        return JSONUtil.formatJsonStr(unescapedJson);
    }
}
