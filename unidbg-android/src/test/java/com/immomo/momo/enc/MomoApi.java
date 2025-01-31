package com.immomo.momo.enc;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MomoApi {

    private final String url;
    private final HeaderBuilder headerBuilder;
    private final ParamBuilder paramBuilder;
    private String zip;
    private String sign;
    private static ApiSecurity security;

    private static final Logger logger = LoggerFactory.getLogger(MomoApi.class);

    public MomoApi(String url) {
        Props props = new Props();
        this.url = url + "?fr=" + props.getFr();
        this.headerBuilder = new HeaderBuilder(props);
        this.paramBuilder = new ParamBuilder(props);
        logger.info("请求地址 - {}", this.url);
        logger.info("密钥 - {}", props.getKey());
        security = new ApiSecurity(props.getKey());
    }

    public MomoApi withParams(JSONObject params) {
        JSONObject body = paramBuilder.buildParams(params);
        byte[] encoded = paramBuilder.encodeParams(body);
        this.zip = Base64.encode(encoded);
        this.sign = paramBuilder.sign(encoded);
        return this;
    }

    public JSONObject doRequest() {
        JSONObject headersJson = headerBuilder.buildHeaders(sign);
        logger.info("请求头 - {}", headersJson);
        logger.info("请求体 - {}", zip);
        Map<String, String> headers = toMap(headersJson);
        HttpClient client = HttpClient.getInstance()
                .url(url).headers(headers).body(zip);
        try {
            return client.build();
        } catch (IOException e) {
            logger.error("请求失败: {}", e.getMessage());
            return null;
        }
    }

    private Map<String, String> toMap(JSONObject json) {
        Map<String, String> map = new HashMap<>();
        Map<String, Object> jm = json.toMap();
        for (Map.Entry<String, Object> entry : jm.entrySet()) {
            map.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return map;
    }

    private static class HeaderBuilder {
        private final Props props;

        public HeaderBuilder(Props props) {
            this.props = props;
        }

        public JSONObject buildHeaders(String sign) {
            JSONObject headers = new JSONObject();
            headers.put("cookie", props.getCookie());
            headers.put("X-SIGN", sign);
            headers.put("X-Span-Id", props.getSpanId());
            headers.put("X-ACT", props.getAct());
            headers.put("X-LV", props.getLv());
            headers.put("X-KV", props.getKv());
            headers.put("X-Trace-Id", Utilize.uuid());
            headers.put("Accept-Language", props.getLanguage());
            return headers;
        }
    }

    private static class ParamBuilder {
        private final Props props;

        public ParamBuilder(Props props) {
            this.props = props;
        }

        public JSONObject buildParams(JSONObject params) {
            JSONObject args = new JSONObject(params.toMap());
            args.put("_net_", props.getNet());
            args.put("_iid_", props.getTokenId());
            args.put("_ab_test_", props.getTest());
            args.put("_uid_", props.getUid());
            return args;
        }

        public byte[] encodeParams(JSONObject body) {
            return security.encode(body.toString().getBytes());
        }

        public String sign(byte[] encoded) {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                bos.write(props.getUa().getBytes());
                bos.write(encoded);
                byte[] bytes = security.sign(bos.toByteArray());
                return Base64.encode(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    public void destroy() {
        security.destroy();
    }
}
