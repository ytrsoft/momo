package com.immomo.momo.base;

public class EnConfig {

    private EnConfig() {
    }

    private static final class InstanceHolder {
        static final EnConfig instance = new EnConfig();
    }

    public static EnConfig getInstance() {
        return InstanceHolder.instance;
    }

    public int a() {
        return 5931;
    }

    public String c() {
        return "MomoChat/8.25_dev Android/" + a() + " (IN2020; Android 10; Gapps 0; zh_CN; 1; OnePlus)";
    }
}
