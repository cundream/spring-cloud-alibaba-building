package com.lison.springcloudservice.config.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


/**
 * @className: com.lison.springcloudservice.config.filter-> LogGatewayFilterFactory
 * @description: 自定义的局部过滤器 1. 类名必须是配置+GatewayFilterFactory 2. 必须继承AbstractGatewayFilterFactory
 * @author: Lison
 * @createDate: 2023-10-20
 */
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }

    //过滤器的逻辑
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //获取封装 HttpRequest 后的对象
                ServerHttpRequest request = exchange.getRequest();
                //获取封装 HttpResponse 后的对象
                ServerHttpResponse response = exchange.getResponse();
                System.out.println("-------请求到来，经过局部 Log Filter 处理-------");
                if (config.isCacheLog()) {
                    System.out.println("cacheLog开启了-------");
                }
                if (config.isConsoleLog()) {
                    System.out.println("consoleLog开启了-------");
                }
                Mono<Void> mono = chain.filter(exchange);
                return mono;
            }
        };
    }

    //用于接收参数，需要和配置文件里的参数对应
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog", "cacheLog");
    }

    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;

        public boolean isConsoleLog() {
            return consoleLog;
        }

        public void setConsoleLog(boolean consoleLog) {
            this.consoleLog = consoleLog;
        }

        public boolean isCacheLog() {
            return cacheLog;
        }

        public void setCacheLog(boolean cacheLog) {
            this.cacheLog = cacheLog;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "consoleLog=" + consoleLog +
                    ", cacheLog=" + cacheLog +
                    '}';
        }
    }
}
