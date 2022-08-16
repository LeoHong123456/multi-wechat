package com.app.wechat.domain.base;

import com.app.wechat.domain.enums.RestCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 * @Description 全局响应消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements java.io.Serializable {
    private static final long serialVersionUID = -5240069820604311741L;

    private Integer code;

    private Object message;

    private T data;
    @JsonIgnore
    private String enumName;

    public Result(RestCodeEnum resultCode, T data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.enumName = resultCode.name();
        this.data = data;
    }

    public Result(RestCodeEnum resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.enumName = resultCode.name();
    }

    public Result(Integer code, T message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, T message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(RestCodeEnum.GLOBAL_SUCCESS.code());
        result.setMessage(RestCodeEnum.GLOBAL_SUCCESS.message());
        result.setEnumName(RestCodeEnum.GLOBAL_SUCCESS.name());
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(RestCodeEnum.GLOBAL_SUCCESS.code());
        result.setMessage(RestCodeEnum.GLOBAL_SUCCESS.message());
        result.setEnumName(RestCodeEnum.GLOBAL_SUCCESS.name());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(RestCodeEnum resultCode) {
        Result<T> result = new Result<>();
        result.setMessage(resultCode.message());
        result.setCode(resultCode.code());
        result.setEnumName(resultCode.name());
        return result;
    }

    public static <T> Result<T> success(RestCodeEnum resultCode, T data) {
        Result<T> result = new Result<>();
        result.setMessage(resultCode.message());
        result.setCode(resultCode.code());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failure() {
        Result<T> result = new Result<>();
        result.setMessage(RestCodeEnum.GLOBAL_FAIL.message());
        result.setCode(RestCodeEnum.GLOBAL_FAIL.code());
        result.setEnumName(RestCodeEnum.GLOBAL_FAIL.name());
        return result;
    }

    public static <T> Result<T> failure(RestCodeEnum resultCode, T data) {
        Result<T> result = new Result<>();
        result.setMessage(resultCode.message());
        result.setCode(resultCode.code());
        result.setEnumName(resultCode.name());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failure(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public static <T> Result<T> failure(RestCodeEnum resultCode) {
        Result<T> result = new Result<>();
        result.setMessage(resultCode.message());
        result.setCode(resultCode.code());
        result.setEnumName(resultCode.name());
        return result;
    }

    public static <T> Result<T> failure(int code, Object message, T data) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(code);
        result.setData(data);
        return result;
    }
}
