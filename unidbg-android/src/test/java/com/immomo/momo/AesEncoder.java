package com.immomo.momo;

import com.immomo.momo.base.*;

import java.util.Map;

public class AesEncoder {
    public static void main(String[] args) throws Exception {
        Momo momo = new Momo();
        Coded coded = momo.loadCoded();
        ApiSecurity security = new ApiSecurity(coded);
        Map<String, String> params = Mock.genRequestParams();
        Map<String, String> header = Mock.genHeader();
        String body = JSON.stringify(params);
        byte[] encrypted = security.encrypted(body);
        System.out.println("mzip:" + Base64.a(encrypted));
        String x_sign = security.x_sign(encrypted, header);
        System.out.println("x-sign:" + x_sign);
        momo.destroy();
    }
}
