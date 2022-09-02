package com.app.wechat.contants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConstant {
    public static String PROFILE;
    public static String CERTIFICATE_URL;

    @Value("${profile}")
    public void setPROFILE(String PROFILE) {
        BaseConstant.PROFILE = PROFILE;
    }

    @Value("${certificate.url}")
    public void setCertificateUrl(String certificateUrl) {
        CERTIFICATE_URL = certificateUrl;
    }
}
