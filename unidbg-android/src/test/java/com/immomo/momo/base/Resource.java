package com.immomo.momo.base;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.dvm.VM;

import java.io.File;

public class Resource {

    private File apk;
    private File libcoded;
        private File libcoded_jni;
    private File libmmcrypto;
    private File libmmssl;

    public File getApk() {
        return apk;
    }

    public void setApk(File apk) {
        this.apk = apk;
    }

    public File getLibcoded() {
        return libcoded;
    }

    public void setLibcoded(File libcoded) {
        this.libcoded = libcoded;
    }

    public File getLibcoded_jni() {
        return libcoded_jni;
    }

    public void setLibcoded_jni(File libcoded_jni) {
        this.libcoded_jni = libcoded_jni;
    }

    public File getLibmmcrypto() {
        return libmmcrypto;
    }

    public void setLibmmcrypto(File libmmcrypto) {
        this.libmmcrypto = libmmcrypto;
    }

    public File getLibmmssl() {
        return libmmssl;
    }

    public void setLibmmssl(File libmmssl) {
        this.libmmssl = libmmssl;
    }

    public void jniLoad(AndroidEmulator emulator, VM vm) {
        vm.loadLibrary(libmmcrypto, false).callJNI_OnLoad(emulator);
        vm.loadLibrary(libmmssl, false).callJNI_OnLoad(emulator);
        vm.loadLibrary(libcoded, false).callJNI_OnLoad(emulator);
        vm.loadLibrary(libcoded_jni, false).callJNI_OnLoad(emulator);
    }

}
