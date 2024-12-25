package com.ytrsoft.core;

import java.io.ByteArrayOutputStream;

public class Base64 implements Constants {

    private static final char[] BASE64_ENCODING_TABLE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', OBJECT_TYPE, 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'
    };

    private static final byte[] BASE64_DECODING_TABLE = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27,
            28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1,
            -1, -1, -1, -1
    };

    public static String encode(byte[] inputBytes) throws Exception {
        StringBuilder stringBuffer = new StringBuilder();
        int length = inputBytes.length;
        int i = 0;

        while (i < length) {
            int i2 = i + 1;
            int i3 = inputBytes[i] & Constants.MAX_VALUE;

            if (i2 == length) {
                stringBuffer.append(BASE64_ENCODING_TABLE[i3 >>> 2]);
                stringBuffer.append(BASE64_ENCODING_TABLE[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }

            int i4 = i2 + 1;
            int i5 = inputBytes[i2] & Constants.MAX_VALUE;

            int i1 = ((i3 & 3) << 4) | ((i5 & DEVICE_DISPLAY_DPI_MEDIAN) >>> 4);
            if (i4 == length) {
                stringBuffer.append(BASE64_ENCODING_TABLE[i3 >>> 2]);
                stringBuffer.append(BASE64_ENCODING_TABLE[i1]);
                stringBuffer.append(BASE64_ENCODING_TABLE[(i5 & 15) << 2]);
                stringBuffer.append(KEY_VALUE_DELIMITER);
                break;
            }

            int i6 = i4 + 1;
            int i7 = inputBytes[i4] & Constants.MAX_VALUE;
            stringBuffer.append(BASE64_ENCODING_TABLE[i3 >>> 2]);
            stringBuffer.append(BASE64_ENCODING_TABLE[i1]);
            stringBuffer.append(BASE64_ENCODING_TABLE[((i5 & 15) << 2) | ((i7 & 192) >>> 6)]);
            stringBuffer.append(BASE64_ENCODING_TABLE[i7 & 63]);
            i = i6;
        }

        return stringBuffer.toString();
    }

    public static byte[] decode(byte[] inputBytes) {
        int length = inputBytes.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        int i = 0;

        while (i < length) {
            byte b2 = decodeBase64Byte(inputBytes, i);
            if (b2 == -1) break;

            i++;

            byte b3 = decodeBase64Byte(inputBytes, i);
            if (b3 == -1) break;

            byteArrayOutputStream.write((b2 << 2) | ((b3 & 48) >>> 4));

            i++;

            byte b4 = decodeBase64Byte(inputBytes, i);
            if (b4 == -1) break;

            byteArrayOutputStream.write(((b3 & 15) << 4) | ((b4 & 60) >>> 2));

            i++;

            byte b5 = decodeBase64Byte(inputBytes, i);
            if (b5 == -1) break;

            byteArrayOutputStream.write(((b4 & 3) << 6) | b5);

            i++;
        }

        return byteArrayOutputStream.toByteArray();
    }

    private static byte decodeBase64Byte(byte[] inputBytes, int i) {
        if (i >= inputBytes.length) {
            return -1;
        }
        return BASE64_DECODING_TABLE[inputBytes[i]];
    }
}
