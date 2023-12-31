package com.yfh.gateway.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NacosConfig {
    
    @RefreshScope
    @Bean(value="blockips.list")
    @ConfigurationProperties(prefix="blockips")
    public List<String> blockIps(){
        return  new ArrayList<String>();
    }
    
}
