package com.yfh.bsecurity.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;

public class DynamicFeignBuilder {
    
    private FeignClientBuilder feignClientBuilder;

    public DynamicFeignBuilder(@Autowired ApplicationContext appContext){

        this.feignClientBuilder = new FeignClientBuilder(appContext);
    } 

    public <T> T getFeignClient(Class<T> apiProxy, String serviceName){
        
        return this.feignClientBuilder.forType(apiProxy, serviceName).build();
    }
    
}
