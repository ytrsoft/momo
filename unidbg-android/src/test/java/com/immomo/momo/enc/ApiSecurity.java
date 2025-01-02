package com.immomo.momo.enc;

import com.immomo.momo.emulator.Momo;
import com.immomo.momo.emulator.SoCoded;

public class ApiSecurity {

    private final String key;
    private final Mode mode;
    private Momo momo;
    private SoCoded coded;

    public enum Mode {
        DEFAULT,
        EMULATOR
    }

    public ApiSecurity(String key) {
        this(key, Mode.DEFAULT);
    }

    public ApiSecurity(String key, Mode mode) {
        this.key = key;
        this.mode = mode;
        if (mode == Mode.EMULATOR) {
            momo = new Momo();
            coded = momo.loadCoded();
        }
    }

    public byte[] sign(byte[] data) {
        byte[] bytes = Coded.sign(data, key.getBytes());
        if (mode == Mode.EMULATOR) {
            bytes = coded.sign(data, key.getBytes());
        }
        return bytes;
    }

    public byte[] encode(byte[] data) {
        byte[] bytes = Coded.encode(data, key.getBytes());
        if (mode == Mode.EMULATOR) {
            bytes = coded.encode(data, key.getBytes());
        }
        return bytes;
    }

    public String decode(byte[] data) {
        byte[] bytes = Coded.decode(data, key.getBytes());
        if (mode == Mode.EMULATOR) {
            bytes = coded.decode(data, key.getBytes());
        }
        return new String(bytes);
    }

    public void destroy() {
        if (mode == Mode.EMULATOR) {
            momo.destroy();
        }
    }

}
