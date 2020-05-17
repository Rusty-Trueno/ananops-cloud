package com.ananops.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class AnanopsDiscoveryApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(AnanopsDiscoveryApp.class, args);
    }
}