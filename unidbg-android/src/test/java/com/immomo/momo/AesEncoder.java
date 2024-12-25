package com.immomo.momo;

import com.immomo.momo.base.*;

import java.util.Map;

public class AesEncoder {
    public static void main(String[] args) throws Exception {
        Momo momo = new Momo();
        Coded coded = momo.loadCoded();
        ApiSecurity security = new ApiSecurity(coded);
        String salt = Mock.genSalt();
        Map<String, String> params = Mock.genRequestParams();
        Map<String, String> header = Mock.genHeader();
        String body = JSON.stringify(params);
        byte[] encrypted = security.encrypted(body, salt);
        System.out.println("mzip:" + Base64.a(encrypted));
        String x_sign = security.x_sign(encrypted, header, salt);
        System.out.println("x-sign:" + x_sign);
        momo.destroy();
    }
}
