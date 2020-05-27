package com.ananops;

import com.ananops.system.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Bridge Wang
 * @version 1.0
 * @date 2020-05-27 9:33
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableRyFeignClients
@MapperScan("com.ananops.*.mapper" )
public class AnanopsBmcApp {
    public static void main(String[] args) {
        SpringApplication.run(AnanopsBmcApp.class, args);
    }
}