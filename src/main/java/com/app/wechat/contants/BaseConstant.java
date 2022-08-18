package com.app.wechat.contants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConstant {
    public static String PROFILE;


    @Value("${profile}")
    public void setPROFILE(String PROFILE) {
        BaseConstant.PROFILE = PROFILE;
    }
}
