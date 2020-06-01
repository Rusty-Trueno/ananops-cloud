package com.ananops.pmc.config;

import com.ananops.common.config.PcObjectMapper;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author zhangyue
 * @CreatedTime 2020/5/31 10:32
 **/
public class PmcWebMvcConfig implements WebMvcConfigurer {
    /**
     * 实体类格式转换
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        PcObjectMapper.buidMvcMessageConverter(converters);
    }
}
