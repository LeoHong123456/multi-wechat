package com.app.wechat.domain.enums;

/**
 * <b>响应全局消息<b/>
 *
 * @author admin
 * @ClassName: RestCodeConstants
 */
public enum RestCodeEnum {
    GLOBAL_SUCCESS(100000, "success"),
    LOGIN_SUCCESS(100001,"登录成功"),
    GLOBAL_FAIL(900000, "error"),
    VAR_CODE_ERROR(900001,"验证码错误"),
    OLD_PASSWORD_ERROR(900002,"原密码错误"),
    FAIL_TO_NO_HANDLER_FOUND(900404, "非法请求"),
    FAIL_TO_PARAM_ERROR(900003, "参数异常"),
    FAIL_TO_LOGIN_ERROR(900005, "用户名或密码错误"),
    FAIL_TO_LOGIN_OUT_ERROR(900006, "请重新登录");

    private String msg;
    private Integer code;

    RestCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String message() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
