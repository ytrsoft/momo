package com.immomo.momo.base;

public class LuaApiUtil {

    public static int a() {
        return 1;
    }

    public static int b() {
        return 2;
    }

    public static String a(String str) {
        return str + str.substring(0, str.length() / 2);
    }

}
