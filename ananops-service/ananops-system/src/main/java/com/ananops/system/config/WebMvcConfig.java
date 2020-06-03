package com.ananops.system.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ananops.system.resolver.LoginUserHandlerResolver;

import javax.annotation.Resource;

/**
 * MVC配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Resource
    private LoginUserHandlerResolver loginUserHandlerResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
        argumentResolvers.add(loginUserHandlerResolver);
    }
}