package com.immomo.momo.base;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.DvmClass;

public class ApkLoader {

    private VM vm;
    private DvmClass dvmClass;
    private final EmulatorManager emulatorManager;
    private final Resource resource;

    public ApkLoader(EmulatorManager emulatorManager, Resource resource) {
        this.emulatorManager = emulatorManager;
        this.resource = resource;
    }

    public void loadApk(boolean verbose) {
        vm = emulatorManager.getEmulator().createDalvikVM(resource.getApk());
        System.out.println(resource.getApk().getAbsolutePath());
        vm.setVerbose(verbose);
        AndroidEmulator emulator = emulatorManager.getEmulator();
        resource.jniLoad(emulator, vm);
    }

    public DvmClass loadDvmClass(String classPath) {
        String path = classPath.replace('.', '/');
        dvmClass = vm.resolveClass(path);
        return dvmClass;
    }

    public VM getVm() {
        return vm;
    }

    public DvmClass getDvmClass() {
        return dvmClass;
    }

    public void close() {
        emulatorManager.close();
    }
}
