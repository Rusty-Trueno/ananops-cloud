package com.ananops;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.ananops.system.annotation.EnableRyFeignClients;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan("com.ananops.*.mapper")
@EnableRyFeignClients
public class AnanopsActApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(AnanopsActApp.class, args);
    }
}
