package com.immomo.momo.base;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JSON {

    private static final Logger logger = LoggerFactory.getLogger(JSON.class);

    private JSON() {
        throw new UnsupportedOperationException();
    }

    public static String stringify(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        for (String str2 : map.keySet()) {
            try {
                jSONObject.put(str2, map.get(str2));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return jSONObject.toString();
    }

}
