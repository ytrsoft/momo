package com.immomo.momo;

import com.immomo.momo.base.Coded;
import com.immomo.momo.base.Momo;

public class AesEncoder {
    public static void main(String[] args) {
        Momo momo = new Momo();
        Coded coded = momo.loadCoded();
        System.out.println(coded);
        momo.destroy();
    }
}
