package com.app.wechat.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class QueryFileListDto implements Serializable {

    @ApiModelProperty(value = "文件类型(1:微信,2:抖音,3:其它)",example ="1")
    private Integer fileType;

    @ApiModelProperty(value = "版本号", example = "8.0.0")
    @Pattern(regexp = "^$|^[.a-z\\d]{1,20}$", message = "请填写正确的版本号")
    private String version;

    @ApiModelProperty(value = "文件名称", example = "wechat")
    @Pattern(regexp = "^$|^[.a-z\\d]{3,64}$", message = "请填写正确的文件名")
    private String fileName;

    @ApiModelProperty(value = "是否上架",example = "1:上架,2:下架")
    private Integer fileOnline;

    @ApiModelProperty(value = "上线开始时间", example = "2022-09-01 00:00:00")
    private String startTime;

    @ApiModelProperty(value = "上线结束时间",example = "2022-09-01 23:59:59")
    private String endTime;
}
