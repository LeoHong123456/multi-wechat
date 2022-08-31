package com.app.wechat.controller;

import com.app.wechat.contants.BaseConstant;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.bo.ReceiveBo;
import com.app.wechat.domain.dto.ReceiveDto;
import com.app.wechat.domain.dto.ReceiveMultiDto;
import com.app.wechat.domain.enums.RestCodeEnum;
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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@Validated
@Api(tags = "文件上传下载")
@RestController
public class SysFileController {

    @Autowired
    private ISysFileService fileService;

    @ApiOperation(value = "单文件上传(V)", notes = "单文件上传")
    @ApiParam(name = "ReceiveDto", value = "单文件上传参数实体", required = true)
    @PostMapping(value="/receive", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> receiveFile(@Valid @ModelAttribute ReceiveDto receiveDto) throws Exception{
        MultipartFile file = receiveDto.getFile();
        String fileDirId = StringsUtil.getFileDirId();
        File uploadFileSaveDir = new File(BaseConstant.PROFILE.concat(fileDirId).concat(File.separator));
        if(!uploadFileSaveDir.exists()){
            uploadFileSaveDir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String filePath = uploadFileSaveDir.getAbsolutePath() + File.separator + fileName;
        File uploadFile = new File(filePath);
        file.transferTo(uploadFile);
        log.info("文件上传路径：{}",uploadFileSaveDir.getAbsolutePath());
        ReceiveBo receiveBo = new ReceiveBo();
        BeanUtils.copyProperties(receiveDto, receiveBo);
        receiveBo.setFileId(StringsUtil.incrementId());
        receiveBo.setFileName(fileName);
        receiveBo.setFilePath(filePath);
        fileService.saveFile(receiveBo);
        return Result.success(RestCodeEnum.FILE_UPLOAD_SUCCESS);
    }
    @ApiOperation(value = "双文件上传(V)", notes = "双文件上传")
    @ApiParam(name = "ReceiveDto", value = "双文件上传参数实体", required = true)
    @PostMapping(value="/receiveMulti")
    public String receiveFile(@RequestParam("file1")MultipartFile file1, @RequestParam("file2")MultipartFile file2, @RequestParam() String filesMark, HttpServletRequest request) throws Exception{
        Resource applicationProperties = new ClassPathResource("application.properties");
        String uploadFileSavePath = applicationProperties.getFile().getParentFile().getAbsolutePath() + File.separator + "static/upload";
        File uploadFileSaveDir = new File(uploadFileSavePath);
        if(!uploadFileSaveDir.exists()){
            uploadFileSaveDir.mkdirs();
        }
        if(file1.isEmpty()){
            return "file1没有选中任何文件！";
        }
        String filename = file1.getOriginalFilename();
        File uploadFile = new File(uploadFileSaveDir.getAbsolutePath() + File.separator + filename);
        System.out.println("文件上传到：" + uploadFile.getAbsolutePath());
        file1.transferTo(uploadFile);
        if(file2.isEmpty()){
            return "file2没有选中任何文件！";
        }
        filename = file2.getOriginalFilename();
        uploadFile = new File(uploadFileSaveDir.getAbsolutePath() + File.separator + filename);
        System.out.println("文件上传到：" + uploadFile.getAbsolutePath());
        file2.transferTo(uploadFile);
        return "File name, " + filesMark + " uploaded success!";
    }

    @ApiOperation(value = "多文件上传(V)", notes = "多文件上传")
    @ApiParam(name = "MultiReceiveDto", value = "双文件上传参数实体", required = true)
    @PostMapping(value="/receiveMultis")
    public Result<Object> receiveFile(@Valid @ModelAttribute ReceiveMultiDto receiveMultiDto) throws Exception{
        MultipartFile[] files = receiveMultiDto.getFiles();
        String fileDirId = StringsUtil.getFileDirId();
        File uploadFileSaveDir = new File(BaseConstant.PROFILE.concat(fileDirId).concat(File.separator));
        System.out.println("上传文件的存放目录："+uploadFileSaveDir.getAbsolutePath());
        if(!uploadFileSaveDir.exists()){
            uploadFileSaveDir.mkdirs();
        }
        List<ReceiveBo> receives = new ArrayList<>();
        for(int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if(file == null){
                continue;
            }
            String filename = file.getOriginalFilename();
            File uploadFile = new File(uploadFileSaveDir.getAbsolutePath() + File.separator + filename);
            System.out.println("文件上传到：" + uploadFile.getAbsolutePath());
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


    @ResponseBody
    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public String fileDownload(HttpServletResponse response)throws Exception{
        String fileName = "udid.mobileconfig";
        String filePath = BaseConstant.PROFILE + "udidconfig" + File.separator + fileName;
        File downloadFile = new File(filePath);
        System.out.println("下载文件的完整路径："+downloadFile.getAbsolutePath());
        if(!downloadFile.exists()){
            System.out.println("要下载的文件不存在："+downloadFile.getAbsolutePath());
            return "File not exists, download fail!";
        }else {
            response.setContentType("application/force-download");
            InputStream in = new FileInputStream(downloadFile);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentLength(in.available());
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while((len = in.read(b))!=-1){
                out.write(b, 0, len);
            }
            out.flush();
            out.close();
            in.close();
            return "Download success!";
        }
    }
}
