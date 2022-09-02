package com.app.wechat.domain.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel
public class FileInfoVo implements Serializable {
    @ApiModelProperty(name="fileId", notes="文件ID", dataType="string")
    private String fileId;

    @ApiModelProperty(name = "memo", notes = "文件描述", dataType = "string")
    private String memo;

    @ApiModelProperty(name = "price", notes = "价格")
    private BigDecimal price;

    @ApiModelProperty(name = "price", notes = "促销价格")
    private BigDecimal promotionPrice;
}
