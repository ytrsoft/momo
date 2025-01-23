package com.immomo.momo;

import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.immomo.momo.emulator.Momo;
import com.immomo.momo.emulator.SoCoded;

import java.util.Arrays;

public class KeyGen {

    private static void useTest01(SoCoded so) {
        byte[] pk = so.getServerPK(2, 1);
        ByteArray bArr = so.newByteArray(24);
        ByteArray bArr2 = so.newByteArray(49);
        so.clientSecretGen(bArr, pk, pk.length, bArr2);
        String s_pk = Arrays.toString(pk);
        String s_aes_key = Arrays.toString(bArr.getValue());
        String s_ck = Arrays.toString(bArr2.getValue());
        System.out.println("pk => " + s_pk);
        System.out.println("ck => " + s_ck);
        System.out.println("ase_key => " + s_aes_key);
    }

    public static void main(String[] args) {
        Momo momo = new Momo();
        SoCoded so = momo.loadCoded();
        useTest01(so);
        momo.destroy();
    }
}
