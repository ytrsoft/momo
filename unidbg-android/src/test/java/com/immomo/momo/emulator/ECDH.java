package com.immomo.momo.emulator;

import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.immomo.momo.enc.Base64;
import com.immomo.momo.enc.Coded;

import java.util.Arrays;

public final class ECDH {

    private ECDH() {
        throw new UnsupportedOperationException();
    }

    public static CheckOS gen() {
        Momo momo = new Momo();
        SoCoded so = momo.loadCoded();
        CheckOS checkOS = new CheckOS();
        byte[] PK = so.getServerPK(2, 1);
        ByteArray SK = so.newByteArray(24);
        ByteArray CK = so.newByteArray(49);
        so.clientSecretGen(SK, PK, PK.length, CK);
        checkOS.setPk(Base64.encode(PK));
        checkOS.setSk(Base64.encode(SK.getValue()));
        checkOS.setCk(Base64.encode(CK.getValue()));
        checkOS.setKv(Coded.md5(checkOS.getCk()).substring(0, 8));
        momo.destroy();
        return checkOS;
    }


}
