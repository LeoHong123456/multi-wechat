package com.app.wechat.domain.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@ApiModel
@Data
@AllArgsConstructor
public class LoginVo implements Serializable {
    @ApiModelProperty(name ="username" ,notes = "会员名")
    private String username;

    @ApiModelProperty(name ="sessionId" ,notes = "会话ID")
    private String sessionId;
}
