package com.ytrsoft;

import com.github.unidbg.linux.android.dvm.DvmClass;
import com.ytrsoft.core.ApkLoader;
import com.ytrsoft.core.Momo;

public class AesEncoder {
    public static void main(String[] args) {
        Momo momo = new Momo();
        ApkLoader loader = momo.getApkLoader();
        DvmClass dvmClass = loader.getDvmClass();
        System.out.println(dvmClass);
        momo.destroy();
    }
}
