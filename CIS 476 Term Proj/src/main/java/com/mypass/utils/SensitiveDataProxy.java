package com.mypass.utils;

public class SensitiveDataProxy {
    private final String data;
    private boolean isMasked;

    public SensitiveDataProxy(String data) {
        this.data = data;
        this.isMasked = true;
    }

    public String getData() {
        return isMasked ? maskData() : data;
    }

    public void toggleMask() {
        isMasked = !isMasked;
    }

    private String maskData() {
        return data.replaceAll(".", "*");
    }

    public boolean isMasked() {
        return isMasked;
    }
}