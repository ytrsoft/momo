package com.immomo.momo.base;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.dvm.DvmClass;

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

    public DvmClass loadDvmClass() {
        return apkLoader.loadDvmClass(getDvmClassPath());
    }

    public void destroy() {
        apkLoader.close();
    }

    public ApkLoader getApkLoader() {
        return apkLoader;
    }

    public AndroidEmulator getEmulator() {
        return emulatorManager.getEmulator();
    }

    protected abstract String getDvmClassPath();
}
