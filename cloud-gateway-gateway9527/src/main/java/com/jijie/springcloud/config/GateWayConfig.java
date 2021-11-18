package com.jijie.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: GateWayConfig
 *
 * @author jijie
 * @date 2021/11/18 @time 16:39
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator customRouteLocatorBuilder(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_atguigu",r -> r.path("/payment/get/**").uri("http://localhost:8001")).build();
        return routes.build();
    }
}
