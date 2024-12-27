package com.immomo.momo.enc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Coded {

    private Coded() {
        throw new UnsupportedOperationException();
    }

    public static int computeOutputLength(int a1, int a2) {
        return a2 == 2 ? (a1 - 7) : (a1 + 23);
    }

    public static byte[] sign(byte[] a1, byte[] a2) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(a1);
            md.update(a2, 0, 8);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[20];
        }
    }

}
