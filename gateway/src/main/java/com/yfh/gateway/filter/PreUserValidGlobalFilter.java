package com.yfh.gateway.filter;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class PreUserValidGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private List<String> blockIps;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //Block IP Verified
        InetSocketAddress remoteAddr = exchange.getRequest().getRemoteAddress();
        if(remoteAddr != null && remoteAddr.getAddress()!= null){
            String hostAddress = remoteAddr.getAddress().getHostAddress();
            if(blockIps.stream().anyMatch(ip-> hostAddress.equalsIgnoreCase(ip))){
                return getBlockedResponse(exchange.getResponse(), "Block IP");
            }
        }

        return chain.filter(exchange);
    }


    private Mono<Void> getBlockedResponse(ServerHttpResponse response, String msg){

       response.setStatusCode(HttpStatus.NOT_FOUND);
       response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
       DataBuffer dataBuffer = response.bufferFactory().wrap(msg.getBytes(StandardCharsets.UTF_8));
       return response.writeWith(Mono.just(dataBuffer));
    }
    
}
