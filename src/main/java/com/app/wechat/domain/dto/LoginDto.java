package com.app.wechat.domain.dto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    @Pattern(regexp = "^[a-z0-9]{5,12}$", message = "会员名必须5~12位数字或字母")
    @NotBlank(message = "会员名必须5~12位数字或字母")
    private String username;

    @Pattern(regexp = "^[a-z0-9]{6,12}$", message = "密码必须6~12位数字或字母")
    @NotBlank(message = "密码必须6~12位数字或字母")
    private String password;

    @Pattern(regexp = "^[0-9]{4}$", message = "验证码不正确")
    @NotBlank(message = "验证码不正确")
    private String varCode;

    @NotBlank(message = "验证码ID错误")
    @NotNull(message = "验证码ID错误")
    private String varCodeId;
}
