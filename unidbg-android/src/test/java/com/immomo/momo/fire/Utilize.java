package com.immomo.momo.fire;

import java.util.Map;

public final class Utilize {

    private Utilize() {
        throw new UnsupportedOperationException();
    }

    public static boolean shouldUseAng(Map<String, String> map) {
        if (map == null || !map.containsKey("useang")) {
            return true;
        }
        return !"false".equalsIgnoreCase(map.get("useang"));
    }

    public static String getUserAgent() {
        return "MomoChat/8.25_dev Android/5931 (IN2020; Android 10; Gapps 0; zh_CN; 1; OnePlus)";
    }
}

