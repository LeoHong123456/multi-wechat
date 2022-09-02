package com.app.wechat.service;

import com.app.wechat.domain.bo.ReceiveBo;
import com.app.wechat.domain.dto.FileListDto;
import com.app.wechat.domain.dto.QueryFileListDto;
import com.app.wechat.domain.entity.SysFile;
import com.app.wechat.domain.vo.FileInfoVo;
import com.app.wechat.domain.vo.FileListVo;
import com.app.wechat.domain.vo.QueryFileListVo;
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
    void saveFile(ReceiveBo receiveBo) throws Exception;

    /**
     * 保存多个文件信息
     * @param receives
     */
    void saveBatchFile(List<ReceiveBo> receives) throws Exception;

    /**
     * 查询文件列表
     * @param queryFileListDto
     * @return
     * @throws Exception
     */
    List<QueryFileListVo> queryFileList(QueryFileListDto queryFileListDto) throws Exception;

    /**
     * 前端获取文件列表
     * @param fileListDto
     * @return
     * @throws Exception
     */
    List<FileListVo> getFileList(FileListDto fileListDto) throws Exception;

    /**
     * 获取文件详情
     * @param fileId
     * @return
     */
    FileInfoVo getFileInfo(String fileId) throws Exception;
}
