package com.lison.springcloudservice.config;

import com.lison.springcloudservice.config.seata.SeataInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: com.lison.springcloudservice.config-> WebMvcConfig
 * @description:
 * @author: Lison
 * @createDate: 2024-05-27
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SeataInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
