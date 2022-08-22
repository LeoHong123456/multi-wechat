package com.app.wechat.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileVo implements Serializable {
    private String id;
    private String fileName;
    private String filePath;
    private String operator;
    private String sign;
    private String memo;
}
