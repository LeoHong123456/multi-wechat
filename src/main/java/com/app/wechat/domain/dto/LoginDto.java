package com.app.wechat.domain.dto;
import lombok.Data;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    private String username;
    private String password;
    private String varcode;
}
