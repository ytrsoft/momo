package com.immomo.momo.enc;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MomoApi {

    private String url;
    private final String key;
    private final Props props;
    private final HeaderBuilder headerBuilder;
    private final ParamBuilder paramBuilder;
    private String zip;
    private String sign;

    public MomoApi() {
        this.key = "M7ydwPqW3AUbQHsbGF+8AJbwLQ2JTSFdM7ydwPqW3AUbQHsb";
        this.props = new Props();
        this.headerBuilder = new HeaderBuilder(this.props);
        this.paramBuilder = new ParamBuilder(this.key, this.props);
    }

    public MomoApi args(Map<String, String> args) {
        JSONObject body = paramBuilder.buildParams(args);
        this.zip = paramBuilder.encodeParams(body);
        this.sign = paramBuilder.generateSign(body);
        return this;
    }

    public MomoApi url(String url) {
        this.url = url + "?fr=" + this.props.getFr();
        return this;
    }

    public String doRequest() {
        HttpResponse response = HttpRequest.post(url)
                .addHeaders(headerBuilder.buildHeaders(sign))
                .form("mzip", zip)
                .execute();
        return response.body();
    }

    private static class HeaderBuilder {
        private final Props props;

        public HeaderBuilder(Props props) {
            this.props = props;
        }

        public Map<String, String> buildHeaders(String sign) {
            Map<String, String> headers = new HashMap<>();
            headers.put("X-LV", this.props.getLv());
            headers.put("X-KV", "806d4fd4");
            headers.put("X-Span-Id", this.props.getSpanId());
            headers.put("X-Trace-Id", Id.get());
            headers.put("cookie", this.props.getCookie());
            headers.put("Accept-Language", this.props.getLanguage());
            headers.put("X-ACT", this.props.getAct());
            headers.put("User-Agent", this.props.getUa());
            headers.put("x-sign", sign);
            return headers;
        }

        public void printHeaders() {
            for (Map.Entry<String, String> entry : buildHeaders("").entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
    }

    private static class ParamBuilder {
        private final String key;
        private final Props props;

        public ParamBuilder(String key, Props props) {
            this.key = key;
            this.props = props;
        }

        public JSONObject buildParams(Map<String, String> params) {
            params.put("_get_", this.props.getNet());
            params.put("_iid_", this.props.getTokenId());
            params.put("_ab_test_", this.props.getTest());
            params.put("_uid_", this.props.getUid());
            return new JSONObject(params);
        }

        public String encodeParams(JSONObject body) {
            byte[] apiKey = this.key.getBytes();
            byte[] data = body.toString().getBytes();
            byte[] encoded = Coded.encode(data, apiKey);
            return Base64.encode(encoded);
        }

        public String generateSign(JSONObject body) {
            byte[] apiKey = this.key.getBytes();
            byte[] encodedData = encodeParams(body).getBytes();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                bos.write(encodedData);
                bos.write(this.props.getUa().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] bytes = Coded.sign(bos.toByteArray(), apiKey);
            return Base64.encode(bytes);
        }
    }

    private static byte[] getCkOs() {
        ClassPathResource resource = new ClassPathResource("");
        String path = resource.getAbsolutePath();
        File file = new File(path + ".ck_os");
        String content = FileUtil.readUtf8String(file);
        return Base64.decode(content.getBytes());
    }
}
