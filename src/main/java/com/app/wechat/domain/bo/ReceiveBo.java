package com.app.wechat.domain.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReceiveBo implements Serializable {
    private String operator;
    private String sign;
    private String memo;
    private Integer fileOnline;
    private String fileId;
    private String fileName;
    private String filePath;
}
