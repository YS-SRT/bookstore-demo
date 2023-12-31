package com.yfh.register.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yfh.bsecurity.security.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                    .cors()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().authorizeRequests()
                    .antMatchers(HttpMethod.GET,
                        "/",
                        "/swagger-ui/",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/mgnt/**").permitAll()
                    .antMatchers("/oauth/**").permitAll()
                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyRequest().authenticated();

                    //.anyRequest().permitAll();

        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authMgt) throws Exception{
        authMgt.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManager();
    }
    
}

//迁移到springboot2.7， 问题AuthenticationManager如何暴露
// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class SecurityConfig {

//     @Autowired
//     private  UserDetailsService userDetailsService;

//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }

//     @Autowired
//     JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

//     @Bean
//     public SecurityFilterChain configure(HttpSecurity http) throws Exception {
      
//         // Configure AuthenticationManagerBuilder
//         AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//         authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//         // Get AuthenticationManager
//         AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

//         http.csrf().disable()
//                     .cors()
//                     .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                     .and().authorizeRequests()
//                     .antMatchers(HttpMethod.GET,
//                         "/",
//                         "/swagger-ui/",
//                         "/swagger-ui.html",
//                         "/swagger-resources/**",
//                         "/v3/api-docs/**",
//                         "/*.html",
//                         "/favicon.ico",
//                         "/**/*.html",
//                         "/**/*.css",
//                         "/**/*.js",
//                         "/mgnt/**").permitAll()
//                     .antMatchers("/oauth/**").permitAll()
//                     .antMatchers(HttpMethod.OPTIONS).permitAll()
//                     .anyRequest().authenticated();

//                     //.anyRequest().permitAll();
        
//         http.authenticationManager(authenticationManager);
//         http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

//         //http.headers().frameOptions().disable();
//         return http.build();
//     }


// }