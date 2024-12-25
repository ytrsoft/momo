package com.ytrsoft;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;

import java.io.File;

public final class FileSupport {

    private FileSupport() {
        throw new UnsupportedOperationException();
    }

    public static File get(String name) {
        ClassPathResource resource = new ClassPathResource(name);
        return FileUtil.file(resource.getFile());
    }

}
