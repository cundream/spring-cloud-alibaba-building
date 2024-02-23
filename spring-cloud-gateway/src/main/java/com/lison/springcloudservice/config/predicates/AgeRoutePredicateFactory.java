package com.lison.springcloudservice.config.predicates;

/**
 * @className: com.lison.springcloudservice.config.predicates-> AgeRoutePredicateFactory
 * @description:   自定义的断言工厂 1.名称必须是配置+RoutePredicateFactory 2.必须继承AbstractRoutePredicateFactory<配置类>
 * @author: Lison
 * @createDate: 2023-10-20
 */
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    //读取配置文件的参数值，赋值到配置类中的属性上
    @Override
    public List<String> shortcutFieldOrder() {
        //顺序必须与yml文件中的配置顺序对应
        return Arrays.asList("minAge", "maxAge");
    }

    @Override
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //serverWebExchange很强大，可以可以获取到很多内容
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                if (StringUtils.isNotEmpty(ageStr)) {
                    int age = Integer.parseInt(ageStr);
                    return age > config.getMinAge() && age < config.getMaxAge();
                }
                return false;
            }
        };
    }

    //用于接收参数
    public static class Config {
        private int minAge;
        private int maxAge;

        public int getMinAge() {
            return minAge;
        }

        public void setMinAge(int minAge) {
            this.minAge = minAge;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "minAge=" + minAge +
                    ", maxAge=" + maxAge +
                    '}';
        }

        public int getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(int maxAge) {
            this.maxAge = maxAge;
        }
    }
}

