package com.lison.springbootbuilding.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: com.lison.springcloudservice.fegin-> ITestService
 * @description:
 * @author: 李村 200900681
 * @createDate: 2023-09-23 17:47
 */

@FeignClient("spring-cloud-service")
public interface ITestService {
    //指定调用提供者的哪个方法
    @GetMapping(value = "/getServerProd")
    String getServerPort();
}

