package com.immomo.momo;

import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.immomo.momo.emulator.Momo;
import com.immomo.momo.emulator.SoCoded;
import com.immomo.momo.enc.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class KeyGen {

    public static String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b2 : digest) {
                StringBuilder hexString = new StringBuilder(Integer.toHexString(b2));
                while (hexString.length() < 2) {
                    hexString.insert(0, "0");
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e2) {
            return str;
        }
    }


    public static void main(String[] args) {
        Momo momo = new Momo();
        SoCoded soCoded = momo.loadCoded();
        VM vm = momo.getApkLoader().getVm();
        byte[] pk = soCoded.getServerPK(1, 2);
        byte[] bArr = new byte[24];
        byte[] bArr2 = new byte[222];
        ByteArray ref_bArr = new ByteArray(vm, bArr);
        ByteArray ref_bArr2 = new ByteArray(vm, bArr2);
        soCoded.clientSecretGen(ref_bArr, pk, pk.length, ref_bArr2);
        byte[] ref_bArrValue = ref_bArr.getValue();
        byte[] ref_bArr2Value = ref_bArr2.getValue();
        System.out.println(Arrays.toString(pk));
        System.out.println(Arrays.toString(ref_bArrValue));
        System.out.println(Arrays.toString(ref_bArr2Value));
        String aesKey = Base64.encode(ref_bArrValue);
        String ck = Base64.encode(ref_bArr2Value);
        System.out.println("key = " + aesKey);
        System.out.println("ck = " + ck);
        String kv = md5(ck).substring(0, 8);
        System.out.println("kv = " + kv);

        momo.destroy();
    }
}
