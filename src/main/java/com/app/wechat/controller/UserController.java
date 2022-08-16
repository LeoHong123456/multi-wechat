package com.app.wechat.controller;

import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.ChangPasswordDto;
import com.app.wechat.domain.dto.LoginDto;
import com.app.wechat.domain.dto.LoginOutDto;
import com.app.wechat.domain.vo.VarCodeVo;
import com.app.wechat.service.IUserService;
import com.app.wechat.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/admin")
public class UserController {
    @Resource
    private IUserService userService;

    @ResponseBody
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> login(LoginDto userDto) throws Exception{
        log.info(JSONUtil.bean2Json(userDto));
        return userService.login(userDto);
    }

    @ResponseBody
    @PostMapping(value = "/loginOut", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> login(LoginOutDto loginOutDto) throws Exception{
        return userService.loginOut(loginOutDto);
    }

    @ResponseBody
    @PostMapping(value = "/changPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> changPassword(ChangPasswordDto changPasswordDto) throws Exception{
        return userService.changPassword(changPasswordDto);
    }

    @ResponseBody
    @GetMapping(value = "/getVarCode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<VarCodeVo> getVarCode() throws Exception{
        return userService.getVarCode();
    }


    @RequestMapping(value = "/index")
    public String index(Model model) throws Exception{
        model.addAttribute("name","admin");
        return "login";
    }
}
