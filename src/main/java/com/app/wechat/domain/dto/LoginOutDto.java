package com.app.wechat.domain.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class LoginOutDto implements Serializable {
    private String username;
    private String sessionId;
}
