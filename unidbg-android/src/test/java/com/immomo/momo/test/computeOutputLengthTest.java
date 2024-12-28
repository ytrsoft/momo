package com.immomo.momo.test;

import com.immomo.momo.base.Momo;
import com.immomo.momo.fire.Coded;

public class computeOutputLengthTest {
    public static void main(String[] args) throws Exception {
        Momo momo = new Momo();
        com.immomo.momo.base.Coded coded = momo.loadCoded();
        int v1 = coded.computeOutputLength(1340, 1);
        System.out.println(1363 == v1);
        int v2 = Coded.computeOutputLength(1340, 1);
        System.out.println(1363 == v2);
        momo.destroy();
    }
}
