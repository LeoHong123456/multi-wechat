package com.app.wechat.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequestMapping(value = "/apple")
public class AppleCallBackController {

    @GetMapping(value = "/getUdid")
    public String getUdid(){
        return "udid";
    }

    @PostMapping(value = "/receive")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        InputStream is = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String buffer;
        while ((buffer = br.readLine()) != null) {
            sb.append(buffer);
        }
/*        if(!StringUtils.isBlank(sb)){
            String content = sb.substring(sb.toString().indexOf("<?xml"), sb.toString().indexOf("</plist>")+8);
            log.info("苹果回调：{}",content);

            XmlUtil xml = new XmlUtil(content);
            String text = xml.getSelectNodes("/plist/dict/key").get(2).getText();
            String udid = xml.getSelectNodes("/plist/dict/string").get(2).getText();
            response.setStatus(301);
            response.setHeader("Location", "http://203.60.15.94:8500/multi-wechat/apple/getUdid?udid="+udid);
        }*/
    }

    public static void main(String[] args) {
        String content ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\"><plist version=\"1.0\"><dict><key>IMEI</key><string>35 307511 462069 8</string><key>PRODUCT</key><string>iPhone13,3</string><key>UDID</key><string>00008101-000925EE3628001E</string><key>VERSION</key><string>18C66</string></dict></plist>";
/*        XmlUtil xml = new XmlUtil(content);
        String text = xml.getSelectNodes("/plist/dict/key").get(2).getText();
        String udid = xml.getSelectNodes("/plist/dict/string").get(2).getText();*/
//        String key = xml.getAttribute("/plist/dict/@key").toString();
//        System.out.println("key="+text+",value="+udid);
    }
}
