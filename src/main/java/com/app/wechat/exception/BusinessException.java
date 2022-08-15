package com.app.wechat.exception;
import com.app.wechat.domain.enums.RestCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author admin
 * @Description 业务异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 3160241586346324994L;
    protected Integer code;
    protected String message;
    protected Object data;
    protected String enumName;

    public BusinessException() {
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(RestCodeEnum restCodeEnum) {
        this.message = restCodeEnum.message();
        this.code = restCodeEnum.code();
        this.enumName = restCodeEnum.name();
    }

    public BusinessException(RestCodeEnum restCodeEnum, Object data) {
        this.message = restCodeEnum.message();
        this.code = restCodeEnum.code();
        this.enumName = restCodeEnum.name();
        this.data = data;
    }

    public BusinessException(RestCodeEnum restCodeEnum, String message) {
        this.message = message;
        this.code = restCodeEnum.code();
        this.enumName = restCodeEnum.name();
    }

    public BusinessException(int code, String message, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public BusinessException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.message = msgFormat;
    }

    public BusinessException(RestCodeEnum restCodeEnum, Object... args) {
        super(String.format(restCodeEnum.message(), args));
        this.code = restCodeEnum.code();
        this.enumName = restCodeEnum.name();
    }
}
