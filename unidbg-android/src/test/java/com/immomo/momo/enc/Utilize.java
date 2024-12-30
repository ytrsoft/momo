package com.immomo.momo.enc;

import cn.hutool.core.lang.UUID;


public final class Utilize {

    private Utilize() {
        throw new UnsupportedOperationException();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().toUpperCase();
    }
}

