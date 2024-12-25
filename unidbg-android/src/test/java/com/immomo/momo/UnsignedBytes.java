package com.immomo.momo;

public final class UnsignedBytes {

    public static final byte MAX_POWER_OF_TWO = Byte.MIN_VALUE;
    public static final byte MAX_VALUE = -1;
    private static final int UNSIGNED_MASK = 255;

    private UnsignedBytes() {
        throw new UnsupportedOperationException();
    }
}
