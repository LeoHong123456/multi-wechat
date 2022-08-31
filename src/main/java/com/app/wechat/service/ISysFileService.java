package com.app.wechat.service;

import com.app.wechat.domain.bo.ReceiveBo;
import com.app.wechat.domain.entity.SysFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-08-31
 */
public interface ISysFileService extends IService<SysFile> {

    /**
     * 保存单个文件信息
     * @param receiveBo
     */
    void saveFile(ReceiveBo receiveBo);

    /**
     * 保存多个文件信息
     * @param receives
     */
    void saveBatchFile(List<ReceiveBo> receives);
}
