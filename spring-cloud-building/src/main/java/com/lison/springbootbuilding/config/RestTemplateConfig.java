package com.lison.springbootbuilding.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @className: com.lison.springbootbuilding.config-> RestTemplateConfig
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-09-23 17:07
 */
@Component
public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
