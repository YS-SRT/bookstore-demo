package com.yfh.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.hutool.captcha.generator.RandomGenerator;

@Configuration
public class HuToolConfig {
    
    @Bean
    public RandomGenerator codeGenerator(){
        return new RandomGenerator("0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZ", 4);
    }
}
