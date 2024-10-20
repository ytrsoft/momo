package com.ytrsoft.momo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class Base64 {
    private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    Base64() {
    }

    private static int decode(char c2) {
        int i;
        if (c2 >= 'A' && c2 <= 'Z') {
            return c2 - 'A';
        }
        if (c2 >= 'a' && c2 <= 'z') {
            i = c2 - 'a';
        } else {
            if (c2 < '0' || c2 > '9') {
                if (c2 == '+') {
                    return 62;
                }
                if (c2 == '/') {
                    return 63;
                }
                if (c2 == '=') {
                    return 0;
                }
                throw new RuntimeException("unexpected code: " + c2);
            }
            i = (c2 - '0') + 26;
        }
        return i + 26;
    }

    private static void decode(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        int i = 0;
        while (true) {
            if (i < length && str.charAt(i) <= ' ') {
                i++;
            } else {
                if (i == length) {
                    return;
                }
                int decode = decode(str.charAt(i));
                int decode2 = decode(str.charAt(i + 1));
                int i2 = i + 2;
                int decode3 = decode(str.charAt(i2));
                int i3 = i + 3;
                int decode4 = (decode << 18) + (decode2 << 12) + (decode3 << 6) + decode(str.charAt(i3));
                outputStream.write((decode4 >> 16) & 255);
                if (str.charAt(i2) == '=') {
                    return;
                }
                outputStream.write((decode4 >> 8) & 255);
                if (str.charAt(i3) == '=') {
                    return;
                }
                outputStream.write(decode4 & 255);
                i += 4;
            }
        }
    }

    public static byte[] decode(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            decode(str, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                System.err.println("Error while decoding BASE64: " + e.toString());
            }
            return byteArray;
        } catch (IOException e2) {
            throw new RuntimeException();
        }
    }

    public static String encode(byte[] bArr) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer((bArr.length * 3) / 2);
        int i = 0;
        int i2 = 0;
        while (i <= length - 3) {
            int i3 = ((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8) | (bArr[i + 2] & 255);
            stringBuffer.append(legalChars[(i3 >> 18) & 63]);
            stringBuffer.append(legalChars[(i3 >> 12) & 63]);
            stringBuffer.append(legalChars[(i3 >> 6) & 63]);
            stringBuffer.append(legalChars[i3 & 63]);
            i += 3;
            if (i2 >= 14) {
                stringBuffer.append(" ");
                i2 = 0;
            } else {
                i2++;
            }
        }
        int i4 = 0 + length;
        if (i == i4 - 2) {
            int i5 = ((bArr[i + 1] & 255) << 8) | ((bArr[i] & 255) << 16);
            stringBuffer.append(legalChars[(i5 >> 18) & 63]);
            stringBuffer.append(legalChars[(i5 >> 12) & 63]);
            stringBuffer.append(legalChars[(i5 >> 6) & 63]);
            stringBuffer.append("=");
        } else if (i == i4 - 1) {
            int i6 = (bArr[i] & 255) << 16;
            stringBuffer.append(legalChars[(i6 >> 18) & 63]);
            stringBuffer.append(legalChars[(i6 >> 12) & 63]);
            stringBuffer.append("==");
        }
        return stringBuffer.toString();
    }
}