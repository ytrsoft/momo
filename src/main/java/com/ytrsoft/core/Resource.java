package com.ytrsoft.core;

import java.io.File;
import java.util.List;

public class Resource {

    private File apk;

    private List<File> so;

    public File getApk() {
        return apk;
    }

    public void setApk(File apk) {
        this.apk = apk;
    }

    public List<File> getSo() {
        return so;
    }

    public void setSo(List<File> so) {
        this.so = so;
    }

}
