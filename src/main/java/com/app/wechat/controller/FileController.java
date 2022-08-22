package com.app.wechat.controller;

import com.app.wechat.domain.base.PageResult;
import com.app.wechat.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class FileController {
    @Autowired
    private IFileService fileService;

    @GetMapping(value = "/list")
    public String upload() throws Exception{
        return "list";
    }

    @ResponseBody
    @GetMapping(value = "/wechatList")
    public PageResult wechatList() throws Exception{
        return fileService.wechatList();
    }
}
