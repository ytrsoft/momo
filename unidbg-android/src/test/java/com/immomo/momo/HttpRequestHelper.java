package com.immomo.momo;

import java.util.Map;

public class HttpRequestHelper {
    public static boolean a(Map<String, String> map) {
        if (map == null || !map.containsKey("useang")) {
            return true;
        }
        return !"false".equalsIgnoreCase(map.get("useang"));
    }
}
