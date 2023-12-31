package com.yfh.register.ddd.adapter.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.yfh.register.config.SecurityConfig;
import com.yfh.register.ddd.infrastructure.mapper.UserInfoMapper;
import com.yfh.register.ddd.infrastructure.mapper.UserPaidInfoMapper;
import com.yfh.register.ddd.infrastructure.model.UserInfoDO;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import cn.hutool.captcha.generator.RandomGenerator;


@WebMvcTest
public class RegisterControllerLayerTest {  //Controller层测试：只mock控制层

    @Autowired
    RegisterController registerController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserInfoMapper userInfoMapper;

    @MockBean
    UserPaidInfoMapper userPaidInfoMapper;

    @MockBean 
    SecurityConfig securityConfig;

    @MockBean
    Authentication authentication;

    @MockBean
    SecurityContext securityContext;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    RandomGenerator RandomGenerator;

    @MockBean
    BeanSearcher beanSearcher;
    
    @Test
    public void testNotNull(){
        Assertions.assertThat(registerController).isNotNull();
    }

    @Test
    @Disabled
    public void testRegister() throws Exception{
        // Mockito.when(securityConfig.configure(Mockito.any(HttpSecurity.class).authorizeRequests().anyRequest().permitAll()));
        // Mockito.when(securityConfig.configure(Mockito.any(AuthenticationManagerBuilder.class)
        //                                       .userDetailsService(Mockito.any(UserDetailsService.class))
        //                                       .passwordEncoder(passwordEncoder)));
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(userInfoMapper.selectCount(Mockito.any(Wrapper.class))).thenReturn(0L);
        Mockito.when(userInfoMapper.insert(Mockito.any(UserInfoDO.class))).thenReturn(1);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/oauth/register")
                                                   .contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}
