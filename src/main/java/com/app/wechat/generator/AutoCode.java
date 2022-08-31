package com.app.wechat.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;

public class AutoCode extends BaseGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://203.60.15.94:3306/multi-wechat?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true", "root", "Audit123456@")
            .schema("multi-wechat")
            .build();


    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().build());

        GlobalConfig globalConfig = globalConfig().author("admin").outputDir("D://workset//multi-wechat//src/main//java").build();

        PackageConfig packageConfig = packageConfig().parent("com.app.wechat")
                .entity("domain.entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .xml("mapper").build();
        generator.packageInfo(packageConfig);
        generator.global(globalConfig);
        generator.execute();
    }
}
