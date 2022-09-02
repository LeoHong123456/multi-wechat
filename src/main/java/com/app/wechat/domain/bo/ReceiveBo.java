package com.app.wechat.domain.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ReceiveBo implements Serializable {
    private String operator;
    private String sign;
    private String memo;
    private Integer fileOnline;
    private String fileId;
    private String fileName;
    private String filePath;
    private String fileType;
    private BigDecimal price;
    private BigDecimal promotionPrice;
}
