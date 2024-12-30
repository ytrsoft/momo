package com.immomo.momo.enc;

import cn.hutool.core.lang.UUID;

public final class Id {

    private Id() {
        throw new UnsupportedOperationException();
    }

    public static String get() {
        return UUID.randomUUID().toString().toUpperCase();
    }

}
