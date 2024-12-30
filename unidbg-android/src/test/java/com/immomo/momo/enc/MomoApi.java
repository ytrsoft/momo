package com.immomo.momo.enc;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MomoApi {

    private final String url;
    private final HeaderBuilder headerBuilder;
    private final ParamBuilder paramBuilder;
    private String zip;
    private String sign;

    public MomoApi(String url) {
        Props props = new Props();
        this.url = url + "?fr=" + props.getFr();
        this.headerBuilder = new HeaderBuilder(props);
        this.paramBuilder = new ParamBuilder(props);
    }

    public MomoApi withParams(Map<String, String> params) {
        JSONObject body = paramBuilder.buildParams(params);
        byte[] encoded = paramBuilder.encodeParams(body);
        this.zip = Base64.encode(encoded);
        this.sign = paramBuilder.sign(encoded);
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
            headers.put("X-LV", props.getLv());
            headers.put("X-KV", props.getKv());
            headers.put("X-Span-Id", props.getSpanId());
            headers.put("X-Trace-Id", Utilize.uuid());
            headers.put("cookie", props.getCookie());
            headers.put("Accept-Language", props.getLanguage());
            headers.put("X-ACT", props.getAct());
            headers.put("User-Agent", props.getUa());
            headers.put("x-sign", sign);
            return headers;
        }
    }

    private static class ParamBuilder {
        private final String key;
        private final Props props;

        public ParamBuilder(Props props) {
            this.key = props.getKey();
            this.props = props;
        }

        public JSONObject buildParams(Map<String, String> params) {
            params.put("_net_", props.getNet());
            params.put("_iid_", props.getTokenId());
            params.put("_ab_test_", props.getTest());
            params.put("_uid_", props.getUid());
            return new JSONObject(params);
        }

        public byte[] encodeParams(JSONObject body) {
            byte[] apiKey = this.key.getBytes();
            byte[] data = body.toString().getBytes();
            return Coded.encode(data, apiKey);
        }

        public String sign(byte[] encoded) {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                bos.write(encoded);
                bos.write(props.getUa().getBytes());
                byte[] bytes = Coded.sign(bos.toByteArray(), key.getBytes());
                return Base64.encode(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
