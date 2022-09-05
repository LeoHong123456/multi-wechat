package com.app.wechat.controller.web;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.RegisterDto;
import com.app.wechat.domain.vo.LoginVo;
import com.app.wechat.service.IMemberService;
import com.app.wechat.utils.RequestUtil;
import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;
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
@Validated
@RestController
public class MemberController {
    @Autowired
    private IMemberService memberService;

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
