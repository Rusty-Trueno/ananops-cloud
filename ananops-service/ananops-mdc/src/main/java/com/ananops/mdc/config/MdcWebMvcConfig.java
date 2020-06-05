package com.ananops.mdc.config;


import com.ananops.common.config.PcObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class MdcWebMvcConfig implements WebMvcConfigurer {


//    @Autowired
//    private LoginUserHandlerResolver loginUserHandlerResolver;
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
//    {
//        argumentResolvers.add(loginUserHandlerResolver);
//    }

    /**
     * 实体类格式转换
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        PcObjectMapper.buidMvcMessageConverter(converters);
    }
}
