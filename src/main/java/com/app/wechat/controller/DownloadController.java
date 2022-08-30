package com.app.wechat.controller;
import com.app.wechat.contants.BaseConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@Slf4j
@Controller
public class DownloadController {

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
