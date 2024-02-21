package com.lison.springcloudservice.config.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: com.lison.springcloudservice.config-> RequestOriginParserDefinition
 * @description:
 * @author: 李村 200900681
 * @createDate: 2024-02-20 10:28
 */
@Component
public class RequestOriginParserDefinition implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String serviceName = request.getParameter("serviceName");
        return serviceName;
    }
}


