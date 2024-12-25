package com.immomo.momo.base;

/* compiled from: ByteUtils.java */
/* loaded from: classes3.dex */
public class ByteUtils implements Constants {

    /* renamed from: a, reason: collision with root package name */
    public static String f17279a = "utf-8";

    public static byte[] a(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("#");
                int i2 = i * 2;
                sb.append(str.substring(i2, i2 + 2));
                bArr[i] = Integer.decode(sb.toString()).byteValue();
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return bArr;
    }

    public static int a(byte[] bArr) {
        return (bArr[1] & MAX_VALUE) | ((bArr[0] & MAX_VALUE) << 8);
    }
}
