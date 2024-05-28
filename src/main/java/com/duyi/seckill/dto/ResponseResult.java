package com.duyi.seckill.dto;

/**
 * 请求响应的结果
 * 封装JSON数据
 */
public class ResponseResult<T> {

    private boolean success;
    private T data;
    private String message;

    public ResponseResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public ResponseResult() {
    }

    public ResponseResult(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
