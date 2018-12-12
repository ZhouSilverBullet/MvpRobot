package com.sdxxtop.mvprobot.model.bean;

public class RequestBean<T> {
    public int code;
    public String msg;
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public boolean getError() {
        return code != 200;
    }
}
