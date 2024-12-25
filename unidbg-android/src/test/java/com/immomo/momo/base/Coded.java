package com.immomo.momo.base;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.dvm.DvmClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Coded {

    private final DvmClass dvmClass;
    private final AndroidEmulator emulator;

    private final Logger logger = LoggerFactory.getLogger(Coded.class);

    protected Coded(AndroidEmulator emulator, DvmClass vmClass) {
        this.emulator = emulator;
        this.dvmClass = vmClass;
    }

    private int csjh7OhLe86(int i, int i2) {
        return dvmClass.callStaticJniMethodInt(emulator, "csjh7OhLe86(II)I", i, i2);
    }

    private int a49kdEba83h(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3) {
        return dvmClass.callStaticJniMethodInt(emulator, "a49kdEba83h([BI[BI[B)I", bArr, i, bArr2, i2, bArr3);
    }

    private void sdbyecbu37x(byte[] bArr, byte[] bArr2, byte[] bArr3, int i){
        dvmClass.callStaticJniMethodInt(emulator, "sdbyecbu37x([B[B[BI)I", bArr, bArr2, bArr3, i);
    }

    private int a9ehcDdu3j8(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3){
        return dvmClass.callStaticJniMethodInt(emulator, "a9ehcDdu3j8[BI[BI[B)I", bArr, i, bArr2, i2, bArr3);
    }

    public int aesEncode(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3) {
        return a49kdEba83h(bArr, i, bArr2, i2, bArr3);
    }

    public int aesDecode(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return -1;
        }
        return a9ehcDdu3j8(bArr, i, bArr2, i2, bArr3);
    }

    public int computeOutputLength(int i, int i2) {
        return csjh7OhLe86(i, i2);
    }

    public String sign(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return "";
        }
        byte[] bArr3 = new byte[20];
        sdbyecbu37x(bArr, bArr2, bArr3, bArr.length);
        try {
            return Base64.a(bArr3);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[computeOutputLength(bArr.length, 2)];
        int aesDecode = aesDecode(bArr, bArr.length, bArr2, bArr2.length, bArr3);
        byte[] bArr4 = new byte[aesDecode];
        System.arraycopy(bArr3, 0, bArr4, 0, aesDecode);
        return bArr4;
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[computeOutputLength(bArr.length, 1)];
        int aesEncode = aesEncode(bArr, bArr.length, bArr2, bArr2.length, bArr3);
        byte[] bArr4 = new byte[aesEncode];
        System.arraycopy(bArr3, 0, bArr4, 0, aesEncode);
        return bArr4;
    }

    public String genSalt() throws Exception {
        String key = "O+Y5UtZVuN0+ao71SKa+TFbVJga2oUUK";
        byte[] b2 = Base64.b(key.getBytes());
        if (b2.length > 0) {
            String a2 = Base64.a(b2);
            return LuaApiUtil.a(a2);
        }
        return "";
    }

}
