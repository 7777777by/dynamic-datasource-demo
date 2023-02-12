package com.by.datasource.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.by.datasource.demo.mapper")
public class DynamicDatasourceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceDemoApplication.class, args);
    }

}
