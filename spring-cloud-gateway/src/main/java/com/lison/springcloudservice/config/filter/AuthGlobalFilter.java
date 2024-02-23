package com.lison.springcloudservice.config.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @className: com.lison.springcloudservice.config.filter-> AuthGlobalFilter
 * @description:  自定义鉴权全局过滤器  必须实现GlobalFilter和Ordered两个接口，并实现接口的方法
 * @author: Lison
 * @createDate: 2023-10-20
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    //过滤器逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//获取封装 HttpRequest 后的对象
        ServerHttpRequest request = exchange.getRequest();
        //获取封装 HttpResponse 后的对象
        ServerHttpResponse response = exchange.getResponse();
        System.out.println("-------请求到来，经过全局 Filter 处理-------");
        //对请求做处理......
        Mono<Void> filter = chain.filter(exchange);//放行 filter 向后执行：也就意味接下来进入到具体的微服务中(例如 user 微服务)
        //对响应做处理......
        System.out.println("-------响应回来，经过全局 Filter 处理-------");

        //将token放在参数里面传递只是一个演示，正常的业务逻辑当然不是这样做，这里只是为了演示。
        String token = request.getQueryParams().getFirst("token");
        if(!StringUtils.equals("admin",token)){
            System.out.println("认证失败");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//401表示未认证
            return exchange.getResponse().setComplete();
        }

        //chain.filter继续传递向下
        return filter;
    }

    //返回值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}