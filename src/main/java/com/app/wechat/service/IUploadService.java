package com.app.wechat.service;

import com.app.wechat.domain.dto.UploadDto;

public interface IUploadService {
    /**
     * 存储文件信息
     * @param uploadDto
     */
    void saveFile(UploadDto uploadDto);
}
