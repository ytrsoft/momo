package com.immomo.momo.base;

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
        return new Coded(getEmulator(), super.loadDvmClass());
    }
}
