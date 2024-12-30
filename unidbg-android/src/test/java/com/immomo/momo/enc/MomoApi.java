package com.immomo.momo.enc;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MomoApi {

    private String url;
    private String zip;
    private String sign;
    private Map<String, String> headers;
    private final String key;
    private final Props props;

    public MomoApi() {
        this.key = "M7ydwPqW3AUbQHsbGF+8AJbwLQ2JTSFdM7ydwPqW3AUbQHsb";
        this.props = new Props();
    }

    private static byte[] getCkOs() {
        ClassPathResource resource = new ClassPathResource("");
        String path = resource.getAbsolutePath();
        File file = new File(path + ".ck_os");
        String content = FileUtil.readUtf8String(file);
        return Base64.decode(content.getBytes());
    };

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-lv", this.props.getLv());
        headers.put("x-kv", "806d4fd4");
        headers.put("x-span-id", this.props.getSpanId());
        headers.put("x-trace-id", Id.get());
        headers.put("cookie", this.props.getCookie());
        headers.put("accept-language", this.props.getLanguage());
        headers.put("content-type", this.props.getContentType());
        headers.put("user-agent", this.props.getUa());
        return headers;
    }


    private String getZip(Map<String, String> params) {
        JSONObject body = new JSONObject(params);
        byte[] data = body.toString().getBytes();
        byte[] encode = Coded.encode(data, this.key.getBytes());
        return Base64.encode(encode);
    }

    private String getSign(Map<String, String> header) {
        JSONObject body = new JSONObject(header);
        byte[] data = body.toString().getBytes();
        byte[] sign = Coded.sign(data, this.key.getBytes());
        return Base64.encode(sign);
    }

    public MomoApi addUrl(String url) {
        this.url = url + "?fr=" + this.props.getFr();
        return this;
    }

    public MomoApi addParams(Map<String, String> params) {
        params.put("_get_", this.props.getNet());
        params.put("_iid_", this.props.getTokenId());
        params.put("_ab_test_", this.props.getTest());
        params.put("_uid_", this.props.getUid());
        byte[] apiKey = this.key.getBytes();
        JSONObject body = new JSONObject(params);
        byte[] data = body.toString().getBytes();
        byte[] encode = Coded.encode(data, apiKey);
        this.zip = Base64.encode(encode);
        byte[] bytes = Coded.sign(encode, apiKey);
        this.sign = Base64.encode(bytes);
        this.headers = getHeaders();
        headers.put("x-sign", sign);
        return this;
    }

    public String build() {
        System.out.println(headers.toString());
        HttpResponse response = HttpRequest.post(url).addHeaders(headers)
                .form("mzip", zip).execute();
        return response.body();
    }

}
