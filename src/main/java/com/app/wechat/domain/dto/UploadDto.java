package com.app.wechat.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UploadDto implements Serializable {
    private String id;
    private String fileName;
    private String filePath;
    private String operator;
    private String sign;
    private String memo;
}
