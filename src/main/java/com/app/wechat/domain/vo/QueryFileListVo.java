package com.app.wechat.domain.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel
public class QueryFileListVo implements Serializable {

    @ApiModelProperty(name = "id", notes = "序列号", dataType = "int")
    private Integer id;

    @ApiModelProperty(name = "fileId", notes = "文件id", dataType = "string")
    private String fileId;

    @ApiModelProperty(name = "fileName", notes = "文件名", dataType = "string")
    private String fileName;

    @ApiModelProperty(name = "filePath", notes = "文件路径", dataType = "string")
    private String filePath;

    @ApiModelProperty(name = "fileOnline", notes = "是否上架(1:已上架,2:已下架)", dataType = "int")
    private Integer fileOnline;

    @ApiModelProperty(name = "fileType", notes = "文件类型(1:微信，2:抖音,3:其它)", dataType = "int")
    private Integer fileType;

    @ApiModelProperty(name = "operator", notes = "操作人", dataType = "int")
    private String operator;

    @ApiModelProperty(name = "sign", notes = "文件签名", dataType = "string")
    private String sign;

    @ApiModelProperty(name = "memo", notes = "文件描述", dataType = "string")
    private String memo;

    @ApiModelProperty(name = "craeteTime", notes = "上传时间", dataType = "string")
    private LocalDateTime craeteTime;

    @ApiModelProperty(name = "updateTime", notes = "更新时间", dataType = "string")
    private LocalDateTime updateTime;
}
