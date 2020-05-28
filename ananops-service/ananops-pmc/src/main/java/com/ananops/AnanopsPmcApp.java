package com.ananops;

import com.ananops.pmc.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author zhangyue
 * @CreatedTime 2020/5/27 14:34
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableRyFeignClients
@MapperScan("com.ananops.*.mapper" )
public class AnanopsPmcApp {
    public static void main(String[] args) {
        SpringApplication.run(AnanopsPmcApp.class, args);
    }
}