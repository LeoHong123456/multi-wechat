package com.app.wechat.domain.dto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class ChangPasswordDto implements Serializable {
    @Pattern(regexp = "^[a-z0-9]{5,12}$", message = "会员名必须5~12位数字或字母")
    @NotBlank(message = "会员名必须5~12位数字或字母")
    private String username;

    @Pattern(regexp = "^[a-z0-9]{6,12}$", message = "密码必须6~12位数字或字母")
    @NotBlank(message = "密码必须6~12位数字或字母")
    private String oldPwd;

    @Pattern(regexp = "^[a-z0-9]{6,12}$", message = "密码必须6~12位数字或字母")
    @NotBlank(message = "密码必须6~12位数字或字母")
    private String password;

    private String sessionId;
}
