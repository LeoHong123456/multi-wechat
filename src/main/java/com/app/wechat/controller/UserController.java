package com.app.wechat.controller;

import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.ChangPwdDto;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.LoginOutDto;
import com.app.wechat.domain.vo.VarCodeVo;
import com.app.wechat.service.IUserService;
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
@RequestMapping("/admin")
public class UserController {
    @Resource
    private IUserService userService;

    @ResponseBody
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> login(LoginDto userDto) throws Exception{
        return userService.login(userDto);
    }

    @ResponseBody
    @PostMapping(value = "/loginOut", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> login(LoginOutDto loginOutDto) throws Exception{
        return userService.loginOut(loginOutDto);
    }

    @ResponseBody
    @PostMapping(value = "/changPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> changPassword(ChangPwdDto changPwdDto) throws Exception{
        return userService.changPassword(changPwdDto);
    }


    @ResponseBody
    @GetMapping(value = "/getVarCode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<VarCodeVo> getVarCode() throws Exception{
        return userService.getVarCode();
    }
}
