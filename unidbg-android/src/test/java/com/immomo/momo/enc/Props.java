package com.immomo.momo.enc;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Props {

    private String lv;
    private String uid;
    private String tokenId;
    private String ua;
    private String spanId;
    private String language;
    private String contentType;
    private String sessionId;
    private String apiKey;
    private String cookie;
    private String fr;
    private String net;
    private String test;

    public Props() {
        Properties props = getProps();
        cookie = props.getProperty("cookie");
        language = props.getProperty("accept_language");
        contentType = props.getProperty("content_type");
        ua = props.getProperty("ua");
        uid = props.getProperty("uid");
        tokenId = props.getProperty("_iid");
        spanId = props.getProperty("x_span_id");
        lv = props.getProperty("x_lv");
        fr = props.getProperty("fr");
        net = props.getProperty("_net_");
        test = props.getProperty("_ab_test_");
    }

    private Properties getProps() {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("");
        String path = resource.getAbsolutePath();
        File file = new File(path + "momo.properties");
        try {
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
