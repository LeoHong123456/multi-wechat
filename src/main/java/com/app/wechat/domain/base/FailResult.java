package com.app.wechat.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
public class FailResult implements Serializable {
    private static final long serialVersionUID = -5240069820604311741L;

    @JsonIgnore
    private String enumType;

    @JsonIgnore
    private Object exception;

    private Integer code;
    private Object message;

    public FailResult(String enumType, String exception) {
        this.enumType = enumType;
        this.exception = exception;
    }

    public FailResult(Integer code, Object message, Object exception) {
        this.code = code;
        this.message = message;
        this.exception = exception;
    }

    public FailResult(Integer code, Object message) {
        this.code = code;
        this.message = message;
    }

    public FailResult(Integer code, Object message, String enumType) {
        this.code = code;
        this.message = message;
        this.enumType = enumType;
    }

    public static FailResult failure(String enumType, String exception) {
        return new FailResult(enumType, exception);
    }
}
