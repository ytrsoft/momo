package com.ytrsoft;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

public final class Http {

    private Http() {
        throw new UnsupportedOperationException();
    }

    public static String post(String url, String mzip) {
        HttpResponse response = HttpRequest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .cookie("SESSIONID=" + Params.SESSION_ID)
                .form("mzip", mzip)
                .execute();
        return response.body();
    }

}
