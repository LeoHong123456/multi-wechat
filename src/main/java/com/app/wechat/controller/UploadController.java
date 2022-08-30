package com.app.wechat.controller;
import com.app.wechat.contants.BaseConstant;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.dto.UploadDto;
import com.app.wechat.domain.enums.RestCodeEnum;
import com.app.wechat.service.IUploadService;
import com.app.wechat.utils.StringsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class UploadController {
    @Autowired
    private IUploadService uploadService;

    @GetMapping(value = "/upload")
    public String upload() throws Exception{
        return "upload";
    }

    @ResponseBody
    @PostMapping(value="/receive")
    public Result<Object> receiveFile(@RequestParam("file")MultipartFile file, @RequestParam("operator") String operator, @RequestParam("sign") String sign, String memo, HttpServletRequest request) throws Exception{
        log.info("operator:{}, sign:{}, memo:{}", operator, sign, memo);
        if(file.isEmpty()){
            return Result.failure(RestCodeEnum.FILE_UPLOAD_IS_NULL);
        }
        String fileDirId = StringsUtil.reportId();
        File uploadFileSaveDir = new File(BaseConstant.PROFILE.concat(fileDirId).concat(File.separator));
        if(!uploadFileSaveDir.exists()){
            uploadFileSaveDir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String filePath = uploadFileSaveDir.getAbsolutePath() + File.separator + fileName;
        File uploadFile = new File(filePath);
        file.transferTo(uploadFile);
        log.info("文件上传路径：{}",uploadFileSaveDir.getAbsolutePath());
        String incrementId = StringsUtil.incrementId();
        UploadDto uploadDto = new UploadDto(incrementId, fileName, filePath, operator, sign, memo);
        uploadService.saveFile(uploadDto);
        return Result.success(RestCodeEnum.FILE_UPLOAD_SUCCESS);
    }

    @ResponseBody
    @PostMapping(value="/receiveFileMulti1")
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

    @ResponseBody
    @PostMapping(value="/receiveFileMulti2")
    public String receiveFile(@RequestParam() MultipartFile[] files, @RequestParam() String filesMark, HttpServletRequest request) throws Exception{
        Resource applicationProperties = new ClassPathResource("application.properties");
        String uploadFileSavePath = applicationProperties.getFile().getParentFile().getAbsolutePath() + File.separator + "static/upload";
        File uploadFileSaveDir = new File(uploadFileSavePath);
        System.out.println("上传文件的存放目录："+uploadFileSaveDir.getAbsolutePath());
        if(!uploadFileSaveDir.exists()){
            uploadFileSaveDir.mkdirs();
        }
        for(int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if(file == null){
                continue;
            }
            String filename = file.getOriginalFilename();
            File uploadFile = new File(uploadFileSaveDir.getAbsolutePath() + File.separator + filename);
            System.out.println("文件上传到：" + uploadFile.getAbsolutePath());
            file.transferTo(uploadFile);
        }
        return "File name, " + filesMark + " uploaded success!";
    }
}

