package com.immomo.momo.base;

import java.util.Map;

/* compiled from: HttpRequestHelper.java */
/* loaded from: classes12.dex */
public class HttpRequestHelper {
    public static boolean a(Map<String, String> map) {
        if (map == null || !map.containsKey("useang")) {
            return true;
        }
        return !"false".equalsIgnoreCase(map.get("useang"));
    }
}