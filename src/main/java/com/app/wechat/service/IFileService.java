package com.app.wechat.service;

import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.vo.FileVo;

import java.util.List;

public interface IFileService {
    Result<List<FileVo>> wechatList();
}
