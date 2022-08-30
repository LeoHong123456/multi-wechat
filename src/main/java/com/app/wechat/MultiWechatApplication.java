package com.app.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.app.wechat")
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class MultiWechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiWechatApplication.class, args);
	}
}
