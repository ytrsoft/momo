package com.immomo.momo.emulator;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.debugger.Debugger;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.VM;

import java.io.File;

public class Resource implements Breakpoint.Hook, Breakpoint.Leave {

    private File apk;
    private File libcoded;
    private File libcoded_jni;
    private File libmmcrypto;
    private File libmmssl;
    private Breakpoint breakpoint;

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

    public DalvikModule[] jniLoad(AndroidEmulator emulator, VM vm) {
        DalvikModule module0 = vm.loadLibrary(libmmcrypto, false);
        module0.callJNI_OnLoad(emulator);
        DalvikModule module1 = vm.loadLibrary(libmmssl, false);
        module1.callJNI_OnLoad(emulator);
        DalvikModule module2 = vm.loadLibrary(libcoded, false);
        Module module = module2.getModule();
        testHook(emulator, module);
        module2.callJNI_OnLoad(emulator);
        DalvikModule module3 = vm.loadLibrary(libcoded_jni, false);
        module3.callJNI_OnLoad(emulator);
        return new DalvikModule[]{module0, module1, module2, module3};
    }

    public void testHook(AndroidEmulator emulator, Module module) {
        breakpoint = new Breakpoint(emulator, module);
        breakpoint.setOnLeave(this);
        breakpoint.setOnHook(this);
        breakpoint.register("clientSecretGen");
    }

    @Override
    public void onHook(Debugger debugger, RegisterContext context) {
        breakpoint.next(debugger, context);
    }

    @Override
    public void onLeave(RegisterContext context) {
        int ret = context.getLRPointer().getInt(0);
        System.out.println(ret);
    }
}
