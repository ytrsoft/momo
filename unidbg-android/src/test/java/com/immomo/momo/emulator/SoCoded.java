package com.immomo.momo.emulator;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.array.ByteArray;

public class SoCoded {

    private final VM vm;
    private final DvmClass dvmClass;
    private final AndroidEmulator emulator;

    protected SoCoded(AndroidEmulator emulator, VM vm, DvmClass vmClass) {
        this.emulator = emulator;
        this.vm = vm;
        this.dvmClass = vmClass;
    }

    private int csjh7OhLe86(int i, int i2) {
        return dvmClass.callStaticJniMethodInt(emulator, "csjh7OhLe86(II)I", i, i2);
    }

    private void c789Sju7G87(ByteArray bArr, byte[] bArr2, int i, ByteArray bArr3) {
        dvmClass.callStaticJniMethodInt(emulator, "c789Sju7G87([B[BI[B)I", bArr, bArr2, i, bArr3);
    }

    private byte[] g7673Shg3P9(int i, int i2) {
        ByteArray byteArray = dvmClass.callStaticJniMethodObject(emulator, "g7673Shg3P9(II)[B", i, i2);
        return byteArray.getValue();
    }

    public void a49kdEba83h(byte[] bArr, int i, byte[] bArr2, int i2, ByteArray bArr3) {
        dvmClass.callStaticJniMethodInt(emulator, "a49kdEba83h([BI[BI[B)I", bArr, i, bArr2, i2, bArr3);
    }

    public void sdbyecbu37x(byte[] bArr, byte[] bArr2, ByteArray bArr3, int i){
        dvmClass.callStaticJniMethodInt(emulator, "sdbyecbu37x([B[B[BI)I", bArr, bArr2, bArr3, i);
    }

    public void a9ehcDdu3j8(byte[] bArr, int i, byte[] bArr2, int i2, ByteArray bArr3){
        dvmClass.callStaticJniMethodInt(emulator, "a9ehcDdu3j8([BI[BI[B)I", bArr, i, bArr2, i2, bArr3);
    }

    public void aesEncode(byte[] bArr, int i, byte[] bArr2, int i2, ByteArray bArr3) {
        a49kdEba83h(bArr, i, bArr2, i2, bArr3);
    }

    public void aesDecode(byte[] bArr, int i, byte[] bArr2, int i2, ByteArray bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return;
        }
        a9ehcDdu3j8(bArr, i, bArr2, i2, bArr3);
    }

    public int computeOutputLength(int i, int i2) {
        return csjh7OhLe86(i, i2);
    }

    public byte[] getServerPK(int i, int i2) {
        return g7673Shg3P9(i, i2);
    }

    public void clientSecretGen(ByteArray bArr, byte[] bArr2, int i, ByteArray bArr3) {
        c789Sju7G87(bArr, bArr2, i, bArr3);
    }

    public byte[] encode(byte[] data, byte[] key) {
        ByteArray bArr = newByteArray(computeOutputLength(data.length, 1));
        aesEncode(data, data.length, key, key.length, bArr);
        return bArr.getValue();
    }

    public byte[] decode(byte[] data, byte[] key) {
        ByteArray bArr = newByteArray(computeOutputLength(data.length, 2));
        aesDecode(data, data.length, key, key.length, bArr);
        return bArr.getValue();
    }

    public byte[] sign(byte[] bArr, byte[] bArr2) {
        byte[] sign = new byte[20];
        if (bArr == null || bArr2 == null) {
            return sign;
        }
        ByteArray bArr3 = new ByteArray(vm, sign);
        sdbyecbu37x(bArr, bArr2, bArr3, bArr.length);
        return bArr3.getValue();
    }

    public ByteArray newByteArray(int size) {
        byte[] bytes = new byte[size];
        return new ByteArray(vm, bytes);
    }

}
