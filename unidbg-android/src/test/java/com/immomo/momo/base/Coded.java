package com.immomo.momo.base;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
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

    public void sdbyecbu37x(byte[] bArr, byte[] bArr2, byte[] bArr3, int i){
        dvmClass.callStaticJniMethodInt(emulator, "sdbyecbu37x([B[B[BI)I", bArr, bArr2, bArr3, i);
    }

    private int a9ehcDdu3j8(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3){
        return dvmClass.callStaticJniMethodInt(emulator, "a9ehcDdu3j8[BI[BI[B)I", bArr, i, bArr2, i2, bArr3);
    }

    private byte[] g7673Shg3P9(int i, int i2) {
        ByteArray bytes = dvmClass.callStaticJniMethodObject(emulator, "g7673Shg3P9(II)[B", i, i2);
        return bytes.getValue();
    }

    private byte[] ggug8Shj3S0(int i, int i2) {
        ByteArray bytes = dvmClass.callStaticJniMethodObject(emulator, "ggug8Shj3S0(II)[B", i, i2);
        return bytes.getValue();
    }

    private int bsuh37Dhjaw(byte[] bArr, byte[] bArr2){
        return dvmClass.callStaticJniMethodInt(emulator, "sdbyecbu37x([B[B)I", bArr, bArr2);
    }

    public int s729dS782nGoo(byte[] bArr, byte[] bArr2, int i, int i2) {
        return dvmClass.callStaticJniMethodInt(emulator, "s729dS782nGoo([B[BII)I", bArr, bArr2);
    }

    public int c789Sju7G87(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        return dvmClass.callStaticJniMethodInt(emulator, "c789Sju7G87([B[BI[B)I", bArr, bArr2, i, bArr3);
    }

    public int clientSecretGen(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        int c789Sju7G87;
        synchronized (Coded.class) {
            c789Sju7G87 = c789Sju7G87(bArr, bArr2, i, bArr3);
        }
        return c789Sju7G87;
    }

    private int serverSecretGen(byte[] bArr, byte[] bArr2, int i, int i2) {
        return s729dS782nGoo(bArr, bArr2, i, i2);
    }

    public int base64Decode(byte[] bArr, byte[] bArr2) {
        return bsuh37Dhjaw(bArr, bArr2);
    }

    public byte[] getServerPK(int i, int i2) {
        return g7673Shg3P9(i, i2);
    }

    private byte[] getServerSK(int i, int i2) {
        return ggug8Shj3S0(i, i2);
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
            return TheBase64.encode(bArr3);
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

    public String genSalt(String key) {
        byte[] b2 = TheBase64.decode(key.getBytes());
        if (b2.length > 0) {
            String a2 = TheBase64.encode(b2);
            return LuaApiUtil.a(a2);
        }
        return "";
    }

}
