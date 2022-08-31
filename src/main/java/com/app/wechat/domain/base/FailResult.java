package com.app.wechat.domain.base;

import com.app.wechat.domain.enums.RestCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class FailResult<T> implements Serializable {
    @JsonIgnore
    private Object exception;
    private Integer code;
    private String message;
    private T data;

    public FailResult(Object exception, Integer code, String message, T data) {
        this.exception = exception;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public FailResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public FailResult(RestCodeEnum restCodeEnum) {
        this.code = restCodeEnum.code();
        this.message = restCodeEnum.message();
    }

    public FailResult(Integer code, String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static FailResult failure(Integer code ,String message){
        return new FailResult(code, message);
    }

    public static <T> FailResult failure(Integer code, String message, T data){
        return new FailResult(code, message, data);
    }
}
