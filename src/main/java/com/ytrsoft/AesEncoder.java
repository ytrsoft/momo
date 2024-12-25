package com.ytrsoft;

import com.github.unidbg.linux.android.dvm.DvmClass;
import com.ytrsoft.core.Momo;

public class AesEncoder {
    public static void main(String[] args) {
        Momo momo = new Momo();
        DvmClass dvmClass = momo.getDvmClass();
        System.out.println(dvmClass);
        momo.destroy();
    }
}
