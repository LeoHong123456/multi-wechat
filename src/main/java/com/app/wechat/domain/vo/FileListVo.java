package com.app.wechat.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Administrator
 */
@Data
public class FileListVo implements Serializable {
    @ApiModelProperty(name="fileId", notes="文件ID", dataType="string")
    private String fileId;

    @ApiModelProperty(name="fileName", notes="文件名", dataType="string")
    private String fileName;

    @ApiModelProperty(name="fileType", notes="文件类型(1:微信2:抖音,3:其它)")
    private Integer fileType;

    @ApiModelProperty(name = "memo", notes = "文件描述", dataType = "string")
    private String memo;

    @ApiModelProperty(name = "version", notes = "版本号")
    private String version;

    @ApiModelProperty(name = "price", notes = "价格")
    private BigDecimal price;

    @ApiModelProperty(name = "price", notes = "促销价格")
    private BigDecimal promotionPrice;
}
