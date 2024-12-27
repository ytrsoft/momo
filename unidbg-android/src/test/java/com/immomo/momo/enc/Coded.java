package com.immomo.momo.enc;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Coded {

    private Coded() {
        throw new UnsupportedOperationException();
    }

    public static int computeOutputLength(int a1, int a2) {
        return a2 == 2 ? (a1 - 7) : (a1 + 23);
    }

    public static byte[] sign(byte[] bArr, byte[] bArr2) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(bArr);
            md.update(bArr2, 0, 8);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[20];
        }
    }

    public static int aesEncode(byte[] data, byte[] key, byte[] output) {
        if (data == null || key == null || output == null || key.length < 16) {
            return -1;
        }
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, 0, 16, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(data);
            if (output.length < encrypted.length) {
                throw new IllegalArgumentException("Output buffer is not large enough to hold encrypted data");
            }
            System.arraycopy(encrypted, 0, output, 0, encrypted.length);
            return encrypted.length + 7;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int aesDecode(byte[] encryptedData, byte[] key, byte[] output) {
        if (encryptedData == null || key == null || output == null || key.length < 16) {
            return -1;
        }
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, 0, 16, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decrypted = cipher.doFinal(encryptedData);
            if (output.length < decrypted.length) {
                throw new IllegalArgumentException("输出缓冲区不足以存放解密后的数据");
            }
            System.arraycopy(decrypted, 0, output, 0, decrypted.length);
            return decrypted.length;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }



}
