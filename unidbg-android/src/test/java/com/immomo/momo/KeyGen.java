package com.immomo.momo;

import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.immomo.momo.emulator.Momo;
import com.immomo.momo.emulator.SoCoded;

import javax.crypto.KeyAgreement;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class KeyGen {

    private static void useTest01(SoCoded so) throws Exception {
        byte[] pk = so.getServerPK(2, 1);
        ByteArray bArr = so.newByteArray(24);
        ByteArray bArr2 = so.newByteArray(49);
        so.clientSecretGen(bArr, pk, pk.length, bArr2);
        String s_pk = Arrays.toString(pk);
        String s_aes_key = Arrays.toString(bArr.getValue());
        String s_ck = Arrays.toString(bArr2.getValue());
        System.out.println("so_pk => " + s_pk);
        System.out.println("so_ck => " + s_ck);
        System.out.println("so_ase_key => " + s_aes_key);
        /*byte[] aesKeyOutput = new byte[24];
        byte[] sharedKeyOutput = new byte[49];
        clientSecretGen(aesKeyOutput, pk, sharedKeyOutput);
        System.out.println("ck => " + Arrays.toString(sharedKeyOutput));
        System.out.println("ase_key => " + Arrays.toString(aesKeyOutput));*/
    }

    public static void clientSecretGen(byte[] aesKeyOutput, byte[] serverPublicKey, byte[] sharedKeyOutput) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        KeyPair clientKeyPair = keyPairGenerator.generateKeyPair();
        PrivateKey clientPrivateKey = clientKeyPair.getPrivate();
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(serverPublicKey);
        PublicKey serverPublicKeyObj = keyFactory.generatePublic(keySpec);
        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
        keyAgreement.init(clientPrivateKey);
        keyAgreement.doPhase(serverPublicKeyObj, true);
        byte[] sharedSecret = keyAgreement.generateSecret();
        System.arraycopy(sharedSecret, 0, sharedKeyOutput, 0, sharedSecret.length);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] aesKey = digest.digest(sharedSecret);
        System.arraycopy(aesKey, 0, aesKeyOutput, 0, aesKeyOutput.length);
    }

    public static void main(String[] args) throws Exception {
        Momo momo = new Momo();
        SoCoded so = momo.loadCoded();
        useTest01(so);
        momo.destroy();
    }
}
