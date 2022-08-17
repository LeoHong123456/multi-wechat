package com.app.wechat.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class LoginOutDto implements Serializable {
    @Pattern(regexp = "^[a-z0-9]{5,12}$", message = "会员名必须5~12位数字字母")
    @NotBlank(message = "会员名必须5~12位数字字母")
    private String username;

    private String sessionId;
}
