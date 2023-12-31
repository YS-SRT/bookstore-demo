package com.yfh.bookstore.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
//@FeignClient(value="${feign.biz.registerclient}")
public interface UserRegisterFeignClient {
    
}
