package com.immomo.momo.enc;

import java.util.Base64;

public final class TheBase64 {

    private TheBase64() {
        throw new UnsupportedOperationException();
    }

    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decode(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }



}
