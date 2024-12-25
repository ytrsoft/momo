    package com.immomo.momo;

    import com.immomo.momo.Constants;
    import com.immomo.momo.ProtocolType;

    import java.io.ByteArrayOutputStream;

    /* compiled from: Base64.java */
    /* loaded from: classes3.dex */
    public class Base64 {

        /* renamed from: a, reason: collision with root package name */
        private static char[] f17256a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Constants.OBJECT_TYPE, 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

        /* renamed from: b, reason: collision with root package name */
        private static byte[] f17257b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, ProtocolType.COMMON_ACTIVITY_BAR_TYPE, ProtocolType.VOTE_CHANGE_STAR, ProtocolType.BIZ_RELIABLE_MSG_ACK, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

        public static String a(byte[] bArr) throws Exception {
            StringBuffer stringBuffer = new StringBuffer();
            int length = bArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                int i2 = i + 1;
                    int i3 = bArr[i] & UnsignedBytes.MAX_VALUE;
                if (i2 == length) {
                    stringBuffer.append(f17256a[i3 >>> 2]);
                    stringBuffer.append(f17256a[(i3 & 3) << 4]);
                    stringBuffer.append("==");
                    break;
                }
                int i4 = i2 + 1;
                int i5 = bArr[i2] & UnsignedBytes.MAX_VALUE;
                if (i4 == length) {
                    stringBuffer.append(f17256a[i3 >>> 2]);
                    stringBuffer.append(f17256a[((i3 & 3) << 4) | ((i5 & GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN) >>> 4)]);
                    stringBuffer.append(f17256a[(i5 & 15) << 2]);
                        stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
                    break;
                }
                int i6 = i4 + 1;
                int i7 = bArr[i4] & UnsignedBytes.MAX_VALUE;
                stringBuffer.append(f17256a[i3 >>> 2]);
                stringBuffer.append(f17256a[((i3 & 3) << 4) | ((i5 & GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN) >>> 4)]);
                stringBuffer.append(f17256a[((i5 & 15) << 2) | ((i7 & 192) >>> 6)]);
                stringBuffer.append(f17256a[i7 & 63]);
                i = i6;
            }
            return stringBuffer.toString();
        }

        public static byte[] b(byte[] bArr) throws Exception {
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
            int i = 0;
            byte b2 = 0;
            byte b3 = 0;
            byte b4 = 0;
            byte b5 = 0;
            while (i < length) {
                while (i < bArr.length) {
                    int i2 = i + 1;
                    b2 = f17257b[bArr[i]];
                    if (i2 >= length || b2 != -1) {
                        i = i2;
                        break;
                    }
                    i = i2;
                }
                if (b2 == -1) {
                    break;
                }
                while (i < bArr.length) {
                    int i3 = i + 1;
                    b3 = f17257b[bArr[i]];
                    if (i3 >= length || b3 != -1) {
                        i = i3;
                        break;
                    }
                    i = i3;
                }
                if (b3 == -1) {
                    break;
                }
                byteArrayOutputStream.write((b2 << 2) | ((b3 & 48) >>> 4));
                while (i < bArr.length) {
                    int i4 = i + 1;
                    byte b6 = bArr[i];
                    if (b6 == 61) {
                        return byteArrayOutputStream.toByteArray();
                    }
                    byte b7 = f17257b[b6];
                    if (i4 >= length || b7 != -1) {
                        b4 = b7;
                        i = i4;
                        break;
                    }
                    b4 = b7;
                    i = i4;
                }
                if (b4 == -1) {
                    break;
                }
                byteArrayOutputStream.write(((b3 & 15) << 4) | ((b4 & 60) >>> 2));
                while (i < bArr.length) {
                    int i5 = i + 1;
                    byte b8 = bArr[i];
                    if (b8 == 61) {
                        return byteArrayOutputStream.toByteArray();
                    }
                    byte b9 = f17257b[b8];
                    if (i5 >= length || b9 != -1) {
                        b5 = b9;
                        i = i5;
                        break;
                    }
                    b5 = b9;
                    i = i5;
                }
                if (b5 == -1) {
                    break;
                }
                byteArrayOutputStream.write(((b4 & 3) << 6) | b5);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }
