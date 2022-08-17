package com.app.wechat.domain.enums;

/**
 * <b>响应全局消息<b/>
 *
 * @author admin
 * @ClassName: RestCodeConstants
 */
public enum RestCodeEnum {
    /**
     * 响应消息
     */
    GLOBAL_SUCCESS(100000, "success"),

    /**
    * 登录成功
    */
    LOGIN_SUCCESS(100001,"登录成功"),

    /**
     * 验证码错误
     */
    VAR_CODE_ERROR(900001,"验证码错误"),

    /**
     * 系统异常,请稍后再试!
     */
    GLOBAL_FAIL(900000, "error"),


    FAIL_TO_NO_HANDLER_FOUND(900404, "非法请求"),


    FAIL_TO_PARAM_ERROR(100001, "参数异常"),

    FAIL_TO_LOGIN_ERROR(100002, "用户名或密码错误");


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
