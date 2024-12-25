package com.ytrsoft.core;


import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.memory.Memory;

import java.io.File;
import java.util.List;

public class ApkLoader {

    private VM vm;
    private final EmulatorManager emulatorManager;
    private final Resource resource;

    public ApkLoader(EmulatorManager emulatorManager, Resource resource) {
        this.emulatorManager = emulatorManager;
        this.resource = resource;
    }

    public void loadApk(boolean verbose) {
        Memory memory = emulatorManager.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));

        vm = emulatorManager.getEmulator().createDalvikVM(resource.getApk());
        vm.setVerbose(verbose);

        List<File> soFiles = resource.getSo();
        soFiles.forEach(file -> {
            vm.loadLibrary(file, false).callJNI_OnLoad(emulatorManager.getEmulator());
        });

    }

    public DvmClass getDvmClass(String classPath) {
        String path = classPath.replace('.', '/');
        return vm.resolveClass(path);
    }

    public VM getVm() {
        return vm;
    }

    public void close() {
        emulatorManager.close();
    }
}
