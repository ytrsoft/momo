package com.ytrsoft.util;

import cn.hutool.core.io.resource.ClassPathResource;
import com.ytrsoft.core.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FileSupport {

    private FileSupport() {
        throw new UnsupportedOperationException();
    }

    private static List<File> filter(String suffix) {
        List<File> filters = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("");
        File root = resource.getFile();
        File[] files = root.listFiles(file -> file.getName().endsWith(suffix));
        if (files != null) {
            filters.addAll(Arrays.asList(files));
        }
        return filters;
    };

    private static List<File> getSoFiles() {
        return filter(".so");
    }

    private static File getApkFile() {
        return filter(".apk").get(0);
    }

    public static Resource getResource() {
        Resource resource = new Resource();
        resource.setApk(getApkFile());
        resource.setSo(getSoFiles());
        return resource;
    }

}
