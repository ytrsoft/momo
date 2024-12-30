package com.immomo.momo.enc;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;

public final class Coded {

    private Coded() {
        throw new UnsupportedOperationException();
    }

    public static byte[] sign(byte[] data, byte[] key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(data);
            md.update(key, 0, 8);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return new byte[20];
        }
    }

    public static byte[] encode(byte[] data, byte[] key) {
        byte[] result = new byte[data.length + 23];
        try {
            byte[] header = new byte[]{2, 3};
            byte[] iv = new byte[4];
            SecureRandom sr = new SecureRandom();
            sr.nextBytes(iv);
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] ivHash = sha1.digest(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(Arrays.copyOfRange(ivHash, 0, 16));
            SecretKeySpec secretKey = new SecretKeySpec(Arrays.copyOfRange(key, 0, 16), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] encrypted = cipher.doFinal(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(header);
            outputStream.write(iv);
            outputStream.write(encrypted);
            byte[] target = outputStream.toByteArray();
            System.arraycopy(target, 0, result, 0, target.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] decode(byte[] data, byte[] key) {
        try {
            byte[] iv = Arrays.copyOfRange(data, 2, 6);
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] ivHash = sha1.digest(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(Arrays.copyOfRange(ivHash, 0, 16));
            SecretKeySpec secretKey = new SecretKeySpec(Arrays.copyOfRange(key, 0, 16), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            int endPos = findEnd(data);
            byte[] encrypted = Arrays.copyOfRange(data, 6, endPos);
            return cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[data.length - 7];
    }

    private static int findEnd(byte[] data) {
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] != 0) {
                return i + 1;
            }
        }
        return -1;
    }

}
