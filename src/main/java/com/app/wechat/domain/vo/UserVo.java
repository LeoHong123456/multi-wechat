package com.app.wechat.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private String username;
    private String password;
    private String sessionId;
}
