package com.immomo.momo.emulator;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.File;

public final class FileSupport {

    private FileSupport() {
        throw new UnsupportedOperationException();
    }

    public static File get(String fileName) {
        ClassPathResource resource = new ClassPathResource("");
        String path = resource.getAbsolutePath();
        return new File(path + fileName);
    };

    public static Resource getResource() {
        Resource resource = new Resource();
        resource.setApk(get("momo.apk"));
        resource.setLibmmcrypto(get("libmmcrypto.so"));
        resource.setLibmmssl(get("libmmssl.so"));
        resource.setLibcoded(get("libcoded.so"));
        resource.setLibcoded_jni(get("libcoded_jni.so"));
        return resource;
    }

}