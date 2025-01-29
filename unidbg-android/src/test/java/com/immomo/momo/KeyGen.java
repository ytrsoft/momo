package com.immomo.momo;

import com.immomo.momo.emulator.CheckOS;
import com.immomo.momo.emulator.ECDH;

public class KeyGen {

    public static void main(String[] args) throws Exception {
        CheckOS checkOS = ECDH.gen();
        System.out.println("momo.key=" + checkOS.getSk());
        System.out.println("momo.ck=" + checkOS.getCk());
        System.out.println("momo.kv=" + checkOS.getKv());
    }
}
