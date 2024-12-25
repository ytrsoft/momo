package com.ytrsoft.core;

import com.github.unidbg.linux.android.dvm.DvmClass;
import com.ytrsoft.util.FileSupport;

public abstract class BaseApk {

    private final EmulatorManager emulatorManager;
    private ApkLoader apkLoader;

    public BaseApk(String processName) {
        this.emulatorManager = new EmulatorManager(processName);
    }

    protected void init(boolean verbose) {
        Resource resource = FileSupport.getResource();
        apkLoader = new ApkLoader(emulatorManager, resource);
        apkLoader.loadApk(verbose);
    }

    public void loadDvmClass(String classPath) {
        apkLoader.loadDvmClass(classPath);
    }

    public void destroy() {
        apkLoader.close();
    }

    public ApkLoader getApkLoader() {
        return apkLoader;
    }
}
