package com.ytrsoft.momo;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class RequestEncoder {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private String mAesKey = ENCUtils.random(12);

    public RequestEncoder() {
    }

    public String getAesKey() {
        return this.mAesKey;
    }

    public String getAesKeyEncoded() {
        try {
            String var1 = Base64.encode(ENCUtils.RSAEncode(this.mAesKey.getBytes()));
            return var1;
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public String getZippedJson(String var1) {
        try {
            var1 = ENCUtils.getInstance().encrypt(Base64.encode(var1.getBytes()), this.mAesKey);
            return var1;
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }
}
