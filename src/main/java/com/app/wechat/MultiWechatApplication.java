package com.app.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.app.wechat")
public class MultiWechatApplication {

	public static void main(String[] args) {

		SpringApplication.run(MultiWechatApplication.class, args);
	}

}
