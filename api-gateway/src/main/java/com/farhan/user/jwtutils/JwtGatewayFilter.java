package com.farhan.user.jwtutils;

import com.farhan.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class JwtGatewayFilter implements GatewayFilter {

    @Autowired
    private TokenManager jwtUtil;
    @Autowired
    private UserService userService;
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        System.out.println("In filter");
//       ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//
//        String username = null;
//        String token = null;
//        final List<String> apiEndpoints = List.of("/users/register", "/users/login");
//
//        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
//                .noneMatch(uri -> r.getURI().getPath().contains(uri));
//        if (isApiSecured.test(request)) {
//            if(request.getHeaders().containsKey("Authorization")){
////            token = tokenHeader.substring(tokenHeader.lastIndexOf("Bearer ")+1);
//                token = request.getHeaders().getOrEmpty("Authorization").get(0);
//                token = token.replaceAll("Bearer ", "");
//                try{
//                    username = tokenManager.getUsernameFromToken(token);
//                    System.out.println(username);
//                }
//                catch (IllegalArgumentException e) {
//
//                    System.out.println("Unable get Token");
//                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
//
//                    return response.setComplete();
//                }
//                catch (ExpiredJwtException e){
//                    System.out.println("JWT is expired");
//                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
//
//                    return response.setComplete();
//                }
//            }
//            else{
//                System.out.println("Bearer token is missing");
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//
//                return response.setComplete();
//            }
//
//            if(username != null ){
//
//                UserDetails userDetails = userService.loadUserByUsername(username);
//                if(tokenManager.validateJwtToken(token, userDetails)){
//                    exchange.getRequest().mutate().header("User-Agent",username).build();
////                    UsernamePasswordAuthenticationToken authenticationToken =
////                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
////                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) exchange.getRequest()));
////                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//                else {
//                    System.out.println("Not valid");
//                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
//
//                    return response.setComplete();
//                }
//            }
//        }
//
//        return chain.filter(exchange);
//    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();
        final List<String> apiEndpoints = List.of("/users/register", "/users/login");
        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));
        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            String token = request.getHeaders().getOrEmpty("Authorization").get(0);
            token = token.replace("Bearer ", "");
            String username = "";
            try {
                username = jwtUtil.getUsernameFromToken(token);
                jwtUtil.validateJwtToken(token,userService.loadUserByUsername(username,token));
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                return response.setComplete();
            }
            Claims claims = jwtUtil.getClaims(token);
            exchange.getRequest().mutate().header("User-Agent", username).build();
        }
        return chain.filter(exchange);
    }
}
