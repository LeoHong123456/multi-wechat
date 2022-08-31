package com.app.wechat.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class ReceiveMultiDto implements Serializable {

    @ApiModelProperty(value = "上传的文件", required = true, example = "上传文件，示例值[file1,file2,file3]")
    @NotNull(message = "文件不能为空")
    private MultipartFile[] files;

    @ApiModelProperty(value = "操作人", required = true, example = "admin")
    @NotNull(message = "操作人不能为空")
    @Pattern(regexp = "^[a-z0-9]{4,24}$", message = "请填写正确的操作人")
    private String operator;

    @ApiModelProperty(value = "签名", required = true,  dataType = "string",  example = "示例值[a,b,c]")
    @NotNull(message = "签名不能为空")
    @Pattern(regexp = "^[a-z0-9/,]{20,150}$", message = "请填写正确的签名")
    private String sign;

    @ApiModelProperty(value = "备注", required = true, example = "可以包含文字图片,示例值[a,b,c]")
    private String memo;

    @ApiModelProperty(value = "是否上线", required = true, dataType = "string", example = "1:上架,2:下架,示例值[1,2,3]")
    @NotNull(message = "是否上架不能为空")
    @Pattern(regexp = "^[1-2/,]{1,10}$", message = "请正确填写上架参数")
    private String fileOnline;
}
