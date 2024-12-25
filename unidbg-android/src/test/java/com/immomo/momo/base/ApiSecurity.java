package com.immomo.momo.base;

public class ApiSecurity {

    private final Coded coded;

    public ApiSecurity(Coded coded) {
        this.coded = coded;
    }

    public String getMZip(String body, String salt) throws Exception {
        byte[] encrypted = coded.encrypt(body.getBytes(), salt.getBytes());
        return Base64.a(encrypted);
    }

}
