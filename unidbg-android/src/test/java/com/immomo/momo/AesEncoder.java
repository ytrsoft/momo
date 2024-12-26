package com.immomo.momo;

import com.immomo.momo.base.*;

public class AesEncoder {
    public static void main(String[] args) throws Exception {
        Momo momo = new Momo();
        Coded coded = momo.loadCoded();
        FakeRequest request = new FakeRequest();
        ApiSecurity security = new ApiSecurity(coded);
        String body = JSON.stringify(request.getBody());
        byte[] encrypted = security.encrypted(body);
        System.out.println("mzip:" + TheBase64.encode(encrypted));
        String x_sign = security.x_sign(encrypted, request.getHeaders());
        System.out.println("x-sign:" + x_sign);
        momo.destroy();
    }
}
