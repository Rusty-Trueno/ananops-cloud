package com.ananops;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.ananops.system.annotation.EnableRyFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ananops.*.mapper")
@EnableRyFeignClients
public class AnanopsGenApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(AnanopsGenApp.class, args);
    }
}
