package com.immomo.momo.emulator;

import com.github.unidbg.linux.android.dvm.VM;

public class Momo extends BaseApk {

    public Momo() {
        this(false);
    }

    public Momo(boolean verbose) {
        super("com.immomo.momo");
        init(verbose);
    }

    @Override
    protected String getDvmClassPath() {
        return "com.immomo.momo.util.jni.Coded";
    }

    public Coded loadCoded() {
        VM vm = getApkLoader().getVm();
        return new Coded(getEmulator(), vm, super.loadDvmClass());
    }
}
