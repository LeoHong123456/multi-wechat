package com.app.wechat.controller.web;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.RegisterDto;
import com.app.wechat.domain.vo.LoginVo;
import com.app.wechat.service.IMemberService;
import com.app.wechat.utils.RequestUtil;
import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Api(tags = "前端接口")
@Validated
@RestController
public class MemberController {
    @Autowired
    private IMemberService memberService;

    @ApiOperation(value = "会员登录(V)", notes = "会员登录")
    @ApiParam(name = "LoginDto", value = "会员登录参数实体", required = true)
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<LoginVo> login(@Valid @ModelAttribute LoginDto loginDto, HttpServletRequest request) throws Exception{
        String ip = RequestUtil.getIpAddr(request);
        QQWry qqwry = new QQWry();
        IPZone ipZone = qqwry.findIP(ip);
        String address = ipZone.getMainInfo().concat(ipZone.getSubInfo());
        loginDto.setIp(ip);
        loginDto.setIpAddr(address);
        return memberService.login(loginDto);
    }

    @ApiOperation(value = "会员注册(V)", notes = "会员注册")
    @ApiParam(name = "RegisterDto", value = "会员注册参数实体", required = true)
    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<LoginVo> login(@Valid @ModelAttribute RegisterDto registerDto, HttpServletRequest request) throws Exception{
        String ip = RequestUtil.getIpAddr(request);
        QQWry qqwry = new QQWry();
        IPZone ipZone = qqwry.findIP(ip);
        String address = ipZone.getMainInfo().concat(ipZone.getSubInfo());
        registerDto.setIp(ip);
        registerDto.setIpAddr(address);
        return memberService.register(registerDto);
    }



}
