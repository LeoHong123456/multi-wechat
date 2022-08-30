package com.app.wechat.domain.base;
import com.app.wechat.domain.enums.RestCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private static final long serialVersionUID = -5240069820604311741L;

    private Integer code;

    private String msg;

    private Integer count;
    private T data;
    @JsonIgnore
    private String enumName;

    public PageResult(RestCodeEnum PageResultCode, T data) {
        this.code = PageResultCode.code();
        this.msg = PageResultCode.message();
        this.enumName = PageResultCode.name();
        this.data = data;
    }

    public PageResult(RestCodeEnum PageResultCode) {
        this.code = PageResultCode.code();
        this.msg = PageResultCode.message();
        this.enumName = PageResultCode.name();
    }

    public PageResult(Integer code, String message, Integer count, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
        this.count = count;
    }

    public PageResult(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public static <T> PageResult<T> success() {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setCode(RestCodeEnum.GLOBAL_SUCCESS.code());
        PageResult.setMsg(RestCodeEnum.GLOBAL_SUCCESS.message());
        PageResult.setEnumName(RestCodeEnum.GLOBAL_SUCCESS.name());
        return PageResult;
    }

    public static <T> PageResult<T> success(T data) {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setCode(RestCodeEnum.GLOBAL_SUCCESS.code());
        PageResult.setMsg(RestCodeEnum.GLOBAL_SUCCESS.message());
        PageResult.setEnumName(RestCodeEnum.GLOBAL_SUCCESS.name());
        PageResult.setData(data);
        return PageResult;
    }

    public static <T> PageResult<T> success(RestCodeEnum PageResultCode) {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setMsg(PageResultCode.message());
        PageResult.setCode(PageResultCode.code());
        PageResult.setEnumName(PageResultCode.name());
        return PageResult;
    }

    public static <T> PageResult<T> success(RestCodeEnum PageResultCode, T data) {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setMsg(PageResultCode.message());
        PageResult.setCode(PageResultCode.code());
        PageResult.setData(data);
        return PageResult;
    }

    public static <T> PageResult<T> success(Integer code, String msg, Integer count, T data) {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setMsg(msg);
        PageResult.setCode(code);
        PageResult.setCount(count);
        PageResult.setData(data);
        return PageResult;
    }

    public static <T> PageResult<T> failure() {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setMsg(RestCodeEnum.GLOBAL_FAIL.message());
        PageResult.setCode(RestCodeEnum.GLOBAL_FAIL.code());
        PageResult.setEnumName(RestCodeEnum.GLOBAL_FAIL.name());
        return PageResult;
    }

    public static <T> PageResult<T> failure(RestCodeEnum PageResultCode, T data) {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setMsg(PageResultCode.message());
        PageResult.setCode(PageResultCode.code());
        PageResult.setEnumName(PageResultCode.name());
        PageResult.setData(data);
        return PageResult;
    }

    public static <T> PageResult<T> failure(Integer code, String message) {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setMsg(message);
        PageResult.setCode(code);
        return PageResult;
    }

    public static <T> PageResult<T> failure(RestCodeEnum PageResultCode) {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setMsg(PageResultCode.message());
        PageResult.setCode(PageResultCode.code());
        PageResult.setEnumName(PageResultCode.name());
        return PageResult;
    }

    public static <T> PageResult<T> failure(int code, String message, T data) {
        PageResult<T> PageResult = new PageResult<>();
        PageResult.setMsg(message);
        PageResult.setCode(code);
        PageResult.setData(data);
        return PageResult;
    }
}
