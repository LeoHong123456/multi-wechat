package com.app.wechat.controller.web;

import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.FileListDto;
import com.app.wechat.domain.enums.RestCodeEnum;
import com.app.wechat.domain.vo.FileInfoVo;
import com.app.wechat.domain.vo.FileListVo;
import com.app.wechat.service.ISysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

@Slf4j
@Validated
@Api(tags = "前端接口")
@RestController
public class FileController {
    @Autowired
    private ISysFileService fileService;

    @ApiOperation(value="文件列表(V)", notes="前台文件列表")
    @PostMapping(value={"/getFileList"}, produces={"application/json"})
    public Result<List<FileListVo>> getFileList(@Valid @ModelAttribute FileListDto fileListDto) throws Exception {
        return Result.success(this.fileService.getFileList(fileListDto));
    }

    @ApiOperation(value="文件详情(V)", notes="文件详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "文件Id", dataType = "String", paramType = "path"),
    })
    @GetMapping(value={"/getFileInfo/{fileId}"}, produces={"application/json"})
    public Result<FileInfoVo> getFileInfo(@Valid @ModelAttribute @PathVariable String fileId) throws Exception {
        return Result.success(this.fileService.getFileInfo(fileId));
    }

    @ApiOperation(value = "描述文件(V)", notes = "描述文件")
    @GetMapping(value = "/mobileFile")
    public Result<Object> fileDownload(HttpServletResponse response) throws Exception {
        String fileName = "udid.mobileconfig";
        String filePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getPath();
        File downFile = new File(filePath);
        if (!downFile.exists()) {
            return Result.failure(RestCodeEnum.FAIL_TO_PARAM_ERROR);
        } else {
            response.setContentType("application/force-download");
            InputStream in = Files.newInputStream(downFile.toPath());
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentLength(in.available());
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
            out.close();
            in.close();
            return Result.success();
        }
    }
}
