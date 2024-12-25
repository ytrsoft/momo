package com.immomo.momo.base;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ApiSecurity {

    private final Coded coded;

    public ApiSecurity(Coded coded) {
        this.coded = coded;
    }

    public byte[] encrypted(String body, String salt) {
        return coded.encrypt(body.getBytes(), salt.getBytes());
    }

    public String x_sign(byte[] encrypted, Map<String, String> header, String salt) {
        if (StringUtils.isEmpty(salt)) {
            return "";
        }
        byte[] bytes = salt.getBytes();
        if (bytes.length < 8) {
            return "";
        }
        boolean status = HttpRequestHelper.a(header);
        byte[] bytes2 = "".getBytes(StandardCharsets.UTF_8);
        if (status) {
            bytes2 = EnConfig.getInstance().c().getBytes();
        }
        if (encrypted == null) {
            return bytes2.length > 0 ? this.coded.sign(bytes2, bytes) : "";
        }
        try {
            byte[] bArr2 = new byte[encrypted.length + bytes2.length];
            System.arraycopy(bytes2, 0, bArr2, 0, bytes2.length);
            System.arraycopy(encrypted, 0, bArr2, bytes2.length, encrypted.length);
            return this.coded.sign(bArr2, bytes);
        } catch (Exception unused) {
            return "";
        }
    }

}
