package com.app.wechat.controller.admin;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
@Api(tags = "后台接口")
@RestController
@RequestMapping(value = "/apple")
public class AppleBackController {

    @ApiOperation(value = "回调获取UDID(V)", notes = "回调获取UDID")
    @PostMapping(value = "/apple/receive")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        InputStream is = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();
        String buffer;
        while ((buffer = br.readLine()) != null) {
            result.append(buffer);
        }
        if (!StringUtils.isBlank(result)) {
            String content = result.substring(result.indexOf("<?xml"), result.indexOf("</plist>") + 8);
            log.info("apple call back={}", content);
            InputStream inputStream = new ByteArrayInputStream(content.getBytes());
            NSObject nsObject = PropertyListParser.parse(inputStream);
            NSDictionary rootDict = (NSDictionary) nsObject;
            String udid = rootDict.get("UDID").toString();
            log.info("udid={}", udid);
            response.setStatus(301);
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Location", "http://203.60.15.94:8500/getUdid?udid=" + udid);
        }
    }
}
