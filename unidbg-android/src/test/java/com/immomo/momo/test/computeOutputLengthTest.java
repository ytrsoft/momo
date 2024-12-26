package com.immomo.momo.test;

import com.immomo.momo.base.Coded;
import com.immomo.momo.base.Momo;

public class computeOutputLengthTest {
    public static void main(String[] args) throws Exception {
        Momo momo = new Momo();
        Coded coded = momo.loadCoded();
        int value = coded.computeOutputLength(1340, 1);
        System.out.println(value == 1363);
        momo.destroy();
    }
}
