package com.ytrsoft.core;


import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.DvmClass;

import java.io.File;
import java.util.List;

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
        vm.setVerbose(verbose);
        List<File> soFiles = resource.getSo();
        soFiles.forEach(file -> {
            vm.loadLibrary(file, false).callJNI_OnLoad(emulatorManager.getEmulator());
        });
    }

    public void loadDvmClass(String classPath) {
        String path = classPath.replace('.', '/');
        dvmClass = vm.resolveClass(path);
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
