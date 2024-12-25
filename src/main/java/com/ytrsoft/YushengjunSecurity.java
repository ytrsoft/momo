package com.ytrsoft;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.memory.Memory;

import java.io.File;
import java.io.IOException;

public final class YushengjunSecurity extends AbstractJni {

    private final AndroidEmulator emulator;
    private final DvmClass dvmClass;

    private static YushengjunSecurity instance;

    private static final String PREFIX = "unidbg-android/libs/";
    private static final File APK = new File(PREFIX + "momo.apk");
    private static final File LIBMMCRYPTO = new File(PREFIX + "libmmcrypto.so");
    private static final File LIBMMSSL =new File(PREFIX + "libmmssl.so");
    private static final File LIBCODED = new File(PREFIX + "libcoded.so");
    private static final File LIBCODED_JNI =new File(PREFIX + "libcoded_jni.so");

    public static YushengjunSecurity getInstance() {
        YushengjunSecurity coded;
        synchronized (YushengjunSecurity.class) {
            if (instance == null) {
                instance = new YushengjunSecurity();
            }
            coded = instance;
        }
        return coded;
    }

    private AndroidEmulator createARMEmulator() {
        return AndroidEmulatorBuilder
                .for32Bit()
                .setProcessName("com.immomo.momo")
                .build();
    }

    public YushengjunSecurity() {
        emulator = createARMEmulator();
        Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        VM vm = emulator.createDalvikVM(APK);
        vm.setJni(this);
        vm.setVerbose(false);
        vm.loadLibrary(LIBMMCRYPTO, false).callJNI_OnLoad(emulator);
        vm.loadLibrary(LIBMMSSL, false).callJNI_OnLoad(emulator);
        vm.loadLibrary(LIBCODED, false).callJNI_OnLoad(emulator);
        vm.loadLibrary(LIBCODED_JNI, false).callJNI_OnLoad(emulator);
        dvmClass = vm.resolveClass("com/immomo/momo/util/jni/Coded");
    }

    public String generateSalt() {
        return "6ueA4OxIkFLAjNV8Dqkxa4sKqxbSNHu66ueA4OxIkFLAjNV8";
    }

    public int computeOutputLength(int i, int i2) {
        return dvmClass.callStaticJniMethodInt(emulator, "csjh7OhLe86(II)I", i, i2);
    }

    public int aesEncode(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3) {
        return dvmClass.callStaticJniMethodInt(emulator, "a49kdEba83h([BI[BI[B)I", bArr, i, bArr2, i2, bArr3);
    }

    public void destroy() {
        try {
            emulator.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
