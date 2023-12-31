package com.yfh.bsecurity.config;


import com.yfh.bsecurity.client.DynamicFeignBuilder;
import com.yfh.bsecurity.client.DynamicDubboBuilder;
import com.yfh.bsecurity.controller.BSecurityTokenController;
import com.yfh.bsecurity.security.BSUserDetailsServiceImpl;
import com.yfh.bsecurity.security.JwtAuthenticationTokenFilter;
import com.yfh.bsecurity.token.JwtTokenService;
import com.yfh.bsecurity.service.LoginService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.logging.LoggingSystemProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.apache.dubbo.spring.boot.autoconfigure.DubboConfigurationProperties;

@Configuration
@EnableConfigurationProperties({BSecurityTokenProperties.class})
public class BSecurityAutoConfig {


    @Bean
    public JwtTokenService jwtTokenService(){
        return new JwtTokenService();
    }

    @Bean("userDetailsService")
    public UserDetailsService userDetailsService(){
        return new BSUserDetailsServiceImpl();
    }
    
    @Bean
    public BSecurityTokenController bSecurityTokenController(){
        return new BSecurityTokenController();
    }
    
    @Bean
    @ConditionalOnClass(JwtTokenService.class)
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @ConditionalOnClass(ApplicationContext.class)
    public DynamicFeignBuilder dynamicFeignBuilder(ApplicationContext context){
        return new DynamicFeignBuilder(context);
    }

    @Bean
    @ConditionalOnProperty(prefix= "bsecurity.token", name="mode", havingValue = "proxy")
    @ConditionalOnClass(DubboConfigurationProperties.class)
    public DynamicDubboBuilder dynamicDubboBuilder(DubboConfigurationProperties dubboConfigProperties){
        return new DynamicDubboBuilder(dubboConfigProperties);
    }

    @Bean
    public LoginService loginService(){
        return new LoginService();
    }
}
