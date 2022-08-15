package com.app.wechat.controller;

import com.app.wechat.domain.dto.ChangPasswordDto;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.LoginOutDto;
import com.app.wechat.domain.base.Result;
import com.app.wechat.service.IUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/admin")
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> login(LoginDto userDto) throws Exception{
        return userService.login(userDto);
    }

    @PostMapping(value = "/loginOut", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> login(LoginOutDto loginOutDto) throws Exception{
        return userService.loginOut(loginOutDto);
    }


    @PostMapping(value = "changPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> changPassword(ChangPasswordDto changPasswordDto) throws Exception{
        return userService.changPassword(changPasswordDto);
    }
}
