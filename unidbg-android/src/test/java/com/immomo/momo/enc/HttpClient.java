package com.immomo.momo.enc;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public final class HttpClient {

    private static volatile HttpClient instance;
    private final OkHttpClient client;
    private RequestBody requestBody;
    private String url;
    private Map<String, String> headers;

    private HttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        client = builder.build();
    }

    public static HttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    public HttpClient headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public HttpClient body(String body) {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        requestBody = RequestBody.create(body, mediaType);
        return this;
    }

    public HttpClient url(String url) {
        this.url = url;
        return this;
    }

    public JSONObject build() throws IOException {
        Request request = new Request.Builder()
                .headers(Headers.of(headers))
                .post(requestBody)
                .url(url).build();
        Response response = client.newCall(request).execute();
        ResponseBody body = response.body();
        if (body != null) {
            return new JSONObject(body.string());
        }
        return new JSONObject();
    }

}
