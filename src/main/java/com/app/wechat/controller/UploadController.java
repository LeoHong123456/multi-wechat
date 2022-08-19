package com.app.wechat.controller;
import com.app.wechat.domain.base.Result;
import com.app.wechat.domain.enums.RestCodeEnum;
import lombok.extern.slf4j.Slf4j;
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
    @GetMapping(value = "/upload")
    public String upload() throws Exception{
        log.info(System.getProperty("user.dir"));
        return "upload";
    }
    @ResponseBody
    @PostMapping(value="/receive")
    public Result<Object> receiveFile(@RequestParam("file")MultipartFile file, @RequestParam("fileName") String fileName,
                                      @RequestParam("sign") String sign, String memo, HttpServletRequest request) throws Exception{
        if(file.isEmpty()){
            return Result.failure(RestCodeEnum.FILE_UPLOAD_IS_NULL);
        }
        Resource applicationProperties = new ClassPathResource("application.properties");
        String uploadFileSavePath = applicationProperties.getFile().getParentFile().getAbsolutePath() + File.separator + "static/upload";
        File uploadFileSaveDir = new File(uploadFileSavePath);
        log.info("fileName:{}, sign:{},memo:{}", fileName, sign, memo);
        if(!uploadFileSaveDir.exists()){
            uploadFileSaveDir.mkdirs();
        }
        String filename = file.getOriginalFilename();
        File uploadFile = new File(uploadFileSaveDir.getAbsolutePath() + File.separator + filename);
        log.info("文件上传到：{}",uploadFile.getAbsolutePath());
        file.transferTo(uploadFile);
        return Result.success(RestCodeEnum.FILE_UPLOAD_SUCCESS);
    }

    @ResponseBody
    @PostMapping(value="/receiveFileMulti1")
    public String receiveFile(@RequestParam("file1")MultipartFile file1, @RequestParam("file2")MultipartFile file2, @RequestParam() String filesMark, HttpServletRequest request) throws Exception{
        Resource applicationProperties = new ClassPathResource("application.properties");
        //然后通过取其父目录获得resources目录，设置上传文件的目录
        String uploadFileSavePath = applicationProperties.getFile().getParentFile().getAbsolutePath() + File.separator + "static/upload";
        File uploadFileSaveDir = new File(uploadFileSavePath);
        System.out.println("上传文件的存放目录："+uploadFileSaveDir.getAbsolutePath());
        if(!uploadFileSaveDir.exists()){
            // 递归生成文件夹
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
