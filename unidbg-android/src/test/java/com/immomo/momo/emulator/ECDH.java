package com.immomo.momo.emulator;

import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.immomo.momo.enc.Base64;
import com.immomo.momo.enc.Coded;

import java.util.Arrays;

public final class ECDH {

    private static final String KEY = "Iu0WKHFy";

    private ECDH() {
        throw new UnsupportedOperationException();
    }

    public static class Result {

        private String key;
        private String ck;
        private String kv;

        public void setCk(String ck) {
            this.ck = ck;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setKv(String kv) {
            this.kv = kv;
        }

        public String getCk() {
            return ck;
        }

        public String getKey() {
            return key;
        }

        public String getKv() {
            return kv;
        }
    }

    public static Result gen() {
        Momo momo = new Momo();
        SoCoded so = momo.loadCoded();
        byte[] PK = so.getServerPK(2, 1);
        ByteArray SK_ARR = so.newByteArray(24);
        ByteArray CK_ARR = so.newByteArray(49);
        so.clientSecretGen(SK_ARR, PK, PK.length, CK_ARR);
        byte[] SK = SK_ARR.getValue();
        byte[] CK = CK_ARR.getValue();
        byte[] encoded = Coded.encode(CK, KEY.getBytes());
        String BASE64_CK = Base64.encode(encoded);
        String KEY = Base64.encode(SK);
        String KV = Coded.md5(BASE64_CK).substring(0, 8);
        Result result = new Result();
        result.setCk(BASE64_CK);
        result.setKey(KEY);
        result.setKv(KV);
        momo.destroy();
        return result;
    }

}
