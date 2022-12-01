package com.farhan.user.config;

import com.farhan.user.jwtutils.JwtGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Autowired
    private JwtGatewayFilter jwtGatewayFilter;



    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("blogs",r -> r.path("/blogs/**").filters(f->f.filter(jwtGatewayFilter))
                        .uri("lb://BLOG-SERVICE"))
                .route("users",r -> r.path("/users/**").filters(f->f.filter(jwtGatewayFilter))
                        .uri("lb://AUTH-SERVICE"))
                .build();
    }
}
