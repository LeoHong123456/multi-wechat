package com.app.wechat.domain.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
/**
 * @author Administrator
 */
@Data
@ApiModel
public class RegisterDto implements Serializable {
    @ApiModelProperty(name = "username", notes = "会员名",dataType = "string")
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^[a-z0-9@]{4,24}$", message = "用户名必须是4~24位数字或字母")
    private String username;

    @ApiModelProperty(name = "pwd", notes = "会员名",dataType = "string")
    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "密码必须是6~12位数字或字母")
    private String pwd;

    @ApiModelProperty(name = "rpwd", notes = "重复密码",dataType = "string")
    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$", message = "重复密码必须是6~12位数字或字母")
    private String rpwd;

    @ApiModelProperty(name = "email", notes = "邮箱",dataType = "string")
    @NotNull(message = "邮箱不能为空")
    @Email(message = "请填写正确的邮箱")
    private String email;

    @ApiModelProperty(name = "varCode", notes = "验证码",dataType = "string")
    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "^[0-9]{4}$", message = "验证码错误")
    private String varCode;

    @ApiModelProperty(name = "varCodeId", notes = "验证码ID",dataType = "string")
    @NotNull(message = "验证码ID不能为空")
    private String varCodeId;

    @ApiModelProperty(name = "ip", notes = "会员IP", hidden = true)
    private String ip;

    @ApiModelProperty(name = "ip", notes = "会员地址", hidden = true)
    private String ipAddr;
}
