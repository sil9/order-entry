package com.netcracker.selyutin.entity;

public class ExceptionInfo {

    private String url;

    private String message;

    public ExceptionInfo() {
    }

    public ExceptionInfo(String url) {
        this.url = url;
    }

    public ExceptionInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionInfo{" + "url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
