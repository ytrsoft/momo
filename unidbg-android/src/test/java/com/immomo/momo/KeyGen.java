package com.immomo.momo;

import com.immomo.momo.emulator.ECDH;


public class KeyGen {

    public static void main(String[] args) throws Exception {
        ECDH.Result result = ECDH.gen();
        String key = result.getKey();
        String ck = result.getCk();
        String kv = result.getKv();
        System.out.println("momo.key=" + key + "\nmomo.ck=" + ck + "\nmomo.kv=" + kv);
    }
}
