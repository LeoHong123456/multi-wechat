package com.app.wechat.controller;

import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.vo.FileVo;
import com.app.wechat.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class FileController {
    @Autowired
    private IFileService fileService;

    @GetMapping(value = "/list")
    public String upload() throws Exception{
        return "admin/list";
    }

    @GetMapping(value = "/wechatList")
    public Result<List<FileVo>> wechatList() throws Exception{
        return fileService.wechatList();
    }
}
