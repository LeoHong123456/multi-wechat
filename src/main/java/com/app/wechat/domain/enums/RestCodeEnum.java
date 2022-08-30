package com.app.wechat.domain.enums;

/**
 * <b>响应全局消息<b/>
 *
 * @author admin
 * @ClassName: RestCodeConstants
 */
public enum RestCodeEnum {
    /**
     * 全局成功
     */
    GLOBAL_SUCCESS(100000, "success"),
    /**
     * 登录成功
     */
    LOGIN_SUCCESS(100001,"登录成功"),
    /**
     * 文件上传成功
     */
    FILE_UPLOAD_SUCCESS(100002,"文件上传成功"),
    /**
     * 全局异常
     */
    GLOBAL_FAIL(900000, "error"),
    /**
     * 验证码错误
     */
    VAR_CODE_ERROR(900001,"验证码错误"),
    /**
     * 原密码错误
     */
    OLD_PASSWORD_ERROR(900002,"原密码错误"),
    /**
     * 非法请求
     */
    FAIL_TO_NO_HANDLER_FOUND(900404, "非法请求"),
    /**
     * 参数异常
     */
    FAIL_TO_PARAM_ERROR(900003, "参数异常"),
    /**
     * 用户名或密码错误
     */
    FAIL_TO_LOGIN_ERROR(900005, "用户名或密码错误"),
    /**
     * 请重新登录
     */
    FAIL_TO_LOGIN_OUT_ERROR(900006, "请重新登录"),
    /**
     * Redis连接异常
     */
    FAIL_TO_REDIS_ERROR(900007, "数据库连接异常"),
    /**
     * 文件不能为空
     */
    FILE_UPLOAD_IS_NULL(900008,"请选择要上传的文件");
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
