package com.app.wechat.controller.admin;

import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.ChangPwdDto;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.LoginOutDto;
import com.app.wechat.domain.vo.VarCodeVo;
import com.app.wechat.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@Slf4j
@Validated
@RestController
@Api(tags = "后台接口")
@RequestMapping("/admin")
public class UserController {
    @Resource
    private IUserService userService;

    @ApiOperation(value = "后台登录(V)", notes = "后台登录")
    @ApiParam(name = "LoginDto", value = "后台登录参数实体", required = true)
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> login(LoginDto userDto) throws Exception{
        return userService.login(userDto);
    }

    @ApiOperation(value = "后台登出(V)", notes = "后台登出")
    @ApiParam(name = "LoginDto", value = "后台登出参数实体", required = true)
    @PostMapping(value = "/loginOut", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> login(LoginOutDto loginOutDto) throws Exception{
        return userService.loginOut(loginOutDto);
    }

    @ApiOperation(value = "修改密码(V)", notes = "修改密码")
    @ApiParam(name = "ChangPwdDto", value = "修改密码参数实体", required = true)
    @PostMapping(value = "/changPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> changPassword(ChangPwdDto changPwdDto) throws Exception{
        return userService.changPassword(changPwdDto);
    }


    @ApiOperation(value = "获取验证码(V)", notes = "获取验证码")
    @GetMapping(value = "/getVarCode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<VarCodeVo> getVarCode() throws Exception{
        return userService.getVarCode();
    }
}
