package com.ytrsoft.core;


public class Momo extends BaseApk {

    public Momo() {
        this(false);
    }

    public Momo(boolean verbose) {
        super("com.immomo.momo");
        init(verbose);
    }

    public String getSalt() {
        return "6ueA4OxIkFLAjNV8Dqkxa4sKqxbSNHu66ueA4OxIkFLAjNV8";
    }

}
