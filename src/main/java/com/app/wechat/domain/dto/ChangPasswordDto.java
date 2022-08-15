package com.app.wechat.domain.dto;
import lombok.Data;
import java.io.Serializable;

@Data
public class ChangPasswordDto implements Serializable {
    private String username;
    private String oldpwd;
    private String password;
}
