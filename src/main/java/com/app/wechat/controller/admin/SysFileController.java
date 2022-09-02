package com.app.wechat.controller.admin;

import com.app.wechat.contants.BaseConstant;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.bo.ReceiveBo;
import com.app.wechat.domain.dto.FileListDto;
import com.app.wechat.domain.dto.QueryFileListDto;
import com.app.wechat.domain.dto.ReceiveDto;
import com.app.wechat.domain.dto.ReceiveMultiDto;
import com.app.wechat.domain.entity.SysFile;
import com.app.wechat.domain.enums.RestCodeEnum;
import com.app.wechat.domain.vo.FileListVo;
import com.app.wechat.domain.vo.QueryFileListVo;
import com.app.wechat.service.ISysFileService;
import com.app.wechat.utils.StringsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 */
@Slf4j
@Validated
@Api(tags = "后台接口")
@RestController
public class SysFileController {
    @Autowired
    private ISysFileService fileService;

    @ApiOperation(value = "单文件上传(V)", notes = "单文件上传")
    @ApiParam(name = "ReceiveDto", value = "单文件上传参数实体", required = true)
    @PostMapping(value = "/admin/receive", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> receiveFile(@Valid @ModelAttribute ReceiveDto receiveDto) throws Exception {
        MultipartFile file = receiveDto.getFile();
        String fileDirId = StringsUtil.getFileDirId();
        File uploadFileSaveDir = new File(BaseConstant.PROFILE.concat(fileDirId).concat(File.separator));
        if (!uploadFileSaveDir.exists()) {
            uploadFileSaveDir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String filePath = uploadFileSaveDir.getAbsolutePath() + File.separator + fileName;
        File uploadFile = new File(filePath);
        file.transferTo(uploadFile);
        log.info("文件上传路径：{}", uploadFileSaveDir.getAbsolutePath());
        ReceiveBo receiveBo = new ReceiveBo();
        BeanUtils.copyProperties(receiveDto, receiveBo);
        receiveBo.setFileId(StringsUtil.incrementId());
        receiveBo.setFileName(fileName);
        receiveBo.setFilePath(filePath);
        fileService.saveFile(receiveBo);
        return Result.success(RestCodeEnum.FILE_UPLOAD_SUCCESS);
    }

    @ApiOperation(value = "多文件上传(V)", notes = "多文件上传")
    @ApiParam(name = "MultiReceiveDto", value = "双文件上传参数实体", required = true)
    @PostMapping(value = "/admin/receiveMultis", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> receiveFile(@Valid @ModelAttribute ReceiveMultiDto receiveMultiDto) throws Exception {
        MultipartFile[] files = receiveMultiDto.getFiles();
        String fileDirId = StringsUtil.getFileDirId();
        File uploadFileSaveDir = new File(BaseConstant.PROFILE.concat(fileDirId).concat(File.separator));
        log.info("上传文件的存放目录：{}",uploadFileSaveDir.getAbsolutePath());
        if (!uploadFileSaveDir.exists()) {
            uploadFileSaveDir.mkdirs();
        }
        List<ReceiveBo> receives = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (file == null) {
                continue;
            }
            String filename = file.getOriginalFilename();
            File uploadFile = new File(uploadFileSaveDir.getAbsolutePath() + File.separator + filename);
            log.info("文件上传到：{}",uploadFileSaveDir.getAbsolutePath());
            file.transferTo(uploadFile);
            ReceiveBo bo = new ReceiveBo();
            bo.setFileOnline(Integer.parseInt(receiveMultiDto.getFileOnline().split(",")[i]));
            bo.setFileName(filename);
            bo.setFilePath(uploadFile.getAbsolutePath());
            bo.setSign(receiveMultiDto.getSign().split(",")[i]);
            bo.setMemo(receiveMultiDto.getMemo().split(",")[i]);
            bo.setOperator(receiveMultiDto.getOperator());
            bo.setFileId(StringsUtil.getFileDirId());
            receives.add(bo);
        }
        fileService.saveBatchFile(receives);
        return Result.success();
    }
}
