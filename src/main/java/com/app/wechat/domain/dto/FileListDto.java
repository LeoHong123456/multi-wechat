package com.app.wechat.domain.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@ApiModel
public class FileListDto implements Serializable {
    @ApiModelProperty(value="搜索内容", example="8.0.0")
    @Pattern(regexp="^$|^[.a-z\\d]{1,20}$", message="搜索参数异常")
    private String content;
}
