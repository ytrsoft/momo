package com.immomo.momo.emulator;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.memory.Memory;

public class EmulatorManager {

    private final AndroidEmulator emulator;

    public EmulatorManager(String processName) {
        this.emulator = AndroidEmulatorBuilder
                .for32Bit()
                .setProcessName(processName)
                .build();
        Memory memory = this.emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
    }

    public AndroidEmulator getEmulator() {
        return emulator;
    }

    public void close() {
        try {
            emulator.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Memory getMemory() {
        return emulator.getMemory();
    }
}
