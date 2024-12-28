package com.immomo.momo.fire;

import com.immomo.momo.base.TheBase64;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class ApiSecurity {

    private String sign(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return "";
        }
        byte[] sign = Coded.sign(bArr, bArr2);
        return TheBase64.encode(sign);
    }

    private byte[] encrypt(byte[] bArr, byte[] bArr2) {
        return Coded.encrypt(bArr, bArr2);
    }

    public byte[] getEncrypt(Map<String, String> reqParams, String apiKey) {
        JSONObject jSONObject = new JSONObject();
        for (String str2 : reqParams.keySet()) {
            jSONObject.put(str2, reqParams.get(str2));
        }
        String jSONObject2 = jSONObject.toString();
        byte[] bytes = jSONObject2.getBytes();
        byte[] bytes2 = apiKey.getBytes();
        return encrypt(bytes, bytes2);
    }

    public String getBase64(byte[] encrypt) {
        return Base64.getEncoder().encodeToString(encrypt);
    }

    public String getSign(byte[] encrypt, Map<String, String> header, String apiKey) {
        if (StringUtils.isEmpty(apiKey) || apiKey.length() < 8) {
            return null;
        }
        byte[] apiKeyBytes = apiKey.getBytes(StandardCharsets.UTF_8);
        boolean useUserAgent = Utilize.shouldUseAng(header);
        byte[] userAgentBytes = useUserAgent ? Utilize.getUserAgent().getBytes(StandardCharsets.UTF_8) : new byte[0];
        byte[] combinedBytes = new byte[encrypt.length + userAgentBytes.length];
        System.arraycopy(userAgentBytes, 0, combinedBytes, 0, userAgentBytes.length);
        System.arraycopy(encrypt, 0, combinedBytes, userAgentBytes.length, encrypt.length);
        return sign(combinedBytes, apiKeyBytes);
    }
}
